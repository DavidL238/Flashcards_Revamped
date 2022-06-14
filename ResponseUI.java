import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponseUI implements ActionListener {
    private JFrame responseUI;
    private JPanel responsePanel;
    private JButton okButton;
    private JTextField errorMessage;

    public ResponseUI(String title, String message) {
        responseUI = SwingBank.createJFrame(title, 400, 120);
        responseUI.setVisible(true);
        responsePanel = new JPanel();
        responsePanel.setBackground(SwingBank.getLight());

        okButton = SwingBank.createTextJButton("OK");
        okButton.addActionListener(this);
        okButton.setBackground(SwingBank.getLightGrey());
        okButton.setFocusPainted(false);

        errorMessage = SwingBank.createJTextField(message, 11);
        responseUI.getContentPane().add(responsePanel, BorderLayout.CENTER);
        responseUI.add(okButton, BorderLayout.SOUTH);
        responsePanel.add(errorMessage, BorderLayout.NORTH);
        errorMessage.setPreferredSize(new Dimension(380, 40));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        responseUI.setVisible(false);
    }
}
