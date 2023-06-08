package com.school.quiz.view.Theme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXButton;

public class TransparentButton extends JXButton {

    private Color foregroundColor = Color.WHITE;

    public TransparentButton(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
        setOpaque(false);
        setForeground(foregroundColor);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        FontMetrics metrics = g2d.getFontMetrics();
        String text = getText();
        int stringWidth = metrics.stringWidth(text);
        int x = (getWidth() - stringWidth) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        g2d.drawString(text, x, y);
        g2d.drawLine(x, y + 1, x + stringWidth, y + 1);
        g2d.dispose();
    }

    @Override
    public void setForeground(Color foreground) {
        super.setForeground(foreground);
        this.foregroundColor = foreground;
    }

}
