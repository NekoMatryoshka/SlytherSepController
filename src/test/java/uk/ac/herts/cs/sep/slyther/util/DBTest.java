/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.herts.cs.sep.slyther.orm.*;

/**
 *
 * @author comqdhb
 */
public class DBTest {

    /**
     * Test of hashString method, of class Sha256.
     */
    @Test
    public void testPU() {
        System.out.println("Entering pu");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uk.ac.herts.cs.sep.SlytherPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("Exiting pu");
    }

    @Test
    public void testInsertPlayerSnake() {
        boolean isPassP = false;
        boolean isPassG = false;
        PlayerSnake player = new PlayerSnake();
        player.setControllerSerialID("controllerSerialID");
        player.setCreatedDateTime("createdDateTime");
        player.setSnakeLength(999);
        player.setName("name");
        player.setTimeAlive(999);
        player.setPlayerSnakeId(LocalDateTime.now().toString());
        ArrayList<GameSnake> gameSnakes = new ArrayList<>();
        GameSnake gameSnake = new GameSnake();
        gameSnake.setName("gameSnakeName");
        gameSnake.setPlayer(player);
        gameSnake.setSnakeLength(888);
        gameSnake.setGameSnakeId((int) System.currentTimeMillis());
        gameSnakes.add(gameSnake);
        player.setGameSnakes(gameSnakes);
        DB.insertPlayerSnake(player);
        for (PlayerSnake playerSnake : DB.getAllPlayerSnake()) {
            if (playerSnake.getHashName() != null && playerSnake.getHashName().equals(Sha256.hashString("name"))) {
                isPassP = true;
            }
        }
        for (GameSnake snake : DB.getAllGameSnake()) {
            if (snake.getHashName() != null && snake.getHashName().equals(Sha256.hashString("gameSnakeName"))) {
                isPassG = true;
            }
        }
        assertTrue(isPassP && isPassG);
    }
}
