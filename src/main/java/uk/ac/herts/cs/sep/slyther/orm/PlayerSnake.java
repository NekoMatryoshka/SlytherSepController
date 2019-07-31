/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.orm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import uk.ac.herts.cs.sep.slyther.util.Sha256;

/**
 *
 * @author jw17aca
 */
@Entity(name = "PlayerSnake")
public class PlayerSnake implements Serializable {

    private static final long serialVersionUID = 5573832826736049153L;

    @Id
    private String playerSnakeId;

    private String name;

    @Column(name = "hashName", nullable = true, length = 255)
    private String hashName;

    @Column(name = "snakeLength", nullable = true, length = 10)
    private int snakeLength;

    @Column(name = "controllerSerialID", nullable = true, length = 255)
    private String controllerSerialID;

    @Column(name = "timeAlive", nullable = true, length = 20)
    private long timeAlive;

    @Column(name = "createdDateTime", nullable = true, length = 255)
    private String createdDateTime;

    @OneToMany(mappedBy = "player", cascade = CascadeType.PERSIST)
    private List<GameSnake> gameSnakes;

    public String getPlayerSnakeId() {
        return playerSnakeId;
    }

    public void setPlayerSnakeId(String playerSnakeId) {
        this.playerSnakeId = playerSnakeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.hashName = Sha256.hashString(name);
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    public String getControllerSerialID() {
        return controllerSerialID;
    }

    public void setControllerSerialID(String controllerSerialID) {
        this.controllerSerialID = controllerSerialID;
    }

    public long getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(long timeAlive) {
        this.timeAlive = timeAlive;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public List<GameSnake> getGameSnakes() {
        return gameSnakes;
    }

    public void setGameSnakes(List<GameSnake> gameSnakes) {
        this.gameSnakes = gameSnakes;
    }

    @Override
    public String toString() {
        return "PlayerSnake{" + "name=" + name + ", snakeLength=" + snakeLength + ", controllerSerialID=" + controllerSerialID + ", timeAlive=" + timeAlive + ", createdDateTime=" + createdDateTime + '}';
    }

}
