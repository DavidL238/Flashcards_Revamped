import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class ImporterUI implements ActionListener {
    private JFrame importerUI;
    private JButton importButton;
    private JTextArea importPrompt;
    private JTextField supportedSites;
    
    public ImporterUI() {
        importerUI = SwingBank.createJFrame("Card Importer", 300, 300);
        importerUI.setVisible(true);
        
        importButton = SwingBank.createTextJButton("Import Flash Cards");
        importButton.addActionListener(this);

        importPrompt = new JTextArea("Paste URL Here!");
        importPrompt.setPreferredSize(new Dimension(50, 90));
        importPrompt.setLineWrap(true);
        importPrompt.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 importPrompt.setText("");
             }
         }
        );
        
        supportedSites = SwingBank.createJTextField("Supported Sites: Quizlet & Cram", 12);
        importerUI.add(supportedSites, BorderLayout.NORTH);
        importerUI.add(importPrompt, BorderLayout.CENTER);
        importerUI.add(importButton, BorderLayout.SOUTH);
        importPrompt.setBounds(0, 200, 50, 280);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == importButton) {
            String url = importPrompt.getText();
            ResponseUI r;
            if (url != null && !url.equals("")) {
                try {
                    URL webURL = new URL(url);
                    CardImporter cI = new CardImporter(webURL);
                    if (!cI.isSuccessful()) {
                        r = new ResponseUI("Error", "Site not supported");
                    }
                    else {
                        r = new ResponseUI("Success", "Imported " + importPrompt.getText());
                    }
                }
                catch (MalformedURLException malURL) {
                    r = new ResponseUI("Error", "Error: Improper URL (MalformedURLException)");
                }
                catch (Exception otherExceptions) {
                    r = new ResponseUI("Error", "Something went wrong..!");
                }
            }
        }
    }
}
