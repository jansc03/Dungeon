package ecs.components.skill;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.collision.ICollide;
import ecs.damage.Damage;
import ecs.entities.Entity;
import graphic.Animation;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import starter.Game;
import tools.Point;

/**
 * Die abstrakte Klasse FallsdownProjectileSkill implementiert das ISkillFunction-Interface und
 * stellt eine Basisimplementierung für einen Projektil-Skill bereit, bei dem das Projektil nach
 * einer bestimmten Reichweite fällt.
 */
public abstract class FallsdownProjectileSkill implements ISkillFunction {

    private static final Logger LOGGER = Logger.getLogger(FallsdownProjectileSkill.class.getName());

    private String pathToTexturesOfProjectile;
    private float projectileSpeed;
    private float projectileRange;
    private Damage projectileDamage;
    private Point projectileHitboxSize;
    private ITargetSelection selectionFunction;
    private float knockbackDistance;

    /**
     * Konstruktor für die FallsdownProjectileSkill-Klasse.
     *
     * @param pathToTexturesOfProjectile Der Pfad zu den Texturdateien des Projektils.
     * @param projectileSpeed Die Geschwindigkeit des Projektils.
     * @param projectileDamage Der Schaden, den das Projektil verursacht.
     * @param projectileHitboxSize Die Größe der Trefferbox des Projektils.
     * @param selectionFunction Die Funktion zur Auswahl des Zielpunkts.
     * @param projectileRange Die Reichweite des Projektils.
     * @param knockbackDistance Die Rückstoßdistanz, die auf getroffene Entitäten angewendet wird.
     */
    public FallsdownProjectileSkill(
            String pathToTexturesOfProjectile,
            float projectileSpeed,
            Damage projectileDamage,
            Point projectileHitboxSize,
            ITargetSelection selectionFunction,
            float projectileRange,
            float knockbackDistance) {
        this.pathToTexturesOfProjectile = pathToTexturesOfProjectile;
        this.projectileDamage = projectileDamage;
        this.projectileSpeed = projectileSpeed;
        this.projectileRange = projectileRange;
        this.projectileHitboxSize = projectileHitboxSize;
        this.selectionFunction = selectionFunction;
        this.knockbackDistance = knockbackDistance;
    }

    /**
     * Führt den Skill für die übergebene Entity aus.
     *
     * @param entity Die Entity, auf die der Skill angewendet werden soll.
     */
    @Override
    public void execute(Entity entity) {
        LOGGER.info("Executing skill: " + this.getClass().getSimpleName());

        Entity projectile = new Entity();

        PositionComponent epc =
                (PositionComponent)
                        entity.getComponent(PositionComponent.class)
                                .orElseThrow(
                                        () -> new MissingComponentException("PositionComponent"));

        new PositionComponent(projectile, epc.getPosition());

        Animation animation = AnimationBuilder.buildAnimation(pathToTexturesOfProjectile);
        new AnimationComponent(projectile, animation);

        Point aimedOn = selectionFunction.selectTargetPoint();
        Point targetPoint =
                SkillTools.calculateLastPositionInRange(
                        epc.getPosition(), aimedOn, projectileRange);
        Point velocity =
                SkillTools.calculateVelocity(epc.getPosition(), targetPoint, projectileSpeed);

        VelocityComponent vc =
                new VelocityComponent(projectile, velocity.x, velocity.y, animation, animation);

        new ProjectileComponent(projectile, epc.getPosition(), targetPoint);

        ICollide collide =
                (a, b, from) -> {
                    if (b != entity) {
                        b.getComponent(HealthComponent.class)
                                .ifPresent(
                                        hc -> {
                                            ((HealthComponent) hc).receiveHit(projectileDamage);
                                            SkillTools.applyKnockback(b, entity, knockbackDistance);
                                            Game.removeEntity(projectile);
                                        });
                    }
                };

        new HitboxComponent(
                projectile, new Point(0.25f, 0.25f), projectileHitboxSize, collide, null);

        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        projectile.removeComponent(VelocityComponent.class);

                        VelocityComponent fallVelocity =
                                new VelocityComponent(
                                        projectile, 0.0f, -projectileSpeed, animation, animation);
                        projectile.addComponent(fallVelocity);
                    }
                },
                (long) (projectileRange * 100));
    }
}
