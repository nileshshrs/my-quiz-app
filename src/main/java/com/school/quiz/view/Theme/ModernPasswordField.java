package com.school.quiz.view.Theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public class ModernPasswordField extends JPasswordField {
    private String placeholder;
    private int paddingLeft = 9;

    public ModernPasswordField(String placeholder) {
        this.placeholder = placeholder;
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        setForeground(Color.WHITE);
        setOpaque(false);
        setCaretColor(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setFocusable(true);
                requestFocusInWindow();
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setOpaque(false);
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setOpaque(false);
                repaint();
                setFocusable(false);
            }
        });
    }

    @Override
    public Insets getInsets() {
        Insets insets = super.getInsets();
        insets.left += paddingLeft;
        return insets;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE); // change placeholder text color to white
            g2.setFont(getFont().deriveFont(Font.BOLD, 16f)); // change placeholder text size to 16 and weight to bold
            g2.drawString(placeholder, paddingLeft, getHeight() / 2 + 5);
            g2.dispose();
        }
    }
}
