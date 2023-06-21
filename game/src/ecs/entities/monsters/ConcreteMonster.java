package ecs.entities.monsters;

import ecs.entities.Entity;

public class ConcreteMonster extends BasicMonster {
    public ConcreteMonster(
        float xSpeed,
        float ySpeed,
        float hp,
        String pathToIdleLeft,
        String pathToIdleRight,
        String pathToRunLeft,
        String pathToRunRight) {
        super(xSpeed, ySpeed, hp, pathToIdleLeft, pathToIdleRight, pathToRunLeft, pathToRunRight);
    }

    @Override
    public void setupVelocityComponent() {
        // Provide the specific implementation details for setting up the velocity component
    }

    @Override
    public void setupAnimationComponent() {
        // Provide the specific implementation details for setting up the animation component
    }

    @Override
    public void setupAIComponent() {
        // Provide the specific implementation details for setting up the AI component
    }

    @Override
    public void setupHitboxComponent() {
        // Provide the specific implementation details for setting up the hitbox component
    }

    @Override
    public void setupHealthComponent(int maxHealthPoints) {
        // Provide the specific implementation details for setting up the health component
    }

    /**
     * Function that is performed when an entity dies
     *
     * @param entity Entity that has died
     */
    @Override
    public void onDeath(Entity entity) {

    }
}

