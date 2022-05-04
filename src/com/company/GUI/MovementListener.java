package com.company.GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementListener extends JFrame implements KeyListener {
    private KeyEvent lastKeyEvent;

    MovementListener(String name) {
        super(name);
        this.setFocusable(true);
    }
    public void keyPressed(KeyEvent e)
    {
        lastKeyEvent = e;
    }
    // The methods keyReleased(..) and KeyTyped(..) inherited // from KeyListener interface // must be defined, but we can ignore them if we choose
    public void keyReleased(KeyEvent e) {
        lastKeyEvent = e;
    }
    public void keyTyped(KeyEvent e) {
        lastKeyEvent = e;
    }
    public KeyEvent getLastKeyEvent() {
        return lastKeyEvent;
    }
}
