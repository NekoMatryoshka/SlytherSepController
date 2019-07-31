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
import net.gegy1000.slyther.client.game.entity.*;
import net.gegy1000.slyther.game.Game;
import net.gegy1000.slyther.game.entity.*;

/**
 *
 * @author comqdhb
 */
public class FoodBehaviour extends SnakeBehaviour {

    @Override
    public boolean isActive(SlytherClient client) {
        SnakePoint playerHead = client.player.points.get(0);
        ArrayList<Point.Double> foods = new ArrayList<>();

        for (Food<?> food : client.getFoods()) {
            Point.Double foodPoint = new Point.Double(food.posX, food.posY);
            foods.add(foodPoint);
        }

        Point.Double nearestFood = PointMaths.getNearestPoint(foods, playerHead);

        if (nearestFood != null && PointMaths.distance(nearestFood, playerHead) < 1500) {
            angle = PointMaths.angle(playerHead, nearestFood);
            //double distance = PointMaths.distance(playerHead, nearestFood);
            System.out.println("FoodBehaviour " + angle * 180 / Math.PI);
            active = true;
        }
        return active;

    }

    @Override
    public double targetAngle() {
        return angle;
    }

    @Override
    public double priority() {
        return 1; //avoid>trapped>food>prey>null, starts from 0; more comparison between same behaviors might be needed.
    }

    @Override
    public boolean isBoosting() {
        return false;
    }

}
