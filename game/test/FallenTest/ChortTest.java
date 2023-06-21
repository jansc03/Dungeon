/*package FallenTest;

import ecs.components.VelocityComponent;
import ecs.entities.monsters.Chort;
import ecs.items.ItemData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class ChortTest {

    @Test
    void chortConstructor_InitializesVariablesAndCallsSetupMethods() {
        // Arrange
        List<ItemData> items = Collections.emptyList();

        // Act
        Chort chort = Mockito.spy(new Chort(items));

        // Assert
        assertEquals(0.3f, chort.xSpeed);
        assertEquals(0.3f, chort.ySpeed);
        assertEquals(5, chort.hp);
        assertEquals("monster/chort/idleLeft", chort.pathToIdleLeft);
        assertEquals("monster/chort/idleRight", chort.pathToIdleRight);
        assertEquals("monster/chort/runLeft", chort.pathToRunLeft);
        assertEquals("monster/chort/runRight", chort.pathToRunRight);

        verify(chort).setupVelocityComponent();
        verify(chort).setupAnimationComponent();
        verify(chort).setupAIComponent();
        verify(chort).setupHitboxComponent();
        verify(chort).setupHealthComponent(5);
        verify(chort).setupInventory(items);
    }

    @Test
    void setupVelocityComponent_CreatesVelocityComponentWithCorrectValues() {
        // Arrange
        Chort chort = new Chort(Collections.emptyList());

        // Act
        chort.setupVelocityComponent();

        // Assert
        VelocityComponent velocityComponent = chort.getComponent(VelocityComponent.class).orElse(null);
        assertNotNull(velocityComponent);
        assertEquals(0.3f, velocityComponent.getXSpeed());
        assertEquals(0.3f, velocityComponent.getYSpeed());
        assertNotNull(velocityComponent.getMoveLeftAnimation());
        assertNotNull(velocityComponent.getMoveRightAnimation());
    }
}

*/
