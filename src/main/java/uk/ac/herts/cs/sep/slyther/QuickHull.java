/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther;

//This is a java program to find a points in convex hull using quick hull method
//source: Alexander Hrishov's website
//URL: http://www.ahristov.com/tutorial/geometry-games/convex-hull.html
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import net.gegy1000.slyther.game.entity.Snake;
import net.gegy1000.slyther.game.entity.SnakePoint;
import uk.ac.herts.cs.sep.slyther.util.PointMaths;

public class QuickHull {

    public Path2D.Double poly = null;
    public ArrayList<SnakePoint> hull;
    int minY = Integer.MAX_VALUE;
    int minX = minY;
    int maxX = -minY, maxY = -minY;
    private int empty = 0;

    public QuickHull() {
        hull = new ArrayList<>();
        poly = new Path2D.Double();
        empty = 0;
    }

    public QuickHull(Snake<?> snake) {
        quickHull(snake);
        fillPoly();
    }

    public QuickHull(ArrayList<SnakePoint> points) {
        quickHull(points);
        fillPoly();
    }

    private void fillPoly() {
        SnakePoint sp;
        if (hull.size() > 0) {
            sp = hull.get(0);
            poly.moveTo(sp.posX, sp.posY);
        }
        for (int i = 1; i < hull.size(); i++) {
            sp = hull.get(i);
            poly.lineTo(sp.posX, sp.posY);
        }
        poly.closePath();
    }

    private void quickHull(Snake<?> snake) {
        quickHull(new ArrayList<>(snake.points));
    }

    private void quickHull(List<SnakePoint> points) {
        poly = new Path2D.Double();
        empty = 0;
        hull = new ArrayList<>();
        if (points.size() < 3) {
            for (SnakePoint sp : points) {
                addPoint(sp);
            }
            return;
        }

        int minPoint = -1, maxPoint = -1;
        double minXl = Integer.MAX_VALUE;
        double maxXl = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).posX < minXl) {
                minXl = points.get(i).posY;
                minPoint = i;
            }
            if (points.get(i).posX > maxXl) {
                maxXl = points.get(i).posX;
                maxPoint = i;
            }
        }
        SnakePoint A = points.get(minPoint);
        SnakePoint B = points.get(maxPoint);
        this.addPoint(A);
        this.addPoint(B);
        points.remove(A);
        points.remove(B);

        ArrayList<SnakePoint> leftSet = new ArrayList<>();
        ArrayList<SnakePoint> rightSet = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            SnakePoint p = points.get(i);
            if (pointLocation(A, B, p) == -1) {
                leftSet.add(p);
            } else if (pointLocation(A, B, p) == 1) {
                rightSet.add(p);
            }
        }
        hullSet(A, B, rightSet, hull);
        hullSet(B, A, leftSet, hull);

    }

    public double distance(SnakePoint A, SnakePoint B, SnakePoint C) {
        double ABx = B.posX - A.posX;
        double ABy = B.posY - A.posY;
        double num = ABx * (A.posY - C.posY) - ABy * (A.posX - C.posX);
        if (num < 0) {
            num = -num;
        }
        return num;
    }

    public void hullSet(SnakePoint A, SnakePoint B, ArrayList<SnakePoint> set,
            ArrayList<SnakePoint> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.isEmpty()) {
            return;
        }
        if (set.size() == 1) {
            SnakePoint p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        double dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            SnakePoint p = set.get(i);
            double distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        SnakePoint P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<SnakePoint> leftSetAP = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            SnakePoint M = set.get(i);
            if (pointLocation(A, P, M) == 1) {
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of PB
        ArrayList<SnakePoint> leftSetPB = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            SnakePoint M = set.get(i);
            if (pointLocation(P, B, M) == 1) {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }

    public int pointLocation(SnakePoint A, SnakePoint B, SnakePoint P) {
        double cp1 = (B.posX - A.posX) * (P.posY - A.posY) - (B.posY - A.posY) * (P.posX - A.posX);
        if (cp1 > 0) {
            return 1;
        } else if (cp1 == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean contains(Point2D sn) {
        return poly.contains(sn);
    }

    public boolean contains(SnakePoint sn) {
        return poly.contains(sn);
    }

    private void addPoint(SnakePoint sp) {
        hull.add(sp);

        if (sp.posX < minX) {
            minX = (int) sp.posX;
        }
        if (sp.posY < minY) {
            minY = (int) sp.posY;
        }
        if (sp.posX > maxX) {
            maxX = (int) sp.posX;
        }
        if (sp.posY > maxY) {
            maxY = (int) sp.posY;
        }
    }

    @Override
    public String toString() {
        return empty + "\t" + minX + "," + minY + "\t" + maxX + "," + maxY;
    }

    protected void printPath() {
        AffineTransform at = new AffineTransform();
        PathIterator pi = poly.getPathIterator(at);
        float[] values = new float[6];
        float x = 0, y = 0;

        while (!pi.isDone()) {
            boolean ok = true;
            int type = pi.currentSegment(values);
            if (type == PathIterator.SEG_LINETO) {
                x = values[0];
                y = values[1];
            } else if (type == PathIterator.SEG_CLOSE) {
                x = 0;
                y = 0;
            } else {
                ok = false;
                // SEG_MOVETO, SEG_QUADTO, SEG_CUBICTO
            }
            if (ok) {
                System.out.println(x + "\t" + y);
            }
            pi.next();
        }
    }
}
