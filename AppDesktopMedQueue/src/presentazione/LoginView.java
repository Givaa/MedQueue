package presentazione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.ImpiegatoBean;
import persistence.DataAccess;
import persistence.DriverManagerConnectionPool;

/**
 * Classe per generare un frame dove e possibile inserire le proprie credenziali ed eseguire un login
 */
public class LoginView {

  // Componenti della view
  private static final JFrame framePannello = new JFrame();
  private final JTextField codiceFiscale = new JTextField(16);
  private final JPasswordField password = new JPasswordField(32);
  private final JButton connect = new JButton("Connetti");
  private final JLabel errore = new JLabel();
  private ImageIcon immagine = new ImageIcon("src/image/LogoNoBG.png");

  /**
   * Inizializzo un nuovo oggetto LoginView che crea un frame di login
   */
  @SuppressWarnings("checkstyle:Indentation")
  public LoginView() {
    // Settaggi frame
    framePannello.setTitle("Pannello di controllo");
    framePannello.setLocationRelativeTo(null);
    framePannello.setSize(400, 400);
    framePannello.setResizable(false);
    framePannello.setLocationRelativeTo(null);
    framePannello.getContentPane().setBackground(Color.white);
    framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");
    framePannello.setIconImage(infermiera.getImage());

    // Settaggi componenti frame
    errore.setForeground(Color.red); // Setto il colore del testo a rosso
    errore.setHorizontalAlignment(JLabel.CENTER); // Centro il testo nella JLabel

    connect.addActionListener(
        l -> { // Action Listener sul bottone di connessione
          if (DriverManagerConnectionPool.createDbConnection() == null) {
            errore.setText("Errore nella connessione");
          } else {
            ImpiegatoBean impiegato=DataAccess.getImpiegato(codiceFiscale.getText());
            if (impiegato.getPassword().equals(password.getText())) { // Verifico le credenziali dell'impiegato
              new AccettazionePrenotazioneView(impiegato)
                  .visible(true);
              codiceFiscale.setText("");
              password.setText("");
              errore.setText("");
              framePannello.setVisible(false);
            } else { // Creo la prossima view e la rendo visibile
              errore.setText("Credenziali errate");
            } // Messaggio d'errore nel caso in cui le credenziali del
            // impiegato sono sbagliate
          }
        });

    JCheckBox select = new JCheckBox("Mostra password");
    select.setBackground(Color.white); // setto il background della checkbox a bianco
      // Listener sulla checkbox
      select.addItemListener(
              e -> {
                if (!(e.getStateChange()
                    == ItemEvent.SELECTED)) { // Se la checkbox non e attiva nascondo la password
                  password.setEchoChar('•');
                } else {
                  password.setEchoChar((char) 0); // Se la checkbox e attiva mostro la password
                }
              });

    password.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
              connect.doClick();
            }
          }
        });

    // Scalo le dimensioni dell'immagine medqueue
    Image image = immagine.getImage();
    Image newimg =
        image.getScaledInstance(250, 180, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    immagine = new ImageIcon(newimg);

    JPanel pannelloaccesso = new JPanel();
    pannelloaccesso.setLayout(new GridLayout(7, 1));
    pannelloaccesso.setBackground(Color.white);
    pannelloaccesso.setBorder(BorderFactory.createTitledBorder("Accesso"));
    JLabel insCf = new JLabel("Inserisci il codice fiscale: ");
    pannelloaccesso.add(insCf);
    pannelloaccesso.add(codiceFiscale);
    JLabel insPass = new JLabel("Inserisci la password: ");
    pannelloaccesso.add(insPass);
    pannelloaccesso.add(password);
    pannelloaccesso.add(select);
    pannelloaccesso.add(errore);
    pannelloaccesso.add(connect);

    framePannello.add(pannelloaccesso, BorderLayout.CENTER);
    JLabel contenitoreImmagine = new JLabel(immagine, JLabel.CENTER);
    framePannello.add(contenitoreImmagine, BorderLayout.NORTH);
    framePannello.setVisible(true);
  }

  public static void main(String[] args) {
    new LoginView();
  }
}
