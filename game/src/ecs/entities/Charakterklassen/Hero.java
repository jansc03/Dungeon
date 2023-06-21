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
import starter.Game;

/**
 * The Hero is the player character. It's entity in the ECS. This class helps to setup the hero with
 * all its components and attributes .
 */
public class Hero extends Entity implements ILevelUp {

    private static final Logger LOGGER = Logger.getLogger(Hero.class.getName());

    private final int fireballCoolDown = 1;
    private final int attackCoolDown = 0;
    private final int healCooldown = 5;
    private final int rageCooldown = 10;
    private final float xSpeed = 0.3f;
    private final float ySpeed = 0.3f;
    private float damage = 1;

    private final String pathToIdleLeft = "knight/idleLeft";
    private final String pathToIdleRight = "knight/idleRight";
    private final String pathToRunLeft = "knight/runLeft";
    private final String pathToRunRight = "knight/runRight";
    private Skill firstSkill;
    private Skill secondSkill;
    private Skill boomerangSkill;
    private Skill blueFireBallSkill;
    private Skill healSkill;
    private Skill rageSkill;

    /** Entity with Components */
    public Hero(int sworddamage,int maxHealth) {
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

    private void setupFireballSkill() {
        firstSkill =
            new Skill(
                new FireballSkill(SkillTools::getCursorPositionAsPoint), fireballCoolDown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(firstSkill));
        LOGGER.log(CustomLogLevel.INFO, "FireballSkill setup");
        System.out.println("setup the skill");
    }

    private void setupSwordSkill(int damage) {
        secondSkill =
                new Skill(new SwordSkill(SkillTools::getCursorPositionAsPoint,damage), attackCoolDown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(secondSkill));
        this.getComponent(PlayableComponent.class)
            .ifPresent(p -> ((PlayableComponent) p).setSkillSlot1(secondSkill));
        LOGGER.log(CustomLogLevel.INFO, "SwordSKill setup");
    }

    private void setupBoomerangSkill() {
        boomerangSkill = new Skill(new BoomerangSkill(SkillTools::getCursorPositionAsPoint), 0);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(boomerangSkill));
        LOGGER.log(CustomLogLevel.INFO, "BoomerangSkill setup");
    }

    private void setupBlueFireBallSkill() {
        blueFireBallSkill =
                new Skill(new BlueFiraballSkill(SkillTools::getCursorPositionAsPoint), 0);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(blueFireBallSkill));
        LOGGER.log(CustomLogLevel.INFO, "blueFireballSKill setup");
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
    public void onLevelUp(long level) {
        if (level == 1) { // unlock Skill
            setupHealSkill();
            this.getComponent(PlayableComponent.class)
                    .ifPresent(p -> ((PlayableComponent) p).setSkillSlot3(healSkill));
        }
        if (level == 2) {
            setupRageSkill();
            this.getComponent(PlayableComponent.class)
                    .ifPresent(p -> ((PlayableComponent) p).setSkillSlot4(rageSkill));
        }
        Hero hero = (Hero) Game.getHero().get(); // increase HP
        hero.getComponent(HealthComponent.class)
                .ifPresent(
                        h ->
                                ((HealthComponent) h)
                                        .setMaximalHealthpoints(
                                                ((HealthComponent) h).getMaximalHealthpoints()
                                                        + 5));
        damage = (float) (damage * 0.1); // increase damage
    }

    /** erstellt rage Skill für spätere nutzung */
    private void setupRageSkill() {
        rageSkill = new Skill(new Rage(), rageCooldown);
        this.getComponent(SkillComponent.class)
                .ifPresent(s -> ((SkillComponent) s).addSkill(rageSkill));
        LOGGER.log(CustomLogLevel.INFO, "RageSkill setup");
        System.out.println("setup the skill");
    }
    /** erstellt heal Skill für spätere nutzung */
    private void setupHealSkill() {
        healSkill = new Skill(new Heal(), healCooldown);
        this.getComponent(SkillComponent.class)
                .ifPresent(s -> ((SkillComponent) s).addSkill(healSkill));
        LOGGER.log(CustomLogLevel.INFO, "HealSkill setup");
    }

    public void levelUp() {
        XPComponent xpC = (XPComponent) this.getComponent(XPComponent.class).get();
        xpC.addXP(xpC.getXPToNextLevel());
        LOGGER.log(CustomLogLevel.INFO, "level Up");
    }
}
