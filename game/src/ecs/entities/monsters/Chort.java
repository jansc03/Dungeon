package ecs.entities.monsters;


import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.idle.PatrouilleWalk;
import ecs.components.ai.transition.RangeTransition;
import ecs.damage.Damage;
import ecs.damage.DamageType;
import ecs.entities.Entity;
import ecs.entities.Hero;
import graphic.Animation;
import starter.Game;

import java.util.logging.Logger;


public class Chort extends BasicMonster {
    private static final Logger LOGGER = Logger.getLogger(Chort.class.getName());

    public Chort() {
        super(0.3f, 0.3f, 5, "monster/chort/idleLeft", "monster/chort/idleRight", "monster/chort/runLeft", "monster/chort/runRight");
        new PositionComponent(this);
        setupVelocityComponent();
        setupAnimationComponent();
        setupAIComponent();
        setupHitboxComponent();
        setupHealthComponent((int) hp);

    }


    @Override
    public void setupVelocityComponent() {
        Animation moveRight = AnimationBuilder.buildAnimation(pathToRunRight);
        Animation moveLeft = AnimationBuilder.buildAnimation(pathToRunLeft);
        new VelocityComponent(this, xSpeed, ySpeed, moveLeft, moveRight);
    }

    @Override
    public void setupAnimationComponent() {
        Animation idleRight = AnimationBuilder.buildAnimation(pathToIdleRight);
        Animation idleLeft = AnimationBuilder.buildAnimation(pathToIdleLeft);
        new AnimationComponent(this, idleLeft, idleRight);
    }

    @Override
    public void setupHitboxComponent() {
        new HitboxComponent(
            this,
            (you, other, direction) -> attackSkill(other),
            (you, other, direction) -> LOGGER.info("monsterCollision")
        );
    }

    @Override
    public void setupHealthComponent(int maxHealthPoints) {
        IOnDeathFunction onDeathFunction = entity -> {
            // Logik für das, was passieren soll, wenn das Monster stirbt
            System.out.println("Das Monster ist gestorben!");
        };

        // Animationen für das Monster, wenn es Schaden erleidet oder stirbt
        String pathToHitAnimation = "monster/chort/hitAnimation";
        String pathToDieAnimation = "monster/chort/dieAnimation";
        Animation hitAnimation = AnimationBuilder.buildAnimation(pathToHitAnimation);
        Animation dieAnimation = AnimationBuilder.buildAnimation(pathToDieAnimation);

        // Erstelle das HealthComponent für das Monster
        new HealthComponent(this, maxHealthPoints, onDeathFunction, hitAnimation, dieAnimation);
    }

    @Override
    public void setupAIComponent() {
        float radius = 7.0f;
        int numberCheckpoints = 3;
        int pauseTime = 2000;
        PatrouilleWalk.MODE mode = PatrouilleWalk.MODE.LOOP;
        PatrouilleWalk patrouilleWalk = new PatrouilleWalk(radius, numberCheckpoints, pauseTime, mode);
        float rushRange = 0.3f;
        CollideAI collideAI = new CollideAI(rushRange);
        float transitionRange = 3.0f;
        RangeTransition rangeTransition = new RangeTransition(transitionRange);
        new AIComponent(this, collideAI, patrouilleWalk, rangeTransition);
    }

    private void attackSkill(Entity entity) {
        LOGGER.info("Chort attack" + entity.getClass().getSimpleName());
        Damage damage = new Damage(2, DamageType.PHYSICAL, this);
        if (entity instanceof Hero) {
            Game.getHero().stream()
                .flatMap(e -> e.getComponent(HealthComponent.class).stream())
                .map(HealthComponent.class::cast)
                .forEach(healthComponent -> {healthComponent.receiveHit(damage);});
        }
    }
}
