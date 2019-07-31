/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.util.*;
import net.gegy1000.slyther.client.*;
import net.gegy1000.slyther.client.game.entity.*;
import net.gegy1000.slyther.game.*;
import net.gegy1000.slyther.game.entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 *
 * @author Neko Matryoshka
 */
public class FoodBehaviourTest {

    private FoodBehaviour instance;
    private List<Food<?>> foods;
    private Game game;
    SlytherClient client;

    @Before
    public void setUp() {
        ClientSnake player = mock(ClientSnake.class);
        client = mock(SlytherClient.class);
        client.player = player;
        game = mock(Game.class);
        when(game.getSectorSize()).thenReturn(1000);
        when(client.getGameRadius()).thenReturn(1000);
        player.points = new ArrayList<>();
        player.points.add(new SnakePoint(game, 0, 0));
        foods = new ArrayList<>();
        when(client.getFoods()).thenReturn(foods);
        instance = new FoodBehaviour();
        foods.add(new Food(game, 10, 10, 1, true, Color.GREEN) {
            @Override
            public boolean update(float delta, float lastDelta, float lastDelta2) {
                return false;
            }
        });
    }

    @Test
    public void testIsActive() {
        boolean expResult = true;
        boolean result = instance.isActive(client);
        assertEquals(expResult, result);
    }

    @Test
    public void testTargetAngle() {
        double expResult = 0.785;
        instance.isActive(client);
        double result = instance.targetAngle();
        assertEquals(expResult, result, 0.1);
    }
    
        @Test
    public void testPriority() {
        double expResult = 1;
        instance.isActive(client);
        double result = instance.priority();
        assertEquals(expResult, result, 0);
    }
    
        @Test
    public void testIsBoosting() {
        boolean expResult = false;
        instance.isActive(client);
        boolean result = instance.isBoosting();
        assertEquals(expResult, result);
    }
}
