package presentazione;

import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class ProgrammaView extends JFrame{

    private static final long serialVersionUID = 1L;
    private static JFrame framePannello = new JFrame();



    public static void main(String[] args) {

        JTextField TF_CF=new JTextField(16);
        JTextField TF_pass=new JTextField(32);
        JButton connect = new JButton("Connetti");
        connect.addActionListener(l -> {
            if(DataAccess.connect()) {
                if(DataAccess.verificaDatiImpiegato(TF_CF.getText(),TF_pass.getText())==false)
                    JOptionPane.showMessageDialog(null, "Credenziali errate");


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
        pannello.setLayout(new GridLayout(6,1));
        pannello.setBorder(BorderFactory.createTitledBorder("Accesso"));
        JLabel insCF= new JLabel("Inserire codice fiscale:");
        JLabel insPass= new JLabel(" Inserire password");
        pannello.add(insCF);
        pannello.add(TF_CF);
        pannello.add(insPass);
        pannello.add(TF_pass);
        pannello.add(new JLabel());
        pannello.add(connect);

        framePannello.add(pannello, BorderLayout.CENTER);
        framePannello.add(contenitoreImmagine, BorderLayout.NORTH);

        framePannello.setTitle("Pannello di controllo");
        framePannello.setVisible(true);
        framePannello.setLocationRelativeTo(null);
        framePannello.setSize(400, 400);
        framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
