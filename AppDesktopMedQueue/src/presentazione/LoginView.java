package presentazione;

import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame{

    private static final long serialVersionUID = 1L;
    private static JFrame framePannello = new JFrame();



    public static void main(String[] args) {


        JTextField TF_CF = new JTextField(16);
        JPasswordField TF_pass = new JPasswordField(32);
        JButton connect = new JButton("Connetti");
        JLabel errore=new JLabel();
        errore.setForeground(Color.red); //Setto il colore del testo a rosso
        errore.setHorizontalAlignment(JLabel.CENTER); //Centro il testo nella JLabel
        JLabel insCF= new JLabel("Inserire codice fiscale:");
        JLabel insPass= new JLabel(" Inserire password");

        connect.addActionListener(l -> {
            if(DataAccess.connect()) {
                if(DataAccess.verificaDatiImpiegato(TF_CF.getText(),TF_pass.getText())) {
                    new SelectQueueView().visible(true);
                    framePannello.setVisible(false);
                }
                else
                    errore.setText("Credenziali errate");
            } else {
               errore.setText("Errore nella connessione");
            }
        });

        JCheckBox select = new JCheckBox("Show password");
        select.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (!(e.getStateChange() == ItemEvent.SELECTED)) {
                    TF_pass.setEchoChar('*');
                } else {
                    TF_pass.setEchoChar((char) 0);
                }
            }
        });

        TF_pass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER)
                    connect.doClick();
            }
        });

        ImageIcon immagine = new ImageIcon("src/image/LogoNoBG.png");
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(250, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);
        JLabel contenitoreImmagine = new JLabel(immagine, JLabel.CENTER);

        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(7,1));
        pannello.setBackground( Color.white );
        pannello.setBorder(BorderFactory.createTitledBorder("Accesso"));
        pannello.add(insCF);
        pannello.add(TF_CF);
        pannello.add(insPass);
        pannello.add(TF_pass);
        pannello.add(select);
        pannello.add(errore);
        pannello.add(connect);

        framePannello.add(pannello, BorderLayout.CENTER);
        framePannello.add(contenitoreImmagine, BorderLayout.NORTH);

        framePannello.setTitle("Pannello di controllo");
        framePannello.setVisible(true);
        framePannello.setLocationRelativeTo(null);
        framePannello.setSize(400, 400);
        framePannello.setLocationRelativeTo(null);
        framePannello.getContentPane().setBackground( Color.white );
        framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
