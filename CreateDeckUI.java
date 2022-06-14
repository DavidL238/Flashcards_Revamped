import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CreateDeckUI implements ActionListener {
    private JFrame creationUI;
    private JPanel creationPanel;
    private JLabel nameLabel, termLabel, definitionLabel;
    private JTextArea namePrompt, termPrompt, definitionPrompt;
    private JButton createDeckButton;

    
    public CreateDeckUI() {
        creationUI = SwingBank.createJFrame("Deck Creator", 600, 600);
        creationUI.setVisible(true);
        
        creationPanel = new JPanel();
        GroupLayout creationLayout = new GroupLayout(creationPanel);
        creationLayout.setAutoCreateGaps(true);
        creationLayout.setAutoCreateContainerGaps(true);
        creationPanel.setLayout(creationLayout);
        creationPanel.setBackground(SwingBank.getLightGrey());

        namePrompt = new JTextArea("Deck Name");
        namePrompt.setPreferredSize(new Dimension (50, 50));
        namePrompt.setLineWrap(true);
        namePrompt.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  namePrompt.setText("");
              }
            }
        );

        termPrompt = new JTextArea("Deck Term");
        termPrompt.setPreferredSize(new Dimension (50, 50));
        termPrompt.setLineWrap(true);
        termPrompt.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  termPrompt.setText("");
              }
          }
        );

        definitionPrompt = new JTextArea("Deck Definition");
        definitionPrompt.setPreferredSize(new Dimension(50 , 50));
        definitionPrompt.setLineWrap(true);
        definitionPrompt.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    definitionPrompt.setText("");
                }
            }
        );

        nameLabel = new JLabel("Name of Deck:");
        termLabel = new JLabel("Deck Term:");
        definitionLabel = new JLabel("Deck Definition:");

        createDeckButton = SwingBank.createTextJButton("Finalize");
        createDeckButton.addActionListener(this);
        createDeckButton.setBackground(SwingBank.getLight());
        createDeckButton.setOpaque(true);

        creationUI.getContentPane().add(creationPanel);
        creationLayout.setHorizontalGroup(
                creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameLabel)
                        .addComponent(namePrompt)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER))
                        .addComponent(termLabel)
                        .addComponent(termPrompt)
                        .addComponent(definitionLabel)
                        .addComponent(definitionPrompt)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING))
                        .addComponent(createDeckButton)
        );
        creationLayout.setVerticalGroup(
                creationLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addComponent(namePrompt)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.CENTER))
                        .addComponent(termLabel)
                        .addComponent(termPrompt)
                        .addComponent(definitionLabel)
                        .addComponent(definitionPrompt)
                        .addGroup(creationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING))
                        .addComponent(createDeckButton)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String invalid = "#%&{}\\<>*?/$!'\":@+`|=";
        boolean valid = true;
        for (int i = 0; i < invalid.length(); i++) {
            String temp = invalid.substring(i, i+1);
            if (namePrompt.getText().contains(temp)) {
                valid = false;
                break;
            }
        }
        if (valid) {
            if (!termPrompt.getText().contains("|") && !definitionPrompt.getText().contains("|")) {
                CardCreator cardCreator = new CardCreator(namePrompt.getText());
                cardCreator.addNewCard(termPrompt.getText(), definitionPrompt.getText());
                creationUI.setVisible(false);
            }
            else {
                //create error ui
            }
        }
    }
}