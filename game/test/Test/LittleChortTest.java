/*package Test;

import dslToGame.AnimationBuilder;
import ecs.components.HealthComponent;
import ecs.components.HitboxComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.PatrouilleWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.entities.Entity;
import ecs.entities.monsters.LittleChort;
import ecs.items.ItemData;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class LittleChortTest {

    @Test
    public void testConstructorWithValidParameters() {
        // Arrange
        List<ItemData> items = new ArrayList<>();

        // Act
        LittleChort littleChort = new LittleChort(items);

        // Assert
        Assert.assertNotNull(littleChort);
        // Add additional assertions for parameter values if necessary
    }

    @Test
    public void testSetupVelocityComponent() {
        // Arrange
        AnimationBuilder animationBuilderMock = Mockito.mock(AnimationBuilder.class);
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        littleChort.setAnimationBuilder(animationBuilderMock);

        // Act
        littleChort.setupVelocityComponent();

        // Assert
        // Add assertions to verify the correct animations are set in the VelocityComponent
    }

    @Test
    public void testSetupAnimationComponent() {
        // Arrange
        LittleChort littleChort = new LittleChort(new ArrayList<>());

        // Act
        littleChort.setupAnimationComponent();

        // Assert
        // Add assertions to verify the correct animations are set in the AnimationComponent
    }

    @Test
    public void testSetupHitboxComponent() {
        // Arrange
        HitboxComponent hitboxComponentMock = Mockito.mock(HitboxComponent.class);
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        littleChort.setHitboxComponent(hitboxComponentMock);

        // Act
        littleChort.setupHitboxComponent();

        // Assert
        // Add assertions to verify the correct callbacks are set in the HitboxComponent
    }

    @Test
    public void testSetupHealthComponent() {
        // Arrange
        HealthComponent healthComponentMock = Mockito.mock(HealthComponent.class);
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        littleChort.setHealthComponent(healthComponentMock);

        // Act
        littleChort.setupHealthComponent(10);

        // Assert
        // Add assertions to verify the correct parameters are set in the HealthComponent
    }

    @Test
    public void testSetupAIComponent() {
        // Arrange
        CollideAI collideAIMock = Mockito.mock(CollideAI.class);
        PatrouilleWalk patrouilleWalkMock = Mockito.mock(PatrouilleWalk.class);
        RangeTransition rangeTransitionMock = Mockito.mock(RangeTransition.class);
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        littleChort.setCollideAI(collideAIMock);
        littleChort.setPatrouilleWalk(patrouilleWalkMock);
        littleChort.setRangeTransition(rangeTransitionMock);

        // Act
        littleChort.setupAIComponent();

        // Assert
        // Add assertions to verify the correct AI components are set in the AIComponent
    }

    @Test
    public void testSetupInventory() {
        // Arrange
        List<ItemData> items = new ArrayList<>();
        items.add(new ItemData("Item 1"));
        items.add(new ItemData("Item 2"));
        LittleChort littleChort = new LittleChort(new ArrayList<>());

        // Act
        littleChort.setupInventory(items);

        // Assert
        // Add assertions to verify the items are correctly added to the inventory
    }

    @Test
    public void testOnDeath() {
        // Arrange
        Entity entityMock = Mockito.mock(Entity.class);
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        littleChort.setEntity(entityMock);

        // Act
        littleChort.onDeath(entityMock);

        // Assert
        // Add assertions to verify the items are correctly dropped
    }
}
*/
