package view;

import javax.swing.*;

public class TelaPrincipal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMenu();
            }
        });
    }
}

