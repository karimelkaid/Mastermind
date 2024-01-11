import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

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
                JLabel Circlelabel = new CircleLabel();
                Circlelabel.setPreferredSize(new Dimension(50, 50));
                Circlelabel.setOpaque(true);
                Circlelabel.setBackground(new Color(0, 51, 102));
                Circlelabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Circlelabel.getBackground().equals(Color.RED)){
                            Circlelabel.setBackground(Color.BLUE);
                        }
                        else if (Circlelabel.getBackground().equals(Color.BLUE)){
                            Circlelabel.setBackground(Color.GREEN);
                        }
                        else if (Circlelabel.getBackground().equals(Color.GREEN)){
                            Circlelabel.setBackground(Color.YELLOW);
                        }
                        else{
                            Circlelabel.setBackground(Color.RED);
                        }
                        Circlelabel.repaint();
                    }
                });
                rowPanel.add(Circlelabel);
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
        //Creation des indices
        JPanel indicepanel= new JPanel(new FlowLayout());
        JLabel indicenoir=new JLabel("Indice Noir : 0");
        JLabel indiceblanc=new JLabel("Indice Blanc : 0");
        //Ajout au panel
        indicepanel.add(indicenoir);
        indicepanel.add(indiceblanc);
        //Seperateurs et ajout panel principal
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(indicepanel);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        /*ImageIcon logoIcon = new ImageIcon("src/view/images/game_logo2_sf.png");
        JLabel lblLogo = new JLabel(logoIcon);
        lblLogo.setPreferredSize(new Dimension(500, 350));
        panel.add(lblLogo);*/
        panel.setBackground(new Color(0, 51, 102));
        setContentPane(panel);
        setVisible(true);
        pack();

    }
    /*public static String getColorName(Color color) {
        Map<Color, String> colorNames = new HashMap<>();
        for(PawnColor value:PawnColor.values()){
            colorNames.put((Color) Color.class.getField(value.toString()).get(null));
        }
        colorNames.put(Color.RED, "red");
        colorNames.put(Color.GREEN, "green");
        colorNames.put(Color.BLUE, "blue");
        return colorNames.get(color);
    }*/
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
    public static class CircleLabel extends JLabel {
            private Color color = Color.WHITE;

            public void setColor(Color color) {
            this.color = color;
            repaint();
            }
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
