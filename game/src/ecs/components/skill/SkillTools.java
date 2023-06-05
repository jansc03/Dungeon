package ecs.components.skill;

import static starter.Game.currentLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ecs.components.MissingComponentException;
import ecs.components.PositionComponent;
import ecs.entities.Entity;
import java.util.logging.Logger;
import level.elements.tile.Tile;
import starter.Game;
import tools.Point;

public class SkillTools {

    private static final Logger LOGGER = Logger.getLogger(SkillTools.class.getName());

    /**
     * calculates the last position in range regardless of aimed position
     *
     * @param startPoint position to start the calculation
     * @param aimPoint point to aim for
     * @param range range from start to
     * @return last position in range if you follow the directon from startPoint to aimPoint
     */
    public static Point calculateLastPositionInRange(
            Point startPoint, Point aimPoint, float range) {

        // calculate distance from startPoint to aimPoint
        float dx = aimPoint.x - startPoint.x;
        float dy = aimPoint.y - startPoint.y;

        // vector from startPoint to aimPoint
        Vector2 scv = new Vector2(dx, dy);

        // normalize the vector (length of 1)
        scv.nor();

        // resize the vector to the length of the range
        scv.scl(range);

        return new Point(startPoint.x + scv.x, startPoint.y + scv.y);
    }

    public static Point calculateVelocity(Point start, Point goal, float speed) {
        float x1 = start.x;
        float y1 = start.y;
        float x2 = goal.x;
        float y2 = goal.y;

        float dx = x2 - x1;
        float dy = y2 - y1;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float velocityX = dx / distance * speed;
        float velocityY = dy / distance * speed;
        return new Point(velocityX, velocityY);
    }

    /**
     * gets the current cursor position as Point
     *
     * @return mouse cursor position as Point
     */
    public static Point getCursorPositionAsPoint() {
        Vector3 mousePosition =
                Game.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Point(mousePosition.x, mousePosition.y);
    }

    /**
     * Wendet Rückstoß auf die spezifizierte Ziel-Entität basierend auf der Entfernung und Richtung
     * vom Ziel auf die Entität an.
     *
     * @param target Die Entität, auf die Rückstoß angewendet wird.
     * @param entity Die Entität, die den Rückstoß verursacht.
     * @param knockbackDistance Der Abstand, um den Rückstoß anzuwenden.
     */
    public static void applyKnockback(Entity target, Entity entity, float knockbackDistance) {
        PositionComponent targetPositionComponent =
                (PositionComponent)
                        target.getComponent(PositionComponent.class)
                                .orElseThrow(
                                        () ->
                                                new MissingComponentException(
                                                        "PositionComponent for target"));

        PositionComponent entityPositionComponent =
                (PositionComponent)
                        entity.getComponent(PositionComponent.class)
                                .orElseThrow(
                                        () ->
                                                new MissingComponentException(
                                                        "PositionComponent for entity"));

        Point direction =
                Point.getUnitDirectionalVector(
                        targetPositionComponent.getPosition(),
                        entityPositionComponent.getPosition());

        Point newPosition =
                new Point(
                        targetPositionComponent.getPosition().x + direction.x * knockbackDistance,
                        targetPositionComponent.getPosition().y + direction.y * knockbackDistance);

        Tile newTile = currentLevel.getTileAt(newPosition.toCoordinate());

        if (newTile.isAccessible()) {
            targetPositionComponent.setPosition(newPosition);
            LOGGER.info("Applied knockback to target entity: " + target.getClass().getSimpleName());
        } else {
            LOGGER.warning(
                    "Knockback blocked. Target entity: " + target.getClass().getSimpleName());
        }
    }
}
