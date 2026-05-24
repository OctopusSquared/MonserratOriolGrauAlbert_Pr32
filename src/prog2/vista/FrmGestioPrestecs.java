package prog2.vista;

import prog2.vista.AppBiblioUB;

import javax.swing.*;
import java.awt.*;

public class FrmGestioPrestecs extends JDialog {

    private final prog2.vista.ControladorGUI ctrl;
    private final DefaultListModel<String> model;

    public FrmGestioPrestecs(AppBiblioUB appBiblioUB, ControladorGUI ctrl, prog2.vista.ControladorGUI ctrl1, DefaultListModel<String> model) {
        this.ctrl = ctrl1;
        this.model = model;
    }
}