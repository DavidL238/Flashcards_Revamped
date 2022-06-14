import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class SwingBank {
    public static Color getDark() {
        return new Color(12,12,12);
    }

    public static Color getLight() {
        return new Color(255,255,255);
    }

    public static Color getLightGrey() {
        return new Color(211, 211, 211);
    }

    public static Color getDarkGrey() {
        return new Color(40,40,40);
    }

    public static Border getTextBorder() {
        return new LineBorder(SwingBank.getDark(), 1, true);
    }

    public static JFrame createJFrame(String name, int dimensionX, int dimensionY) {
        JFrame returnFrame = new JFrame(name);
        returnFrame.setVisible(false);
        returnFrame.setSize(dimensionX, dimensionY);
        returnFrame.setResizable(false);
        returnFrame.setLocationRelativeTo(null);
        return returnFrame;
    }

    public static JButton createIconJButton(String iconPath) {
        JButton newButton = new JButton();
        ImageIcon icon = formatIcon(iconPath);
        newButton.setIcon(icon);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusPainted(false);
        return newButton;
    }

    public static ImageIcon formatIcon(String iconPath) {
        ImageIcon buttonIcon = new ImageIcon(Objects.requireNonNull(SwingBank.class.getResource(iconPath)));
        Image image = buttonIcon.getImage();
        image = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(image);
        return buttonIcon;
    }

    public static ImageIcon formatIcon(String iconPath, int width, int height) {
        ImageIcon buttonIcon = new ImageIcon(Objects.requireNonNull(SwingBank.class.getResource(iconPath)));
        Image image = buttonIcon.getImage();
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(image);
        return buttonIcon;
    }

    public static JButton createTextJButton(String text) {
        JButton newButton = new JButton(text);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusPainted(false);
        return newButton;
    }

    public static JButton createTextJButton(String text, Dimension dimension) {
        JButton newButton = createTextJButton(text);
        newButton.setPreferredSize(dimension);
        return newButton;
    }

    public static JTextField createJTextField(String text, int fontSize) {
        Border transparent = javax.swing.BorderFactory.createEmptyBorder();
        JTextField newText = new JTextField(text);
        newText.setPreferredSize(new Dimension(100, 20));
        newText.setEditable(false);
        newText.setFocusable(false);
        newText.setFont(new Font("Roboto", Font.PLAIN, fontSize)); //better font
        newText.setOpaque(false);
        newText.setBorder(transparent);
        return newText;
    }
}
