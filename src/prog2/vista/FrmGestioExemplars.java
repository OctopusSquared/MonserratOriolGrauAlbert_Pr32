package prog2.vista;
import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioExemplars extends JDialog {

    private JPanel panel;
    private JList Exemplars;
    private JButton afegirExemplarButton;
    private JButton tornarEnrereButton;

    private Adaptador adaptador;
    private DefaultListModel<String> model;

    public FrmGestioExemplars(AppBiblioUB parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;

        setSize(600, 400);
        setTitle("Gestio Exemplars");
        setLocationRelativeTo(parent);
        setContentPane(panel);
        setModal(true);

        model = new DefaultListModel<>();
        Exemplars.setModel(model);

        carregarExemplars();

        tornarEnrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        afegirExemplarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               FrmAfegirExemplars afegirExemplars = new FrmAfegirExemplars(FrmGestioExemplars.this, adaptador );
               afegirExemplars.setVisible(true);
               carregarExemplars();
            }
        });

    }

    public void carregarExemplars(){
        // Buidar model
        model.clear();

        // Afegim iterativament
        ArrayList<String> exemplars = (ArrayList<String>) adaptador.llistaExemplarsToLlistaString();
        for (String usu : exemplars) {
            model.addElement(usu);
        }
    }
}
