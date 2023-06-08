package com.school.quiz.view.Theme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import org.jdesktop.swingx.JXButton;

public class ModernButton extends JXButton {
    
    private boolean hover;
    private boolean pressed;
    private Color backgroundColor = new Color(29, 97, 90);
    private Color hoverColor = new Color (57, 121, 114);
    private Color pressedColor = new Color (16, 82, 76);
    private Color foregroundColor = Color.WHITE;
    private int borderRadius = 10;
    private int borderWidth = 0;
    
    public ModernButton(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
        setOpaque(false);
        setForeground(foregroundColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
            g2d.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(backgroundColor);
        }
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        String text = getText();
        int stringWidth = g2d.getFontMetrics().stringWidth(text);
        int x = (getWidth() - stringWidth) / 2;
        int y = (getHeight() - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
        g2d.drawString(text, x, y);
        g2d.dispose();
    }
    
    @Override
    public void setForeground(Color foreground) {
        super.setForeground(foreground);
        this.foregroundColor = foreground;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }
    
    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}
