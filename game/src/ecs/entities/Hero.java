package ecs.entities;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.AnimationComponent;
import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import ecs.components.skill.*;
import graphic.Animation;

import java.util.logging.Logger;

/**
 * The Hero is the player character. It's entity in the ECS. This class helps to setup the hero with
 * all its components and attributes .
 */
public class Hero extends Entity {


    private static final Logger LOGGER = Logger.getLogger(Hero.class.getName());

    private final int fireballCoolDown = 1;
    private final int attackCoolDown = 0;
    private final float xSpeed = 0.3f;
    private final float ySpeed = 0.3f;

    private final String pathToIdleLeft = "knight/idleLeft";
    private final String pathToIdleRight = "knight/idleRight";
    private final String pathToRunLeft = "knight/runLeft";
    private final String pathToRunRight = "knight/runRight";
    private Skill firstSkill;
    private Skill secondSkill;
    private Skill boomerangSkill;

    private Skill blueFireBallSkill;


    /**
     * Entity with Components
     */
    public Hero() {
        super();
        new PositionComponent(this);
        setupVelocityComponent();
        setupAnimationComponent();
        setupHitboxComponent();
        PlayableComponent pc = new PlayableComponent(this);
        setupFireballSkill();
        setupSwordSkill();
        setupBoomerangSkill();
        setupBlueFireBallSkill();
        pc.setSkillSlot1(boomerangSkill);
        pc.setSkillSlot2(blueFireBallSkill);
        setupHealthComponent(5);
        setInventoryComponent();
    }

    private void setInventoryComponent() {
        new InventoryComponent(this,20);
    }

    private void setupVelocityComponent() {
        Animation moveRight = AnimationBuilder.buildAnimation(pathToRunRight);
        Animation moveLeft = AnimationBuilder.buildAnimation(pathToRunLeft);
        new VelocityComponent(this, xSpeed, ySpeed, moveLeft, moveRight);
    }

    private void setupAnimationComponent() {
        Animation idleRight = AnimationBuilder.buildAnimation(pathToIdleRight);
        Animation idleLeft = AnimationBuilder.buildAnimation(pathToIdleLeft);
        new AnimationComponent(this, idleLeft, idleRight);
    }

    private void setupFireballSkill() {
        firstSkill =
            new Skill(
                new FireballSkill(SkillTools::getCursorPositionAsPoint), fireballCoolDown);
    }

    private void setupSwordSkill() {
        secondSkill =
            new Skill(
                new SwordSkill(SkillTools::getCursorPositionAsPoint), attackCoolDown);
    }


    private void setupBoomerangSkill() {
        boomerangSkill =
            new Skill(
                new BoomerangSkill(SkillTools::getCursorPositionAsPoint), 0);
    }

    private void setupBlueFireBallSkill() {
        blueFireBallSkill =
            new Skill(
                new BlueFiraballSkill(SkillTools::getCursorPositionAsPoint), 0);
    }

    private void setupHitboxComponent() {
        new HitboxComponent(
            this,
            (you, other, direction) -> LOGGER.info("heroCollisionEnter"),
            (you, other, direction) -> LOGGER.info("heroCollisionEnter"));
    }

    public void setupHealthComponent(int maxHealthPoints) {
        IOnDeathFunction onDeathFunction = entity -> {
            // Logik für das, was passieren soll, wenn das Monster stirbt
            LOGGER.info("Der Held ist gestorben");
        };

        // Animationen für das Monster, wenn es Schaden erleidet oder stirbt
        String pathToHitAnimation = "character/knight/hit";
        String pathToDieAnimation = "character/knight/dieAnimation";
        Animation hitAnimation = AnimationBuilder.buildAnimation(pathToHitAnimation);
        Animation dieAnimation = AnimationBuilder.buildAnimation(pathToDieAnimation);

        // Erstelle das HealthComponent für das Monster
        new HealthComponent(this, maxHealthPoints, onDeathFunction, hitAnimation, dieAnimation);
    }
}
