package ecs.components.skill;

import ecs.components.HealthComponent;
import ecs.components.VelocityComponent;
import ecs.entities.Entity;
import ecs.entities.Hero;
import logging.CustomLogLevel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Rage extends MagicSkill{
    private static final Logger LOGGER = Logger.getLogger(Rage.class.getName());
    int skillDuration = 10;//   skill Duration in seconds
    @Override
    public void execute(Entity entity) {
        if(entity instanceof Hero){
            Hero hero = (Hero) entity;

            if(entity.getComponent(HealthComponent.class).isPresent()){
                HealthComponent hCp = (HealthComponent) entity.getComponent(HealthComponent.class).get();
                int hpCost = (int) (hCp.getMaximalHealthpoints() * 0.3);
                int newHP = hCp.getCurrentHealthpoints()-hpCost;
                hCp.setCurrentHealthpoints(newHP);
                LOGGER.log(CustomLogLevel.INFO,this.getClass().getSimpleName()+"Hero Hp decreased");
            }

            if(entity.getComponent(VelocityComponent.class).isPresent()){
                VelocityComponent vCp = (VelocityComponent) entity.getComponent(VelocityComponent.class).get();
                float ogX = vCp.getXVelocity();
                float ogY = vCp.getYVelocity();
                vCp.setXVelocity(vCp.getXVelocity()*1.25f);
                vCp.setYVelocity(vCp.getYVelocity()*1.25f);
                int ogDamage = hero.getDamage();
                hero.setDamge(ogDamage*2);
                durationTimer(vCp,ogX,ogY, hero, ogDamage);
                LOGGER.log(CustomLogLevel.INFO,this.getClass().getSimpleName()+"Hero Damage and Velocity Increased");
            }

        }
    }

    /**
     * Resets the heros Stats after Skill Durationh
     * @param velocityComponent
     * @param ogX   Speed before Increase
     * @param ogY   Speed before Increase
     * @param hero
     * @param ogDamage  Damage before Increase
     */

    public void durationTimer(VelocityComponent velocityComponent, float ogX, float ogY, Hero hero, int ogDamage){
        LOGGER.log(CustomLogLevel.INFO,this.getClass().getSimpleName()+" Effect Timer started");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                hero.setDamge(ogDamage);
                velocityComponent.setXVelocity(ogX);
                velocityComponent.setYVelocity(ogY);
            }
        }, (long) skillDuration*1000);
        LOGGER.log(CustomLogLevel.INFO,this.getClass().getSimpleName()+" Effect Timer ended");
    }

}
