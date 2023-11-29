package view;

import javax.swing.*;

public class view_start extends JFrame
{
    public view_start() {
        super( "My App" );
        setSize( 400, 500 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel texte = new JLabel("Mon texte");
        add( texte );
        JLabel texte2 = new JLabel("Mon autre texte");
        add( texte2 );

        setVisible( true );
    }
}
