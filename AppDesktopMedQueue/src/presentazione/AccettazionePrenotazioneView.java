package presentazione;

import business.PrenotazioneBean;
import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class AccettazionePrenotazioneView {

    private JFrame frame=new JFrame();
    private JPanel pannelloNord=new JPanel();
    private JPanel pannelloCentro=new JPanel();
    private JPanel pannelloCoda=new JPanel();
    private ImageIcon immagine = new ImageIcon("src/image/frameIcon.png");
    private JLabel impiegato=new JLabel("Nome Impiegato");
    private JButton logout=new JButton("Logout");
    private JLabel jl=new JLabel("Scegli prenotazioni operazione da gestire");
    private JComboBox<String> operazioni = new JComboBox<String>();
    private JButton selezionaCoda=new JButton("Seleziona Operazione");


    private boolean servizioPrenotazione=false;

    public AccettazionePrenotazioneView(){
        //Settaggi frame
        frame.setTitle("MedQueue");
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);



        //Pannello Nord
        pannelloNord.setLayout(new BoxLayout(pannelloNord, BoxLayout.X_AXIS));
        pannelloNord.setOpaque(false);
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(70, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);
        pannelloNord.add(Box.createRigidArea(new Dimension(30,0)));
        pannelloNord.add(new JLabel(immagine));
        impiegato.setFont(new Font(impiegato.getFont().getName(), impiegato.getFont().getStyle(), 30));
        pannelloNord.add(Box.createRigidArea(new Dimension(280,0)));
        pannelloNord.add(impiegato);
        pannelloNord.add(Box.createRigidArea(new Dimension(250,0)));
        pannelloNord.add(logout);




        pannelloCentro.setLayout(new BoxLayout(pannelloCentro, BoxLayout.X_AXIS));
        pannelloCoda.setLayout(new BoxLayout(pannelloCoda, BoxLayout.Y_AXIS));
        pannelloCoda.setPreferredSize(new Dimension(300,530));
        pannelloCoda.setMaximumSize(pannelloCoda.getPreferredSize());
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pannelloCoda.add(jl);
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,10)));
        operazioni.setPreferredSize(new Dimension(280,30));
        operazioni.setMaximumSize(operazioni.getPreferredSize());
        //POPOLO JCOMBO BOX
        for(int i = 0; i< DataAccess.getOperazioni().size(); i++)
            operazioni.addItem(DataAccess.getOperazioni().get(i));
        pannelloCoda.add(operazioni);
        pannelloCoda.add(Box.createRigidArea(new Dimension(0,400)));
        selezionaCoda.setPreferredSize(new Dimension(200,30));
        selezionaCoda.setMaximumSize(selezionaCoda.getPreferredSize());
        selezionaCoda.setAlignmentX(Component.CENTER_ALIGNMENT);
        selezionaCoda.setBorder(BorderFactory.createRaisedBevelBorder());
        selezionaCoda.setBackground(Color.ORANGE);
        pannelloCoda.add(selezionaCoda);
        pannelloCoda.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.gray));
        pannelloCoda.setOpaque(false);
        selezionaCoda.addActionListener(e->{//ActionListener sul bottone invio
            if(!servizioPrenotazione){
                if(pannelloCentro.getComponentCount()>2)
                    pannelloCentro.remove(2);
                    pannelloCentro.add(setServiPrenotazione(operazioni.getSelectedItem().toString()));
                    frame.validate();
            }
        });



        pannelloCentro.add(pannelloCoda);
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
        JLabel coda=new JLabel("Stai gestendo le prenotazioni di tipo: "+tipoOperazione);
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
            servizioPrenotazione=true;
            pannelloCentro.remove(2);
            //Inserire il metodo accetto prenotazione

            //Creo un oggetto per testare il funzionamento da eliminare in fase finale
            PrenotazioneBean p1 = new PrenotazioneBean(1,"data","tempo",true,"codicefiscale 1",1,1);
            pannelloCentro.add(setPrenotazione(p1));
            frame.validate();
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
            pannelloCentro.remove(2);
            //Inserire il metodo cancellare prenotazione


            pannelloCentro.add(setServiPrenotazione(tipoOperazioneText.getText()));
            frame.validate();
        });

        dettagliPrenotazione.setOpaque(false);
        return dettagliPrenotazione;
    };


    public void visible(boolean v){
        frame.setVisible(v);
    }


}
