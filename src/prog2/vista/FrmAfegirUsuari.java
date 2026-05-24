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
        this.adaptador = adaptador;
        setTitle("Afegir Usuari");
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);

        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionarAfegirUsuari();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void accionarAfegirUsuari() {
        String email  = txtEmail.getText().trim();
        String nom    = txtNom.getText().trim();
        String adreca = txtAdreca.getText().trim();
        boolean esEst = chkEstudiant.isSelected();

        if (email.isEmpty() || nom.isEmpty() || adreca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "S'han d'omplir tots els camps obligatoris.",
                    "Camps Buits",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirUsuari(email, nom, adreca, esEst);
            JOptionPane.showMessageDialog(this, "Usuari afegit correctament.");
            dispose();
        } catch (BiblioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
