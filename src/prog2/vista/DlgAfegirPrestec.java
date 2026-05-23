package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DlgAfegirPrestec extends JDialog {

    public DlgAfegirPrestec(JFrame parent, Vista.ControladorGUI ctrl) {
        super(parent, "Afegir préstec", true);

        setSize(520, 260);
        setLocationRelativeTo(parent);

        JComboBox<String> cmbUsuaris = new JComboBox<>();
        JComboBox<String> cmbExemplars = new JComboBox<>();
        JCheckBox chkLlarg = new JCheckBox("Préstec llarg");

        try {
            List<String> us = ctrl.usuaris();
            for (String u : us) cmbUsuaris.addItem(u);

            List<String> ex = ctrl.exemplars();
            for (String x : ex) cmbExemplars.addItem(x);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton btnAcceptar = new JButton("Acceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAcceptar.addActionListener(e -> {
            try {
                String usuariSel = (String) cmbUsuaris.getSelectedItem();
                String exemplarSel = (String) cmbExemplars.getSelectedItem();
                ctrl.afegirPrestec(usuariSel, exemplarSel, chkLlarg.isSelected());
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel form = new JPanel(new GridLayout(3,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Usuari:"));
        form.add(cmbUsuaris);
        form.add(new JLabel("Exemplar:"));
        form.add(cmbExemplars);
        form.add(new JLabel(" "));
        form.add(chkLlarg);

        JPanel botons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botons.add(btnAcceptar);
        botons.add(btnCancelar);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(botons, BorderLayout.SOUTH);
    }
}
