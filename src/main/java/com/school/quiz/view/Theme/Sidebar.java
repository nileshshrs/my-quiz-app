package com.school.quiz.view.Theme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import org.jdesktop.swingx.JXPanel;

public class Sidebar extends JXPanel implements ActionListener {
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(29, 97, 90);
    private static final Color BUTTON_HOVER_COLOR = new Color(57, 121, 114);
    private static final Color BUTTON_CLICK_COLOR = new Color(16, 82, 76);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int TOP_MARGIN = 40;
    private static final int VERTICAL_GAP = 30; // Adjust the vertical gap as desired

    private ActionListener buttonActionListener;

    public Sidebar() {
        setLayout(new VerticalFlowLayout(VERTICAL_GAP));
    }

    public void addButton(String buttonName) {
        JButton button = createButton(buttonName);
        add(button);
    }

    public void addButtons(String[] buttonNames) {
        for (String buttonName : buttonNames) {
            addButton(buttonName);
        }
    }

    private JButton createButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setPreferredSize(new Dimension(180, 40)); // Adjust the size as needed
        button.setBackground(BUTTON_BACKGROUND_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.addActionListener(this);

        // Create custom button UI
        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setBorderPainted(false);
            }
        });

        final JButton buttonRef = button; // Declare final variable

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonRef.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!buttonRef.getModel().isPressed()) {
                    buttonRef.setBackground(BUTTON_BACKGROUND_COLOR);
                }
            }
        });

        // Add click color
        button.getModel().addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ButtonModel model = (ButtonModel) evt.getSource();
                if (model.isPressed()) {
                    buttonRef.setBackground(BUTTON_CLICK_COLOR);
                } else if (model.isRollover()) {
                    buttonRef.setBackground(BUTTON_HOVER_COLOR);
                } else {
                    buttonRef.setBackground(BUTTON_BACKGROUND_COLOR);
                }
            }
        });

        return button;
    }

    public void setButtonActionListener(ActionListener listener) {
        this.buttonActionListener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Paint the translucent glass effect
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 200), 0, height,
                new Color(255, 255, 255, 50));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonActionListener != null) {
            buttonActionListener.actionPerformed(e);
        }
    }

    private static class VerticalFlowLayout implements java.awt.LayoutManager {
        private final int verticalGap;

        public VerticalFlowLayout(int verticalGap) {
            this.verticalGap = verticalGap;
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                int width = 0;
                int height = 0;
                int componentCount = parent.getComponentCount();

                for (int i = 0; i < componentCount; i++) {
                    Component component = parent.getComponent(i);
                    Dimension componentSize = component.getPreferredSize();
                    width = Math.max(width, componentSize.width);
                    height += componentSize.height + verticalGap;
                }

                Insets insets = parent.getInsets();
                width += insets.left + insets.right;
                height += insets.top + insets.bottom;

                return new Dimension(width, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left + (parent.getWidth() - insets.left - insets.right
                        - parent.getComponent(0).getPreferredSize().width) / 2;
                int y = insets.top + TOP_MARGIN; // Add the TOP_MARGIN to the y position
                int width = parent.getWidth() - insets.left - insets.right;

                int componentCount = parent.getComponentCount();
                for (int i = 0; i < componentCount; i++) {
                    Component component = parent.getComponent(i);
                    Dimension componentSize = component.getPreferredSize();
                    component.setBounds(x, y, componentSize.width, componentSize.height);
                    y += componentSize.height + verticalGap;
                }
            }
        }
    }
}
