package prog2.vista;

import prog2.vista.AppBiblioUB;

import javax.swing.*;
import java.awt.*;

public class FrmGestioPrestecs extends JDialog {

    private final ControladorGUI ctrl;
    private final DefaultListModel<String> model = new DefaultListModel<>();

    public FrmGestioPrestecs(JFrame parent, ControladorGUI ctrl) {
        this.ctrl = ctrl;
    }
}