import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectionUI implements ActionListener {
    private StudyUI studyUI;
    private JFrame selectionUI;
    private JComboBox<String> deckComboBox;
    private JButton buttonType;

    public SelectionUI(StudyUI studyUI) {
        this.studyUI = studyUI;
    }

    public void initializeUI(JButton button) {
        initializeButton(button);
        selectionUI = SwingBank.createJFrame("Select Deck", 300, 90);
        ArrayList<String> allTitles = CardCreator.getAllTitles();
        deckComboBox = new JComboBox<>();
        for (String allTitle : allTitles) {
            deckComboBox.addItem(allTitle);
        }
        deckComboBox.setEditable(false);
        selectionUI.getContentPane().add(deckComboBox, BorderLayout.NORTH);
        selectionUI.getContentPane().add(buttonType, BorderLayout.CENTER);
        selectionUI.setVisible(true);
        selectionUI.revalidate();
        selectionUI.repaint();
    }

    private void initializeButton(JButton button) {
        if (button.getText().contains("Study")) {
            buttonType = new JButton("Study");
        }
        else if (button.getText().contains("Match")) {
            buttonType = new JButton("Play");
        }
        else if (button.getText().contains("Edit")) {
            buttonType = new JButton("Edit");
        }
        buttonType.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String text = buttonType.getText();
        selectionUI.setVisible(false);
        String nameOfSet = String.valueOf(deckComboBox.getSelectedItem());
        selectionUI.remove(deckComboBox);
        if (text.contains("Study")) {
            FlashcardsUI flashcardsUI = new FlashcardsUI(nameOfSet);
        }
        else if (text.contains("Play")) {
            MatchTermsUI matchTermsUI = new MatchTermsUI(nameOfSet, studyUI.getSettingsUI().isKahoot());
        }
        else if (text.contains("Edit")) {
            DeckEditorUI deckEditorUI = new DeckEditorUI(nameOfSet);
        }
    }

}


