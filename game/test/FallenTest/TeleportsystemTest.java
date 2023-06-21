/*package FallenTest;

import ecs.components.PositionComponent;
import ecs.entities.Entity;
import ecs.entities.Hero;
import ecs.entities.Teleportpads;
import ecs.entities.Teleportsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import starter.Game;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeleportsystemTest {

    private Teleportpads mockPad1;
    private Teleportpads mockPad2;
    private Teleportsystem teleportsystem;
    private Game mockGame;

    @BeforeEach
    public void setup() {
        mockPad1 = mock(Teleportpads.class);
        mockPad2 = mock(Teleportpads.class);
        teleportsystem = new Teleportsystem();
        teleportsystem.pad1 = mockPad1;
        teleportsystem.pad2 = mockPad2;
        mockGame = mock(Game.class);
    }

    @Test
    public void testUsedPad() {
        PositionComponent mockPositionComponent = mock(PositionComponent.class);
        Hero mockHero = mock(Hero.class);
        when(mockGame.getHero().get()).thenReturn(mockHero);
        when(mockHero.getComponent(PositionComponent.class)).thenReturn(Optional.of(mockPositionComponent));

        teleportsystem.usedPad(mockPad1);

        verify(mockPositionComponent).setPosition(any());
        verify(mockPad1).Aniused(any(Entity.class));
        verify(mockPad1).reduceUsages();
        assertFalse(teleportsystem.usable);
    }

    @Test
    public void testCheckUsages_NoUsageLeft() {
        when(mockPad1.getUsages()).thenReturn(0);
        when(mockPad2.getUsages()).thenReturn(1);

        teleportsystem.checkUsages();

        verify(mockGame, times(1)).removeEntity(mockPad1);
        verify(mockGame, never()).removeEntity(mockPad2);
    }


    @Test
    public void testCheckUsages_UsageLeft() {
        when(mockPad1.getUsages()).thenReturn(2);
        when(mockPad2.getUsages()).thenReturn(1);

        teleportsystem.checkUsages();

        verify(mockGame, never()).removeEntity(mockPad1);
        verify(mockGame, never()).removeEntity(mockPad2);
    }

    @Test
    public void testMakePads() {
        teleportsystem.makePads();

        verify(mockGame).addEntity(mockPad1);
        verify(mockGame).addEntity(mockPad2);
    }

    @Test
    public void testUpdateTeleportSystem_Usable() {
        teleportsystem.usable = true;
        teleportsystem.ticcounter = 5 * 30;

        teleportsystem.updateTeleportSystem();

        verify(mockPad1).Aniusable(mockPad1);
        verify(mockPad2).Aniusable(mockPad2);
    }

    @Test
    public void testUpdateTeleportSystem_NotUsable() {
        teleportsystem.usable = false;
        teleportsystem.ticcounter = 5 * 30;

        teleportsystem.updateTeleportSystem();

        verify(mockPad1, never()).Aniusable(mockPad1);
        verify(mockPad2, never()).Aniusable(mockPad2);
    }

    // Weitere Testfälle für andere Methoden der Klasse Teleportsystem

}
*/
