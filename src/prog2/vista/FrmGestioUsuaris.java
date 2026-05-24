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
        this.adaptador = adaptador;
        setTitle("Gestio Usuari");
        setContentPane(panel);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        model = new DefaultListModel<>();
        Usuaris.setModel(model);
        carregarUsuaris();
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
        model.clear(); // buidem el model
        ArrayList<String> usuaris = (ArrayList<String>) adaptador.llistaUsuarisToLlistaString();
        for (String u : usuaris) {
            model.addElement(u); // afegim al model, el JList es refresca sol
        }
    }

}
