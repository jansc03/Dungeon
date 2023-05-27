package ecs.entities.MonsterChest;

import ecs.components.AnimationComponent;
import ecs.components.InteractionComponent;
import ecs.components.InventoryComponent;
import ecs.components.PositionComponent;
import ecs.entities.Entity;
import ecs.items.ItemData;
import graphic.Animation;
import starter.Game;

import java.util.List;

public class MonsterChest extends Entity {
    public static final float defaultInteractionRadius = 1f;
    public static final List<String> DEFAULT_CLOSED_ANIMATION_FRAMES =
        List.of("objects/treasurechest/chest_full_open_anim_f0.png");
    public static final List<String> DEFAULT_OPENING_ANIMATION_FRAMES =
        List.of(
            "objects/treasurechest/chest_full_open_anim_f0.png",
            "objects/treasurechest/chest_full_open_anim_f1.png",
            "objects/treasurechest/chest_full_open_anim_f2.png",
            "objects/treasurechest/chest_empty_open_anim_f2.png");

    public MonsterChest(List<ItemData> itemData) {
        new PositionComponent(this);
        InventoryComponent ic = new InventoryComponent(this, itemData.size());
        itemData.forEach(ic::addItem);
        new InteractionComponent(this, defaultInteractionRadius, false, this::spawnMonster);
        AnimationComponent ac =
            new AnimationComponent(
                this,
                new Animation(DEFAULT_CLOSED_ANIMATION_FRAMES, 100, false),
                new Animation(DEFAULT_OPENING_ANIMATION_FRAMES, 100, false));
    }
    public void spawnMonster(Entity e){
        e.getComponent(PositionComponent.class).ifPresent(c -> {
               e.getComponent(InventoryComponent.class).ifPresent(i ->{
                   new ChestMonster(((InventoryComponent)i).getItems(),((PositionComponent)c).getPosition());
               });
            });
        Game.getEntitiesToRemove().add(this);
    }
}
