package Test;

import ecs.components.AnimationComponent;
import ecs.components.PositionComponent;
import ecs.entities.Entity;
import ecs.entities.Teleportpads;
import ecs.entities.Teleportsystem;
import level.elements.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testklasse für die Klasse "Teleportpads".
 */
public class TeleportpadsTest {

    private Teleportsystem mockTeleportsystem;

    @BeforeEach
    public void setup() {
        mockTeleportsystem = mock(Teleportsystem.class);
    }

    /**
     * Testet den Standardwert der "Usages" in der Klasse "Teleportpads".
     */
    @Test
    public void testDefaultUsages() {
        Teleportpads pads = new Teleportpads(10000, mockTeleportsystem);
        assertEquals(10000, pads.getUsages());
    }

    /**
     * Testet die "Usages" in der Klasse "Teleportpads" mit einem benutzerdefinierten Wert.
     */
    @Test
    public void testCustomUsages() {
        Teleportpads pads = new Teleportpads(500, mockTeleportsystem);
        assertEquals(500, pads.getUsages());
    }

    /**
     * Testet das Vorhandensein einer PositionComponent in der Klasse "Teleportpads".
     */
    @Test
    public void testPositionComponent() {
        Teleportpads pads = new Teleportpads(10000, mockTeleportsystem);
        assertNotNull(pads.getComponent(PositionComponent.class));
    }

    /**
     * Testet die Animation beim Verlassen einer Kollision.
     */
    @Test
    public void testCollisionLeaveAnimation() {
        Teleportpads pads = new Teleportpads(10000, mockTeleportsystem);
        Entity mockEntity = mock(Entity.class);
        AnimationComponent mockAnimationComponent = mock(AnimationComponent.class);
        when(mockEntity.getComponent(AnimationComponent.class)).thenReturn(Optional.ofNullable(mockAnimationComponent));

        pads.onCollisionleave(pads, mockEntity, Tile.Direction.W); //W = UP

        verify(mockAnimationComponent).setCurrentAnimation(any());
    }
}
