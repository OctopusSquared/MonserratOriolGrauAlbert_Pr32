package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JTextField txtEmail;
    private JTextField txtNom;
    private JTextField txtAdreca;
    private JCheckBox chkEstudiant;
    private JButton btnAfegir;
    private JButton btnCancelar;

    private Adaptador adaptador;

    public FrmAfegirUsuari(FrmGestioUsuaris parent, Adaptador adaptador) {
        super(parent);

        setSize(500, 400);
        setTitle("Afegir Usuari");
        setLocationRelativeTo(parent);
        setContentPane(contentPane);
        setModal(true);

        this.adaptador = adaptador;

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionarAfegirUsuari();
            }
        });
    }

    private void accionarAfegirUsuari() {
        String adreca = txtAdreca.getText().trim();
        String email  = txtEmail.getText().trim();
        String nom    = txtNom.getText().trim();
        boolean esEst = chkEstudiant.isSelected();

        if (email.isEmpty() || nom.isEmpty() || adreca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hi ha camps buits. S'han d'omplir tots els campps", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirUsuari(email, nom, adreca, esEst);
            JOptionPane.showMessageDialog(this, "Usuari afegit correctament.");
            dispose();
        } catch (BiblioException ex) {
            JOptionPane.showMessageDialog(this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
