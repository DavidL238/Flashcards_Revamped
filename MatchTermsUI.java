import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MatchTermsUI implements ActionListener {
    private JFrame matchTermsUI;
    private JPanel topPanel, promptPanel;
    private JButton cOne, cTwo, cThree, cFour;
    private JTextArea questionArea;
    private JLabel score;
    private ArrayList<String> choices;
    private CardShuffler shuffler;
    private boolean showScreen;

    public MatchTermsUI(String nameOfSet, boolean showScreen) {
        this.showScreen = showScreen;
        matchTermsUI = SwingBank.createJFrame("Multiple Choice", 800, 600);
        matchTermsUI.setVisible(true);

        CardCreator questionDeck = CardCreator.getSelectedDeck(nameOfSet);
        shuffler = new CardShuffler(questionDeck);
        Font kahoot = new Font("Montserrat", Font.PLAIN, 32);
        Font kahootChoices = new Font("Montserrat", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(300, 150);

        questionArea = new JTextArea(shuffler.getTerm());
        questionArea.setFont(kahoot);
        questionArea.setOpaque(false);
        questionArea.setEditable(false);
        questionArea.setPreferredSize(new Dimension(600, 200));

        choices = shuffler.getChoices();
        cOne = SwingBank.createTextJButton(choices.get(0), buttonSize);
        cOne.setFont(kahootChoices);
        cOne.setBackground(Color.decode("#ff3355"));
        cOne.setOpaque(true);
        cOne.addActionListener(this);
        cTwo = SwingBank.createTextJButton(choices.get(1), buttonSize);
        cTwo.setFont(kahootChoices);
        cTwo.setBackground(Color.decode("#45a3e5"));
        cTwo.addActionListener(this);
        cTwo.setOpaque(true);
        cThree = SwingBank.createTextJButton(choices.get(2), buttonSize);
        cThree.setFont(kahootChoices);
        cThree.setBackground(Color.decode("#ffc00a"));
        cThree.addActionListener(this);
        cThree.setOpaque(true);
        cFour = SwingBank.createTextJButton(choices.get(3), buttonSize);
        cFour.setFont(kahootChoices);
        cFour.setBackground(Color.decode("#66bf39"));
        cFour.addActionListener(this);
        cFour.setOpaque(true);

        score = new JLabel("Score: 0");

        topPanel = new JPanel();
        topPanel.add(score);
        promptPanel = new JPanel();
        promptPanel.add(questionArea);
        promptPanel.add(cOne);
        promptPanel.add(cTwo);
        promptPanel.add(cThree);
        promptPanel.add(cFour);

        matchTermsUI.add(topPanel, BorderLayout.NORTH);
        matchTermsUI.add(promptPanel, BorderLayout.CENTER);
    }

    public void reinitializeUI() {
        questionArea.setText(shuffler.getTerm());
        choices = shuffler.getChoices();
        cOne.setText(choices.get(0));
        cTwo.setText(choices.get(1));
        cThree.setText(choices.get(2));
        cFour.setText(choices.get(3));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean c = false;
        if (e.getSource() == cOne) {
            c = shuffler.selectAnswer(cOne.getText());
        }
        else if (e.getSource() == cTwo) {
            c = shuffler.selectAnswer(cTwo.getText());
        }
        else if (e.getSource() == cThree) {
            c = shuffler.selectAnswer(cThree.getText());
        }
        else if (e.getSource() == cFour) {
            c = shuffler.selectAnswer(cFour.getText());
        }
        score.setText("Score: " + shuffler.getScore());
        JFrame temp;
        ImageIcon result;
        if (showScreen) {
            if (c) {
                temp = SwingBank.createJFrame("Correct!", 600, 600);
                result = SwingBank.formatIcon("images\\success.png", 600, 600);
            } else {
                temp = SwingBank.createJFrame("Incorrect!", 600, 600);
                result = SwingBank.formatIcon("images\\failure.png", 600, 600);
            }
            temp.add(new JLabel(result));
            temp.setVisible(true);
        }
        reinitializeUI();
    }

}
