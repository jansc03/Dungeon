package ecs.entities;

import ecs.components.PositionComponent;

/** Basic Baustein für fallen, Haupsächlich wird hier nur Position Component himzugefügt */
public class Traps extends Entity {
    public int usages;

    public Traps() {
        super();
        usages = 10000;
        new PositionComponent(this);
    }

    public void setUsages(int usages) {
        if (usages < 0) {
            throw new IllegalArgumentException("Usages cannot be negative");
        }
        this.usages = usages;
    }
    public int getUsages() {
        return usages;
    }
}
