package FallenTest;

import ecs.entities.Traps;
import ecs.components.PositionComponent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testklasse für die Klasse "Traps".
 */
public class TrapsTest {

    private Traps traps;

    @Before
    public void setUp() {
        traps = new Traps();
    }

    /**
     * Testet den Standardwert der "Usages" in der Klasse "Traps".
     */
    @Test
    public void testDefaultUsages() {
        assertEquals(10000, traps.getUsages());
    }

    /**
     * Testet die "Usages" in der Klasse "Traps" mit einem gültigen Wert.
     */
    @Test
    public void testValidUsages() {
        int expectedUsages = 500;
        traps.setUsages(expectedUsages);
        assertEquals(expectedUsages, traps.getUsages());
    }

    /**
     * Testet die "Usages" in der Klasse "Traps" mit einem ungültigen Wert.
     * Erwartet eine IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUsages() {
        int invalidUsages = -100;
        traps.setUsages(invalidUsages);
    }

    /**
     * Testet den minimalen Wert der "Usages" in der Klasse "Traps".
     */
    @Test
    public void testMinUsagesValue() {
        traps.setUsages(0);
        assertEquals(0, traps.getUsages());
    }

    /**
     * Testet den maximalen Wert der "Usages" in der Klasse "Traps".
     */
    @Test
    public void testMaxUsagesValue() {
        int maxUsages = Integer.MAX_VALUE;
        traps.setUsages(maxUsages);
        assertEquals(maxUsages, traps.getUsages());
    }

    /**
     * Testet einen Wert für "Usages", der größer als der maximal mögliche Wert ist.
     * Erwartet eine IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxUsagesValuePlusOne() {
        int maxUsagesPlusOne = Integer.MAX_VALUE + 1;
        traps.setUsages(maxUsagesPlusOne);
    }

    /**
     * Testet das Vorhandensein einer PositionComponent in der Klasse "Traps".
     */
    @Test
    public void testPositionComponent() {
        Traps traps = new Traps();
        assertNotNull(traps.getComponent(PositionComponent.class));
    }
}
