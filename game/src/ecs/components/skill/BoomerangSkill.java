package ecs.components.skill;

import ecs.damage.Damage;
import ecs.damage.DamageType;
import tools.Point;

public class BoomerangSkill extends ReturnProjectileSkill {
    public BoomerangSkill(ITargetSelection targetSelection) {
        super(
                "skills/boomerang/",
                0.3f,
                new Damage(10, DamageType.PHYSICAL, null),
                new Point(1, 1),
                targetSelection,
                5f,
                0.3f);
    }
}
