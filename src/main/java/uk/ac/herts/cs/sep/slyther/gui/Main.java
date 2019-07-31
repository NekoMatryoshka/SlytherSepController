/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.gui;

import java.lang.reflect.*;
import javax.swing.UIManager;
import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.client.controller.Controller;
import uk.ac.herts.cs.sep.slyther.gui.GameStatsGui;
import uk.ac.herts.cs.sep.slyther.util.DB;
import uk.ac.herts.cs.sep.slyther.util.Subsumption;

/**
 *
 * @author Neko Matryoshka
 */
public class Main extends net.gegy1000.slyther.client.ClientMain {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        loadNatives();
        ListenerClient client = new ListenerClient();
        Subsumption sub = new Subsumption();
        client.setController(sub);
        GameStatsGui statsScreen = new GameStatsGui();
        statsScreen.setClient(client);
        statsScreen.setVisible(true);
        client.run();
    }
}
