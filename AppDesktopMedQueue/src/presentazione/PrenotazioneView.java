package presentazione;

import business.PrenotazioneBean;
import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class PrenotazioneView {
    private JFrame framePrenotazioni = new JFrame();
    public PrenotazioneView(PrenotazioneBean p) {
        JLabel idPrenotazione = new JLabel("Id prenotazione: ");
        JTextField idPrenotazioneText = new JTextField(Integer.toString(p.getId()));
        idPrenotazioneText.setEditable(false);
        JLabel codiceFiscale = new JLabel("Codice fiscale: ");
        JTextField codiceFiscaleText = new JTextField(p.getCodiceFiscale());
        codiceFiscaleText.setEditable(false);
        JLabel data = new JLabel("Data: ");
        JTextField dataText = new JTextField(p.getData());
        dataText.setEditable(false);
        JLabel ora = new JLabel("Ora: ");
        JTextField oraText = new JTextField(p.getTime());
        oraText.setEditable(false);
        JLabel tipoOperazione = new JLabel("Tipo operazione: ");
        JTextField tipoOperazioneText = new JTextField(DataAccess.getOperazione(p.getIdOperazione()).getTipoOperazione());
        tipoOperazioneText.setEditable(false);


        JPanel pannello = new JPanel();
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

        JButton fine = new JButton("Fine");

        framePrenotazioni.add(pannello);
        framePrenotazioni.add(fine, BorderLayout.PAGE_END);

        ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");

        framePrenotazioni.setVisible(true);
        framePrenotazioni.setSize(350,350);
        framePrenotazioni.setLocationRelativeTo(null);
        framePrenotazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrenotazioni.setTitle("Dettaglio prenotazione");
        framePrenotazioni.setBackground(Color.white);
        framePrenotazioni.setResizable(false);
        framePrenotazioni.setIconImage(infermiera.getImage());

    }

    public void visible(boolean v){
        framePrenotazioni.setVisible(v);
    }
}
