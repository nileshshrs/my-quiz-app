package com.school.quiz.view.Theme;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TranslucentCardLayout extends JPanel {
    private float transparency;

    public TranslucentCardLayout(float transparency) {
        this.transparency = transparency;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(transparency));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g2d);
        g2d.dispose();
    }

    public JPanel createCardPanel(String cardName) {
        JPanel cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setBackground(new Color(255, 255, 255, 200));

        // Set a background color with transparency
        Color backgroundColor = new Color(255, 255, 255, 100);
        cardPanel.setBackground(backgroundColor);

        // Create and add components to the card panel
        JLabel cardLabel = new JLabel(cardName);
        cardPanel.add(cardLabel);

        return cardPanel;
    }
}
