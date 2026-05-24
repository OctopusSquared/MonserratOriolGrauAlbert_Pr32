package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppBiblioUB extends JFrame {

    private final prog2.vista.ControladorGUI ctrl;

    public AppBiblioUB() {
        this.ctrl = new prog2.vista.ControladorGUI(new Vista.AdaptadorFacade());

        setTitle("BiblioUB - Pràctica 3 Part 2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 220);
        setLocationRelativeTo(null);

        JButton btnUsuaris = new JButton("Gestió Usuaris");
        JButton btnExemplars = new JButton("Gestió Exemplars");
        JButton btnPrestecs = new JButton("Gestió Préstecs");
        JButton btnGuardar = new JButton("Guardar dades");
        JButton btnCarregar = new JButton("Carregar dades");

        btnUsuaris.addActionListener(e -> new prog2.vista.FrmGestioUsuaris(this, ctrl).setVisible(true));
        btnExemplars.addActionListener(e -> new prog2.vista.FrmGestioExemplars(this, ctrl).setVisible(true));
        btnPrestecs.addActionListener(e -> new prog2.vista.FrmGestioPrestecs(this, ctrl).setVisible(true));

        btnGuardar.addActionListener(e -> guardar());
        btnCarregar.addActionListener(e -> carregar());

        JPanel p = new JPanel(new GridLayout(2, 3, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(btnUsuaris);
        p.add(btnExemplars);
        p.add(btnPrestecs);
        p.add(btnGuardar);
        p.add(btnCarregar);

        setContentPane(p);
    }

    private void guardar() {
        try {
            String nom = JOptionPane.showInputDialog(this, "Nom del fitxer per guardar:");
            if (nom == null || nom.trim().isEmpty()) return;
            ctrl.guardar(nom.trim());
            JOptionPane.showMessageDialog(this, "Dades guardades correctament.");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void carregar() {
        try {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(this);
            if (res != JFileChooser.APPROVE_OPTION) return;
            File f = fc.getSelectedFile();
            ctrl.carregar(f);
            JOptionPane.showMessageDialog(this, "Dades carregades correctament.");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppBiblioUB().setVisible(true));
    }
}
