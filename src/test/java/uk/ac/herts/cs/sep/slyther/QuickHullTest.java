/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther;

import java.util.ArrayList;
import net.gegy1000.slyther.game.entity.SnakePoint;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author comqdhb
 */
public class QuickHullTest {
    
    public QuickHullTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void a(){
        System.out.println("Quick Hull Test");
        int N = 5;

        ArrayList<SnakePoint> points = new ArrayList<SnakePoint>();
        
        points.add(new SnakePoint(null,0.3f,0.3f));
        points.add(new SnakePoint(null,1,0));
        points.add(new SnakePoint(null,0,1));
        points.add(new SnakePoint(null,1,1));
        points.add(new SnakePoint(null,2,2));
        points.add(new SnakePoint(null,0.5f,0.5f));
        

        QuickHull qh = new QuickHull(points);
        System.out
                .println("The points in the Convex hull using Quick Hull are: ");
        for (int i = 0; i < qh.hull.size(); i++) {
            ArrayList<SnakePoint> p = qh.hull;
            System.out.println("(" + p.get(i).posX + ", " + p.get(i).posY + ")");
        }
        
        int n=qh.hull.size();
        assertTrue("size should be 4",n==4);
        qh.printPath();
        boolean inside=qh.contains(new SnakePoint(null,0.5f,0.5f));
        assertTrue("point should be inside convex hull",inside);
        inside=qh.contains(new SnakePoint(null,0.1f,0.1f));
        assertFalse("point should be outside convex hull",inside);
    }
}
