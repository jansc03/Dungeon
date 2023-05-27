package ecs.components.skill;

import ecs.damage.Damage;
import ecs.damage.DamageType;
import tools.Point;

public class BlueFiraballSkill extends FallsdownProjectileSkill {
    public BlueFiraballSkill(ITargetSelection targetSelection) {
        super(
            "skills/bluefireball/bluefireBall_Down",
            0.3f,
            new Damage(5, DamageType.FIRE, null),
            new Point(1, 1),
            targetSelection,
            5f,0.3f);
    }
}

