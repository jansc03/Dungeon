package ecs.entities.Charakterklassen;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.AnimationComponent;
import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import ecs.components.skill.*;
import ecs.components.stats.StatsComponent;
import ecs.components.xp.ILevelUp;
import ecs.components.xp.XPComponent;
import ecs.entities.Entity;
import graphic.Animation;
import java.util.logging.Logger;
import logging.CustomLogLevel;

/**
 * The Hero is the player character. It's entity in the ECS. This class helps to setup the hero with
 * all its components and attributes .
 */
public class Hero extends Entity implements ILevelUp {

    private static final Logger LOGGER = Logger.getLogger(Hero.class.getName());

    private final int attackCoolDown = 0;
    private final float xSpeed = 0.3f;
    private final float ySpeed = 0.3f;
    private float damage = 1;

    private final String pathToIdleLeft = "knight/idleLeft";
    private final String pathToIdleRight = "knight/idleRight";
    private final String pathToRunLeft = "knight/runLeft";
    private final String pathToRunRight = "knight/runRight";
    private Skill swordSkill;

    /** Entity with Components */
    public Hero(int sworddamage, int maxHealth) {
        super();
        new PositionComponent(this);
        new XPComponent(this, this);
        new SkillComponent(this);
        new StatsComponent(this);
        setupVelocityComponent();
        setupAnimationComponent();
        setupHitboxComponent();
        PlayableComponent pc = new PlayableComponent(this);
        setupSwordSkill(sworddamage);
        setupHealthComponent(maxHealth);
        setInventoryComponent();
    }

    private void setInventoryComponent() {
        new InventoryComponent(this, 20);
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

    private void setupSwordSkill(int damage) {
        swordSkill =
                new Skill(
                        new SwordSkill(SkillTools::getCursorPositionAsPoint, damage),
                        attackCoolDown);
        this.getComponent(SkillComponent.class)
                .ifPresent(s -> ((SkillComponent) s).addSkill(swordSkill));
        this.getComponent(PlayableComponent.class)
                .ifPresent(p -> ((PlayableComponent) p).setSkillSlot1(swordSkill));
        LOGGER.log(CustomLogLevel.INFO, "SwordSKill setup");
    }

    private void setupHitboxComponent() {
        new HitboxComponent(
                this,
                (you, other, direction) -> LOGGER.info("heroCollisionEnter"),
                (you, other, direction) -> LOGGER.info("heroCollisionEnter"));
    }

    public void setupHealthComponent(int maxHealthPoints) {
        IOnDeathFunction onDeathFunction =
                entity -> {
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

    public float getDamage() {
        return (int) damage;
    }

    public void setDamage(float d) {
        damage = d;
    }

    @Override
    public void onLevelUp(long level) {}

    public void levelUp() {
        XPComponent xpC = (XPComponent) this.getComponent(XPComponent.class).get();
        xpC.addXP(xpC.getXPToNextLevel());
        LOGGER.log(CustomLogLevel.INFO, "level Up");
    }
}
