package presentazione;

import business.Gestione;
import business.ImpiegatoBean;
import business.OperazioneBean;
import business.PrenotazioneBean;
import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class AccettazionePrenotazioneView {

    private JFrame frame=new JFrame();
    private JPanel pannelloNord=new JPanel();
    private JPanel pannelloCentro=new JPanel();
    private ImageIcon immagine = new ImageIcon("src/image/frameIcon.png");
    private ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");
    private JLabel impiegato=new JLabel();
    private JButton logout=new JButton("Logout");
    private JLabel jl=new JLabel("Scegli l'operazione da gestire: ");
    private JComboBox<String> operazioni = new JComboBox<String>();
    private JButton selezionaCoda=new JButton("Seleziona operazione");
    private ArrayList<OperazioneBean> listoperazioni;
    private int idOperazione;
    private int idStruttura;
    private boolean servizioPrenotazione=false; //Variabile booleana utilizzata per controllare se l'impiegato sta servendo una prenotazione

    public AccettazionePrenotazioneView(ImpiegatoBean imp){
        idStruttura=imp.getIdStruttura(); //Salvo la struttura in cui lavora l'impiegato per poter ottenere le prenotazioni sono di quella struttura
        listoperazioni=DataAccess.getOperazioni(); //Salvo le operazioni che potra gestire l'impiegato

        //Settaggi frame
        frame.setTitle("MedQueue");
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //Posiziona il pannello al centro dello schermo
        frame.setIconImage(infermiera.getImage());


        //--------Pannello Nord (logo,nome impiegato, logout)--------
        pannelloNord.setLayout(new BoxLayout(pannelloNord, BoxLayout.X_AXIS)); //BoxLayout che posiziona gli elementi sull'asse X
        pannelloNord.setOpaque(false);//Nascondo lo sfondo del JPanel
        //Operazioni per ridimensionare l'immagine
        Image image = immagine.getImage(); //prendo l'immagine dall'ImagIcond
        Image newimg = image.getScaledInstance(70, 50,  java.awt.Image.SCALE_SMOOTH); //Scalo l'immagine in base a width e height
        immagine = new ImageIcon(newimg); //ricreo l'ImageIcon

        pannelloNord.add(Box.createRigidArea(new Dimension(30,0))); //Spazio vuoto che viene utilizzato per distanziare il logo dal bordo del frame
        pannelloNord.add(new JLabel(immagine)); //Inserimento del logo

        //Creo il testo che conterra il nome dell'impiegato che ha eseguito l'accesso all'app
        impiegato.setText(imp.getNome().substring(0,1).toUpperCase() + imp.getNome().substring(1).toLowerCase() + " " +imp.getCognome().substring(0,1).toUpperCase() + imp.getCognome().substring(1).toLowerCase());
        impiegato.setFont(new Font(impiegato.getFont().getName(), impiegato.getFont().getStyle(), 30));
        pannelloNord.add(Box.createRigidArea(new Dimension(280,0)));//Spazio vuoto che viene utilizzato per distanziare il nome dell'impiegato dal logo
        pannelloNord.add(impiegato);//Aggiungo il nome dell'impigato al pannello
        pannelloNord.add(Box.createRigidArea(new Dimension(250,0)));//Spazio vuoto che viene utilizzato per distanziare il pulsante di logout dal nome dell'impiegato
        pannelloNord.add(logout);//Aggiungo il pulsante di logout
        //ActionListener sul pulsante di logout
        logout.addActionListener(e->{
                frame.setVisible(false); //nascondo il frame
                new LoginView(); //Riapro il frame per l'autenticazione
        });
        //--------------------Fine Pannello Nord---------------------


        /*--------------------Pannnello Centrale----------------------
        Conterra 2 JPanel:
        1. JPanel che conterra dei button per poter selezionare la coda da gestire
        2. JPanel che permette l'accettazione di una prenotazione oppure
           JPanel che mostra le informazioni sulla prenotazione accettata
         */
        pannelloCentro.setLayout(new BoxLayout(pannelloCentro, BoxLayout.X_AXIS));//BoxLayout che posiziona gli elementi sull'asse X
        pannelloCentro.add(pannelloCoda()); //Aggiungo al pannello centrale il primo JPanel che conterra i button, viene generato nel metodo pannelloCoda()
        pannelloCentro.add(Box.createRigidArea(new Dimension(100,0))); //Spazio vuoto che viene utilizzato per distanziare i 2 JPanel del pannelloCentro
        pannelloCentro.setOpaque(false); //Nascondo lo sfondo del JPanel
        //------------------Fine Pannello Centrale--------------------

        System.out.println(pannelloCentro.getComponentCount());

        //Aggiungo al frame i 2 pannelli Nord e Centro
        frame.add(pannelloNord,BorderLayout.NORTH);
        frame.add(pannelloCentro,BorderLayout.CENTER);

    }


    //Metodo in cui viene generato il pannelloCoda che conterrà n button ovvero le n code possibili da gestire
    private JPanel pannelloCoda(){
        JPanel pannelloCoda=new JPanel();
        pannelloCoda.setLayout(new BoxLayout(pannelloCoda, BoxLayout.Y_AXIS));//BoxLayout che posiziona gli elementi sull'asse Y
        //Setto la dimensione del pannello
        pannelloCoda.setPreferredSize(new Dimension(300,530));
        pannelloCoda.setMaximumSize(pannelloCoda.getPreferredSize());

        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));//Spazio vuoto per allontanere la scritta Scegli l'operazione da gestire dal bordo del panel
        jl.setAlignmentX(Component.CENTER_ALIGNMENT); //Assegno alla scritta un alignment centrale
        pannelloCoda.add(jl);//Aggiungo la scritta al panel
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));//Spazio vuoto per allontanere il button dalla scritta Scegli l'operazione da gestire


        //Ciclo for basato sul numero di operazioni gestibili presenti nel db
        for(int i = 0; i < listoperazioni.size(); i++) {
            //Creo il bottone che corrispondera ad un operazione gestibile nel db
            JButton operazione = new JButton();
            //Setto il testo del button con il formato (tipo operazioni: prenotazioni in attesta di accettazioni)
            operazione.setText(listoperazioni.get(i).getTipoOperazione() + ": " + DataAccess.numPrenotazioni(listoperazioni.get(i).getId(),idStruttura));
            //Modifico le dimensioni del button
            operazione.setPreferredSize(new Dimension(230, 25));
            operazione.setMaximumSize(operazione.getPreferredSize());
            //Assegno un name al bottone corrispondente al id dell'operazione
            operazione.setName(Integer.toString(listoperazioni.get(i).getId()));
            operazione.setAlignmentX(Component.CENTER_ALIGNMENT); //Centro il button
            //ActionListener sul bottone
            operazione.addActionListener(e -> {
                if (!servizioPrenotazione) { //Se non si sta servendo nessuna prenotazione e possibile cambiare la coda scelta
                    if (pannelloCentro.getComponentCount() > 2) //if che ci permette di controllare se era stata gia scelta una coda
                        pannelloCentro.remove(2); //rimuovo il pannello della coda scelta
                    pannelloCentro.add(setServiPrenotazione(operazione.getText())); //Aggiungo un nuovo pannello per la nuova coda
                    idOperazione = Integer.parseInt(operazione.getName()); //Aggiorno l'id dell operazione gestita dall'utente in base al name assegnato al button
                    frame.validate(); //Aggiorno il frame
                }
            });
            pannelloCoda.add(operazione); //Aggiungo il button al pannello
            pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));//Spazio vuoto per distanziare i button
        }//Fine ciclo for (vengono creati dinamicamente n button con le stesse caratteristiche in base alle operazioni contenute nel db)



        Timer timer = new Timer(true);
        //Metodo per aggiornare il numero di prenotazioni per tipo operazione automaticamente ogni tot secondi
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                aggiornoNumPrenotazioni(pannelloCoda); //Metodo per aggiornare il testo dei button
                frame.validate(); //Aggiorno il frame
            }

        }, 10000, 10000);

        pannelloCoda.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.gray)); //Creo un bordo a destra per il pannello
        pannelloCoda.setOpaque(false);//Setto lo sfondo del pannello opaco
        return pannelloCoda;
    }


    //Metodo in cui viene genrato il pannello per l'accettazione di una prenotazione
    private JPanel setServiPrenotazione(String tipoOperazione){
        JPanel pannelloAccettazione=new JPanel();
        pannelloAccettazione.setLayout(new BoxLayout(pannelloAccettazione, BoxLayout.Y_AXIS)); //BoxLayout che posiziona gli elementi sull'asse Y
        //Setto le dimensioni del pannello
        pannelloAccettazione.setPreferredSize(new Dimension(690,530));
        pannelloAccettazione.setMaximumSize(pannelloAccettazione.getPreferredSize());
        pannelloAccettazione.add(Box.createRigidArea(new Dimension(0,20))); //Blocco vuoto per distanziare la scritta Stai gestendo... dal bordo superiore del panel
        //Poiche al metodo viene passato il text del button selezionato e qudini avremo un text col formato (Tipo operazione:num Prenotazioni) divido il text in base ai :
        JLabel coda=new JLabel("Stai gestendo "+tipoOperazione.split(":")[0]); //Creo la scritta col formato Stai gestendo Tipo Operazione
        coda.setFont(new Font(coda.getFont().getName(), impiegato.getFont().getStyle(), 15)); //Modifico la size della scritta
        coda.setAlignmentX(Component.CENTER_ALIGNMENT); //Centro la scritta
        pannelloAccettazione.add(coda); //Aggiungo la scritta al pannello
        JButton accetta=new JButton("Servi Prenotazione"); //Creo il bottone per accettare una prenotazione
        //Setto le dimensioni del button
        accetta.setPreferredSize(new Dimension(200,80));
        accetta.setMaximumSize(accetta.getPreferredSize());
        accetta.setBackground(Color.ORANGE);//Assegno un colore al bottone
        accetta.setBorder(BorderFactory.createRaisedBevelBorder());//Setto dei bordi rialzati al bottone
        pannelloAccettazione.add(Box.createRigidArea(new Dimension(0,200))); //Spazio vuoto per distanziare il bottone dalla scritta Stai gestendo...
        accetta.setAlignmentX(Component.CENTER_ALIGNMENT); //Centro il bottone
        pannelloAccettazione.add(accetta);//aggiungo il bottone al panel
        pannelloAccettazione.setOpaque(false);//Setto lo sfondo del panel trasparente
        accetta.addActionListener(e->{ //ActionListener sul bottone per accettare una prenotazione
            PrenotazioneBean p=Gestione.accettaPrenotazione(idOperazione,idStruttura); //Invoco il metodo per l'accettazione di una prenotazione
            if(p!=null) { //Se il metodo mi restituisce una prenotazione
                servizioPrenotazione = true;  //Assegno alla true alla variabile che indica il servizio di una prenotazione
                if(pannelloCentro.getComponentCount()>2)
                    pannelloCentro.remove(2);//Rimuovo il pannello per l'accettazione
                pannelloCentro.add(setPrenotazione(p)); //Aggiungo il panel contenente le informazioni sulla prenotazione generato tramite il metodo setPrenotazione
                logout.setEnabled(false); //blocco il bottone per il logout
                aggiornoNumPrenotazioni((JPanel) pannelloCentro.getComponent(0));
                frame.validate(); //Aggiorno il frame
            }
        });

        if(frame.getWindowListeners().length>0) { //Verifico se e stato assegnato qualche WindowsListener al frame
            /*L'esecuzione del if indica che il panel e stato generato dopo l'accettazione di una prenotazione
              quindi non era possibile chiudere direttamente il frame
             */
            frame.removeWindowListener(frame.getWindowListeners()[0]); //Rimuovo il listener che mostra la dialogBox
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Setto il frame per la chiusura diretta
        }

        return pannelloAccettazione;
    };

    //Metodo che conterra informazioni sulla prenotazione accettata
    private JPanel setPrenotazione(PrenotazioneBean p){
        JPanel dettagliPrenotazione=new JPanel(new BorderLayout());//Pannello principale che conterra i dettagli della prenotazione piu il button per finire il servizio della prenotazione
        JPanel pannello = new JPanel();//Pannello che conterra informazioni sulla prenotazioe accetta
        pannello.setLayout(new GridLayout(5,1));
        pannello.setBorder(BorderFactory.createTitledBorder("Informazioni prenotazione"));
        pannello.setOpaque(false);

        //Componenti utilizzate per mostrare a video le informazioni della prenotazione accettata
        JLabel idPrenotazione = new JLabel("Id prenotazione: ");
        JTextField idPrenotazioneText = new JTextField();
        JLabel codiceFiscale = new JLabel("Codice fiscale: ");
        JTextField codiceFiscaleText = new JTextField();
        JLabel data = new JLabel("Data: ");
        JLabel ora = new JLabel("Ora: ");
        JTextField dataText = new JTextField();
        JTextField oraText = new JTextField();
        JLabel tipoOperazione = new JLabel("Tipo operazione: ");
        JTextField tipoOperazioneText = new JTextField();
        idPrenotazioneText.setText(Integer.toString(p.getId()));
        idPrenotazioneText.setEditable(false); //Setto la Text field a non editabile
        codiceFiscaleText.setText(p.getCodiceFiscale());
        codiceFiscaleText.setEditable(false); //Setto la Text field a non editabile
        dataText.setText(p.getData());
        dataText.setEditable(false); //Setto la Text field a non editabile
        oraText.setText(p.getTime());
        oraText.setEditable(false); //Setto la Text field a non editabile
        tipoOperazioneText.setText(DataAccess.getOperazione(p.getIdOperazione()).getTipoOperazione());
        tipoOperazioneText.setEditable(false); //Setto la Text field a non editabile
        //Aggiungo le componenti al pannello che contiene le informazioni sulla prenotazione accettata
        pannello.add(idPrenotazione);
        pannello.add(idPrenotazioneText);
        pannello.add(codiceFiscale);
        pannello.add(codiceFiscaleText);
        pannello.add(data);
        pannello.add(dataText);
        pannello.add(ora);
        pannello.add(oraText);
        pannello.add(tipoOperazione);
        pannello.add(tipoOperazioneText);
        //-----Fine Pannello contenente informazioni sulla prenotazione accettata------

        JButton fine = new JButton("Fine Servizio");//Button per terminare il servizio della prenotazione
        //Setto la dimensione del button
        fine.setPreferredSize(new Dimension(200,30));
        fine.setMaximumSize(fine.getPreferredSize());
        //Aggiungo al pannello principale il pannello contenete le info sulla prenotazione e il bottone per concludere il servizio
        dettagliPrenotazione.add(pannello,BorderLayout.CENTER);
        dettagliPrenotazione.add(fine,BorderLayout.SOUTH);

        fine.addActionListener(e->{ //ActionListener sul bottone per concludere il servizio
            servizioPrenotazione=false; //Assegno false alla variabile che indica che l'impiegato sta servendo il un impiegato
            pannelloCentro.remove(2); //Rimuovo il pannello contenete le informazioni sulla prenotazione dal pannelloCentrale
            pannelloCentro.add(setServiPrenotazione(tipoOperazioneText.getText())); //Aggiungo il pannello per l'accettazione di una prenotazione al pannelo centrale
            aggiornoNumPrenotazioni((JPanel) pannelloCentro.getComponent(0)); //Aggiorno in numero di prenotazioni per ogni coda
            logout.setEnabled(true);//riabilito il pulsante per il logout
            frame.validate();//Aggiorno il frame
        });


        //Blocco la chiusura del frame dirrettamente quando l'impiegato sta servendo ua prenotazione
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {//Listener che fa comparire una DialogBox quando l'impiegato cerca di chiudere il frame
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();//Domando all'impiegato come vuole procedere
            }
        });

        dettagliPrenotazione.setOpaque(false);
        return dettagliPrenotazione;
    };

    //Metodo per aggiornare il numero di prenotazioni per ogni coda
    private void aggiornoNumPrenotazioni(JPanel pannelloCoda){
        //Ottengo tutte le componenti del pannello
        Component[] comp = pannelloCoda.getComponents();
        ArrayList<Integer> numeroPrenotazioni = new ArrayList<Integer>();
        for (int i = 0, j = 0; i < comp.length; i++) { //Ciclo for sul numero di componenti del pannello
            if (comp[i] instanceof JButton) {//Se la componente e un JButton aggiorno il testo (TipoOperazione: numero prenotazioni)
                numeroPrenotazioni.add(DataAccess.numPrenotazioni(listoperazioni.get(j).getId(), idStruttura));
                ((JButton) comp[i]).setText(listoperazioni.get(j).getTipoOperazione() + ": " + numeroPrenotazioni.get(j));
                j++;//La variabile j viene utilizzata poichè nel pannello non conterrà solo JButton quindi lavoreremo su due array diversi
            }
        }
    }

    public void visible(boolean v){
        frame.setVisible(v);
    }

    private void handleClosing() {
            int answer = showWarningMessage();//Mostro all'impiegato la dialogBox
            switch (answer){
                case JOptionPane.YES_OPTION: //Se clicca su cancel allora annulla l'operazione di chiusura del frame
                    break;

                case JOptionPane.NO_OPTION: //Se clicca su termina chiude il frame
                    frame.dispose();
                    break;
            }
    }

    //DialogBox
    private int showWarningMessage() {
        String[] buttonLabels = new String[] {"Indietro", "Termina"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;

        return JOptionPane.showOptionDialog(frame,
                "Stai ancora servendo una prenotazione\n",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);
    }

}
