package jarhead.InfoPanel;

import jarhead.*;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    public SettingsPanel settingsPanel;
    public EditPanel editPanel;

    public InfoPanel(Main main, ProgramProperties props) {
        this.setOpaque(true);
        this.settingsPanel = new SettingsPanel(main, props);
        this.editPanel = new EditPanel(main);
        this.setBackground(Color.darkGray.darker());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(editPanel, BorderLayout.NORTH);
//        this.add(Box.createVerticalStrut((int)main.scale*100));
        this.add(settingsPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void setManagerName(String name) {
        editPanel.name.setText(name);
    }

}
