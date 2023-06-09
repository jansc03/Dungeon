package ecs.entities.monsters;

import dslToGame.AnimationBuilder;
import ecs.GeneralGenerator;
import ecs.components.*;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.PatrouilleWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.entities.Chest;
import ecs.entities.Entity;
import ecs.items.ItemData;
import graphic.Animation;
import java.util.List;
import java.util.logging.Logger;
import logging.CustomLogLevel;

public class LittleChort extends BasicMonster {
    private final Logger littleChortLogger = Logger.getLogger(this.getClass().getName());

    public LittleChort(List<ItemData> items) {
        super(
                0.3f,
                0.3f,
                5,
                "monster/imp/idleLeft",
                "monster/imp/idleRight",
                "monster/imp/runLeft",
                "monster/imp/runRight");
        new PositionComponent(this);
        setupInventory(items);
        setupVelocityComponent();
        setupAnimationComponent();
        setupAIComponent();
        setupHitboxComponent();
        setupHealthComponent((int) hp);
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
                this, HitboxComponent.DEFAULT_COLLIDER, HitboxComponent.DEFAULT_COLLIDER);
    }

    @Override
    public void setupHealthComponent(int maxHealthPoints) {
        // Maximale Gesundheitspunkte für das Monster

        // Funktion, die aufgerufen wird, wenn das Monster stirbt
        IOnDeathFunction onDeathFunction =
                entity -> {
                    // Logik für das, was passieren soll, wenn das Monster stirbt
                    System.out.println("Das Monster ist gestorben!");
                };

        // Animationen für das Monster, wenn es Schaden erleidet oder stirbt
        String pathToHitAnimation = "monster/imp/hitAnimation";
        String pathToDieAnimation = "monster/imp/dieAnimation";
        Animation hitAnimation = AnimationBuilder.buildAnimation(pathToHitAnimation);
        Animation dieAnimation = AnimationBuilder.buildAnimation(pathToDieAnimation);

        // Erstelle das HealthComponent für das Monster
        new HealthComponent(this, maxHealthPoints, this, hitAnimation, dieAnimation);
    }

    @Override
    public void setupAIComponent() {
        float radius = 5.0f;
        int numberCheckpoints = 2;
        int pauseTime = 2000;
        PatrouilleWalk.MODE mode = PatrouilleWalk.MODE.RANDOM;
        PatrouilleWalk patrouilleWalk =
                new PatrouilleWalk(radius, numberCheckpoints, pauseTime, mode);
        float rushRange = 0.3f;
        CollideAI collideAI = new CollideAI(rushRange);
        float transitionRange = 2.0f;
        RangeTransition rangeTransition = new RangeTransition(transitionRange);
        new AIComponent(this, collideAI, patrouilleWalk, rangeTransition);
    }

    public void setupInventory(List<ItemData> items) {
        new InventoryComponent(this, 10);
        for (ItemData i : items) {
            InventoryComponent inv =
                    (InventoryComponent) this.getComponent(InventoryComponent.class).get();
            inv.addItem(i);
            littleChortLogger.log(
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
        dropItems(entity);
        littleChortLogger.log(CustomLogLevel.INFO, "Chort has dropped Items");
    }
    /**
     * method to drop Items when entity dies(the default iOnDrop had some issues that we could not
     * figure out)
     *
     * @param entity
     */
    private void dropItems(Entity entity) {
        InventoryComponent inventoryComponent =
                entity.getComponent(InventoryComponent.class)
                        .map(InventoryComponent.class::cast)
                        .orElseThrow(
                                () ->
                                        createMissingComponentException(
                                                InventoryComponent.class.getName(), entity));
        PositionComponent positionComponent =
                entity.getComponent(PositionComponent.class)
                        .map(PositionComponent.class::cast)
                        .orElseThrow(
                                () ->
                                        createMissingComponentException(
                                                PositionComponent.class.getName(), entity));
        List<ItemData> itemData = inventoryComponent.getItems();

        for (ItemData i : itemData) {
            GeneralGenerator.getInstance().dropItems(i, positionComponent.getPosition());
        }
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
