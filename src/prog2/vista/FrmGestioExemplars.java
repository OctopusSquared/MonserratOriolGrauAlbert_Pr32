package prog2.vista;

import javax.swing.*;
import java.awt.*;

public class FrmGestioExemplars extends JDialog {

    private final ControladorGUI ctrl;
    private final DefaultListModel<String> model = new DefaultListModel<>();

    public FrmGestioExemplars(JFrame parent, ControladorGUI ctrl) {
        super(parent, "Gestió Exemplars", true);
        this.ctrl = ctrl;

        setSize(520, 360);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        JList<String> lst = new JList<>(model);

        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnAfegir = new JButton("Afegir exemplar");

        btnRefrescar.addActionListener(e -> carregar());
        btnAfegir.addActionListener(e -> {
            new prog2.vista.DlgAfegirExemplar(parent, ctrl).setVisible(true);
            carregar();
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnRefrescar);
        top.add(btnAfegir);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(lst), BorderLayout.CENTER);

        carregar();
    }

    private void carregar() {
        try {
            model.clear();
            for (String x : ctrl.exemplars()) model.addElement(x);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
