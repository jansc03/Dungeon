package ecs.entities.MonsterChest;

import ecs.components.AnimationComponent;
import ecs.components.InteractionComponent;
import ecs.components.InventoryComponent;
import ecs.components.PositionComponent;
import ecs.entities.Entity;
import ecs.items.ItemData;
import graphic.Animation;
import java.util.List;
import java.util.logging.Logger;
import logging.CustomLogLevel;
import starter.Game;

public class MonsterChest extends Entity {
    public static final float defaultInteractionRadius = 1f;
    private static final Logger LOGGER = Logger.getLogger(MonsterChest.class.getName());
    public static final List<String> DEFAULT_CLOSED_ANIMATION_FRAMES =
            List.of("Object/treasurechest/chest_full_open_anim_f0.png");
    public static final List<String> DEFAULT_OPENING_ANIMATION_FRAMES =
            List.of(
                    "Object/treasurechest/chest_full_open_anim_f0.png",
                    "Object/treasurechest/chest_full_open_anim_f1.png",
                    "Object/treasurechest/chest_full_open_anim_f2.png",
                    "Object/treasurechest/chest_empty_open_anim_f2.png");

    /**
     * creates a MOnsterChest that spawnes a Monster on Interaction
     *
     * @param itemData Inventory of the Chest
     */
    public MonsterChest(List<ItemData> itemData) {
        new PositionComponent(this);
        InventoryComponent ic = new InventoryComponent(this, itemData.size());
        itemData.forEach(ic::addItem);
        new InteractionComponent(this, defaultInteractionRadius, false, this::spawnMonster);
        AnimationComponent ac =
                new AnimationComponent(
                        this,
                        new Animation(DEFAULT_CLOSED_ANIMATION_FRAMES, 10, false),
                        new Animation(DEFAULT_OPENING_ANIMATION_FRAMES, 5, false));
        LOGGER.log(CustomLogLevel.INFO, this.getClass().getSimpleName() + " was created");
    }

    /**
     * called on death creates ChestMonster at the Position of the Chest and with the Inventory
     *
     * @param e
     */
    public void spawnMonster(Entity e) {
        e.getComponent(PositionComponent.class)
                .ifPresent(
                        c -> {
                            e.getComponent(InventoryComponent.class)
                                    .ifPresent(
                                            i -> {
                                                ChestMonster chestMonster =
                                                        new ChestMonster(
                                                                ((InventoryComponent) i).getItems(),
                                                                ((PositionComponent) c)
                                                                        .getPosition());
                                                LOGGER.log(
                                                        CustomLogLevel.INFO,
                                                        this.getClass().getSimpleName()
                                                                + " has Spawned :"
                                                                + chestMonster
                                                                        .getClass()
                                                                        .getSimpleName());
                                            });
                        });
        Game.getEntitiesToRemove().add(this);
        LOGGER.log(CustomLogLevel.INFO, this.getClass().getSimpleName() + " has died");
    }
}
