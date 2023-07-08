package adminOld_dontuse;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class ButtonsInTextField {

    JPanel gui = new JPanel(new GridBagLayout());
    JTextField textField;

    ButtonsInTextField(int cols) {
        JPanel textFieldWithButtonsPanel = new JPanel(new FlowLayout(
                SwingConstants.LEADING, 5, 1));
        textField = new JTextField(cols);
        textFieldWithButtonsPanel.add(textField);

        addButtonToPanel(textFieldWithButtonsPanel, 8);
        addButtonToPanel(textFieldWithButtonsPanel, 16);
        addButtonToPanel(textFieldWithButtonsPanel, 24);

        // WARNING:  Not sensitive to PLAF change!
        textFieldWithButtonsPanel.setBackground(textField.getBackground());
        textFieldWithButtonsPanel.setBorder(textField.getBorder());
        textField.setBorder(null);
        // END WARNING:  

        gui.add(textFieldWithButtonsPanel);
    }

    private final void addButtonToPanel(JPanel panel, int height) {
        BufferedImage bi = new BufferedImage(
                // find the size of an icon from the system, 
                // this is just a guess
                24, height, BufferedImage.TYPE_INT_RGB);
        JButton b = new JButton(new ImageIcon(bi));
        b.setContentAreaFilled(false);
        //b.setBorderPainted(false);
        b.setMargin(new Insets(0,0,0,0));
        panel.add(b);
    }

    public final JComponent getGui() {
        return gui;
    }

    public final JTextField getField() {
        return textField;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                ButtonsInTextField bitf = new ButtonsInTextField(20);
                JOptionPane.showMessageDialog(null, bitf.getGui());
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}