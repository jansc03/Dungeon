package ecs.entities.monsters;

import ecs.components.IOnDeathFunction;
import ecs.entities.Entity;

public abstract class BasicMonster extends Entity implements IOnDeathFunction {
    /** Die horizontale Bewegungsgeschwindigkeit des Monsters. */
    protected final float xSpeed;

    /** Die vertikale Bewegungsgeschwindigkeit des Monsters. */
    protected final float ySpeed;

    /** Die Gesundheitspunkte des Monsters. */
    protected final float hp;

    /** Der Dateipfad zur Leerlauf-Animation, wenn das Monster nach links schaut. */
    protected String pathToIdleLeft;

    /** Der Dateipfad zur Leerlauf-Animation, wenn das Monster nach rechts schaut. */
    protected String pathToIdleRight;

    /** Der Dateipfad zur Lauf-Animation, wenn das Monster nach links schaut. */
    protected String pathToRunLeft;

    /** Der Dateipfad zur Lauf-Animation, wenn das Monster nach rechts schaut. */
    protected String pathToRunRight;

    /**
     * Konstruiert ein BasicMonster-Objekt mit den angegebenen x- und y-Geschwindigkeiten,
     * Gesundheitspunkten und Animationsdateipfaden.
     *
     * @param xSpeed Die horizontale Bewegungsgeschwindigkeit des Monsters.
     * @param ySpeed Die vertikale Bewegungsgeschwindigkeit des Monsters.
     * @param hp Die Gesundheitspunkte des Monsters.
     * @param pathToIdleLeft Der Dateipfad zur Leerlauf-Animation, wenn das Monster nach links
     *     schaut.
     * @param pathToIdleRight Der Dateipfad zur Leerlauf-Animation, wenn das Monster nach rechts
     *     schaut.
     * @param pathToRunLeft Der Dateipfad zur Lauf-Animation, wenn das Monster nach links schaut.
     * @param pathToRunRight Der Dateipfad zur Lauf-Animation, wenn das Monster nach rechts schaut.
     */
    public BasicMonster(
            float xSpeed,
            float ySpeed,
            float hp,
            String pathToIdleLeft,
            String pathToIdleRight,
            String pathToRunLeft,
            String pathToRunRight) {
        super();
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
        this.pathToIdleLeft = pathToIdleLeft;
        this.pathToIdleRight = pathToIdleRight;
        this.pathToRunLeft = pathToRunLeft;
        this.pathToRunRight = pathToRunRight;
    }

    /**
     * Richten Sie die Geschwindigkeitskomponente der Monsterentität ein. Diese Methode soll von
     * Subklassen überschrieben werden, um spezifische Implementierungsdetails bereitzustellen.
     */
    public abstract void setupVelocityComponent();

    /**
     * Richten Sie die Animationskomponente der Monsterentität ein. Diese Methode soll von
     * Subklassen überschrieben werden, um spezifische Implementierungsdetails bereitzustellen.
     */
    public abstract void setupAnimationComponent();

    /**
     * Richten Sie die AI-Komponente der Monsterentität ein. Diese Methode soll von Subklassen
     * überschrieben werden, um spezifische Implement lungsdetails bereitzustellen.
     */
    public abstract void setupAIComponent();

    /**
     * Richtet die Hitbox-Komponente der Monster-Entität ein. Diese Methode sollte von Unterklassen
     * überschrieben werden, um spezifische Implementierungsdetails bereitzustellen.
     */
    public abstract void setupHitboxComponent();

    /**
     * Richtet die Gesundheitskomponente der Monstereinheit mit den angegebenen maximalen
     * Gesundheitspunkten ein. Diese Methode sollte von Unterklassen überschrieben werden, um
     * spezifische Implementierungsdetails bereitzustellen.
     *
     * @param maxHealthPoints Die maximalen Gesundheitspunkte des Monsters.
     */
    public abstract void setupHealthComponent(int maxHealthPoints);
}
