package controller;

import ecs.systems.ECS_System;

/** used to integrate ECS_Systems in PM-Dungeon game loop */
public class SystemController extends AbstractController<ECS_System> {

    public SystemController() {
        super();
    }

    @Override
    public void process(ECS_System e) {
        if (e.isRunning()) e.update();
    }
}