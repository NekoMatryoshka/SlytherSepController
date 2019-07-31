/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.awt.Point;
import java.awt.geom.Point2D;
import static java.lang.Math.atan2;
import java.util.ArrayList;
import net.gegy1000.slyther.game.entity.SnakePoint;

/**
 *
 * @author comqdhb
 */
public class PointMaths {

    public static double angle(Point.Double p1, Point.Double p2) {
        return atan2(p2.y - p1.y, p2.x - p1.x);
        //return degree * 180.0 / Math.PI ;
    }

    public static double distance(Point.Double p1, Point.Double p2) {
        double distanceX = Math.abs(p1.x - p2.x);
        double distanceY = Math.abs(p1.y - p2.y);
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public static Point.Double getNearestPoint(ArrayList<Point.Double> points, SnakePoint origin) {
        Point.Double nearestPoint = null;
        double nearestDistance = -1;
        for (Point.Double point : points) {
            double distance = distance(point, origin);
            if (nearestPoint == null || nearestDistance > distance) {
                nearestPoint = point;
                nearestDistance = distance;
            }
        }
        return nearestPoint;
    }
}
