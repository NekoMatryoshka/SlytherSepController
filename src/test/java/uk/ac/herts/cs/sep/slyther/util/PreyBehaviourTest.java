/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.util.ArrayList;
import java.util.List;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.game.entity.ClientSnake;
import net.gegy1000.slyther.game.Color;
import net.gegy1000.slyther.game.Game;
import net.gegy1000.slyther.game.entity.Food;
import net.gegy1000.slyther.game.entity.Prey;
import net.gegy1000.slyther.game.entity.SnakePoint;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 *
 * @author Neko Matryoshka
 */
public class PreyBehaviourTest {
    
    
    private PreyBehaviour instance;
    private List<Prey<?>> preys;
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
        preys = new ArrayList<>();
        when(client.getPreys()).thenReturn(preys);
        instance = new PreyBehaviour();
        preys.add(new Prey(game, 0, 100, 10, 1, Color.GREEN, 1, 3.14159f, 3.14159f, 1) {
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
        double expResult = 0.0997;
        instance.isActive(client);
        double result = instance.targetAngle();
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testPriority() {
        double expResult = 2;
        instance.isActive(client);
        double result = instance.priority();
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testIsBoosting() {
        boolean expResult = true;
        instance.isActive(client);
        boolean result = instance.isBoosting();
        assertEquals(expResult, result);
    }
    
}
