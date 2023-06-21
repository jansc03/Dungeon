package ecs.entities.Charakterklassen;

import ecs.components.HealthComponent;
import ecs.components.PlayableComponent;
import ecs.components.VelocityComponent;
import ecs.components.skill.BoomerangSkill;
import ecs.components.skill.Skill;
import ecs.components.skill.SkillComponent;
import ecs.components.skill.SkillTools;
import ecs.components.stats.DamageModifier;
import ecs.components.stats.StatsComponent;
import ecs.damage.DamageType;
import logging.CustomLogLevel;
import starter.Game;

import java.util.logging.Logger;

public class Archer extends Hero {
    private static final Logger LOGGER = Logger.getLogger(Archer.class.getName());
    private final int boomerrangCoolDown = 1;
    private final float xSpeed = 0.4f;
    private final float ySpeed = 0.4f;
    private float damage = 1.5f;
    private Skill boomerangSkill;

    public Archer(){
        super(3,30);
        updateVelocityComponent();
        setupModifier();
        LOGGER.info(this.getClass().getSimpleName()+ " created");
    }
    @Override
    public void onLevelUp(long level) {
        if (level == 1) { // unlock Skill
            setupBoomerangSkill();
            this.getComponent(PlayableComponent.class)
                .ifPresent(p -> ((PlayableComponent) p).setSkillSlot3(boomerangSkill));
        }
        Hero hero = (Hero) Game.getHero().get(); // increase HP
        hero.getComponent(HealthComponent.class)
            .ifPresent(
                h ->
                    ((HealthComponent) h)
                        .setMaximalHealthpoints(
                            ((HealthComponent) h).getMaximalHealthpoints()
                                + 4));
        damage = (float) (damage * 0.1); // increase damage
    }
    private void setupBoomerangSkill() {
        boomerangSkill = new Skill(new BoomerangSkill(SkillTools::getCursorPositionAsPoint), boomerrangCoolDown);
        this.getComponent(SkillComponent.class)
            .ifPresent(s -> ((SkillComponent) s).addSkill(boomerangSkill));
        LOGGER.log(CustomLogLevel.INFO, "BoomerangSkill setup");
    }
    private void updateVelocityComponent(){
        if(this.getComponent(VelocityComponent.class).isPresent()){
            VelocityComponent vCp =
                (VelocityComponent) this.getComponent(VelocityComponent.class).get();
            vCp.setXVelocity(xSpeed);
            vCp.setYVelocity(ySpeed);
        }
    }
    private void setupModifier() {
        if(this.getComponent(StatsComponent.class).isPresent()) {
            StatsComponent sCp =
                (StatsComponent) this.getComponent(StatsComponent.class).get();
            DamageModifier dmgM=new DamageModifier();
            dmgM.setMultiplier(DamageType.PHYSICAL,0.8f);
            dmgM.setMultiplier(DamageType.MAGIC,1f);
            dmgM.setMultiplier(DamageType.FIRE,1.5f);
            sCp.setDamageModifier(dmgM);
        }
    }
}
