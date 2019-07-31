/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.controller.IController;

/**
 *
 * @author comqdhb
 */
public class Subsumption implements IController, Serializable {
    private static final long serialVersionUID = 5573832826736049153L;  
    private transient final List<SnakeBehaviour> behaviours;
    boolean isAccelerating;
    float angle;

    public Subsumption(List<SnakeBehaviour> s) {
        behaviours = s;
    }

    public Subsumption() {
        behaviours = new ArrayList<>();
//      behaviours.add(new AvoidHeadBehaviour());
//      behaviours.add(new AvoidTrappedBehaviour());
        behaviours.add(new FoodBehaviour());
        behaviours.add(new NullBehaviour());
        behaviours.add(new AvoidBehaviour());
        behaviours.add(new PreyBehaviour());
        behaviours.add(new TrappedBehaviour());
    }

    public SnakeBehaviour getBestBehaviour(SlytherClient client) {
        SnakeBehaviour priorBehaviour = null;
        for (SnakeBehaviour behaviour : behaviours) {
            if (behaviour.isActive(client) && priorBehaviour == null) {
                priorBehaviour = behaviour;
            } else if (behaviour.isActive(client) && (priorBehaviour.priority() < behaviour.priority())) {
                priorBehaviour = behaviour;
            }
        }
        return priorBehaviour;
    }

    @Override
    public void update(SlytherClient client) {
        SnakeBehaviour priorBehaviour = this.getBestBehaviour(client);
        priorBehaviour.isActive(client);
        angle = (float) priorBehaviour.targetAngle();
        isAccelerating = priorBehaviour.isBoosting();
    }

    @Override
    public float getTargetAngle() {
        return angle;
    }

    @Override
    public boolean shouldAccelerate() {
        return isAccelerating;
    }
}
