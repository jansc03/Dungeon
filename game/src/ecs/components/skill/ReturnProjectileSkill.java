package ecs.components.skill;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.collision.ICollide;
import ecs.damage.Damage;
import ecs.entities.Entity;
import graphic.Animation;
import starter.Game;
import tools.Point;

public abstract class ReturnProjectileSkill implements ISkillFunction {

    private String pathToTexturesOfProjectile;
    private float projectileSpeed;
    private float projectileRange;
    private Damage projectileDamage;
    private Point projectileHitboxSize;
    private ITargetSelection selectionFunction;
    private float knockbackDistance;

    /**
     * Konstruiert ein ReturningProjectileSkill-Objekt mit den angegebenen Parametern.
     *
     * @param pathToTexturesOfProjectile Der Pfad zu den Texturen des Projektils.
     * @param projectileSpeed            Die Geschwindigkeit des Projektils.
     * @param projectileDamage           Der Schaden, der vom Projektil verursacht wird.
     * @param projectileHitboxSize       Die Größe der Trefferbox des Projektils.
     * @param selectionFunction          Die Zielwahl-Funktion für das Projektil.
     * @param projectileRange            Die Reichweite des Projektils.
     * @param knockbackDistance          Die Rückstoßdistanz, die auf getroffene Entitäten angewendet wird.
     */
    public ReturnProjectileSkill(String pathToTexturesOfProjectile, float projectileSpeed,
                                 Damage projectileDamage, Point projectileHitboxSize,
                                 ITargetSelection selectionFunction, float projectileRange,
                                 float knockbackDistance) {
        this.pathToTexturesOfProjectile = pathToTexturesOfProjectile;
        this.projectileSpeed = projectileSpeed;
        this.projectileDamage = projectileDamage;
        this.projectileHitboxSize = projectileHitboxSize;
        this.selectionFunction = selectionFunction;
        this.projectileRange = projectileRange;
        this.knockbackDistance = knockbackDistance;
    }

    /**
     * Führt die Fähigkeit aus, indem ein Projektil von der Ausführenden Entität abgefeuert wird
     * und bei Kollision des initialen Projektils mit einer anderen Entität als der Ausführenden ein
     * Rückkehrprojektil erstellt wird.
     *
     * @param entity Die Entität, die die Fähigkeit ausführt.
     */
    @Override
    public void execute(Entity entity) {
        Entity projectile = new Entity();
        PositionComponent epc = (PositionComponent) entity.getComponent(PositionComponent.class)
            .orElseThrow(() -> new MissingComponentException("PositionComponent"));
        new PositionComponent(projectile, epc.getPosition());
        Animation animation = AnimationBuilder.buildAnimation(pathToTexturesOfProjectile);
        new AnimationComponent(projectile, animation);
        Point aimedOn = selectionFunction.selectTargetPoint();
        Point targetPoint = SkillTools.calculateLastPositionInRange(epc.getPosition(), aimedOn, projectileRange);
        Point velocity = SkillTools.calculateVelocity(epc.getPosition(), targetPoint, projectileSpeed);
        VelocityComponent vc = new VelocityComponent(projectile, velocity.x, velocity.y, animation, animation);
        new ProjectileComponent(projectile, epc.getPosition(), targetPoint);
        ICollide collide = (a, b, from) -> {
            if (b != entity) {
                b.getComponent(HealthComponent.class)
                    .ifPresent(hc -> {
                        ((HealthComponent) hc).receiveHit(projectileDamage);
                        createReturnProjectile(projectile, entity, b);
                         SkillTools.applyKnockback(b, entity, knockbackDistance);
                        Game.removeEntity(projectile);
                    });
            }
        };
        new HitboxComponent(projectile, new Point(0.25f, 0.25f), projectileHitboxSize, collide, null);
    }

    /**
     * Erstellt ein Rückkehrprojektil, wenn das initiale Projektil mit einer anderen Entität als der Ausführenden kollidiert.
     *
     * @param projectile Das initiale Projektil.
     * @param entity     Die Ausführende Entität.
     * @param hitEntity  Die Entität, die vom initialen Projektil getroffen wurde.
     */
    private void createReturnProjectile(Entity projectile, Entity entity, Entity hitEntity) {
        Entity returningProjectile = new Entity();
        Point startPoint = getPosEntity(projectile);
        Point endPoint = getPosProjectile(projectile);
        new PositionComponent(returningProjectile, startPoint);
        Animation animation = AnimationBuilder.buildAnimation(pathToTexturesOfProjectile);
        new AnimationComponent(returningProjectile, animation);
        Point aimedOn = endPoint;
        Point targetPoint = SkillTools.calculateLastPositionInRange(startPoint, aimedOn, projectileRange);
        Point velocity = SkillTools.calculateVelocity(startPoint, targetPoint, projectileSpeed);
        VelocityComponent vc = new VelocityComponent(returningProjectile, velocity.x, velocity.y, animation, animation);
        new ProjectileComponent(returningProjectile, startPoint, targetPoint);
        ICollide collide = (a, b, from) -> {
            if (b != entity && b != hitEntity) {
                b.getComponent(HealthComponent.class)
                    .ifPresent(hc -> {
                        ((HealthComponent) hc).receiveHit(projectileDamage);
                        Game.removeEntity(returningProjectile);
                    });
            }
            if (b == entity) {
                Game.removeEntity(returningProjectile);
            }
        };
        new HitboxComponent(returningProjectile, new Point(0.25f, 0.25f), projectileHitboxSize, collide, null);
    }

    /**
     * Gibt die Position der angegebenen Entität zurück.
     *
     * @param entity Die Entität, deren Position zurückgegeben werden soll.
     * @return Die Position der Entität.
     * @throws MissingComponentException wenn die Entität kein PositionComponent hat.
     */
    private Point getPosEntity(Entity entity) {
        PositionComponent positionComponent = (PositionComponent) entity.getComponent(PositionComponent.class)
            .orElseThrow(() -> new MissingComponentException("PositionComponent"));
        return positionComponent.getPosition();
    }

    /**
     * Gibt die Startposition des angegebenen Projektils zurück.
     *
     * @param entity Das Projektil, dessen Startposition zurückgegeben werden soll.
     * @return Die Startposition des Projektils.
     * @throws MissingComponentException wenn das Projektil kein ProjectileComponent hat.
     */
    private Point getPosProjectile(Entity entity) {
        ProjectileComponent projectileComponent = (ProjectileComponent) entity.getComponent(ProjectileComponent.class)
            .orElseThrow(() -> new MissingComponentException("ProjectileComponent"));
        return projectileComponent.getStartPosition();
    }
}
