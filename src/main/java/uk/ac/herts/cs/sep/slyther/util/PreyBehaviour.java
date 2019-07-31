/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.game.entity.ClientSnake;
import net.gegy1000.slyther.game.Game;
import net.gegy1000.slyther.game.entity.Food;
import net.gegy1000.slyther.game.entity.Snake;
import net.gegy1000.slyther.game.entity.Prey;
import net.gegy1000.slyther.game.entity.SnakePoint;

/**
 *
 * @author comqdhb
 */
public class PreyBehaviour extends SnakeBehaviour {

    @Override
    public boolean isActive(SlytherClient client) {
        SnakePoint playerHead = client.player.points.get(0);
        ArrayList<Point.Double> preys = new ArrayList<>();

        for (Prey<?> prey : client.getPreys()) {
            Point.Double preyPoint = new Point.Double(prey.posX, prey.posY);
            preys.add(preyPoint);
        }

        Point.Double nearestPrey = PointMaths.getNearestPoint(preys, playerHead);

        if (nearestPrey != null && PointMaths.distance(nearestPrey, playerHead) < 1200) {
            angle = PointMaths.angle(playerHead, nearestPrey);
          //double distance = PointMaths.distance(playerHead, nearestPrey);
            System.out.println("PreyBehaviour " + angle * 180 / Math.PI);
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
        return 2;
    }

    @Override
    public boolean isBoosting() {
        return true;
    }

}
