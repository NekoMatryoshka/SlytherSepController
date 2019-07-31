package uk.ac.herts.cs.sep.slyther.util;

import net.gegy1000.slyther.client.SlytherClient;

/**
 *
 * @author comqdhb
 */
public abstract class SnakeBehaviour implements Comparable<SnakeBehaviour> {

    protected double angle;
    protected boolean active;

    public SnakeBehaviour() {
        angle = 0;
        active = false;
    }

    public abstract boolean isActive(SlytherClient client);

    public double targetAngle() {
        return angle;
    }

    public abstract double priority();

    public abstract boolean isBoosting();

    @Override
    public int compareTo(SnakeBehaviour other) {
        return Double.compare(priority(), other.priority());
        //return ((Double) priority()).compareTo(other.priority());
    }

}
