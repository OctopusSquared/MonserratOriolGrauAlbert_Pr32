package Vista;

import javax.swing.*;
import java.awt.*;

public class DlgAfegirUsuari extends JDialog {

    public DlgAfegirUsuari(JFrame parent, ControladorGUI ctrl) {
        super(parent, "Afegir usuari", true);

        setSize(420, 240);
        setLocationRelativeTo(parent);

        JTextField txtNom = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JCheckBox chkEstudiant = new JCheckBox("És estudiant"); // requerit [1](https://github.com/OctopusSquared/MonserratOriolGrauAlbert)

        JButton btnAcceptar = new JButton("Acceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAcceptar.addActionListener(e -> {
            try {
                ctrl.afegirUsuari(txtNom.getText().trim(), txtEmail.getText().trim(), chkEstudiant.isSelected());
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel form = new JPanel(new GridLayout(3,2,8,8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Nom:"));
        form.add(txtNom);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel(" "));
        form.add(chkEstudiant);

        JPanel botons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botons.add(btnAcceptar);
        botons.add(btnCancelar);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(botons, BorderLayout.SOUTH);
    }
}
