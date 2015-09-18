
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Classe dedicata al Menu.
 * @author Paolo D'Arienzo VR376656, Serena Cavaletti VR364691
 */
public class BarMenu extends MenuBar {

	//TODO
	//sistemare la javadoc
	private static final long serialVersionUID = -2397370419829851151L;

	private final Menu help = new Menu("Help");

    private final MenuItem about = new MenuItem("About");
    private final MenuItem rules = new MenuItem("Rules");


    public BarMenu() {
        addActionALL();
        help.add(rules);
        help.add(about);
        
        add(help);
    }

    private void addActionALL() {

        rules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "L'obbiettivo del giocatore e' quello di battere l'avversario dando scacco \n"
                		+ "matto al suo Re. Questo accade quando il Re nemico si trova sotto scacco, \n"
                		+ "cioe' sotto il tiro di uno o piu' pezzi del giocatore, e non gli e' possibile \n"
                		+ "spostarsi in altre caselle, perche' bloccate o anch'esse sotto scacco, o bloccare \n"
                		+ "lo scacco in altra maniera, come per esempio mangiando il pezzo che sta dando \n"
                		+ "scacco con un qualunque pezzo del proprio schieramento o interponendo uno di \n"
                		+ "essi lungo la linea del pezzo che gli sta dando scacco. \n", "Rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"                     Created by \n Paolo D'Arienzo & Serena Cavaletti", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}