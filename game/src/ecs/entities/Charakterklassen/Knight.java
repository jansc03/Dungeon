package ecs.entities.Charakterklassen;

import ecs.components.HealthComponent;
import ecs.components.PlayableComponent;
import ecs.components.VelocityComponent;
import ecs.components.skill.Heal;
import ecs.components.skill.Rage;
import ecs.components.skill.Skill;
import ecs.components.skill.SkillComponent;
import ecs.components.stats.DamageModifier;
import ecs.components.stats.StatsComponent;
import ecs.damage.DamageType;
import logging.CustomLogLevel;
import starter.Game;

import java.util.logging.Logger;

public class Knight extends Hero {
    private static final Logger LOGGER = Logger.getLogger(Knight.class.getName());
    private final int healCooldown = 5;
    private final int rageCooldown = 10;
    private final float xSpeed = 0.2f;
    private final float ySpeed = 0.2f;
    private float damage = 1f;
    private Skill healSkill;
    private Skill rageSkill;
    public Knight(){
        super(4,50);
        updateVelocityComponent();
        setupModifier();
        LOGGER.info(this.getClass().getSimpleName()+ " created");
    }


    @Override
    public void onLevelUp(long level) {
        if (level == 1) { // unlock Skill
            this.setupHealSkill();
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
                                + 10));
        damage = (float) (damage * 0.1); // increase damage
    }

    /**
     * erstellt Rage Skill
     */
    private void setupRageSkill() {
        rageSkill = new Skill(new Rage(), rageCooldown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(rageSkill));
        LOGGER.log(CustomLogLevel.INFO, "RageSkill setup");
        System.out.println("setup the skill");
    }
    /**
     * erstellt heal Skill
     */
    private void setupHealSkill() {
        healSkill = new Skill(new Heal(), healCooldown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(healSkill));
        LOGGER.log(CustomLogLevel.INFO, "HealSkill setup");
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
            dmgM.setMultiplier(DamageType.PHYSICAL,0.4f);
            dmgM.setMultiplier(DamageType.MAGIC,0.8f);
            dmgM.setMultiplier(DamageType.FIRE,0.9f);
            sCp.setDamageModifier(dmgM);
        }
    }
}

