package presentazione;

import business.PrenotazioneBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ListPrenotazioniView {
    private ArrayList<PrenotazioneBean> prenotazioni = new ArrayList<PrenotazioneBean>();
    private JFrame frame = new JFrame();
    private JLabel frase = new JLabel("Prenotazioni da servire");
    private JPanel pannelloNord = new JPanel();
    private JPanel pannelloCentrale=new JPanel(new GridLayout(2,1));
    private JPanel pannelloSud=new JPanel();
    private JPanel lista1=new JPanel();
    private JPanel lista2=new JPanel();
    private JButton button=new JButton("Servi cliente");
    private int elementi;


    //Classe per settare sfondo della prenotazione
    class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }




    public ListPrenotazioniView(){

        frame.setTitle("MedQueue");
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        ImageIcon immagine = new ImageIcon("src/image/LogoNoBG.png");
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(100, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        immagine = new ImageIcon(newimg);
        ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");


        PrenotazioneBean p1 = new PrenotazioneBean(1,"data","tempo",true,"codicefiscale 1",1,1);
        PrenotazioneBean p2 = new PrenotazioneBean(2,"data","tempo",true,"codicefiscale 2",1,1);

        frase.setFont(new Font(frase.getFont().getName(), frase.getFont().getStyle(), 20));
        pannelloNord.setBackground(Color.white);
        pannelloNord.setLayout(new BoxLayout(pannelloNord, BoxLayout.X_AXIS));
        pannelloNord.add(Box.createRigidArea(new Dimension(10,0)));
        pannelloNord.add( new JLabel(immagine));
        pannelloNord.add(Box.createRigidArea(new Dimension(100,0)));
        pannelloNord.add(frase);


        //Simulo un array per verificare il correttamento funzionamento della generazione icone delle prenotazioni
        //Problema l'ultima incona aggiunta non possiede lo sfondo
        for(int i=0;i<5;i++){
            prenotazioni.add(p1);
        }

        setPrenotazioni(prenotazioni);


        pannelloCentrale.add(lista1);
        pannelloCentrale.add(lista2);

        pannelloSud.setLayout(new BoxLayout(pannelloSud, BoxLayout.X_AXIS));
        button.setPreferredSize(new Dimension(150,70));
        //centrare bottone e settare background bianco
        pannelloSud.add(button);

        frame.add(pannelloNord,BorderLayout.NORTH);
        frame.add(pannelloCentrale,BorderLayout.CENTER);
        frame.add(pannelloSud,BorderLayout.SOUTH);
        button.addActionListener(e->{
            frame.setVisible(false);
            new PrenotazioneView(prenotazioni.get(0)).visible(true);
        });
        frame.setIconImage(infermiera.getImage());
        frame.getContentPane().setBackground( Color.white );
    }

    public ImagePanel bloccoPrenotazione(PrenotazioneBean prenotazione){
        ImageIcon immagine = new ImageIcon("src/image/sfondoPrenotazione.jpg");
        Image image = immagine.getImage();
        Image newimg = image.getScaledInstance(100, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImagePanel blocco = new ImagePanel(newimg);
        blocco.setLayout(new GridLayout(2,1));
        blocco.setMaximumSize(new Dimension(100,140));
        JLabel idPrenotazione = new JLabel(Integer.toString(prenotazione.getId()));
        JLabel codiceFiscale = new JLabel(prenotazione.getCodicefiscale());
        idPrenotazione.setHorizontalAlignment(JLabel.CENTER);
        idPrenotazione.setFont(new Font(frase.getFont().getName(), frase.getFont().getStyle(), 30));
        codiceFiscale.setHorizontalAlignment(JLabel.CENTER);
        codiceFiscale.setFont(new Font(frase.getFont().getName(), frase.getFont().getStyle(), 10));
        blocco.add(idPrenotazione);
        blocco.add(codiceFiscale);
        blocco.setOpaque(true);
        blocco.setBackground(Color.ORANGE);
        return blocco;
    }

//Metodo per generare la lista delle prenotazioni dal punto di vista visivo
//Funge sia in avanti e in indiedro
    public void setPrenotazioni(ArrayList<PrenotazioneBean> p){
        prenotazioni = p;
        lista1.removeAll();
        lista2.removeAll();

        lista1.setBackground(Color.white);
        lista1.setLayout(new BoxLayout(lista1, BoxLayout.X_AXIS));
        lista1.add(Box.createRigidArea(new Dimension(30,0)));
        lista2.setBackground(Color.white);
        lista2.setLayout(new BoxLayout(lista2, BoxLayout.X_AXIS));
        lista2.add(Box.createRigidArea(new Dimension(30,0)));


        if(prenotazioni.size()<8)
            elementi=prenotazioni.size();
        else
            elementi=8;

        for(int i=0;i<elementi;i++){
            if(i<4) {
                lista1.add(bloccoPrenotazione(prenotazioni.get(i)));
                lista1.add(Box.createRigidArea(new Dimension(30, 0)));
            }else{
                lista2.add(bloccoPrenotazione(prenotazioni.get(i)));
                lista2.add(Box.createRigidArea(new Dimension(30, 0)));
            }
        }

    }


    public void visible(boolean v){
        frame.setVisible(v);
    }
}
