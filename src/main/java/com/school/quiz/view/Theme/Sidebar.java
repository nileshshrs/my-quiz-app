package com.school.quiz.view.Theme;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sidebar extends JPanel implements ActionListener {
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(29, 97, 90);
    private static final Color BUTTON_HOVER_COLOR = new Color(57, 121, 114);
    private static final Color BUTTON_CLICK_COLOR = new Color(16, 82, 76);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int TOP_MARGIN = 20;
    private static final int VERTICAL_GAP = 30;

    private JLabel titleLabel;
    private JPanel buttonPanel;
    private ActionListener buttonActionListener;

    public Sidebar() {
        this(TOP_MARGIN, TOP_MARGIN);
    }

    public Sidebar(int topMargin, int bottomMargin) {
        setLayout(new VerticalFlowLayout(VERTICAL_GAP, topMargin, bottomMargin));
        addTitle("Dashboard", "/com/school/quiz/assets/graduation hat.png");
        createButtonPanel();
    }

    private void addTitle(String title, String imagePath) {
        JPanel titlePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();
    
                // Set translucent glass effect
                g2d.setColor(new Color(255, 255, 255, 0));
                g2d.fillRect(0, 0, width, height);
    
                // Draw gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 0), 0, height,
                        new Color(255, 255, 255, 50));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
    
                g2d.dispose();
            }
        };
        
    
        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource(imagePath);
            BufferedImage image = ImageIO.read(imageUrl);
            Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(icon) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int width = getWidth();
                    int height = getHeight();
    
                    // Set translucent glass effect
                    g2d.setColor(new Color(255, 255, 255, 0));
                    g2d.fillRect(0, 0, width, height);
    
                    // Draw gradient background
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255, 0), 0, height,
                            new Color(255, 255, 255, 50));
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, width, height);
    
                    g2d.dispose();
                }
            };
            imageLabel.setBounds(650, 45, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        if (icon != null) {
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setOpaque(false); // Set imageLabel background color as transparent
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titlePanel.add(imageLabel, BorderLayout.NORTH);
        }
    
        titleLabel = new JLabel(title);
        titleLabel.setPreferredSize(new Dimension(180, 40));
        titleLabel.setBackground(new Color(0, 0, 0, 0)); // Set titleLabel background color as transparent
        titleLabel.setForeground(new Color(29, 97, 90));
        titleLabel.setFont(BUTTON_FONT.deriveFont(Font.BOLD));
        titleLabel.setFont(BUTTON_FONT);
        titleLabel.setOpaque(false); // Set titleLabel background color as transparent
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    
        add(titlePanel);
    }
    

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        int margin = VERTICAL_GAP * 2;
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(margin, 0, 0, 0));
        buttonPanel.setLayout(new VerticalFlowLayout(VERTICAL_GAP, 0, 0));
        buttonPanel.setOpaque(false);
        add(buttonPanel);
    }

    public void setTitle(String title) {
        if (titleLabel != null) {
            titleLabel.setText(title);
        }
    }

    public void addButton(String buttonName) {
        JButton button = createButton(buttonName);
        buttonPanel.add(button);
        revalidate();
        repaint();
    }

    public void addButtons(String[] buttonNames) {
        for (String buttonName : buttonNames) {
            addButton(buttonName);
        }
    }

    private JButton createButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setPreferredSize(new Dimension(180, 40));
        button.setBackground(BUTTON_BACKGROUND_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.addActionListener(this);
        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setBorderPainted(false);
            }
        });

        final JButton buttonRef = button;
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

    private static class VerticalFlowLayout implements LayoutManager {
        private final int verticalGap;
        private final int topMargin;
        private final int bottomMargin;

        public VerticalFlowLayout(int verticalGap, int topMargin, int bottomMargin) {
            this.verticalGap = verticalGap;
            this.topMargin = topMargin;
            this.bottomMargin = bottomMargin;
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
                height += insets.top + insets.bottom + topMargin + bottomMargin;

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
                int y = insets.top + topMargin;
                int width = parent.getWidth() - insets.left - insets.right;

                int containerHeight = parent.getHeight() - insets.top - insets.bottom - topMargin - bottomMargin;
                int remainingHeight = containerHeight - y;

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
