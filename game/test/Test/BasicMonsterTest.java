/*package Test;
import ecs.entities.monsters.BasicMonster;
import ecs.entities.monsters.ConcreteMonster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BasicMonsterTest {

    @Mock
    private BasicMonster basicMonster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructor() {
        // Test valid parameters
        BasicMonster monster = new ConcreteMonster(1.0f, 2.0f, 100.0f, "idle_left.png", "idle_right.png", "run_left.png", "run_right.png");
        assertEquals(1.0f, monster.xSpeed, 0.0f);
        assertEquals(2.0f, monster.ySpeed, 0.0f);
        assertEquals(100.0f, monster.hp, 0.0f);
        assertEquals("idle_left.png", monster.pathToIdleLeft);
        assertEquals("idle_right.png", monster.pathToIdleRight);
        assertEquals("run_left.png", monster.pathToRunLeft);
        assertEquals("run_right.png", monster.pathToRunRight);

        // Test invalid parameters
        BasicMonster invalidMonster = new ConcreteMonster(-1.0f, -2.0f, -100.0f, "", "", "", "");
        assertNull(invalidMonster);
    }

    @Test
    public void testAbstractMethods() {
        // Create a concrete implementation of BasicMonster for testing
        BasicMonster monster = new ConcreteMonster(1.0f, 2.0f, 100.0f, "idle_left.png", "idle_right.png", "run_left.png", "run_right.png");

        // Test setupVelocityComponent()
        monster.setupVelocityComponent();
        // Assert specific behavior or state related to setupVelocityComponent()

        // Test setupAnimationComponent()
        monster.setupAnimationComponent();
        // Assert specific behavior or state related to setupAnimationComponent()

        // Test setupAIComponent()
        monster.setupAIComponent();
        // Assert specific behavior or state related to setupAIComponent()

        // Test setupHitboxComponent()
        monster.setupHitboxComponent();
        // Assert specific behavior or state related to setupHitboxComponent()

        // Test setupHealthComponent()
        monster.setupHealthComponent(200);
        // Assert specific behavior or state related to setupHealthComponent()
    }

    @Test
    public void testGettersAndSetters() {
        BasicMonster monster = new ConcreteMonster(1.0f, 2.0f, 100.0f, "idle_left.png", "idle_right.png", "run_left.png", "run_right.png");

        // Test getters
        assertEquals(1.0f, monster.getXSpeed(), 0.0f);
        assertEquals(2.0f, monster.getYSpeed(), 0.0f);
        assertEquals(100.0f, monster.getHp(), 0.0f);
        assertEquals("idle_left.png", monster.getPathToIdleLeft());
        assertEquals("idle_right.png", monster.getPathToIdleRight());
        assertEquals("run_left.png", monster.getPathToRunLeft());
        assertEquals("run_right.png", monster.getPathToRunRight());

        // Test setters
        monster.setXSpeed(2.0f);
        assertEquals(2.0f, monster.getXSpeed(), 0.0f);

        monster.setYSpeed(3.0f);
        assertEquals(3.0f, monster.getYSpeed(), 0.0f);

        monster.setHp(200.0f);
        assertEquals(200.0f, monster.getHp(), 0.0f);

        monster.setPathToIdleLeft("new_idle_left.png");
        assertEquals("new_idle_left.png", monster.getPathToIdleLeft());

        monster.setPathToIdleRight("new_idle_right.png");
        assertEquals("new_idle_right.png", monster.getPathToIdleRight());

        monster.setPathToRunLeft("new_run_left.png");
        assertEquals("new_run_left.png", monster.getPathToRunLeft());

        monster.setPathToRunRight("new_run_right.png");
        assertEquals("new_run_right.png", monster.getPathToRunRight());
    }

    @Test
    public void testOnDeathFunction() {
        // Mocking the behavior of the IOnDeathFunction
        when(basicMonster.onDeath()).thenReturn(true);

        // Test the behavior of onDeath() method
        boolean result = basicMonster.onDeath();
        assertTrue(result);

        // Verify that the onDeath() method was called
        verify(basicMonster, times(1)).onDeath();
    }
}
*/
