package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MastermindGameDisplay extends JFrame {
    public MastermindGameDisplay() {
        super("Mastermind");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        int PawnNumber=3;
        int AttemptNumber=5;
        int CombinationNumber=6;
        for(int i=0;i<AttemptNumber;i++){
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1,CombinationNumber,0,0));
            for(int j=0;j<CombinationNumber;j++){
                JLabel label = new CircleLabel();
                label.setPreferredSize(new Dimension(50, 50));
                label.setOpaque(true);
                rowPanel.add(label);
            }
            if(i!=0) {
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
                JSeparator separator = new JSeparator();
                separator.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(separator);
            }
            panel.add(Box.createRigidArea(new Dimension(0,10)));
            panel.add(rowPanel);
        }
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        setContentPane(panel);
        setVisible(true);
        pack();

    }
        /*super("Mastermind");
        JPanel panel = new JPanel(new GridLayout(0, 9, 0, 50));
        for (int i = 0; i < 27; i++) {
            JLabel label = new CircleLabel();
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setPreferredSize(new Dimension(50, 50));
            label.setOpaque(true);
            label.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.BLACK));
            panel.add(label);
        }
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Button " + (i + 1));
            buttonPanel.add(button);
        }
        buttonPanel.add(new JSeparator());

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }*/
    private static class CircleLabel extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(1, 1, getWidth() - 3, getHeight() - 3);
            g2d.dispose();
        }
    }

}
