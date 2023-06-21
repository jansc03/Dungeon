/*package MonsterTest;

import dslToGame.AnimationBuilder;
import ecs.components.AnimationComponent;
import ecs.components.HealthComponent;
import ecs.components.HitboxComponent;
import ecs.components.ai.AIComponent;
import ecs.entities.monsters.BasicMonster;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BasicMonsterTest {

    @Test
    public void testConstructorWithValidParameters() {
        // Arrange
        float xSpeed = 1.5f;
        float ySpeed = 2.0f;
        float hp = 100.0f;
        String pathToIdleLeft = "idle_left.png";
        String pathToIdleRight = "idle_right.png";
        String pathToRunLeft = "run_left.png";
        String pathToRunRight = "run_right.png";

        // Act
        BasicMonster monster = new BasicMonster(xSpeed, ySpeed, hp, pathToIdleLeft, pathToIdleRight, pathToRunLeft, pathToRunRight);

        // Assert
        Assert.assertEquals(xSpeed, monster.xSpeed, 0.0f);
        Assert.assertEquals(ySpeed, monster.ySpeed, 0.0f);
        Assert.assertEquals(hp, monster.hp, 0.0f);
        Assert.assertEquals(pathToIdleLeft, monster.pathToIdleLeft);
        Assert.assertEquals(pathToIdleRight, monster.pathToIdleRight);
        Assert.assertEquals(pathToRunLeft, monster.pathToRunLeft);
        Assert.assertEquals(pathToRunRight, monster.pathToRunRight);
    }

    @Test
    public void testSetupVelocityComponent() {
        // Arrange
        BasicMonster monster = Mockito.mock(BasicMonster.class);
        AnimationBuilder animationBuilder = Mockito.mock(AnimationBuilder.class);
        Mockito.when(monster.getAnimationBuilder()).thenReturn(animationBuilder);

        // Act
        monster.setupVelocityComponent();

        // Assert
        Mockito.verify(animationBuilder).setIdleAnimation(monster.pathToIdleLeft);
        Mockito.verify(animationBuilder).setRunAnimation(monster.pathToRunLeft);
    }

    @Test
    public void testSetupAnimationComponent() {
        // Arrange
        BasicMonster monster = new BasicMonster(/|* constructor parameters *|/);
        AnimationComponent animationComponent = Mockito.mock(AnimationComponent.class);
        monster.setAnimationComponent(animationComponent);
        String expectedIdleAnimation = "expected_idle_animation";
        String expectedRunAnimation = "expected_run_animation";
        Mockito.when(animationComponent.getIdleAnimation()).thenReturn(expectedIdleAnimation);
        Mockito.when(animationComponent.getRunAnimation()).thenReturn(expectedRunAnimation);

        // Act
        monster.setupAnimationComponent();

        // Assert
        Mockito.verify(animationComponent).setIdleAnimation(expectedIdleAnimation);
        Mockito.verify(animationComponent).setRunAnimation(expectedRunAnimation);
    }

    @Test
    public void testSetupHitboxComponent() {
        // Arrange
        BasicMonster monster = new BasicMonster(/|* constructor parameters *|/);
        HitboxComponent hitboxComponent = Mockito.mock(HitboxComponent.class);
        monster.setHitboxComponent(hitboxComponent);
        /|* setup expected callbacks *|/

        // Act
        monster.setupHitboxComponent();

        // Assert
        Mockito.verify(hitboxComponent).setCallbacks(/|* expected callbacks *|/);
    }

    @Test
    public void testSetupHealthComponent() {
        // Arrange
        BasicMonster monster = new BasicMonster(/|* constructor parameters *|/);
        HealthComponent healthComponent = Mockito.mock(HealthComponent.class);
        monster.setHealthComponent(healthComponent);
        int maxHealthPoints = 200;

        // Act
        monster.setupHealthComponent(maxHealthPoints);

        // Assert
        Mockito.verify(healthComponent).setMaxHealthPoints(maxHealthPoints);
    }

    @Test
    public void testSetupAIComponent() {
        // Arrange
        BasicMonster monster = new BasicMonster(/|* constructor parameters *|/);
        AIComponent aiComponent = Mockito.mock(AIComponent.class);
        monster.setAIComponent(aiComponent);
        /|* setup expected AI components *|/

        // Act
        monster.setupAIComponent();

        // Assert
        Mockito.verify(aiComponent).setAIComponents(/|* expected AI components *|/);
    }

    @Test
    public void testSetupHealthComponentWithMaxHealthPointsBoundaryValues() {
        // Arrange
        BasicMonster monster = new BasicMonster(/|* constructor parameters *|/);
        HealthComponent healthComponent = Mockito.mock(HealthComponent.class);
        monster.setHealthComponent(healthComponent);
        int maxHealthPoints = 0;

        // Act
        monster.setupHealthComponent(maxHealthPoints);

        // Assert
        Mockito.verify(healthComponent).setMaxHealthPoints(maxHealthPoints);
    }
}*/

