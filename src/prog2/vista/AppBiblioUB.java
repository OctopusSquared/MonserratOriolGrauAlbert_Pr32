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

    private JLabel Separador1;
    private JLabel Separador2;

    private Adaptador adaptador;



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB biblioUB = new AppBiblioUB();
            biblioUB.setVisible(true);
        });
    }
    public AppBiblioUB() {
        adaptador  = new Adaptador();

        setSize(500,300);
        setTitle("Biblioteca UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(biblioUB);

        guardarDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFitxer = JOptionPane.showInputDialog(AppBiblioUB.this, "Introdueix el nom del fitxer:", "Guardar Dades", JOptionPane.QUESTION_MESSAGE);

                if (nomFitxer != null && !nomFitxer.trim().isEmpty()) {
                    try {
                        adaptador.guardaDades(nomFitxer.trim());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades guardades.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioUsuaris gestioUsuaris = new FrmGestioUsuaris(AppBiblioUB.this, adaptador);
                gestioUsuaris.setVisible(true);
            }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars gestioExemplars = new FrmGestioExemplars(AppBiblioUB.this, adaptador);
                gestioExemplars.setVisible(true);
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
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades carregades.");
                    } catch (BiblioException ex) {
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Atencio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs gestioPrestecs = new FrmGestioPrestecs(AppBiblioUB.this, adaptador);
                gestioPrestecs.setVisible(true);
            }
        });
    }

}
