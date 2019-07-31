package uk.ac.herts.cs.sep.slyther.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.controller.IController;
import net.gegy1000.slyther.game.entity.Snake;
import uk.ac.herts.cs.sep.slyther.orm.*;
import uk.ac.herts.cs.sep.slyther.util.Subsumption;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Neko Matryoshka
 */
public class ListenerClient extends SlytherClient {

    private SnakeDiesEventListener listener;
    public PlayerSnake playerSnake = null;
    private int oldPlayerID = -1;
    private int deadPlayerID = -1;

    public void registerSnakeDiesEventListener(SnakeDiesEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void update() {
        super.update();
        //initialize when first snake spawns
        if (player != null && playerSnake == null && player.dead == false) {
            oldPlayerID = player.id;
            playerSnake = new PlayerSnake();
            playerSnake.setControllerSerialID(getControllerSerialID(this));
            playerSnake.setCreatedDateTime(LocalDateTime.now().toString());
            playerSnake.setName(player.name);
            playerSnake.setPlayerSnakeId(LocalDateTime.now().toString() + player.id);
            System.out.println("initialize when first snake spawns: " + playerSnake.getCreatedDateTime());
        }
        //new snake spawns after old snake dies
        if (player != null && player.id != oldPlayerID && playerSnake != null && player.dead == false) {
            oldPlayerID = player.id;
            playerSnake = new PlayerSnake();
            playerSnake.setControllerSerialID(getControllerSerialID(this));
            playerSnake.setCreatedDateTime(LocalDateTime.now().toString());
            playerSnake.setName(player.name);
            playerSnake.setPlayerSnakeId(LocalDateTime.now().toString() + player.id);
            System.out.println("new snake spawns after old snake dies: " + playerSnake.getCreatedDateTime());
        }
        //snake dies
        if (player != null && player.dead == true && deadPlayerID != player.id) {
            deadPlayerID = player.id;
            LocalDateTime spawningTime = LocalDateTime.parse(playerSnake.getCreatedDateTime());
            LocalDateTime deadTime = LocalDateTime.now();
            long duration = java.time.Duration.between(spawningTime, deadTime).toNanos()/1000000000;
            playerSnake.setTimeAlive(duration);
            System.out.println("update when snake dies : " + " spawn: " + spawningTime.toString() + " dead: " + deadTime.toString() + " duration:" + duration);
            playerSnake.setSnakeLength(player.getLength());

            List<GameSnake> gameSnakes = new ArrayList<>();
            for (Snake<?> snake : this.getSnakes()) {
                if (snake != player) {
                    GameSnake gameSnake = new GameSnake();
                    gameSnake.setName(snake.name);
                    gameSnake.setPlayer(playerSnake);
                    gameSnake.setSnakeLength(snake.getLength());
                    gameSnake.setGameSnakeId((int) (System.currentTimeMillis() + snake.id));
                    gameSnakes.add(gameSnake);
                }
            }
            playerSnake.setGameSnakes(gameSnakes);
            System.out.println("update when snake dies: " + playerSnake.toString());
            listener.snakeDies(new SnakeDiesEvent(this));
        }
    }

    private static String getControllerSerialID(SlytherClient client) {
        String controllerSerialID = "";
        System.out.println("begin to get controller serial id");
        try {
            Field controllerField = getDeclaredField(client, "controller");
            controllerField.setAccessible(true);
            Subsumption controller = (Subsumption) controllerField.get(client);
            Field controllerSerialIDField = getDeclaredField(controller, "serialVersionUID");
            controllerSerialIDField.setAccessible(true);
            controllerSerialID = controllerSerialIDField.get(controller).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controllerSerialID;
    }

    private static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
