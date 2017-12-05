package osuextractor;

import javax.swing.JOptionPane;
import windows.MainMenu;

public class Main {

    public static void main(String[] args) {
        try {
            MainMenu m = new MainMenu();
            m.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
