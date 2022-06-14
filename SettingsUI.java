import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsUI implements ActionListener {
    private StudyUI studyUI;
    private JFrame settingsUI;
    private JPanel settingsPanel;
    private JCheckBox darkTheme, kahootScreen;
    private boolean dark, kahoot;

    public SettingsUI(StudyUI studyUI) {
        this.studyUI = studyUI;
        initializeUI();
    }

    private void initializeUI() {
        dark = false;
        settingsUI = SwingBank.createJFrame("Settings", 600, 600);

        darkTheme = new JCheckBox("Dark Theme");
        darkTheme.setVisible(true);
        darkTheme.addActionListener(this);
        darkTheme.setFocusPainted(false);
        darkTheme.setPreferredSize(new Dimension(100, 25));

        kahootScreen = new JCheckBox("Show Kahoot Screen");
        kahootScreen.setVisible(true);
        kahootScreen.addActionListener(this);
        kahootScreen.setFocusPainted(false);
        kahootScreen.setPreferredSize(new Dimension(100,25));

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));
        settingsPanel.add(darkTheme);
        settingsPanel.add(kahootScreen);
        settingsUI.add(settingsPanel);
    }

    public void setVisible(boolean visibility) {
        settingsUI.setVisible(visibility);
        settingsUI.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == darkTheme) {
            if (!dark) {
                darkTheme.setForeground(SwingBank.getLight());
                darkTheme.setBackground(SwingBank.getDarkGrey());
                settingsUI.setBackground(SwingBank.getDarkGrey());
                kahootScreen.setForeground(SwingBank.getLight());
                kahootScreen.setBackground(SwingBank.getDarkGrey());
                settingsPanel.setBackground(SwingBank.getDarkGrey());
            }
            else {
                darkTheme.setForeground(SwingBank.getDark());
                darkTheme.setBackground(SwingBank.getLight());
                settingsUI.setBackground(SwingBank.getLight());
                kahootScreen.setBackground(SwingBank.getLight());
                settingsPanel.setBackground(SwingBank.getLight());
            }
            dark = !dark;
            studyUI.setDark(dark);
        }
        else if (e.getSource() == kahootScreen) {
            kahoot = !kahoot;
        }
    }

    public boolean isKahoot() {
        return kahoot;
    }
}
