package ecs.entities.Charakterklassen;

import ecs.components.HealthComponent;
import ecs.components.PlayableComponent;
import ecs.components.VelocityComponent;
import ecs.components.skill.*;
import ecs.components.stats.DamageModifier;
import ecs.components.stats.StatsComponent;
import ecs.damage.DamageType;
import logging.CustomLogLevel;
import starter.Game;

import java.util.logging.Logger;

public class Mage extends Hero {
    private static final Logger LOGGER = Logger.getLogger(Mage.class.getName());
    private final int blueFireballCoolDown = 0;
    private final int fireballCoolDown = 1;
    private final float xSpeed = 0.3f;
    private final float ySpeed = 0.3f;
    private float damage = 2f;

    private Skill fireballSkill;
    private Skill blueFireBallSkill;

    public Mage(){
        super(1,20);
        updateVelocityComponent();
        setupModifier();
        LOGGER.info(this.getClass().getSimpleName()+ " created");
    }
    @Override
    public void onLevelUp(long level) {
        if (level == 1) { // unlock Skill
            setupFireballSkill();
            this.getComponent(PlayableComponent.class)
                .ifPresent(p -> ((PlayableComponent) p).setSkillSlot3(fireballSkill));
        }
        if (level == 2) {
            setupBlueFireBallSkill();
            this.getComponent(PlayableComponent.class)
                .ifPresent(p -> ((PlayableComponent) p).setSkillSlot4(blueFireBallSkill));
        }
        Hero hero = (Hero) Game.getHero().get(); // increase HP
        hero.getComponent(HealthComponent.class)
            .ifPresent(
                h ->
                    ((HealthComponent) h)
                        .setMaximalHealthpoints(
                            ((HealthComponent) h).getMaximalHealthpoints()
                                + 3));
        damage = (float) (damage * 0.1); // increase damage
    }

    /**
     * Sets up BlueFireball skill on LevelUp for the Mage to use
     */
    private void setupBlueFireBallSkill() {
        blueFireBallSkill =
            new Skill(new BlueFiraballSkill(SkillTools::getCursorPositionAsPoint), blueFireballCoolDown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(blueFireBallSkill));
        LOGGER.log(CustomLogLevel.INFO, "blueFireballSKill setup");
    }
    /**
     * Sets up Fireball skill on LevelUp for the Mage to use
     */
    private void setupFireballSkill() {
        fireballSkill =
            new Skill(
                new FireballSkill(SkillTools::getCursorPositionAsPoint), fireballCoolDown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(fireballSkill));
        LOGGER.log(CustomLogLevel.INFO, "FireballSkill setup");
        System.out.println("setup the skill");
    }

    /**
     * changes the velocity to the claas specific
     */
    private void updateVelocityComponent(){
        if(this.getComponent(VelocityComponent.class).isPresent()){
            VelocityComponent vCp =
                (VelocityComponent) this.getComponent(VelocityComponent.class).get();
            vCp.setXVelocity(xSpeed);
            vCp.setYVelocity(ySpeed);
        }
    }

    /**
     * creates Stats component
     * and adds damage Modifier
     */
    private void setupModifier() {
        if(this.getComponent(StatsComponent.class).isPresent()) {
            StatsComponent sCp =
                (StatsComponent) this.getComponent(StatsComponent.class).get();
            DamageModifier dmgM=new DamageModifier();
            dmgM.setMultiplier(DamageType.PHYSICAL,1.5f);
            dmgM.setMultiplier(DamageType.MAGIC,1f);
            dmgM.setMultiplier(DamageType.FIRE,0.9f);
            sCp.setDamageModifier(dmgM);
        }
    }
}
