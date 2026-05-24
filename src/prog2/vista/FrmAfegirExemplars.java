package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirExemplars extends JDialog {
    private JPanel panel;
    private JTextField txtId;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JCheckBox prestecLlargCheckBox;
    private JButton afegirButton;
    private JButton cancelaButton;
    private Adaptador adaptador;
    public FrmAfegirExemplars(FrmGestioExemplars parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setTitle("Afegir Exemplar");
        setContentPane(panel);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setModal(true);
        afegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionarAfegirExemplars();
            }
        });
        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void accionarAfegirExemplars() {
        String id = txtId.getText().trim();
        String titol = txtTitol.getText().trim();
        String autor = txtAutor.getText().trim();
        boolean prestecLlarg = prestecLlargCheckBox.isSelected();

        if (id.isEmpty() || titol.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "S'han d'omplir tots els camps obligatoris.",
                    "Camps Buits",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirExemplar(id, titol, autor, prestecLlarg);
            JOptionPane.showMessageDialog(this, "Exemplar afegit correctament.");
            dispose();
        } catch (BiblioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
