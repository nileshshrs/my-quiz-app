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
import javax.swing.JTextField;

public class ModernTextField extends JTextField {
    private String placeholder;
    private int paddingLeft = 9;
    private Color glassBorderColor;
    private Color foregroundColor;
    private Color caretColor;
    private Color placeholderColor;
    

    public ModernTextField(String placeholder, Color glassBorderColor, Color foregroundColor, Color caretColor,
            Color placeholderColor) {
                
        this.placeholder = placeholder;
        this.glassBorderColor = glassBorderColor;
        this.foregroundColor = foregroundColor;
        this.caretColor = caretColor;
        this.placeholderColor = placeholderColor;

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, glassBorderColor));
        setForeground(foregroundColor);
        setCaretColor(caretColor);
        setFont(new Font("Arial", Font.BOLD, 16));
        setOpaque(false);
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
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.BOLD, 16f));
            g2.drawString(placeholder, paddingLeft, getHeight() / 2 + 5);
            g2.dispose();
        }
    }
}
