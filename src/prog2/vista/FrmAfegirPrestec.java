package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmAfegirPrestec extends JDialog {

    private JComboBox Usuari;
    private JComboBox Exemplar;
    private JButton afegirButton;
    private JButton cancelarButton;
    private JCheckBox prestecLlargCheckBox;
    private JPanel panel;

    private Adaptador adaptador;


    public FrmAfegirPrestec(FrmGestioPrestecs parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;

        this.setSize(700, 400);
        this.setTitle("Afegir Prestec");
        this.setLocationRelativeTo(parent);
        setContentPane(panel);
        this.setModal(true);

        carregarExemplars();
        carregarUsuaris();


        afegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afegirPrestec();
            }
        });
        Exemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprovarPrestecLlarg();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void afegirPrestec() {
        int exemplarPos = Exemplar.getSelectedIndex();
        int usuariPos   = Usuari.getSelectedIndex();
        boolean esLlarg = prestecLlargCheckBox.isSelected();

        if (exemplarPos < 0 || usuariPos < 0) {
            JOptionPane.showMessageDialog(this, "S'ha de seleccionar un Usuari i Un Exemplar", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirPrestec(exemplarPos, usuariPos, esLlarg);
            JOptionPane.showMessageDialog(this, "Prestec afegit.");
            dispose();
        } catch (BiblioException ex) {
            JOptionPane.showMessageDialog(this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void carregarExemplars() {
        Exemplar.removeAllItems();
        ArrayList<String> exemplars = (ArrayList<String>) adaptador.llistaExemplarsToLlistaString();
        for (String ex : exemplars) {
            Exemplar.addItem(ex);
        }
        // Comprovem l'estat inicial
        comprovarPrestecLlarg();
    }
    private void carregarUsuaris() {
        Usuari.removeAllItems();
        ArrayList<String> usuaris = (ArrayList<String>) adaptador.llistaUsuarisToLlistaString();
        for (String u : usuaris) {
            Usuari.addItem(u);
        }
    }
    private void comprovarPrestecLlarg() {
        int idx = Exemplar.getSelectedIndex();
        if (idx < 0) {
            prestecLlargCheckBox.setEnabled(false);
            prestecLlargCheckBox.setSelected(false);
            return;
        }

        String exemplarStr = (String) Exemplar.getSelectedItem();
        boolean admet = exemplarStr != null && exemplarStr.contains("AdmetPrestecLlarg=true");
        prestecLlargCheckBox.setEnabled(admet);
        if (!admet) prestecLlargCheckBox.setSelected(false);
    }
}
