package ecs.entities.MonsterChest;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.PatrouilleWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.damage.Damage;
import ecs.damage.DamageType;
import ecs.entities.Chest;
import ecs.entities.Entity;
import ecs.entities.Charakterklassen.Hero;
import ecs.entities.monsters.BasicMonster;
import ecs.items.ItemData;
import graphic.Animation;
import java.util.List;
import java.util.logging.Logger;
import logging.CustomLogLevel;
import starter.Game;
import tools.Point;

public class ChestMonster extends BasicMonster {
    int damage = 0;
    public static String ani = "character/monster/monsterChest";
    private static final Logger LOGGER = Logger.getLogger(ChestMonster.class.getName());

    public ChestMonster(List<ItemData> items, Point p) {
        super(0.3f, 0.3f, 20, ani, ani, ani, ani);
        new PositionComponent(this, p);
        setupVelocityComponent();
        setupAnimationComponent();
        setupAIComponent();
        setupHitboxComponent();
        setupHealthComponent((int) hp);
        setupInventory(items);
    }

    @Override
    public void setupVelocityComponent() {
        Animation moveRight = AnimationBuilder.buildAnimation(pathToRunRight);
        Animation moveLeft = AnimationBuilder.buildAnimation(pathToRunLeft);
        new VelocityComponent(this, xSpeed, ySpeed, moveLeft, moveRight);
    }

    @Override
    public void setupAnimationComponent() {
        Animation idleRight = AnimationBuilder.buildAnimation(pathToIdleRight);
        Animation idleLeft = AnimationBuilder.buildAnimation(pathToIdleLeft);
        new AnimationComponent(this, idleLeft, idleRight);
    }

    @Override
    public void setupHitboxComponent() {
        new HitboxComponent(
                this,
                (you, other, direction) -> attackSkill(other),
                (you, other, direction) -> LOGGER.info("monsterCollision"));
    }

    @Override
    public void setupHealthComponent(int maxHealthPoints) {
        IOnDeathFunction onDeathFunction =
                entity -> {
                    // Logik für das, was passieren soll, wenn das Monster stirbt
                    System.out.println("Das ChestMonster ist gestorben!");
                };

        // Animationen für das Monster, wenn es Schaden erleidet oder stirbt
        String pathToHitAnimation = "monster/chort/hitAnimation";
        String pathToDieAnimation = "monster/chort/dieAnimation";
        Animation hitAnimation = AnimationBuilder.buildAnimation(pathToHitAnimation);
        Animation dieAnimation = AnimationBuilder.buildAnimation(pathToDieAnimation);

        // Erstelle das HealthComponent für das Monster
        new HealthComponent(this, maxHealthPoints, this, hitAnimation, dieAnimation);
    }

    @Override
    public void setupAIComponent() {
        float radius = 7.0f;
        int numberCheckpoints = 3;
        int pauseTime = 2000;
        PatrouilleWalk.MODE mode = PatrouilleWalk.MODE.LOOP;
        PatrouilleWalk patrouilleWalk =
                new PatrouilleWalk(radius, numberCheckpoints, pauseTime, mode);
        float rushRange = 0.3f;
        CollideAI collideAI = new CollideAI(rushRange);
        float transitionRange = 3.0f;
        RangeTransition rangeTransition = new RangeTransition(transitionRange);
        new AIComponent(this, collideAI, patrouilleWalk, rangeTransition);
    }

    private void attackSkill(Entity entity) {
        LOGGER.info("ChestMonster attack" + entity.getClass().getSimpleName());
        Damage damage = new Damage(this.damage, DamageType.PHYSICAL, this);
        if (entity instanceof Hero) {
            Game.getHero().stream()
                    .flatMap(e -> e.getComponent(HealthComponent.class).stream())
                    .map(HealthComponent.class::cast)
                    .forEach(
                            healthComponent -> {
                                healthComponent.receiveHit(damage);
                            });
        }
    }

    public void setupInventory(List<ItemData> items) {
        new InventoryComponent(this, items.size());
        for (ItemData i : items) {
            InventoryComponent inv =
                    (InventoryComponent) this.getComponent(InventoryComponent.class).get();
            inv.addItem(i);
            LOGGER.log(
                    CustomLogLevel.INFO,
                    "item: "
                            + i.getItemType()
                            + i.getItemName()
                            + "has been added to inventory of"
                            + this.getClass().getName());
        }
    }

    @Override
    public void onDeath(Entity entity) {
        spawnChest(entity);
        LOGGER.log(CustomLogLevel.INFO, this.getClass().getSimpleName() + " has dropped Items");
    }

    /**
     * creates a Chest wich the Player can Loot at the position the Monster dies and with the
     * Inventory of the Monster
     *
     * @param entity
     */
    private void spawnChest(Entity entity) {
        InventoryComponent
                inventoryComponent = // items to spawn Chest with (inventory of the Monster)
                entity.getComponent(InventoryComponent.class)
                                .map(InventoryComponent.class::cast)
                                .orElseThrow(
                                        () ->
                                                createMissingComponentException(
                                                        InventoryComponent.class.getName(),
                                                        entity));
        PositionComponent positionComponent = // pos to spawn Chest(pos Monster died)
                entity.getComponent(PositionComponent.class)
                        .map(PositionComponent.class::cast)
                        .orElseThrow(
                                () ->
                                        createMissingComponentException(
                                                PositionComponent.class.getName(), entity));
        new Chest(
                inventoryComponent.getItems(),
                positionComponent.getPosition()); // creating LootChest
        Game.getEntitiesToRemove().add(this); // deleting Monster
    }

    private static MissingComponentException createMissingComponentException(
            String Component, Entity e) {
        return new MissingComponentException(
                Component
                        + " missing in "
                        + Chest.class.getName()
                        + " in Entity "
                        + e.getClass().getName());
    }
}
