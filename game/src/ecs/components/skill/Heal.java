package ecs.components.skill;

import ecs.components.HealthComponent;
import ecs.entities.Entity;

public class Heal extends MagicSkill {
    @Override
    public void execute(Entity entity) {
        if (entity.getComponent(HealthComponent.class).isPresent()) {
            HealthComponent hCp =
                    (HealthComponent) entity.getComponent(HealthComponent.class).get();

            int current = hCp.getCurrentHealthpoints();
            float healedByPercentage = 0.5f;
            int toHeal = ((int) (current * healedByPercentage));
            int HP = (current + toHeal);
            hCp.setCurrentHealthpoints(HP);
            System.out.println("Current HP is: " + HP);
        }
    }
}
