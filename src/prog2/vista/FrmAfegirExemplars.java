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

        setSize(500, 400);
        setTitle("Afegir Exemplar");
        setLocationRelativeTo(parent);
        setContentPane(panel);
        setModal(true);

        this.adaptador = adaptador;


        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        afegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionarAfegirExemplars();
            }
        });
    }

    public void accionarAfegirExemplars() {
        String titol = txtTitol.getText().trim();
        String id = txtId.getText().trim();
        String autor = txtAutor.getText().trim();
        boolean prestecLlarg = prestecLlargCheckBox.isSelected();

        if (id.isEmpty() || titol.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hi ha camps buits. S'han d'omplir tots els campps", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirExemplar(id, titol, autor, prestecLlarg);
            JOptionPane.showMessageDialog(this, "Exemplar afegit.");
            dispose();
        } catch (BiblioException ex) {
            JOptionPane.showMessageDialog(this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
