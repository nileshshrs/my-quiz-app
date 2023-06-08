package com.school.quiz.view.Theme;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jdesktop.swingx.JXPanel;





public class GlassPanel extends JXPanel {

    private float transparency = 0.4f;

    public GlassPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Set the transparency
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));

        // Paint the translucent background
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, width, height);

        // Paint the panel
        super.paintComponent(g2d);

        g2d.dispose();
    }

}
