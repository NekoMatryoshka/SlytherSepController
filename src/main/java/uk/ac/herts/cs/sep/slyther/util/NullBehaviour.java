/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.awt.Point;
import java.util.ArrayList;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.game.Game;
import net.gegy1000.slyther.game.entity.Snake;
import net.gegy1000.slyther.game.entity.SnakePoint;

/**
 *
 * @author comqdhb
 */
public class NullBehaviour extends SnakeBehaviour {
    
    @Override
    public boolean isActive(SlytherClient client) {
        SnakePoint playerHead = client.player.points.get(0);
        double radius = client.GAME_RADIUS;
        Point.Double center = new Point.Double(radius, radius);
        angle = PointMaths.angle(playerHead, center);
        return true;
    }

    @Override
    public double targetAngle() {
        return angle;
    }

    @Override
    public double priority() {
        return 0.0;
    }

    @Override
    public boolean isBoosting() {
        return false;
    }

}
