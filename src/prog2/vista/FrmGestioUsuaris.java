package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioUsuaris extends JDialog {

    private JPanel panel;
    private JButton btnAfegir;
    private JList Usuaris;
    private JButton btnTornar;
    private Adaptador adaptador;
    private DefaultListModel<String> model;

    public FrmGestioUsuaris(AppBiblioUB parent, Adaptador adaptador) {
        super(parent);

        setSize(600, 400);
        setTitle("Gestio Usuari");
        setLocationRelativeTo(parent);
        setContentPane(panel);
        setModal(true);

        model = new DefaultListModel<>();
        Usuaris.setModel(model);
        carregarUsuaris();

        this.adaptador = adaptador;

        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari afegirUsuari = new FrmAfegirUsuari(FrmGestioUsuaris.this, adaptador);
                afegirUsuari.setVisible(true);
                carregarUsuaris();
            }
        });
        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void carregarUsuaris() {
        // Buidar model
        model.clear();

        // Afegim iterativament
        ArrayList<String> usuaris = (ArrayList<String>) adaptador.llistaUsuarisToLlistaString();
        for (String usu : usuaris) {
            model.addElement(usu);
        }
    }

}
