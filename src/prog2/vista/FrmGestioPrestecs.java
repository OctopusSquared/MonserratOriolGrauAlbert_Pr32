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

        setSize(600, 400);
        setTitle("Gestio Prestecs");
        setLocationRelativeTo(parent);
        setContentPane(panel1);
        setModal(true);
        prestecsRetornatsCheckBox.setSelected(true);

        model = new DefaultListModel<>();
        Prestecs.setModel(model);
        carregarPrestecs();

        this.adaptador = adaptador;

        prestecsRetornatsCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
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
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        tornarEnrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    }

    private void carregarPrestecs() {
        // Buidar model
        model.clear();

        // Afegim iterativament
        ArrayList<String> prestecs = (ArrayList<String>) (prestecsRetornatsCheckBox.isSelected() ? adaptador.llistaPrestecsToLlistaString() : adaptador.llistaPrestecsNoRetornatsToLlistaString());
        for (String u : prestecs) {
                model.addElement(u);
        }
    }

}


