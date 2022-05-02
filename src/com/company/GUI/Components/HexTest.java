package com.company.GUI.Components;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Simple test class for HexagonalLayout.
 *
 * @author Kristian Johansen
 *
 */
public class HexTest {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Change insets and columns here.
        //The flag indicates wether or not to begin with a small row.
        //
        //Rows are calculated automaticaly, based on number of columns/elements
        f.setLayout(new HexagonalLayout(6, new Insets(5, 5, 5, 5), false));

        for (int i = 0; i < 44; i++) { // Change the number in the loop to get
            // more/less buttons

            HexagonalButton b = new HexagonalButton();
            b.setBackground(Color.blue);

            //"Random" color actionlistener, just for fun.
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton a = (JButton) e.getSource();
                    a.setBackground(Color.getHSBColor(
                            (float) Math.random() * 10000, (float) Math
                                    .random(), (float) Math.random()));
                }

            });
            f.add(b);
        }

        f.setVisible(true);

    }
}