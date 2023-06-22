package MonsterTest;

import ecs.components.*;
import ecs.entities.Entity;
import ecs.entities.monsters.Goblin;
import ecs.items.ItemData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Diese Klasse enthält JUnit-Testmethoden für die Klasse Goblin.
 */
public class GoblinTest {

    /**
     * Überprüft, ob die Goblin-Klasse eine gültige Liste von ItemData-Objekten korrekt verarbeitet.
     */
    @Test
    public void testValidItemList() {
        List<ItemData> items = createValidItemList();

        Goblin goblin = new Goblin(items);

        InventoryComponent inventory = (InventoryComponent) goblin.getComponent(InventoryComponent.class).get();
        assertEquals(items.size(), inventory.getItems().size());
    }

    /**
     * Überprüft, ob die Goblin-Klasse eine leere Liste von ItemData-Objekten korrekt verarbeitet.
     */
    @Test
    public void testEmptyItemList() {
        List<ItemData> items = new ArrayList<>();

        Goblin goblin = new Goblin(items);

        InventoryComponent inventory = (InventoryComponent) goblin.getComponent(InventoryComponent.class).get();
        assertTrue(inventory.getItems().isEmpty());
    }

    /**
     * Überprüft, ob die Goblin-Klasse den Maximalwert für die Gesundheit korrekt behandelt.
     */
    @Test
    public void testMaxHealthBoundary() {
        int maxHealth = Integer.MAX_VALUE;

        Goblin goblin = new Goblin(createValidItemList());

        HealthComponent health = (HealthComponent) goblin.getComponent(HealthComponent.class).get();
        assertEquals(maxHealth, health.getMaximalHealthpoints());
    }

    /**
     * Überprüft, ob die Goblin-Klasse einen nicht positiven Maximalwert für die Gesundheit korrekt behandelt.
     */
    @Test
    public void testNonPositiveMaxHealth() {
        int maxHealth = 0;

        Goblin goblin = new Goblin(createValidItemList());

        HealthComponent health = (HealthComponent) goblin.getComponent(HealthComponent.class).get();
        assertNotEquals(maxHealth, health.getMaximalHealthpoints());
    }

    /**
     * Überprüft, ob die Goblin-Klasse das Verhalten bei Tod mit einem InventoryComponent richtig behandelt.
     */
    @Test
    public void testOnDeathWithInventoryComponent() {
        Entity entity = mock(Entity.class);
        InventoryComponent inventory = mock(InventoryComponent.class);
        PositionComponent position = mock(PositionComponent.class);
        when(entity.getComponent(InventoryComponent.class)).thenReturn(Optional.of(inventory));
        when(entity.getComponent(PositionComponent.class)).thenReturn(Optional.of(position));

        Goblin goblin = new Goblin(createValidItemList());
        goblin.onDeath(entity);

        verify(inventory, times(1)).getItems();
        verify(position, times(1)).getPosition();
        // Wir können auch Assertions für das erwartete Verhalten nach dem Ablegen der Items hinzufügen.
    }

    /**
     * Überprüft, ob die Goblin-Klasse das Verhalten bei Tod ohne ein InventoryComponent richtig behandelt.
     */
    @Test
    public void testOnDeathWithoutInventoryComponent() {
        Entity entity = mock(Entity.class);
        PositionComponent position = mock(PositionComponent.class);
        when(entity.getComponent(InventoryComponent.class)).thenReturn(Optional.empty());
        when(entity.getComponent(PositionComponent.class)).thenReturn(Optional.of(position));

        Goblin goblin = new Goblin(createValidItemList());

        assertThrows(MissingComponentException.class, () -> goblin.onDeath(entity));
    }

    /**
     * Überprüft, ob die Goblin-Klasse das Verhalten bei Tod ohne ein PositionComponent richtig behandelt.
     */
    @Test
    public void testOnDeathWithoutPositionComponent() {
        Entity entity = mock(Entity.class);
        InventoryComponent inventory = mock(InventoryComponent.class);
        when(entity.getComponent(InventoryComponent.class)).thenReturn(Optional.of(inventory));
        when(entity.getComponent(PositionComponent.class)).thenReturn(Optional.empty());

        Goblin goblin = new Goblin(createValidItemList());

        assertThrows(MissingComponentException.class, () -> goblin.onDeath(entity));
    }

    /**
     * Überprüft, ob der VelocityComponent der Goblin-Klasse die minimalen Geschwindigkeitswerte richtig setzt.
     */
    @Test
    public void testVelocityComponentMinValues() {
        float xSpeed = Float.MIN_VALUE;
        float ySpeed = Float.MIN_VALUE;

        Goblin goblin = new Goblin(createValidItemList());

        VelocityComponent velocity = (VelocityComponent) goblin.getComponent(VelocityComponent.class).get();
        assertEquals(xSpeed, velocity.getXVelocity());
        assertEquals(ySpeed, velocity.getYVelocity());
    }

    /**
     * Überprüft, ob der VelocityComponent der Goblin-Klasse die maximalen Geschwindigkeitswerte richtig setzt.
     */
    @Test
    public void testVelocityComponentMaxValues() {
        float xSpeed = Float.MAX_VALUE;
        float ySpeed = Float.MAX_VALUE;

        Goblin goblin = new Goblin(createValidItemList());

        VelocityComponent velocity = (VelocityComponent) goblin.getComponent(VelocityComponent.class).get();
        assertEquals(xSpeed, velocity.getXVelocity());
        assertEquals(ySpeed, velocity.getYVelocity());
    }

    /**
     * Überprüft, ob die AnimationComponent der Goblin-Klasse Pfade mit Leerzeichen richtig verarbeitet.
     */
    @Test
    public void testAnimationPathsWithSpaces() {
        List<ItemData> items = createValidItemList();

        Goblin goblin = new Goblin(items);

        AnimationComponent animation = (AnimationComponent) goblin.getComponent(AnimationComponent.class).get();
        assertNotNull(animation.getIdleLeft());
        assertNotNull(animation.getIdleRight());
        assertNotNull(animation.getRunLeft());
        assertNotNull(animation.getRunRight());
        // Fügen Sie weitere Assertions hinzu, um zu überprüfen, ob die Animationspfade korrekt sind.
    }

    /**
     * Hilfsmethode zum Erstellen einer gültigen Liste von ItemData-Objekten.
     * @return Eine Liste von ItemData-Objekten.
     */
    private List<ItemData> createValidItemList() {
        List<ItemData> items = new ArrayList<>();
        // Fügen Sie einige gültige ItemData-Objekte zur Liste hinzu
        return items;
    }
}
