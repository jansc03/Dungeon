/*package FallenTest;

import ecs.GeneralGenerator;
import ecs.components.*;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.WanderingWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.entities.Entity;
import ecs.entities.monsters.Goblin;
import ecs.items.ItemData;
import graphic.Animation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoblinTest {

    @Test
    public void testInitialization() {
        // Arrange
        Goblin goblin = new Goblin();

        // Assert
        assertNotNull(goblin);

        // Verify the setup of components
        assertNotNull(goblin.getVelocityComponent());
        assertNotNull(goblin.getAnimationComponent());
        assertNotNull(goblin.getHitboxComponent());
        assertNotNull(goblin.getHealthComponent());
        assertNotNull(goblin.getAIComponent());
        assertNotNull(goblin.getInventoryComponent());

        // Verify the creation of the logger
        assertNotNull(goblin.goblinLogger);
        assertEquals("ecs.entities.monsters.Goblin", goblin.goblinLogger.getName());
    }

    @Test
    public void testVelocityComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        VelocityComponent velocityComponent = goblin.getVelocityComponent();

        // Assert
        assertNotNull(velocityComponent);

        // Verify the initialization of velocity properties
        assertEquals(0.2f, velocityComponent.getXSpeed());
        assertEquals(0.2f, velocityComponent.getYSpeed());

        // Verify the setup of animation for movement in the right direction
        Animation moveRightAnimation = velocityComponent.getMoveRightAnimation();
        assertNotNull(moveRightAnimation);
        assertEquals("monster/goblin/runRight", moveRightAnimation.getPath());
        // Add additional assertions for other properties of moveRightAnimation

        // Verify the setup of animation for movement in the left direction
        Animation moveLeftAnimation = velocityComponent.getMoveLeftAnimation();
        assertNotNull(moveLeftAnimation);
        assertEquals("monster/goblin/runLeft", moveLeftAnimation.getPath());
        // Add additional assertions for other properties of moveLeftAnimation
    }


    @Test
    public void testAnimationComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        AnimationComponent animationComponent = goblin.getAnimationComponent();

        // Assert
        assertNotNull(animationComponent);

        // Verify the setup of animation for idle in the right direction
        Animation idleRightAnimation = animationComponent.getIdleRightAnimation();
        assertNotNull(idleRightAnimation);
        assertEquals("monster/goblin/idleRight", idleRightAnimation.getPath());
        // Add additional assertions for other properties of idleRightAnimation

        // Verify the setup of animation for idle in the left direction
        Animation idleLeftAnimation = animationComponent.getIdleLeftAnimation();
        assertNotNull(idleLeftAnimation);
        assertEquals("monster/goblin/idleLeft", idleLeftAnimation.getPath());
        // Add additional assertions for other properties of idleLeftAnimation
    }

    @Test
    public void testHitboxComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        HitboxComponent hitboxComponent = goblin.getHitboxComponent();

        // Assert
        assertNotNull(hitboxComponent);

        // Verify the collider values
        assertEquals(HitboxComponent.DEFAULT_COLLIDER, hitboxComponent.getCollider());
        assertEquals(HitboxComponent.DEFAULT_COLLIDER, hitboxComponent.getTriggerCollider());
        // Add additional assertions for other properties of the HitboxComponent if applicable
    }

    @Test
    public void testHealthComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        HealthComponent healthComponent = goblin.getHealthComponent();

        // Assert
        assertNotNull(healthComponent);

        // Verify the maximum health points
        assertEquals(7, healthComponent.getMaxHealthPoints());

        // Verify the setup of hit and die animations
        Animation hitAnimation = healthComponent.getHitAnimation();
        assertNotNull(hitAnimation);
        assertEquals("monster/goblin/hitAnimation", hitAnimation.getPath());
        // Add additional assertions for other properties of the hitAnimation

        Animation dieAnimation = healthComponent.getDieAnimation();
        assertNotNull(dieAnimation);
        assertEquals("monster/goblin/idleLeft", dieAnimation.getPath());
        // Add additional assertions for other properties of the dieAnimation

        // Verify the onDeath action
        // Simulate the onDeath event by calling the onDeath method explicitly
        goblin.onDeath();
        // Assert or verify the desired actions or behavior that should happen on death
        // For example, check if items were dropped or if a specific message was logged
    }

    @Test
    public void testAIComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        AIComponent aiComponent = goblin.getAIComponent();

        // Assert
        assertNotNull(aiComponent);

        // Verify the setup of wandering behavior
        WanderingWalk wanderingWalk = aiComponent.getWanderingWalk();
        assertNotNull(wanderingWalk);
        assertEquals(5.0f, wanderingWalk.getWanderRadius());
        assertEquals(2, wanderingWalk.getWanderDuration());
        assertEquals(2000, wanderingWalk.getWanderInterval());
        // Add additional assertions for other properties of the wanderingWalk

        // Verify the setup of collision-based AI behavior
        CollideAI collideAI = aiComponent.getCollideAI();
        assertNotNull(collideAI);
        assertEquals(0.3f, collideAI.getRushRange());
        // Add additional assertions for other properties of the collideAI

        // Verify the setup of range-based transition behavior
        RangeTransition rangeTransition = aiComponent.getRangeTransition();
        assertNotNull(rangeTransition);
        assertEquals(2.0f, rangeTransition.getTransitionRange());
        // Add additional assertions for other properties of the rangeTransition
    }


    @Test
    public void testInventoryComponent() {
        // Arrange
        Goblin goblin = new Goblin();
        InventoryComponent inventoryComponent = goblin.getInventoryComponent();

        // Assert
        assertNotNull(inventoryComponent);
        assertEquals(10, inventoryComponent.getMaxCapacity());

        // Verify adding items to the inventory
        List<ItemData> items = createMockItems(); // Create a list of mock ItemData objects
        goblin.setupInventory(items);
        assertEquals(items.size(), inventoryComponent.getItemCount());
        for (ItemData item : items) {
            assertTrue(inventoryComponent.hasItem(item));
        }
    }

    @Test
    public void testItemDrop() {
        // Arrange
        Goblin goblin = new Goblin();
        Entity mockEntity = mock(Entity.class);
        PositionComponent positionComponent = new PositionComponent(mockEntity);
        when(mockEntity.getComponent(PositionComponent.class)).thenReturn(Optional.of(positionComponent));

        // Simulate items in the inventory
        List<ItemData> items = createMockItems(); // Create a list of mock ItemData objects
        InventoryComponent inventoryComponent = new InventoryComponent(goblin, 10);
        inventoryComponent.setItems(items);

        // Act
        goblin.dropItems(mockEntity);

        // Assert or Verify
        verify(mockEntity, times(items.size())).getComponent(PositionComponent.class);
        for (ItemData item : items) {
            verify(GeneralGenerator.getInstance(), times(1)).dropItems(eq(item), any(Position.class));
        }
    }

    private List<ItemData> createMockItems() {
        List<ItemData> items = new ArrayList<>();

        // Erstelle und füge Mock-ItemData-Objekte zur Liste hinzu
        ItemData item1 = mock(ItemData.class);
        when(item1.getItemType()).thenReturn("Weapon");
        when(item1.getItemName()).thenReturn("Sword");
        items.add(item1);

        ItemData item2 = mock(ItemData.class);
        when(item2.getItemType()).thenReturn("Armor");
        when(item2.getItemName()).thenReturn("Shield");
        items.add(item2);

        // Weitere Mock-Objekte für ItemData erstellen und hinzufügen

        return items;
    }

    @Test
    public void testMissingComponentException() {
        // Arrange
        Goblin goblin = new Goblin();
        Entity mockEntity = mock(Entity.class);

        // Test missing PositionComponent
        when(mockEntity.getComponent(PositionComponent.class)).thenReturn(Optional.empty());
        assertThrows(MissingComponentException.class, () -> {
            goblin.dropItems(mockEntity);
        });

        // Test missing InventoryComponent
        when(mockEntity.getComponent(PositionComponent.class)).thenReturn(Optional.of(new PositionComponent(mockEntity)));
        when(mockEntity.getComponent(InventoryComponent.class)).thenReturn(Optional.empty());
        assertThrows(MissingComponentException.class, () -> {
            goblin.dropItems(mockEntity);
        });

        // Test missing PositionComponent and InventoryComponent
        when(mockEntity.getComponent(PositionComponent.class)).thenReturn(Optional.empty());
        when(mockEntity.getComponent(InventoryComponent.class)).thenReturn(Optional.empty());
        assertThrows(MissingComponentException.class, () -> {
            goblin.dropItems(mockEntity);
        });

        // Verify that the appropriate methods were called
        verify(mockEntity, times(3)).getComponent(PositionComponent.class);
        verify(mockEntity, times(3)).getComponent(InventoryComponent.class);
    }
}
*/
