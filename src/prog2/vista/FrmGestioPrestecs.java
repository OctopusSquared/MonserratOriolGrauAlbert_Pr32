package prog2.vista;

import prog2.vista.AppBiblioUB;

import javax.swing.*;
import java.awt.*;

public class FrmGestioPrestecs extends JDialog {

    private final Vista.ControladorGUI ctrl;
    private final DefaultListModel<String> model;

    public FrmGestioPrestecs(AppBiblioUB appBiblioUB, Vista.ControladorGUI ctrl, Vista.ControladorGUI ctrl1, DefaultListModel<String> model) {
        this.ctrl = ctrl1;
        this.model = model;
    }
