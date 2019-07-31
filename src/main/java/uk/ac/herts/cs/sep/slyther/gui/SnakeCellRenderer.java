/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.gui;

import java.awt.Component;
import javax.swing.*;
import uk.ac.herts.cs.sep.slyther.orm.PlayerSnake;

/**
 *
 * @author jw17aca
 */
public class SnakeCellRenderer extends JLabel implements ListCellRenderer{
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        return this;
    }
     
}
