/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.game.entity.ClientSnake;
import net.gegy1000.slyther.game.Game;
import net.gegy1000.slyther.game.entity.Snake;
import net.gegy1000.slyther.game.entity.SnakePoint;
import uk.ac.herts.cs.sep.slyther.QuickHull;

/**
 *
 * @author comqdhb
 */
public class TrappedBehaviour extends SnakeBehaviour {

    @Override
    public boolean isActive(SlytherClient client) {
        boolean result = false;
        ClientSnake cs = client.player;
        SnakePoint head = cs.points.get(0);
        for (Snake<?> snake : client.getSnakes()) {
            QuickHull qh = new QuickHull(snake);
            if (qh.contains(head)) {
                System.out.println("trapped angle: " + angle);
                result = true;
                SnakePoint tail = snake.points.get(snake.points.size() - 1);
                angle = PointMaths.angle(head, tail);
            }
        }
        return result;
    }

    @Override
    public double targetAngle() {
        return angle;
    }

    @Override
    public double priority() {
        return 3;  //0-avoid, 1-trapped, 2-food, 3-prey, 4-null
    }

    @Override
    public boolean isBoosting() {
        return true;  //boost to escape from trap;
    }
}
