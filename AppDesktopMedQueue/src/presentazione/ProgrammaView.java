package presentazione;

import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class ProgrammaView extends JFrame{

    private static final long serialVersionUID = 1L;
    private static JFrame framePannello = new JFrame();

    public static void main(String[] args) {

        JButton connect = new JButton("Connetti");
        connect.addActionListener(l -> {
            if(DataAccess.connect()) {
                JOptionPane.showMessageDialog(null, "Connessione riuscita!");
                @SuppressWarnings("unused")
                ProgrammaView programma = new ProgrammaView();
                framePannello.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Connessione non riuscita.");
            }
        });


        ImageIcon immagine = new ImageIcon("src/image/LogoNoBG.png");
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(250, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);
        JLabel contenitoreImmagine = new JLabel(immagine, JLabel.CENTER);

        JPanel pannello = new JPanel();

        pannello.setLayout(new GridLayout(1,1));
        pannello.setBorder(BorderFactory.createTitledBorder("Accesso"));
        pannello.add(connect);

        framePannello.add(pannello, BorderLayout.CENTER);
        framePannello.add(contenitoreImmagine, BorderLayout.NORTH);

        framePannello.setTitle("Pannello di controllo");
        framePannello.setVisible(true);
        framePannello.setLocationRelativeTo(null);
        framePannello.setSize(300, 350);
        framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
