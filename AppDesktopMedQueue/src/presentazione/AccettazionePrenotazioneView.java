package presentazione;

import business.Gestione;
import business.ImpiegatoBean;
import business.OperazioneBean;
import business.PrenotazioneBean;
import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

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
    private int i = 0;


    private boolean servizioPrenotazione=false;

    public AccettazionePrenotazioneView(ImpiegatoBean imp){
        idStruttura=imp.getIdStruttura();
        //Settaggi frame
        frame.setTitle("MedQueue");
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(infermiera.getImage());



        //Pannello Nord
        pannelloNord.setLayout(new BoxLayout(pannelloNord, BoxLayout.X_AXIS));
        pannelloNord.setOpaque(false);
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(70, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);
        pannelloNord.add(Box.createRigidArea(new Dimension(30,0)));
        pannelloNord.add(new JLabel(immagine));
        impiegato.setText(imp.getNome().substring(0,1).toUpperCase() + imp.getNome().substring(1).toLowerCase() + " " +imp.getCognome().substring(0,1).toUpperCase() + imp.getCognome().substring(1).toLowerCase());
        impiegato.setFont(new Font(impiegato.getFont().getName(), impiegato.getFont().getStyle(), 30));
        pannelloNord.add(Box.createRigidArea(new Dimension(280,0)));
        pannelloNord.add(impiegato);
        pannelloNord.add(Box.createRigidArea(new Dimension(250,0)));
        pannelloNord.add(logout);

        listoperazioni=DataAccess.getOperazioni();
        pannelloCentro.setLayout(new BoxLayout(pannelloCentro, BoxLayout.X_AXIS));
        pannelloCentro.add(pannelloCoda());
        pannelloCentro.add(Box.createRigidArea(new Dimension(10,0)));
        pannelloCentro.setOpaque(false);

        logout.addActionListener(e->{
            if(!servizioPrenotazione) {
                frame.setVisible(false);
                new LoginView();
            }
        });

        frame.add(pannelloNord,BorderLayout.NORTH);
        frame.add(pannelloCentro,BorderLayout.CENTER);

    }

    public JPanel setServiPrenotazione(String tipoOperazione){
        JPanel pannelloAccettazione=new JPanel();
        pannelloAccettazione.setLayout(new BoxLayout(pannelloAccettazione, BoxLayout.Y_AXIS));
        pannelloAccettazione.setPreferredSize(new Dimension(690,530));
        pannelloAccettazione.setMaximumSize(pannelloAccettazione.getPreferredSize());
        pannelloAccettazione.add(Box.createRigidArea(new Dimension(0,20)));
        String[] splitString=tipoOperazione.split(":");
        JLabel coda=new JLabel("Stai gestendo "+splitString[0]);
        coda.setFont(new Font(coda.getFont().getName(), impiegato.getFont().getStyle(), 15));
        coda.setAlignmentX(Component.CENTER_ALIGNMENT);
        pannelloAccettazione.add(coda);
        JButton accetta=new JButton("Servi Prenotazione");
        accetta.setPreferredSize(new Dimension(200,80));
        accetta.setMaximumSize(accetta.getPreferredSize());
        accetta.setBackground(Color.ORANGE);
        accetta.setBorder(BorderFactory.createRaisedBevelBorder());
        pannelloAccettazione.add(Box.createRigidArea(new Dimension(0,200)));
        accetta.setAlignmentX(Component.CENTER_ALIGNMENT);
        pannelloAccettazione.add(accetta);
        pannelloAccettazione.setOpaque(false);

        accetta.addActionListener(e->{
            PrenotazioneBean p=Gestione.accettaPrenotazione(idOperazione,idStruttura);
            if(p!=null) {
                servizioPrenotazione = true;
                pannelloCentro.remove(1);
                pannelloCentro.add(setPrenotazione(p));
                frame.validate();
                logout.setEnabled(false);
            }
        });

        return pannelloAccettazione;
    };


    public JPanel setPrenotazione(PrenotazioneBean p){
        JPanel dettagliPrenotazione=new JPanel(new BorderLayout());
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
        JPanel pannello = new JPanel();
        JButton fine = new JButton("Fine Servizio");

        pannello.setLayout(new GridLayout(5,1));
        pannello.setBorder(BorderFactory.createTitledBorder("Informazioni prenotazione"));
        pannello.setOpaque(false);
        idPrenotazioneText.setText(Integer.toString(p.getId()));
        idPrenotazioneText.setEditable(false);
        codiceFiscaleText.setText(p.getCodiceFiscale());
        codiceFiscaleText.setEditable(false);
        dataText.setText(p.getData());
        dataText.setEditable(false);
        oraText.setText(p.getTime());
        oraText.setEditable(false);
        tipoOperazioneText.setText(DataAccess.getOperazione(p.getIdOperazione()).getTipoOperazione());
        tipoOperazioneText.setEditable(false);
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
        fine.setPreferredSize(new Dimension(200,30));
        fine.setMaximumSize(fine.getPreferredSize());
        dettagliPrenotazione.add(pannello,BorderLayout.CENTER);
        dettagliPrenotazione.add(fine,BorderLayout.SOUTH);

        fine.addActionListener(e->{
            servizioPrenotazione=false;
            DataAccess.deletePrenotazione(p.getId());
            pannelloCentro.removeAll();
            pannelloCentro.add(pannelloCoda());
            pannelloCentro.add(setServiPrenotazione(tipoOperazioneText.getText()));
            frame.validate();
            logout.setEnabled(true);
        });

        dettagliPrenotazione.setOpaque(false);
        return dettagliPrenotazione;
    };

    public JPanel pannelloCoda(){
        JPanel pannelloCoda=new JPanel();
        pannelloCoda.setLayout(new BoxLayout(pannelloCoda, BoxLayout.Y_AXIS));
        pannelloCoda.setPreferredSize(new Dimension(300,530));
        pannelloCoda.setMaximumSize(pannelloCoda.getPreferredSize());
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pannelloCoda.add(jl);
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));

        ArrayList<JButton> bottoni = new ArrayList<JButton>();

        for(int i = 0; i < listoperazioni.size(); i++) {
            JButton operazione = new JButton();
            bottoni.add(operazione);

            operazione.setText(listoperazioni.get(i).getTipoOperazione() + ": " + DataAccess.numPrenotazioni(listoperazioni.get(i).getId(),idStruttura));
            operazione.setPreferredSize(new Dimension(230, 25));
            operazione.setMaximumSize(operazione.getPreferredSize());
            operazione.setName(Integer.toString(listoperazioni.get(i).getId()));
            operazione.setAlignmentX(Component.CENTER_ALIGNMENT);
            operazione.addActionListener(e -> {
                if (!servizioPrenotazione) {
                    if (pannelloCentro.getComponentCount() > 1)
                        pannelloCentro.remove(1);
                    pannelloCentro.add(setServiPrenotazione(operazione.getText()));
                    idOperazione = Integer.parseInt(operazione.getName());
                    frame.validate();
                }
            });
            pannelloCoda.add(operazione);
            pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));


        }

        pannelloCoda.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.gray));
        pannelloCoda.setOpaque(false);
        return pannelloCoda;
    }



    public void visible(boolean v){
        frame.setVisible(v);
    }



}
