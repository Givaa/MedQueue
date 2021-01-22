package presentazione;

import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectQueueView {
    private JFrame frame=new JFrame();
    private JLabel sceltaCoda=new JLabel("Seleziona tipo d'operazione da gestire");
    private JComboBox<String> operazioni=new JComboBox<String>();
    private JPanel pannello1= new JPanel(new GridLayout(4,1));
    private JButton bottone= new JButton("Invio");



    public SelectQueueView(){
        frame.setTitle("MedQueue");
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //Posiziono il frame al centro dello schermo
        pannello1.setBorder(BorderFactory.createTitledBorder("Operazione"));
        //POPOLO JCOMBO BOX
        for(int i = 0; i< DataAccess.getOperazioni().size(); i++)
            operazioni.addItem(DataAccess.getOperazioni().get(i));
        pannello1.add(sceltaCoda);
        pannello1.add(operazioni);
        pannello1.add(new JLabel());
        pannello1.add(bottone);
        pannello1.setBackground( Color.white );
        frame.add(pannello1,BorderLayout.CENTER);

        bottone.addActionListener(e->{
            frame.setVisible(false);
            new ListPrenotazioniView().visible(true);
        });

        ImageIcon infermiera = new ImageIcon("src/image/frameIcon.png");
        frame.setIconImage(infermiera.getImage());

    }

    public void visible(boolean v){
        frame.setVisible(v);
    }


}
