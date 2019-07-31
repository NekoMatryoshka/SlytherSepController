/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.orm;

import java.io.Serializable;
import javax.persistence.*;
import uk.ac.herts.cs.sep.slyther.util.Sha256;

/**
 *
 * @author jw17aca
 */
@Entity(name = "GameSnake")
public class GameSnake implements Serializable {

    private static final long serialVersionUID = 5573832826736049153L;

    @Id
    private int gameSnakeId;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "playerSnakeId")
    private PlayerSnake player;

    @Column(name = "hashName", nullable = true, length = 255)
    private String hashName;

    private String name;

    @Column(name = "snakeLength", nullable = true, length = 10)
    private int snakeLength;

    public int getGameSnakeId() {
        return gameSnakeId;
    }

    public void setGameSnakeId(int gameSnakeId) {
        this.gameSnakeId = gameSnakeId;
    }

    public PlayerSnake getPlayer() {
        return player;
    }

    public void setPlayer(PlayerSnake player) {
        this.player = player;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.hashName = Sha256.hashString(name);
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    @Override
    public String toString() {
        return "GameSnake{" + "name=" + name + ", snakeLength=" + snakeLength + '}';
    }

}
