import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeckEditorUI implements ActionListener {
    private JFrame editorUI;
    private JPanel editorPanel;
    private JButton editDeckButton, newCardButton;
    private JComboBox<String> termsBox;
    private JComboBox<String> definitionsBox;
    private JTextArea termPrompt, definitionPrompt;
    private ArrayList<String> terms, definitions;
    private CardCreator editSet;

    public DeckEditorUI(String nameOfSet) {
        editSet = CardCreator.getSelectedDeck(nameOfSet);
        editorUI = SwingBank.createJFrame("Edit Deck", 600, 600);
        editorUI.setVisible(true);

        editorPanel = new JPanel();
        editorPanel.setBackground(SwingBank.getLightGrey());

        editDeckButton = SwingBank.createTextJButton("Make Edit");
        editDeckButton.addActionListener(this);
        editDeckButton.setBackground(SwingBank.getLightGrey());
        editDeckButton.setOpaque(true);

        newCardButton = SwingBank.createTextJButton("Create Card");
        newCardButton.addActionListener(this);
        newCardButton.setBackground(SwingBank.getLightGrey());
        newCardButton.setOpaque(true);

        if (editSet != null) {
            terms = editSet.getTerms();
            definitions = editSet.getDefinitions();
            revalidateTermCombo();
            revalidateDefinitionCombo();
            termPrompt = new JTextArea();
            termPrompt.setLineWrap(true);
            termPrompt.setBorder(SwingBank.getTextBorder());
            definitionPrompt = new JTextArea();
            definitionPrompt.setLineWrap(true);
            definitionPrompt.setBorder(SwingBank.getTextBorder());
            editorPanel.removeAll();
            editorPanel.revalidate();
            editorPanel.repaint();
            GridLayout editorLayout = new GridLayout(0, 2);
            editorPanel.setLayout(editorLayout);
            editorPanel.add(termsBox);
            editorPanel.add(definitionsBox);
            editorPanel.add(termPrompt);
            editorPanel.add(definitionPrompt);
            editorPanel.add(editDeckButton);
            editorPanel.add(newCardButton);
            editorUI.add(editorPanel);
        }
    }

    public void revalidateTermCombo() {
        try {
            editorPanel.remove(termsBox);
        }
        catch (Exception e) {}
        termsBox = new JComboBox<String>();
        for (int i = 0; i < terms.size(); i++) {
            String temp = terms.get(i);
            if (temp.length() > 30) {
                temp = temp.substring(0, 30) + "...";
            }
            termsBox.addItem((i+1) + ". " + temp);
        }
        for (Component component : termsBox.getComponents())
        {
            if (component instanceof JButton) {
                termsBox.remove(component);
            }
        }
        termsBox.addActionListener(this);
        termsBox.revalidate();
        termsBox.repaint();
        editorPanel.add(termsBox, 0);
        editorPanel.revalidate();
        editorPanel.repaint();
    }

    public void revalidateDefinitionCombo() {
        try {
            editorPanel.remove(definitionsBox);
        }
        catch (Exception ignored) {}
        definitionsBox = new JComboBox<>();
        for (int i = 0; i < definitions.size(); i++) {
            String temp = definitions.get(i);
            if (temp.length() > 30) {
                temp = temp.substring(0, 30) + "...";
            }
            definitionsBox.addItem((i+1) + ". " + temp);
        }
        for (Component component : definitionsBox.getComponents())
        {
            if (component instanceof JButton) {
                definitionsBox.remove(component);
            }
        }
        definitionsBox.addActionListener(this);
        definitionsBox.revalidate();
        definitionsBox.repaint();
        editorPanel.add(definitionsBox, 0, 1);
        editorPanel.revalidate();
        editorPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == termsBox) {
            int i = termsBox.getSelectedIndex();
            termPrompt.setText(terms.get(i));
        }
        else if (e.getSource() == definitionsBox) {
            int i = definitionsBox.getSelectedIndex();
            definitionPrompt.setText(definitions.get(i));
        }
        else if (e.getSource() == editDeckButton)
        {
            int indexOfTerm = termsBox.getSelectedIndex();
            int indexOfDefinition = definitionsBox.getSelectedIndex();
            String newTerm = termPrompt.getText();
            String newDefinition = definitionPrompt.getText();
            if (editSet != null) {
                if (newTerm.length() > 0) {
                    editSet.replaceCard(indexOfTerm, newTerm, true);
                }
                if (newDefinition.length() > 0) {
                    editSet.replaceCard(indexOfDefinition, newDefinition, false);
                }
            }
            revalidateTermCombo();
            revalidateDefinitionCombo();
        }
        else {
            editSet.addNewCard(termPrompt.getText(), definitionPrompt.getText());
            revalidateTermCombo();
            revalidateDefinitionCombo();
        }
    }

}

