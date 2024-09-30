package modelo;

import vista.BolsaDeEmpleoGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BolsaDeEmpleoGUI gui = new BolsaDeEmpleoGUI();
            gui.setVisible(true);
        });
    }


}
