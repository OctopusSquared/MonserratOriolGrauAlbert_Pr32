package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioPrestecs extends JDialog {
    private JList Prestecs;
    private JPanel panel1;
    private JCheckBox prestecsRetornatsCheckBox;
    private JButton tornarPrestecButton;
    private JButton afegirPrestecButton;
    private JButton tornarEnrereButton;
    private Adaptador adaptador;
    private DefaultListModel<String> model;

    public FrmGestioPrestecs(AppBiblioUB parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setTitle("Gestio Prestecs");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setModal(true);
        model = new DefaultListModel<>();
        Prestecs.setModel(model);
        prestecsRetornatsCheckBox.setSelected(true);
        carregarPrestecs();
        prestecsRetornatsCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                carregarPrestecs();
            }
        });
        afegirPrestecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec afegirPrestec = new FrmAfegirPrestec(FrmGestioPrestecs.this, adaptador);
                afegirPrestec.setVisible(true);
                carregarPrestecs();
            }
        });
        tornarPrestecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prestecSeleccionat = Prestecs.getSelectedIndex();
                try {
                    adaptador.retornarPrestec(prestecSeleccionat);
                    carregarPrestecs();
                } catch (BiblioException ex) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this,
                            "Error: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        tornarEnrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void carregarPrestecs() {
        model.clear(); // buidem el model
        ArrayList<String> prestecs = (ArrayList<String>) (prestecsRetornatsCheckBox.isSelected() ? adaptador.llistaPrestecsToLlistaString() : adaptador.llistaPrestecsNoRetornatsToLlistaString());
        for (String u : prestecs) {
                model.addElement(u);
        }
    }

}


