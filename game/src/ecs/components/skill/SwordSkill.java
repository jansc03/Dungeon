package ecs.components.skill;

import ecs.damage.Damage;
import ecs.damage.DamageType;
import tools.Point;

public class SwordSkill extends MeleeSkill {
    public SwordSkill(ITargetSelection targetSelection, int damageAmount) {
        super(
                "weapon",
                0.1f,
                new Damage(damageAmount, DamageType.PHYSICAL, null),
                new Point(10, 10),
                0.3f,
                targetSelection,
                0.1f);
    }
}
