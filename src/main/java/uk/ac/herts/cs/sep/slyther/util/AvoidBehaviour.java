/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.awt.Point;
import java.util.*;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.game.entity.*;
import net.gegy1000.slyther.client.gui.*;
import net.gegy1000.slyther.game.*;
import net.gegy1000.slyther.game.entity.*;
import uk.ac.herts.cs.sep.slyther.QuickHull;

/**
 *
 * @author comqdhb
 */
public class AvoidBehaviour extends SnakeBehaviour {

    float angle;

    @Override
    public boolean isActive(SlytherClient client) {
        SnakePoint playerHead = client.player.points.get(0);

        ArrayList<Point.Double> snakePoints = new ArrayList<>();
        for (Snake<?> snake : client.getSnakes()) {
            if (!snake.equals(client.player)) {
                for (SnakePoint point : snake.points) {
                    snakePoints.add(point);
                }
            }
        }

        SnakePoint nearestPoint = (SnakePoint) PointMaths.getNearestPoint(snakePoints, playerHead);

        if (nearestPoint != null && PointMaths.distance(nearestPoint, playerHead) < 1000) {
            angle = (float) (PointMaths.angle(playerHead, nearestPoint) + 3.141592653589793);
            System.out.println("AvoidBehaviour" + angle * 180 / Math.PI);
            return true;
        }
        return false;
    }

    @Override
    public double targetAngle() {
        return angle;
    }

    @Override
    public double priority() {
        return 4;
    }

    @Override
    public boolean isBoosting() {
        return false;
    }
}
