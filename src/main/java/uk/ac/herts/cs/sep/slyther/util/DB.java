/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.io.PrintWriter;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.gegy1000.slyther.client.game.entity.ClientSnake;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import net.gegy1000.slyther.client.SlytherClient;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;
import net.gegy1000.slyther.client.controller.IController;
import net.gegy1000.slyther.game.entity.Snake;
import uk.ac.herts.cs.sep.slyther.orm.*;

/**
 *
 * @author comqdhb
 */
public class DB {

    public static void insertPlayerSnake(PlayerSnake snake) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uk.ac.herts.cs.sep.SlytherPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(snake);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static List<PlayerSnake> getAllPlayerSnake() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uk.ac.herts.cs.sep.SlytherPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from PlayerSnake p");
        List<PlayerSnake> result = q.getResultList();
        em.close();
        emf.close();
        return result;
    }

    public static List<GameSnake> getAllGameSnake() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uk.ac.herts.cs.sep.SlytherPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select g from GameSnake g");
        List<GameSnake> result = q.getResultList();
        em.close();
        emf.close();
        return result;
    }
}
