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
import static net.gegy1000.slyther.game.Skin.PURPLE_DEFAULT;
import net.gegy1000.slyther.game.entity.Food;
import net.gegy1000.slyther.game.entity.Snake;
import net.gegy1000.slyther.game.entity.SnakePoint;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 *
 * @author Neko Matryoshka
 */
public class AvoidBehaviourTest {
    SlytherClient client;
    private AvoidBehaviour instance;
    private List<Snake<?>> snakes;
    private Game game;
    
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
        snakes = new ArrayList<>();
        when(client.getSnakes()).thenReturn(snakes);
        instance = new AvoidBehaviour();
        ArrayList<SnakePoint> snakePoints = new ArrayList<>();
        snakePoints.add(new SnakePoint(game, 10, 10));
        snakes.add(new Snake(game, "XIA", 1, 10, 10,PURPLE_DEFAULT, 3.14159F, snakePoints) {
            @Override
            public boolean update(float delta, float lastDelta, float lastDelta2) {
                return false;
            }
        });
    }
    
    @Test
    public void testIsActive(){
        boolean expResult = true;
        boolean result = instance.isActive(client);
        assertEquals(expResult, result);
    }

    @Test
    public void testTargetAngle(){
        double expResult = 3.927;
        instance.isActive(client);
        double result = instance.targetAngle();
        assertEquals(expResult, result, 0.1);
    }
    
    @Test
    public void testPriority(){
        double expResult = 4;
        double result = instance.priority();
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testIsBoosting(){
        boolean expResult = false;
        boolean result = instance.isBoosting();
        assertEquals(expResult, result);
    }
    
}
