package prog2.vista;

import prog2.vista.AppBiblioUB;

import javax.swing.*;
import java.awt.*;

public class FrmGestioUsuaris extends JDialog {

    private final ControladorGUI ctrl;
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> lst = new JList<>(model);

    public FrmGestioUsuaris(JFrame parent, ControladorGUI ctrl) {
        super(parent, "Gestió Usuaris", true);
        this.ctrl = ctrl;

        setSize(520, 360);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnAfegir = new JButton("Afegir usuari");

        btnRefrescar.addActionListener(e -> carregarLlista());
        btnAfegir.addActionListener(e -> {
            new DlgAfegirUsuari(parent, ctrl).setVisible(true);
            carregarLlista();
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnRefrescar);
        top.add(btnAfegir);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(lst), BorderLayout.CENTER);

        carregarLlista();
    }

    public FrmGestioUsuaris(AppBiblioUB parent, Vista.ControladorGUI ctrl) {
    }

    private void carregarLlista() {
        try {
            model.clear();
            for (String u : ctrl.usuaris()) model.addElement(u);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
