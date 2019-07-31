/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Point;
import java.util.ArrayList;
import net.gegy1000.slyther.game.entity.SnakePoint;

/**
 *
 * @author jw17aca
 */
public class PointMathsTest {

    public PointMathsTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testAngle() {
        Point.Double point1 = new Point.Double(1.2, 1.3);
        Point.Double point2 = new Point.Double(2.7, 5.1);
        Double expResult = 1.194835372923591;
        Double result = PointMaths.angle(point1, point2);
        assertEquals(expResult, result);
    }

    @Test
    public void testDistance() {
        Point.Double point1 = new Point.Double(1.2, 7.3);
        Point.Double point2 = new Point.Double(6.7, 5.1);
        Double expResult = 5.923681287847955;
        Double result = PointMaths.distance(point1, point2);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNearestPoint() {
        ArrayList<Point.Double> points = new ArrayList<>();
        points.add(new Point.Double(1, 1));
        points.add(new Point.Double(2, 2));
        points.add(new Point.Double(3, 3));
        SnakePoint origin = new SnakePoint(null, 0, 0);
        Point.Double expResult = points.get(0);
        Point.Double result = PointMaths.getNearestPoint(points, origin);
        assertEquals(expResult, result);
    }
}
