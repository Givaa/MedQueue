package presentazione;

import persistence.DataAccess;
import persistence.DriverManagerConnectionPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

public class LoginView{

    private static final long serialVersionUID = 1L;
    //Componenti della view
    private static JFrame framePannello = new JFrame();
    private JTextField TF_CF = new JTextField(16);
    private JPasswordField TF_pass = new JPasswordField(32);
    private JButton connect = new JButton("Connetti");
    private JLabel errore = new JLabel();
    private JLabel insCF = new JLabel("Inserire codice fiscale: ");
    private JLabel insPass = new JLabel(" Inserire password: ");
    private JCheckBox select = new JCheckBox("Mostra password");
    private ImageIcon immagine = new ImageIcon("src/image/LogoNoBG.png");
    private ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");


    public LoginView(){
        //Settaggi frame
        framePannello.setTitle("Pannello di controllo");
        framePannello.setLocationRelativeTo(null);
        framePannello.setSize(400, 400);
        framePannello.setResizable(false);
        framePannello.setLocationRelativeTo(null);
        framePannello.getContentPane().setBackground( Color.white );
        framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePannello.setIconImage(infermiera.getImage());

        //Settaggi componenti frame
        errore.setForeground(Color.red); //Setto il colore del testo a rosso
        errore.setHorizontalAlignment(JLabel.CENTER); //Centro il testo nella JLabel


        connect.addActionListener(l -> { //Action Listener sul bottone di connessione
            if(DriverManagerConnectionPool.createDBConnection() == null) { //Creo la connessione con il db se non riesco stampo un messaggio di errore
                errore.setText("Errore nella connessione");
            } else {
                if(DataAccess.verificaDatiImpiegato(TF_CF.getText(), TF_pass.getText()))  //Verifico le credenziali dell'impiegato
                    new SelectQueueView(framePannello).visible(true); //Creo la prossima view e la rendo visibile
                else errore.setText("Credenziali errate"); //Messaggio d'errore nel caso in cui le credenziali del impiegato sono sbaglaite
            }
        });

        select.setBackground(Color.white); //setto il background della checkbox a bianco
        select.addItemListener(new ItemListener() { //Listener sulla checkbox
            public void itemStateChanged(ItemEvent e) {
                if (!(e.getStateChange() == ItemEvent.SELECTED)) { //Se la checkbox non e attiva nascondo la password
                    TF_pass.setEchoChar('•');
                } else {
                    TF_pass.setEchoChar((char) 0); //Se la checkbox e attiva mostro la password
                }
            }
        });

        TF_pass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER)
                    connect.doClick();
            }
        });


        //Scalo le dimensioni dell'immagine medqueue
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(250, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);


        JLabel contenitoreImmagine = new JLabel(immagine, JLabel.CENTER);

        JPanel pannelloaccesso = new JPanel();
        pannelloaccesso.setLayout(new GridLayout(7,1));
        pannelloaccesso.setBackground( Color.white );
        pannelloaccesso.setBorder(BorderFactory.createTitledBorder("Accesso"));
        pannelloaccesso.add(insCF);
        pannelloaccesso.add(TF_CF);
        pannelloaccesso.add(insPass);
        pannelloaccesso.add(TF_pass);
        pannelloaccesso.add(select);
        pannelloaccesso.add(errore);
        pannelloaccesso.add(connect);



        framePannello.add(pannelloaccesso, BorderLayout.CENTER);
        framePannello.add(contenitoreImmagine, BorderLayout.NORTH);
        framePannello.setVisible(true);

    }


    public static void main(String[] args) {
        new LoginView();
    }

}
