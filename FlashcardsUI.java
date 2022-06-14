import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FlashcardsUI implements ActionListener {
    private ArrayList<String> selectedTerms, selectedDefinitions;
    private JFrame flashcardsUI;
    private JButton flashcardDisplayButton, previousButton, nextButton;
    private JTextArea flashcardInformation;
    private JLabel flashcardIndex;
    private CardCreator deck;
    private int index;

    public FlashcardsUI(String nameOfSet) {
        flashcardsUI = SwingBank.createJFrame("Flashcards", 600, 400);
        deck = CardCreator.getSelectedDeck(nameOfSet);
        index = 0;

        assert deck != null;
        selectedTerms = deck.getTerms();
        selectedDefinitions = deck.getDefinitions();

        flashcardDisplayButton = SwingBank.createTextJButton("Click me to Flip Card");
        flashcardDisplayButton.addActionListener(this);
        flashcardDisplayButton.setBackground(SwingBank.getLightGrey());
        flashcardDisplayButton.setOpaque(true);

        flashcardInformation = new JTextArea("Term: " + selectedTerms.get(0));
        flashcardInformation.setLineWrap(true);
        flashcardInformation.setEditable(false);
        flashcardInformation.setFocusable(false);

        flashcardIndex = new JLabel("1/" + selectedTerms.size(), SwingConstants.CENTER);

        Dimension buttonDimension = new Dimension(100, 400);
        nextButton = SwingBank.createTextJButton("Next", buttonDimension);
        nextButton.addActionListener(this);
        previousButton = SwingBank.createTextJButton("Previous", buttonDimension);
        previousButton.addActionListener(this);

        flashcardsUI.add(flashcardIndex, BorderLayout.NORTH);
        flashcardsUI.add(flashcardInformation, BorderLayout.CENTER);
        flashcardsUI.add(flashcardDisplayButton, BorderLayout.SOUTH);
        flashcardsUI.add(previousButton, BorderLayout.WEST);
        flashcardsUI.add(nextButton, BorderLayout.EAST);
        flashcardsUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flashcardDisplayButton) {
            String text = flashcardInformation.getText();
            if (text.startsWith("Term")) {
                flashcardInformation.setText("Definition: " + selectedDefinitions.get(index));
            }
            else {
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
            }
        }
        else if (e.getSource() == nextButton) {
            if (index < selectedTerms.size() - 1) {
                index++;
                flashcardIndex.setText((index+1) + "/" + selectedTerms.size());
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
            }
        }
        else if (e.getSource() == previousButton) {
            if (index > 0) {
                index--;
                flashcardIndex.setText((index+1) + "/" + selectedTerms.size());
                flashcardInformation.setText("Term: " + selectedTerms.get(index));
            }
        }
    }
}