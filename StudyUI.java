import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudyUI implements ActionListener {
    private JButton cardsIconButton, settingsIconButton, studyButton, matchTermsButton, newDeckButton, editDeckButton, importButton;
    private JFrame mainUI;
    private JPanel menuPanel, mainPanel, welcomePanel;
    private JLabel welcomeLabel;
    private SettingsUI settingsUI;
    private SelectionUI selectionUI;

    public StudyUI() {
        initializeUI();
        settingsUI = new SettingsUI(this);
        selectionUI = new SelectionUI(this);
    }

    public void initializeUI() {
        Dimension buttonSize = new Dimension(220, 120);
        Font buttonFont = new Font("Roboto", Font.PLAIN, 40);
        Font welcomeFont = new Font("Roboto", Font.PLAIN, 40);

        mainUI = SwingBank.createJFrame("Java Study Tool", 852, 720);
        mainUI.setVisible(true);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setVisible(true);
        menuPanel.setBackground(SwingBank.getLightGrey());

        mainPanel = new JPanel();
        mainPanel.setVisible(true);
        mainPanel.setBackground(SwingBank.getLight());

        cardsIconButton = SwingBank.createIconJButton("images\\flashcards.png");
        cardsIconButton.addActionListener(this);
        settingsIconButton = SwingBank.createIconJButton("images\\gear.png");
        settingsIconButton.addActionListener(this);

        studyButton = SwingBank.createTextJButton("Study Flashcards", buttonSize);
        studyButton.addActionListener(this);
        studyButton.setBackground(SwingBank.getLightGrey());
        studyButton.setOpaque(true);
        studyButton.setFont(buttonFont);

        matchTermsButton = SwingBank.createTextJButton("Match The Terms", buttonSize);
        matchTermsButton.addActionListener(this);
        matchTermsButton.setBackground(SwingBank.getLightGrey());
        matchTermsButton.setOpaque(true);
        matchTermsButton.setFont(buttonFont);

        newDeckButton = SwingBank.createTextJButton("Create New Deck", buttonSize);
        newDeckButton.addActionListener(this);
        newDeckButton.setBackground(SwingBank.getLightGrey());
        newDeckButton.setOpaque(true);
        newDeckButton.setFont(buttonFont);

        editDeckButton = SwingBank.createTextJButton("Edit Existing Deck", buttonSize);
        editDeckButton.addActionListener(this);
        editDeckButton.setBackground(SwingBank.getLightGrey());
        editDeckButton.setOpaque(true);
        editDeckButton.setFont(buttonFont);

        importButton = SwingBank.createTextJButton("Import Flash Cards", buttonSize);
        importButton.addActionListener(this);
        importButton.setBackground(SwingBank.getLightGrey());
        importButton.setOpaque(true);
        importButton.setFont(buttonFont);

        welcomePanel = new JPanel();
        welcomeLabel = new JLabel("Welcome",SwingConstants.CENTER);
        welcomeLabel.setFont(welcomeFont);
        welcomePanel.add(welcomeLabel);

        mainUI.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        mainUI.getContentPane().add(mainPanel, BorderLayout.CENTER);
        menuPanel.add(cardsIconButton);
        menuPanel.add(settingsIconButton);
        mainPanel.add(welcomeLabel);
        mainPanel.add(studyButton);
        mainPanel.add(matchTermsButton);
        mainPanel.add(newDeckButton);
        mainPanel.add(editDeckButton);
        mainPanel.add(importButton);
        mainUI.revalidate();
    }

    public void setDark(boolean t) {
        if (t) {
            menuPanel.setBackground(SwingBank.getDark());
            mainPanel.setBackground(SwingBank.getDarkGrey());
            welcomePanel.setBackground(SwingBank.getDarkGrey());
            welcomeLabel.setBackground(SwingBank.getDarkGrey());
            welcomeLabel.setForeground(SwingBank.getLight());
            cardsIconButton.setIcon(SwingBank.formatIcon("images\\flashcardsDarkMode.png"));
            settingsIconButton.setIcon(SwingBank.formatIcon("images\\gearDarkMode.png"));
        }
        else {
            menuPanel.setBackground(SwingBank.getLightGrey());
            mainPanel.setBackground(SwingBank.getLight());
            welcomePanel.setBackground(SwingBank.getLight());
            welcomeLabel.setBackground(SwingBank.getLight());
            welcomeLabel.setForeground(SwingBank.getDark());
            cardsIconButton.setIcon(SwingBank.formatIcon("images\\flashcards.png"));
            settingsIconButton.setIcon(SwingBank.formatIcon("images\\gear.png"));
        }
        mainUI.revalidate();
        mainUI.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardsIconButton) {
            settingsUI.setVisible(false);
        }
        else if (e.getSource() == settingsIconButton) {
            settingsUI.setVisible(true);
        }
        else if (e.getSource() == studyButton) {
            selectionUI.initializeUI(studyButton);
        }
        else if (e.getSource() == matchTermsButton) {
            selectionUI.initializeUI(matchTermsButton);
        }
        else if (e.getSource() == newDeckButton) {
            CreateDeckUI createDeckUI = new CreateDeckUI();
        }
        else if (e.getSource() == editDeckButton) {
            selectionUI.initializeUI(editDeckButton);
        }
        else if (e.getSource() == importButton){
            ImporterUI importerUI = new ImporterUI();
        }
    }

    public SettingsUI getSettingsUI() {
        return settingsUI;
    }
}
