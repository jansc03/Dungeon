/*package FallenTest;

import ecs.components.AnimationComponent;
import ecs.entities.Entity;
import ecs.entities.Teleportpads;
import ecs.entities.Teleportsystem;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

public class TeleportpadsTest {

    @Test
    public void testDefaultUsages() {
        Teleportsystem mockTeleportSystem = mock(Teleportsystem.class);
        Teleportpads teleportpads = new Teleportpads(3, mockTeleportSystem);
        assertEquals(3, teleportpads.getUsages());
    }

    @Test
    public void testAnimationComponentSetup() throws Exception {
        AnimationComponent mockAnimationComponent = mock(AnimationComponent.class);
        whenNew(AnimationComponent.class).withAnyArguments().thenReturn(mockAnimationComponent);

        Teleportpads teleportpads = new Teleportpads(3, mock(Teleportsystem.class));

        verifyNew(AnimationComponent.class).withArguments(eq(teleportpads), any(), any());
    }

    @Test
    public void testAniused() {
        Teleportsystem mockTeleportSystem = mock(Teleportsystem.class);
        Teleportpads teleportpads = new Teleportpads(3, mockTeleportSystem);

        Entity mockEntity = mock(Entity.class);
        AnimationComponent mockAnimationComponent = mock(AnimationComponent.class);
        when(mockEntity.getComponent(AnimationComponent.class)).thenReturn(Optional.of(mockAnimationComponent));

        teleportpads.Aniused(mockEntity);

        verify(mockTeleportSystem).usedPad(teleportpads);
        verify(mockAnimationComponent).setCurrentAnimation(any());
    }

    // Weitere Testfälle für andere Methoden der Klasse Teleportpads

}
*/
