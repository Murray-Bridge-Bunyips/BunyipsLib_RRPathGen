package jarhead;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ExportPanel extends JPanel {

    JTextArea field = new JTextArea();
    JScrollPane scroll = new JScrollPane(field);
    JCheckBox setInitialPoseCheckbox = new JCheckBox("Set Initial Pose", true);
//    JCheckBox poseEstimate = new JCheckBox("setPoseEstimate", false);
    public boolean setInitialPose = true;
    JButton copy = new JButton("Copy to clipboard");
    Main main;

    ExportPanel(Main main) {
//        field.setText("Export");

        this.main = main;
        this.setOpaque(true);
        this.setBackground(Color.darkGray.darker());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setMinimumSize(new Dimension(200,10));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(setInitialPoseCheckbox, BorderLayout.WEST);
//        this.add(poseEstimate, BorderLayout.EAST);
        this.add(copy);
        this.add(scroll);
        this.setVisible(true);

        copy.addActionListener(e -> {
            StringSelection selection = new StringSelection(field.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        });

        setInitialPoseCheckbox.addItemListener(e -> {
            setInitialPose = e.getStateChange()==1;
            main.buttonPanel.export();
        });
//        poseEstimate.addItemListener(e -> {
//            addPoseEstimate = e.getStateChange()==1;
//            main.buttonPanel.export();
//        });

    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }


}
