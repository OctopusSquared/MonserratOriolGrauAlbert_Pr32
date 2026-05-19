package Vista;

import javax.swing.*;
import java.awt.*;

public class DlgAfegirExemplar extends JDialog {

    public DlgAfegirExemplar(JFrame parent, ControladorGUI ctrl) {
        super(parent, "Afegir exemplar", true);

        setSize(420, 240);
        setLocationRelativeTo(parent);

        JTextField txtId = new JTextField(20);
        JTextField txtTitol = new JTextField(20);
        JCheckBox chkLlarg = new JCheckBox("Admet préstec llarg"); // requerit [1](https://github.com/OctopusSquared/MonserratOriolGrauAlbert)

        JButton btnAcceptar = new JButton("Acceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAcceptar.addActionListener(e -> {
            try {
                ctrl.afegirExemplar(txtId.getText().trim(), txtTitol.getText().trim(), chkLlarg.isSelected());
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel form = new JPanel(new GridLayout(3,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Identificador:"));
        form.add(txtId);
        form.add(new JLabel("Títol:"));
        form.add(txtTitol);
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
