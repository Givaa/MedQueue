package presentazione;

import business.Connessione;
import business.ConnessioneInterface;
import business.Gestione;
import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.GestioneInterface;
import eccezioni.InvalidManagementException;
import persistence.DataAccess;
import persistence.DriverManagerConnectionPool;

/**
 * Classe che genera un frame che permette di selezionare la coda di prenotazioni da gestire,
 * accettare e servire una prenotazione.
 */
public class ControlPanelView implements ControlPanelInterface {

  private GestioneInterface gestione=new Gestione();
  private ConnessioneInterface connessione=new Connessione();
  private LoginInterface loginView;
  private ShowPrenotazioneInterface prentazioneAccettata;
  private final JFrame frame = new JFrame();
  private final JPanel pannelloNord = new JPanel();
  private final JPanel pannelloCentro = new JPanel();
  private final ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");
  private final JLabel impiegato = new JLabel();
  private final JButton logout = new JButton("Logout");
  private final JLabel jl = new JLabel("Scegli l'operazione da gestire: ");
  private JFrame showPrenotazione;
  private ArrayList<OperazioneBean> listoperazioni;
  private int idStruttura;
  private ImageIcon immagine = new ImageIcon("src/image/frameIcon.png");
  private int idOperazione;
  private boolean servizioPrenotazione =
      false; // Variabile booleana utilizzata per controllare se l'impiegato sta servendo una
  // prenotazione


  public ControlPanelView() { }

  /**
   * Metodo che genera il pannello di controllo che permettera all'impiegato di visualizzare
   * le code che potra gestire, il numero di prenotazioni per coda da servire e accettare una
   * prenotazione.
   *
   * @param imp impiegato che si è loggato
   * @param con connessione al database
   */
  @Override
  public void showControlPanel(ImpiegatoBean imp, Connection con) {
    idStruttura =
            imp.getIdStruttura(); // Salvo la struttura in cui lavora l'impiegato per poter ottenere le
    // prenotazioni sono di quella struttura
    listoperazioni =
            gestione.getListaOperazioni(); // Salvo le operazioni che potra gestire l'impiegato

    // Settaggi frame
    frame.setTitle("MedQueue");
    frame.setSize(1000, 600);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(Color.white);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Posiziona il pannello al centro dello schermo
    frame.setIconImage(infermiera.getImage());

    // --------Pannello Nord (logo,nome impiegato, logout)--------
    pannelloNord.setLayout(
            new BoxLayout(
                    pannelloNord, BoxLayout.X_AXIS)); // BoxLayout che posiziona gli elementi sull'asse X
    pannelloNord.setOpaque(false); // Nascondo lo sfondo del JPanel
    // Operazioni per ridimensionare l'immagine
    Image image = immagine.getImage(); // prendo l'immagine dall'ImagIcond
    Image newimg =
            image.getScaledInstance(
                    70, 50, java.awt.Image.SCALE_SMOOTH); // Scalo l'immagine in base a width e height
    immagine = new ImageIcon(newimg); // ricreo l'ImageIcon

    pannelloNord.add(
            Box.createRigidArea(
                    new Dimension(
                            30, 0))); // Spazio vuoto che viene utilizzato per distanziare il logo dal bordo del
    // frame
    pannelloNord.add(new JLabel(immagine)); // Inserimento del logo

    // Creo il testo che conterra il nome dell'impiegato che ha eseguito l'accesso all'app
    impiegato.setText(
            imp.getNome().substring(0, 1).toUpperCase()
                    + imp.getNome().substring(1).toLowerCase()
                    + " "
                    + imp.getCognome().substring(0, 1).toUpperCase()
                    + imp.getCognome().substring(1).toLowerCase());
    impiegato.setFont(new Font(impiegato.getFont().getName(), impiegato.getFont().getStyle(), 30));
    pannelloNord.add(
            Box.createRigidArea(
                    new Dimension(
                            280,
                            0))); // Spazio vuoto che viene utilizzato per distanziare il nome dell'impiegato
    // dal logo
    pannelloNord.add(impiegato); // Aggiungo il nome dell'impigato al pannello
    pannelloNord.add(
            Box.createRigidArea(
                    new Dimension(
                            250,
                            0))); // Spazio vuoto che viene utilizzato per distanziare il pulsante di logout dal
    // nome dell'impiegato
    pannelloNord.add(logout); // Aggiungo il pulsante di logout
    // ActionListener sul pulsante di logout
    logout.addActionListener(
            e -> {
              // eseguo la disconnessione dal database
              connessione.disconnect(con);
              loginView=new LoginView();
              loginView.showLoginView();
              frame.dispose(); // Chiudo il frame
            });
    // --------------------Fine Pannello Nord---------------------

    /*--------------------Pannnello Centrale----------------------
    Conterra 2 JPanel:
    1. JPanel che conterra dei button per poter selezionare la coda da gestire
    2. JPanel che permette l'accettazione di una prenotazione oppure
       JPanel che mostra le informazioni sulla prenotazione accettata
     */
    pannelloCentro.setLayout(
            new BoxLayout(
                    pannelloCentro, BoxLayout.X_AXIS)); // BoxLayout che posiziona gli elementi sull'asse X
    pannelloCentro.add(
            pannelloCoda()); // Aggiungo al pannello centrale il primo JPanel che conterra i button,
    // viene generato nel metodo pannelloCoda()
    pannelloCentro.add(
            Box.createRigidArea(
                    new Dimension(
                            10, 0))); // Spazio vuoto che viene utilizzato per distanziare i 2 JPanel del
    // pannelloCentro
    pannelloCentro.setOpaque(false); // Nascondo lo sfondo del JPanel
    // ------------------Fine Pannello Centrale--------------------

    // Aggiungo al frame i 2 pannelli Nord e Centro
    frame.add(pannelloNord, BorderLayout.NORTH);
    frame.add(pannelloCentro, BorderLayout.CENTER);
    frame.setVisible(true);

  }

  /**
   * Metodo che genera un pannello che ci permette di scegliere la coda da gestire
   * e mostra per ogni coda il numero di prenotazioni da accettare.
   *
   * @return pannello con le code da gestire
   */
  // Metodo in cui viene generato il pannelloCoda che conterrà n button ovvero le n code possibili
  // da gestire
  private JPanel pannelloCoda() {
    JPanel pannelloCoda = new JPanel();
    pannelloCoda.setLayout(
        new BoxLayout(
            pannelloCoda, BoxLayout.Y_AXIS)); // BoxLayout che posiziona gli elementi sull'asse Y
    // Setto la dimensione del pannello
    pannelloCoda.setPreferredSize(new Dimension(300, 530));
    pannelloCoda.setMaximumSize(pannelloCoda.getPreferredSize());

    pannelloCoda.add(
        Box.createRigidArea(
            new Dimension(
                0,
                10))); // Spazio vuoto per allontanere la scritta Scegli l'operazione da gestire dal
    // bordo del panel
    jl.setAlignmentX(Component.CENTER_ALIGNMENT); // Assegno alla scritta un alignment centrale
    pannelloCoda.add(jl); // Aggiungo la scritta al panel
    pannelloCoda.add(
        Box.createRigidArea(
            new Dimension(
                0,
                10))); // Spazio vuoto per allontanere il button dalla scritta Scegli l'operazione
    // da gestire

    // Ciclo for basato sul numero di operazioni gestibili presenti nel db
    for (int i = 0; i < listoperazioni.size(); i++) {
      // Creo il bottone che corrispondera ad un operazione gestibile nel db
      JButton operazione = new JButton();
      // Setto il testo del button con il formato (tipo operazioni: prenotazioni in attesta di
      // accettazioni)
      try{
        if(listoperazioni.get(i).getId()<=0 || idStruttura<=0)
          throw new InvalidManagementException("Id non validi");
        operazione.setText(
                listoperazioni.get(i).getTipoOperazione()
                        + ": "
                        + gestione.getNumPrenotazioni(listoperazioni.get(i).getId(), idStruttura));
      }catch (InvalidManagementException ex){
        operazione.setText(
                listoperazioni.get(i).getTipoOperazione()
                        + ": 0");
        System.out.println(ex.toString());
      }
      // Modifico le dimensioni del button
      operazione.setPreferredSize(new Dimension(230, 25));
      operazione.setMaximumSize(operazione.getPreferredSize());
      // Assegno un name al bottone corrispondente al id dell'operazione
      operazione.setName(Integer.toString(listoperazioni.get(i).getId()));
      operazione.setAlignmentX(Component.CENTER_ALIGNMENT); // Centro il button
      // ActionListener sul bottone
      operazione.addActionListener(
          e -> {
            if (!servizioPrenotazione) { // Se non si sta servendo nessuna prenotazione e possibile
              // cambiare la coda scelta
              if (pannelloCentro.getComponentCount()
                  > 2) { // if che ci permette di controllare se era stata gia scelta una coda
                pannelloCentro.remove(2);
              } // rimuovo il pannello della coda scelta
              pannelloCentro.add(
                  setServiPrenotazione(
                      operazione.getText())); // Aggiungo un nuovo pannello per la nuova coda
              idOperazione =
                  Integer.parseInt(
                      operazione
                          .getName()); // Aggiorno l'id dell operazione gestita dall'utente in base
              // al name assegnato al button
              frame.validate(); // Aggiorno il frame
            }
          });
      pannelloCoda.add(operazione); // Aggiungo il button al pannello
      pannelloCoda.add(
          Box.createRigidArea(new Dimension(0, 10))); // Spazio vuoto per distanziare i button
    } // Fine ciclo for (vengono creati dinamicamente n button con le stesse caratteristiche in base
    // alle operazioni contenute nel db)

    Timer timer = new Timer(true);
    // Metodo per aggiornare il numero di prenotazioni per tipo operazione automaticamente ogni tot
    // secondi
    timer.scheduleAtFixedRate(
        new TimerTask() {
          @Override
          public void run() {

            if (pannelloCentro.getComponentCount()
                > 2) { // se il pannello centrale e formato da 3 componenti
              JPanel panel = (JPanel) pannelloCentro.getComponent(2); // Prendiamo il terzo pannello
              Component[] component = panel.getComponents();
              // Ciclo for sul numero di componenti del pannello
              // Il doppio controllo in and ci assicura che il pannello preso sia quello che
              // permette l'accettazione
              for (int i = 0, j = 0;
                  i < component.length && component.length > 2;
                  i++) { // Ciclo for sul numero di componenti del pannello
                if (component[i] instanceof JButton) {
                  // Aggiornamento che permette di bloccare il bottone di accettazione in casso di 0
                  // prenotazioni da servire
                  aggiornoNumPrenotazioni(pannelloCoda, (JButton) component[i]);
                }
              }
            } else {
              aggiornoNumPrenotazioni(pannelloCoda, null);
            } // Metodo per aggiornare il testo dei button
            frame.validate(); // Aggiorno il frame
          }
        },
        7000,
        7000);

    pannelloCoda.setBorder(
        BorderFactory.createMatteBorder(
            0, 0, 0, 1, Color.gray)); // Creo un bordo a destra per il pannello
    pannelloCoda.setOpaque(false); // Setto lo sfondo del pannello opaco
    return pannelloCoda;
  }

  /**
   * Metodo che genera un pannello che ci permette di accettare una prenotazione.
   *
   * @param tipoOperazione operazione per la quale si vuole accettare una prenotazione
   * @return pannello che permette di accettare una prenotazione
   */
  // Metodo in cui viene genrato il pannello per l'accettazione di una prenotazione
  private JPanel setServiPrenotazione(String tipoOperazione) {
    JPanel pannelloAccettazione = new JPanel();
    pannelloAccettazione.setLayout(
        new BoxLayout(
            pannelloAccettazione,
            BoxLayout.Y_AXIS)); // BoxLayout che posiziona gli elementi sull'asse Y
    // Setto le dimensioni del pannello
    pannelloAccettazione.setPreferredSize(new Dimension(690, 530));
    pannelloAccettazione.setMaximumSize(pannelloAccettazione.getPreferredSize());
    pannelloAccettazione.add(
        Box.createRigidArea(
            new Dimension(
                0, 20))); // Blocco vuoto per distanziare la scritta Stai gestendo... dal bordo
    // superiore del panel
    // Poiche al metodo viene passato il text del button selezionato e qudini avremo un text col
    // formato (Tipo operazione:num Prenotazioni) divido il text in base ai :
    JLabel coda =
        new JLabel(
            "Stai gestendo "
                + tipoOperazione
                    .split(":")[0]); // Creo la scritta col formato Stai gestendo Tipo Operazione
    coda.setFont(
        new Font(
            coda.getFont().getName(),
            impiegato.getFont().getStyle(),
            15)); // Modifico la size della scritta
    coda.setAlignmentX(Component.CENTER_ALIGNMENT); // Centro la scritta
    pannelloAccettazione.add(coda); // Aggiungo la scritta al pannello
    JButton accetta =
        new JButton("Servi Prenotazione"); // Creo il bottone per accettare una prenotazione
    // Setto le dimensioni del button
    accetta.setPreferredSize(new Dimension(200, 80));
    accetta.setMaximumSize(accetta.getPreferredSize());
    accetta.setBackground(Color.ORANGE); // Assegno un colore al bottone
    accetta.setBorder(
        BorderFactory.createRaisedBevelBorder()); // Setto dei bordi rialzati al bottone
    pannelloAccettazione.add(
        Box.createRigidArea(
            new Dimension(
                0, 200))); // Spazio vuoto per distanziare il bottone dalla scritta Stai gestendo...
    accetta.setAlignmentX(Component.CENTER_ALIGNMENT); // Centro il bottone
    pannelloAccettazione.add(accetta); // aggiungo il bottone al panel
    pannelloAccettazione.setOpaque(false); // Setto lo sfondo del panel trasparente
    accetta.addActionListener(
        e -> { // ActionListener sul bottone per accettare una prenotazione
          try {
            if(idOperazione<=0 || idStruttura<=0)
              throw new InvalidManagementException("Id non validi");
            PrenotazioneBean p =
                    gestione.accettaPrenotazione(
                            idOperazione,
                            idStruttura); // Invoco il metodo per l'accettazione di una prenotazione
            if (p != null) { // Se il metodo mi restituisce una prenotazione
              servizioPrenotazione =
                      true; // Assegno alla true alla variabile che indica il servizio di una prenotazione
              if (pannelloCentro.getComponentCount() > 2) {
                pannelloCentro.remove(2);
              } // Rimuovo il pannello per l'accettazione
              pannelloCentro.add(
                      setPrenotazione(
                              p)); // Aggiungo il panel contenente le informazioni sulla prenotazione generato
              // tramite il metodo setPrenotazione
              logout.setEnabled(false); // blocco il bottone per il logout
              aggiornoNumPrenotazioni((JPanel) pannelloCentro.getComponent(0), null);
              frame.validate(); // Aggiorno il frame
            }
          }catch (InvalidManagementException ex){
            System.out.println(ex.toString());
          }
        });

    aggiornoNumPrenotazioni(
        (JPanel) pannelloCentro.getComponent(0),
        accetta); // se non ci sono prenotazioni da servire si blocca il bottone per accettare

    if (frame.getWindowListeners().length > 0) {
      frame.removeWindowListener(
          frame.getWindowListeners()[0]); // Rimuovo il listener che mostra la dialogBox
      frame.setDefaultCloseOperation(
          JFrame.EXIT_ON_CLOSE); // Setto il frame per la chiusura diretta
    }

    return pannelloAccettazione;
  }

  /**
   * Metodo che genera un pannello che mostra le informazioni sulla prenotazione accettata.
   *
   * @param p prenotazione accettate
   * @return pannello che mostra le informazioni della prenotazione accettata
   */
  // Metodo che conterra informazioni sulla prenotazione accettata
  private JPanel setPrenotazione(PrenotazioneBean p) {


    // piu il button per finire il servizio della prenotazione
    JPanel pannello = new JPanel(); // Pannello che conterra informazioni sulla prenotazioe accetta
    pannello.setLayout(new GridLayout(5, 1));
    pannello.setBorder(BorderFactory.createTitledBorder("Informazioni prenotazione"));
    pannello.setOpaque(false);

    // Componenti utilizzate per mostrare a video le informazioni della prenotazione accettata

    JTextField idPrenotazioneText = new JTextField();
    JTextField codiceFiscaleText = new JTextField();
    idPrenotazioneText.setText(Integer.toString(p.getId()));
    idPrenotazioneText.setEditable(false); // Setto la Text field a non editabile
    codiceFiscaleText.setText(p.getCodiceFiscale());
    codiceFiscaleText.setEditable(false); // Setto la Text field a non editabile
    JTextField dataText = new JTextField();
    dataText.setText(p.getData().toString());
    dataText.setEditable(false); // Setto la Text field a non editabile
    JTextField oraText = new JTextField();
    oraText.setText(p.getTime().toString());
    oraText.setEditable(false); // Setto la Text field a non editabile
    JTextField tipoOperazioneText = new JTextField();
    tipoOperazioneText.setText(gestione.getOperazione(p.getIdOperazione()).getTipoOperazione());
    tipoOperazioneText.setEditable(false); // Setto la Text field a non editabile
    JLabel idPrenotazione = new JLabel("Id prenotazione: ");
    JLabel codiceFiscale = new JLabel("Codice fiscale: ");
    // Aggiungo le componenti al pannello che contiene le informazioni sulla prenotazione accettata
    pannello.add(idPrenotazione);
    pannello.add(idPrenotazioneText);
    pannello.add(codiceFiscale);
    pannello.add(codiceFiscaleText);
    JLabel data = new JLabel("Data: ");
    pannello.add(data);
    pannello.add(dataText);
    JLabel ora = new JLabel("Ora: ");
    pannello.add(ora);
    pannello.add(oraText);
    JLabel tipoOperazione = new JLabel("Tipo operazione: ");
    pannello.add(tipoOperazione);
    pannello.add(tipoOperazioneText);
    // -----Fine Pannello contenente informazioni sulla prenotazione accettata------

    JButton fine =
        new JButton("Fine Servizio"); // Button per terminare il servizio della prenotazione
    // Setto la dimensione del button
    fine.setPreferredSize(new Dimension(200, 30));
    fine.setMaximumSize(fine.getPreferredSize());
    // Aggiungo al pannello principale il pannello contenete le info sulla prenotazione e il bottone
    // per concludere il servizio
    JPanel dettagliPrenotazione = new JPanel(new BorderLayout());
    dettagliPrenotazione.add(pannello, BorderLayout.CENTER);
    dettagliPrenotazione.add(fine, BorderLayout.SOUTH);

    prentazioneAccettata=new MostraPrenotazioneAccettataView();
    showPrenotazione=prentazioneAccettata.showPrenotation(p);
    fine.addActionListener(
        e -> { // ActionListener sul bottone per concludere il servizio
          servizioPrenotazione =
              false; // Assegno false alla variabile che indica che l'impiegato sta servendo il un
          // impiegato
          pannelloCentro.remove(
              2); // Rimuovo il pannello contenete le informazioni sulla prenotazione dal
          // pannelloCentrale
          pannelloCentro.add(
              setServiPrenotazione(
                  tipoOperazioneText
                      .getText())); // Aggiungo il pannello per l'accettazione di una prenotazione
          // al pannelo centrale
          logout.setEnabled(true); // riabilito il pulsante per il logout
          // Sblocco la scelta coda in fase di servizio prenotazione
          JPanel panel = (JPanel) pannelloCentro.getComponent(0); // Prendiamo il primo pannello
          Component[] component = panel.getComponents();
          for (int i = 0, j = 0;
              i < component.length;
              i++) { // Ciclo for sul numero di componenti del pannello
            if (component[i] instanceof JButton) {
              component[i].setEnabled(true);
            }
            showPrenotazione.dispose();
          }
          // -------------------------------------------------------
          frame.validate(); // Aggiorno il frame
        });

    // Blocco la scelta coda in fase di servizio prenotazione
    JPanel panel = (JPanel) pannelloCentro.getComponent(0); // Prendiamo il primo pannello
    Component[] component = panel.getComponents();
    for (int i = 0, j = 0;
        i < component.length;
        i++) { // Ciclo for sul numero di componenti del pannello
      if (component[i] instanceof JButton) {
        component[i].setEnabled(false);
      }
    }
    // -------------------------------------------------------

    // Blocco la chiusura del frame dirrettamente quando l'impiegato sta servendo ua prenotazione
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(
        new WindowAdapter() { // Listener che fa comparire una DialogBox quando l'impiegato cerca di
          // chiudere il frame
          @Override
          public void windowClosing(WindowEvent e) {
            handleClosing(); // Domando all'impiegato come vuole procedere
          }
        });

    dettagliPrenotazione.setOpaque(false);
    return dettagliPrenotazione;
  }

  /**
   * Metodo che ci permette di aggiornare il numero di prenotazioni in attesa di essere accettate.
   *
   * @param pannelloCoda pannello che conterra i button per le code disponibili
   * @param button button che ha la funzione di accettare una prenotazione se presente oppure null
   */
  // Metodo per aggiornare il numero di prenotazioni per ogni coda
  private void aggiornoNumPrenotazioni(JPanel pannelloCoda, JButton button) {
    // Ottengo tutte le componenti del pannello
    Component[] comp = pannelloCoda.getComponents();
    ArrayList<Integer> numeroPrenotazioni = new ArrayList<Integer>();
    for (int i = 0, j = 0;
        i < comp.length;
        i++) { // Ciclo for sul numero di componenti del pannello
      if (comp[i]
          instanceof
          JButton) { // Se la componente e un JButton aggiorno il testo (TipoOperazione: numero
        // prenotazioni)
        try {
          if(listoperazioni.get(j).getId()<=0 || idStruttura<=0)
            throw new InvalidManagementException("Id non validi");
          numeroPrenotazioni.add(
                  gestione.getNumPrenotazioni(listoperazioni.get(j).getId(), idStruttura));
        }catch (InvalidManagementException ex){
          numeroPrenotazioni.add(0);
          System.out.println(ex.toString());
        }
        ((JButton) comp[i])
            .setText(listoperazioni.get(j).getTipoOperazione() + ": " + numeroPrenotazioni.get(j));
        j++; // La variabile j viene utilizzata poichè nel pannello non conterrà solo JButton quindi
        // lavoreremo su due array diversi
      }
    }

    int j = 0;
    for (int i : numeroPrenotazioni) { // Sommiamo il numero di prenotazioni
      j += i;
    }

    if (button != null) {
      button.setEnabled(j != 0);
    }
  }


  /** Metodo che assegna e processa la risposta scelta dall'utente ad una dialogbox. */
  private void handleClosing() {
    int answer = showWarningMessage(); // Mostro all'impiegato la dialogBox
    switch (answer) {
      case JOptionPane
          .YES_OPTION: // Se clicca su cancel allora annulla l'operazione di chiusura del frame
        break;

      case JOptionPane.NO_OPTION: // Se clicca su termina chiude il frame
        System.exit(0);
        break;
      default:
        break;
    }
  }

  /**
   * Metodo che crea una dialogBox.
   *
   * @return dialogBox
   */
  // DialogBox
  private int showWarningMessage() {
    String[] buttonLabels = new String[] {"Indietro", "Termina"};
    String defaultOption = buttonLabels[0];
    Icon icon = null;

    return JOptionPane.showOptionDialog(
        frame,
        "Stai ancora servendo una prenotazione\n",
        "Warning",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE,
        icon,
        buttonLabels,
        defaultOption);
  }


}
