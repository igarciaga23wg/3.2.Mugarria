package Interfazea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Interfazea extends JFrame implements ActionListener{
    private int x;
    private int y;
    private String izena;
    private JFrame frame;
    private JPanel menua, menua2;
    private JComboBox<String> lista;
    private JButton garbitu, itxi;
    private JTextArea textua;
    private JScrollPane scroll;

    public Interfazea(String izena, int x, int y){
        this.x = x;
        this.y = y;
        this.izena = izena;

        sortuInterfazea();

        menua();
        menua2();

        erakutsi();

    }
    void sortuInterfazea(){
        frame = new JFrame(izena);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(x, y));
    }
    void erakutsi(){
        frame.add(menua, BorderLayout.WEST);
        frame.add(menua2, BorderLayout.EAST);



        frame.pack();
        frame.setVisible(true);
    }
    void menua(){

        menua = new JPanel();


        lista = new JComboBox<String>();
        lista.setBounds(100, 10, 150, 20);
        lista.addItem("Python.txt");
        lista.addItem("C.txt");
        lista.addItem("Java.txt");
        lista.addActionListener(this);


        lista.setPreferredSize(new Dimension(200, 25));
        menua.add(lista);

        garbitu = new JButton("Clear");
        garbitu.addActionListener(this);
        menua.add(garbitu);

    }
    public void menua2() {
        menua2 = new JPanel();
        menua2.setLayout(new BorderLayout());

        textua = new JTextArea();
        textua.setLineWrap(true);
        textua.setPreferredSize(new Dimension(488, 600));

        JScrollPane scroll = new JScrollPane(textua);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        menua2.add(scroll, BorderLayout.CENTER);

        itxi = new JButton("Close");
        itxi.addActionListener(this);
        itxi.setPreferredSize(new Dimension(2, 20));

        menua2.add(itxi, BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == garbitu) {
            textua.setText("");

        } else if (e.getSource() == itxi) {

            System.exit(0);

        }else if (e.getSource() == lista) {

            String selectedOption = (String) lista.getSelectedItem();

            try {

                BufferedReader r1 = new BufferedReader(new FileReader("3.2.Mugarria\\Fitxategiak\\"+selectedOption));
                StringBuilder string = new StringBuilder();

                String line;
                while ((line = r1.readLine()) != null) {
                    string.append(line).append("\n");
                }
                textua.setText(string.toString());
                r1.close();

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, (selectedOption+" fitxategia ezin izan da aurkitu"), "Error: 404", JOptionPane.ERROR_MESSAGE);
                System.err.println(selectedOption+" fitxategia ezin izan da aurkitu");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, (selectedOption+" fitxategia ezin izan da ongi itxi"), "Error: 808", JOptionPane.ERROR_MESSAGE);

                System.err.println(selectedOption+" fitxategia ezin izan da ongi itxi");
            }
        }

    }

}
