/*package FallenTest;

import ecs.components.AnimationComponent;
import ecs.components.VelocityComponent;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.PatrouilleWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.entities.monsters.LittleChort;
import ecs.items.ItemData;
import graphic.Animation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

public class LittleChortTest {
    private LittleChort littleChort;
    private List<ItemData> items;

    @BeforeEach
    public void setup() {
        items = new ArrayList<>();
        items.add(new ItemData("Sword", "Iron Sword"));
        items.add(new ItemData("Potion", "Health Potion"));
        littleChort = new LittleChort(items);
    }

    @Test
    public void testConstructor_WithValidItemList_ShouldInitializeMonster() {
        assertEquals(0.3f, littleChort.getXSpeed());
        assertEquals(0.3f, littleChort.getYSpeed());
        assertEquals(5, littleChort.getHP());
        // Additional assertions for other properties of the monster
    }

    @Test
    public void testConstructor_WithEmptyItemList_ShouldInitializeMonster() {
        LittleChort littleChort = new LittleChort(new ArrayList<>());
        assertEquals(0.3f, littleChort.getXSpeed());
        assertEquals(0.3f, littleChort.getYSpeed());
        assertEquals(5, littleChort.getHP());
        // Additional assertions for other properties of the monster
    }

    @Test
    public void testSetupVelocityComponent_ShouldCreateVelocityComponentWithCorrectAnimations() throws Exception {
        AnimationComponent animationComponent = mock(AnimationComponent.class);
        when(animationComponent.getAnimation("monster/imp/runLeft")).thenReturn(mock(Animation.class));
        when(animationComponent.getAnimation("monster/imp/runRight")).thenReturn(mock(Animation.class));
        VelocityComponent velocityComponent = mock(VelocityComponent.class);
        whenNew(VelocityComponent.class).withAnyArguments().thenReturn(velocityComponent);

        littleChort.setupVelocityComponent();

        verifyNew(VelocityComponent.class).withArguments(
            eq(littleChort),
            eq(0.3f),
            eq(0.3f),
            any(Animation.class),
            any(Animation.class)
        );
    }

    @Test
    public void testSetupAnimationComponent_ShouldCreateAnimationComponentWithCorrectAnimations() throws Exception {
        AnimationComponent animationComponent = mock(AnimationComponent.class);
        when(animationComponent.getAnimation("monster/imp/idleLeft")).thenReturn(mock(Animation.class));
        when(animationComponent.getAnimation("monster/imp/idleRight")).thenReturn(mock(Animation.class));
        AnimationComponent mockedAnimationComponent = mock(AnimationComponent.class);
        whenNew(AnimationComponent.class).withAnyArguments().thenReturn(mockedAnimationComponent);

        littleChort.setupAnimationComponent();

        verifyNew(AnimationComponent.class).withArguments(
            eq(littleChort),
            any(Animation.class),
            any(Animation.class)
        );
    }

    @Test
    public void testSetupAIComponent_ShouldCreateAIComponentWithCorrectAIComponents() throws Exception {
        AIComponent aiComponent = mock(AIComponent.class);
        PatrouilleWalk patrouilleWalk = mock(PatrouilleWalk.class);
        CollideAI collideAI = mock(CollideAI.class);
        RangeTransition rangeTransition = mock(RangeTransition.class);
        whenNew(AIComponent.class).withAnyArguments().thenReturn(aiComponent);

        littleChort.setupAIComponent();

        verifyNew(AIComponent.class).withArguments(
            eq(littleChort),
            eq(collideAI),
            eq(patrouilleWalk),
            eq(rangeTransition)
        );
    }

    // Additional test methods for other setup methods

    // You can also write tests for other methods in the LittleChort class

}
*/
