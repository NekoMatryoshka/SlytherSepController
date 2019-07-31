/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.gui;

/**
 *
 * @author Neko Matryoshka
 */
public class SnakeDiesEvent {
    private ListenerClient client;
    
    public SnakeDiesEvent(ListenerClient client) {
        super();
        this.client = client;
    }

    public SnakeDiesEvent() {
        super();
    }

    public ListenerClient getClient() {
        return client;
    }

    public void setClient(ListenerClient client) {
        this.client = client;
    }
}
