package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppBiblioUB extends JFrame {

    private JPanel biblioUB;
    private JButton btnGestioUsuaris;
    private JButton btnGestioExemplars;
    private JButton btnGestioPrestecs;
    private JButton guardarDadesButton;
    private JButton carregarDadesButton;
    private JButton sortirButton;
    private Adaptador adaptador;



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB biblioUB = new AppBiblioUB();
            biblioUB.setVisible(true);
        });
    }
    public AppBiblioUB() {
        adaptador  = new Adaptador();
        setTitle("Biblioteca UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(biblioUB);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioUsuaris gestioUsuaris = new FrmGestioUsuaris(AppBiblioUB.this, adaptador);
                gestioUsuaris.setVisible(true);
            }
        });
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars gestioExemplars = new FrmGestioExemplars(AppBiblioUB.this, adaptador);
                gestioExemplars.setVisible(true);
            }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs gestioPrestecs = new FrmGestioPrestecs(AppBiblioUB.this, adaptador);
                gestioPrestecs.setVisible(true);
            }
        });
        guardarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFitxer = JOptionPane.showInputDialog(
                        AppBiblioUB.this,
                        "Introdueix el nom del fitxer:",
                        "Guardar Dades",
                        JOptionPane.QUESTION_MESSAGE);
                if (nomFitxer != null && !nomFitxer.trim().isEmpty()) {
                    try {
                        adaptador.guardaDades(nomFitxer.trim());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades guardades correctament.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                "Error: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        carregarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Carregar Dades");
                int resultat = fileChooser.showOpenDialog(AppBiblioUB.this);

                if (resultat == JFileChooser.APPROVE_OPTION) {
                    String cami = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        adaptador.carregaDades(cami);
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades carregades correctament.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                "Error: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
    }

}
