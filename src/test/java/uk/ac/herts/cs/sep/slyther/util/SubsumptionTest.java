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
import net.gegy1000.slyther.game.entity.Prey;
import net.gegy1000.slyther.game.entity.Snake;
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
public class SubsumptionTest {

    SlytherClient client;
    private AvoidBehaviour instance;
    private List<Food<?>> foods;
    private List<Prey<?>> preys;
    private List<Snake<?>> snakes;
    private Game game;
    private Subsumption sub;
    ArrayList<SnakePoint> snakePoints;

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
        preys = new ArrayList<>();
        foods = new ArrayList<>();
        when(client.getSnakes()).thenReturn(snakes);
        when(client.getPreys()).thenReturn(preys);
        when(client.getFoods()).thenReturn(foods);
        instance = new AvoidBehaviour();
        snakePoints = new ArrayList<>();
        snakePoints.add(new SnakePoint(game, 10, 10));
        sub = new Subsumption();
    }

    @Test
    public void testGetBestBehaviour() {
        snakes.add(new Snake(game, "XIA", 1, 10, 10, PURPLE_DEFAULT, 3.14159F, snakePoints) {
            @Override
            public boolean update(float delta, float lastDelta, float lastDelta2) {
                return false;
            }
        });
        SnakeBehaviour behav = sub.getBestBehaviour(client);
        System.out.println(behav);
        if (behav instanceof AvoidBehaviour) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testGetBestBehaviour2() {
        foods.add(new Food(game, 10, 10, 1, true, Color.GREEN) {
            @Override
            public boolean update(float delta, float lastDelta, float lastDelta2) {
                return false;
            }
        });
        SnakeBehaviour behav = sub.getBestBehaviour(client);
        System.out.println(behav);
        if (behav instanceof FoodBehaviour) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }
}
