package presentazione;

import persistence.DataAccess;

import javax.swing.*;
import java.awt.*;

public class SelectQueueView {
    private JFrame frame=new JFrame();
    private JLabel sceltaCoda=new JLabel("Seleziona tipo d'operazione da gestire");
    private JComboBox<String> operazioni=new JComboBox<String>();
    private JPanel pannello1= new JPanel(new GridLayout(4,1));
    private JButton bottone= new JButton("Invio");

    public SelectQueueView(){
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        frame.setTitle("MedQueue");
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground( Color.white );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation ((screenSize.width/2)-(frame.getWidth()/2),(screenSize.height/2)-(frame.getHeight()/2)); //Posiziono il frame al centro dello schermo
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

    }

    public void visible(boolean v){
        frame.setVisible(v);
    }


}
