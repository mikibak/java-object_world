package com.company.GUI.Components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Simple hexagonal button class.
 *
 * @author Kristian Johansen
 *
 */
public class HexagonalButton extends JButton {

    /**
     * autoGenerated serial-version.
     */
    private static final long serialVersionUID = -7142502695252118612L;
    Polygon hexagonalShape;
    String imagePath;


    public HexagonalButton() {
        this.setOpaque(false);
        hexagonalShape = getHexPolygon();
    }
    public HexagonalButton(String imagePath) {
        this.imagePath = imagePath;
        this.setOpaque(false);
        hexagonalShape = getHexPolygon();
    }

    /**
     * Generates the buttons Hexagonal shape
     *
     * @return Polygon with the buttons hexagonal shape.
     */
    private Polygon getHexPolygon() {
        Polygon hex = new Polygon();
        int w = getWidth() - 1;
        int h = getHeight() - 1;
        int ratio = (int) (h * .25);

        hex.addPoint(w / 2, 0);
        hex.addPoint(w, ratio);
        hex.addPoint(w, h - ratio);
        hex.addPoint(w / 2, h);
        hex.addPoint(0, h - ratio);
        hex.addPoint(0, ratio);

        return hex;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#contains(java.awt.Point)
     */
    @Override
    public boolean contains(Point p) {
        return hexagonalShape.contains(p);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.JComponent#contains(int, int)
     */
    @Override
    public boolean contains(int x, int y) {
        return hexagonalShape.contains(x, y);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#setSize(java.awt.Dimension)
     */
    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        hexagonalShape = getHexPolygon();
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#setSize(int, int)
     */
    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);
        hexagonalShape = getHexPolygon();
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#setBounds(int, int, int, int)
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        hexagonalShape = getHexPolygon();
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#setBounds(java.awt.Rectangle)
     */
    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        hexagonalShape = getHexPolygon();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.JComponent#processMouseEvent(java.awt.event.MouseEvent)
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (contains(e.getPoint()))
            super.processMouseEvent(e);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        if(imagePath == null) {
            g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getBackground());
            g.drawPolygon(hexagonalShape);
            g.fillPolygon(hexagonalShape);
        }
        if(imagePath != null) {
            //TODO could clear up this shit
            try {
                BufferedImage img = ImageIO.read(new File(imagePath));
                g.drawImage(img, 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return true;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawPolygon(hexagonalShape);
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.AbstractButton#paintBorder(java.awt.Graphics)
     */
    @Override
    protected void paintBorder(Graphics g) {
        // Does not print border
    }

}