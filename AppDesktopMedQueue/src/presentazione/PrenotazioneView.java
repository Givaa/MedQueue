package presentazione;

import business.PrenotazioneBean;
import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class PrenotazioneView {
    private JFrame framePrenotazioni = new JFrame();
    private JLabel idPrenotazione = new JLabel("Id prenotazione: ");
    private JTextField idPrenotazioneText = new JTextField();
    private JLabel codiceFiscale = new JLabel("Codice fiscale: ");
    private JTextField codiceFiscaleText = new JTextField();
    private JLabel data = new JLabel("Data: ");
    private JLabel ora = new JLabel("Ora: ");
    private JTextField dataText = new JTextField();
    private JTextField oraText = new JTextField();
    private JLabel tipoOperazione = new JLabel("Tipo operazione: ");
    private JTextField tipoOperazioneText = new JTextField();
    private JPanel pannello = new JPanel();
    private JButton fine = new JButton("Fine");
    private ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");

    public PrenotazioneView(PrenotazioneBean p) {
        //Settaggi frame
        framePrenotazioni.setSize(350,350);
        framePrenotazioni.setLocationRelativeTo(null);
        framePrenotazioni.setTitle("Dettaglio prenotazione");
        framePrenotazioni.setBackground(Color.white);
        framePrenotazioni.setResizable(false);
        framePrenotazioni.setIconImage(infermiera.getImage());
        framePrenotazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Settaggio componenti
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


        pannello.setLayout(new GridLayout(5,1));
        pannello.setBorder(BorderFactory.createTitledBorder("Informazioni prenotazione"));
        pannello.setBackground( Color.white );

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


        framePrenotazioni.add(pannello);
        framePrenotazioni.add(fine, BorderLayout.PAGE_END);
        framePrenotazioni.setVisible(true);


    }

    public void visible(boolean v){
        framePrenotazioni.setVisible(v);
    }
}
