package FallenTest;

import ecs.entities.Teleportpads;
import ecs.entities.Teleportsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeleportsystemTest {

    @Mock
    private Teleportpads mockPad1;

    @Mock
    private Teleportpads mockPad2;

    private Teleportsystem teleportsystem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        teleportsystem = new Teleportsystem();
        teleportsystem.pad1 = mockPad1;
        teleportsystem.pad2 = mockPad2;
    }

    /**
     * Testet das Verhalten, wenn ein Teleportpad verwendet wird, während es benutzbar ist und der Tic-Counter größer als der Schwellenwert ist.
     */
    @Test
    public void testUsedPad_TeleportingWhenUsableAndTicCounterGreaterThanThreshold() {
        teleportsystem.usable = true;
        teleportsystem.ticcounter = 5 * 30 + 1;

        teleportsystem.usedPad(mockPad1);

    }

    /**
     * Testet das Verhalten, wenn ein Teleportpad nicht verwendet wird, wenn es nicht benutzbar ist oder der Tic-Counter kleiner oder gleich dem Schwellenwert ist.
     */
    @Test
    public void testUsedPad_NotTeleportingWhenNotUsableOrTicCounterLessThanOrEqualThreshold() {
        teleportsystem.usable = false;
        teleportsystem.ticcounter = 5 * 30;

        teleportsystem.usedPad(mockPad1);

    }

    /**
     * Testet das Überprüfen der Nutzungen der Teleportpads und das Entfernen der Pads, wenn keine Nutzungen mehr übrig sind.
     */
    @Test
    public void testCheckUsages_RemovePadsWhenNoUsagesLeft() {
        when(mockPad1.getUsages()).thenReturn(0);
        when(mockPad2.getUsages()).thenReturn(2);

        teleportsystem.checkUsages();

    }

    /**
     * Testet das Überprüfen der Nutzungen der Teleportpads und das Beibehalten der Pads, wenn noch Nutzungen übrig sind.
     */
    @Test
    public void testCheckUsages_KeepPadsWhenUsagesLeft() {
        when(mockPad1.getUsages()).thenReturn(2);
        when(mockPad2.getUsages()).thenReturn(2);

        teleportsystem.checkUsages();

    }

    /**
     * Testet das Erstellen neuer Teleportpads und das Hinzufügen zur Entity-Liste.
     */
    @Test
    public void testMakePads_CreateNewPadsAndAddToEntityList() {
        teleportsystem.makePads();

    }

    /**
     * Testet das Setzen des benutzbar-Flags auf "true".
     */
    @Test
    public void testSetUsable_SetUsableToTrue() {
        teleportsystem.setUsable();

        assertTrue(teleportsystem.usable);
    }

    /**
     * Testet das Aktualisieren des Teleportsystems und ruft die Aniusable-Methode auf, wenn der Tic-Counter größer als der Schwellenwert ist.
     */
    @Test
    public void testUpdateTeleportSystem_CallAniusableMethodWhenTicCounterGreaterThanThreshold() {
        teleportsystem.usable = true;
        teleportsystem.ticcounter = 5 * 30 + 2;

        teleportsystem.updateTeleportSystem();

    }

    /**
     * Testet das Aktualisieren des Teleportsystems und ruft die Aniusable-Methode nicht auf, wenn der Tic-Counter kleiner oder gleich dem Schwellenwert ist.
     */
    @Test
    public void testUpdateTeleportSystem_DoNotCallAniusableMethodWhenTicCounterLessThanOrEqualThreshold() {
        teleportsystem.usable = true;
        teleportsystem.ticcounter = 5 * 30 + 1;

        teleportsystem.updateTeleportSystem();

    }
}
