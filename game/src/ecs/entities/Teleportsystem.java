package ecs.entities;

import ecs.components.MissingComponentException;
import ecs.components.PositionComponent;
import starter.Game;

public class Teleportsystem {

    public Teleportpads pad1;

    public Teleportpads pad2;

    public Boolean usable = true;
    public int ticcounter = 5 * 30 + 1;

    /**
     * Wird vom jeweiligen betretenen Pad aufgerufen, um dann den Helden zu teleportieren. Dies
     * geschieht durch aufrufen der Positioncomponenten der Pads und des Helden
     */
    public void usedPad(Teleportpads UsedPad) {
        if (usable && ticcounter > 5 * 30) {
            ticcounter = 0;
            PositionComponent pch =
                    (PositionComponent)
                            Game.getHero()
                                    .get()
                                    .getComponent(PositionComponent.class)
                                    .orElseThrow(
                                            () ->
                                                    new MissingComponentException(
                                                            "PositionComponent"));
            if (UsedPad == pad1) {
                PositionComponent pcp =
                        (PositionComponent)
                                pad2.getComponent(PositionComponent.class)
                                        .orElseThrow(
                                                () ->
                                                        new MissingComponentException(
                                                                "PositionComponent"));

                pch.setPosition(pcp.getPosition());
            } else {
                PositionComponent pcp =
                        (PositionComponent)
                                pad1.getComponent(PositionComponent.class)
                                        .orElseThrow(
                                                () ->
                                                        new MissingComponentException(
                                                                "PositionComponent"));

                pch.setPosition(pcp.getPosition());
            }
            checkUsages();
            usable = false;
        }
    }
    /** schaut ob eines der Pads keine Nutzungen mehr und löscht die dann aus der Entityliste */
    public void checkUsages() {
        if (pad1.getUsages() == 0 || pad2.getUsages() == 0) {
            Game.removeEntity(pad1);
            Game.removeEntity(pad2);
        }
    }
    /** Wird am Anfang eines jeden lewels Aufgerufen zum generieren der Pads */
    public void makePads() {
        ticcounter = 5 * 30 + 1;
        usable = true;
        pad1 = new Teleportpads(2, this);
        Game.addEntity(pad1);
        pad2 = new Teleportpads(2, this);
        Game.addEntity(pad2);
    }

    public void setUsable() {
        usable = true;
    }

    /**
     * Wird in Frame aufgerufen zum Zählen von 5 Sekunden und setzt dann die fallen Animationzurück
     */
    public void updateTeleportSystem() {
        if (ticcounter < 5 * 30 + 1) {
            ticcounter += 1;
        } else {
            if (usable) {
                pad1.Aniusable(pad1);
                pad2.Aniusable(pad2);
            }
        }
    }
}
