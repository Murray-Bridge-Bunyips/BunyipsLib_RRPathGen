package jarhead;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ButtonPanel extends JPanel {

//    private final JButton exportButton = new JButton("Export");
    private final JButton unitButton = new JButton("Unit: Inches");
    public final JButton flipButton = new JButton("Flip");
    private final JButton clearButton = new JButton("Clear");
    private final JButton undoButton = new JButton("Undo");
    private final JButton redoButton = new JButton("Redo");
    private LinkedList<NodeManager> managers;
    private Main main;
    private ProgramProperties robot;
    private int unitIdx = 0;

    ButtonPanel(LinkedList<NodeManager> managers, Main main, ProgramProperties props){
        this.robot = props;
        this.main = main;
        this.managers = managers;
        this.setMinimumSize(new Dimension(0,20));
        this.setLayout(new GridLayout(1, 4, 1, 1));

//        exportButton.setFocusable(false);
        unitButton.setFocusable(false);
        flipButton.setFocusable(false);
        clearButton.setFocusable(false);
        undoButton.setFocusable(false);
        redoButton.setFocusable(false);
//        this.add(exportButton);
        this.add(unitButton);
        this.add(flipButton);
        this.add(clearButton);
        this.add(undoButton);
        this.add(redoButton);

        this.setVisible(true);

//        exportButton.addActionListener(e -> export());

        unitButton.addActionListener(e -> {
            unitIdx++;
            if (unitIdx >= 3) unitIdx = 0;
            unitButton.setText(unitIdx == 0 ? "Unit: Inches" : unitIdx == 1 ? "Unit: Centimeters" : "Unit: FieldTiles");
            export();
        });
        flipButton.addActionListener(e -> {
            main.flip();
            main.drawPanel.repaint();

            Node recordOfFlip = new Node();
            recordOfFlip.state = Node.State.FLIP;
            getCurrentManager().undo.add(recordOfFlip);
        });
        undoButton.addActionListener(e -> {
            main.undo(true);
            main.drawPanel.repaint();
        });
        redoButton.addActionListener(e -> {
            main.redo();
            main.drawPanel.repaint();
        });
        clearButton.addActionListener(e -> {
            getCurrentManager().undo.clear();
            getCurrentManager().redo.clear();
            getCurrentManager().clear();
            int id = main.currentM;
            for (int i = id; i < managers.size()-1; i++) {
                managers.set(i, managers.get(i+1));
            }
            if(managers.size() > 1)
                managers.removeLast();
            else main.currentM = 0;
            if(main.currentM > 0)
                main.currentM--;
            main.currentN = -1;
            main.infoPanel.editPanel.updateText();
            main.drawPanel.resetPath();

            main.drawPanel.renderBackgroundSplines();
            main.drawPanel.repaint();
        });
//        importButton.addActionListener(e -> {
//            File file;
//            if(robot.importPath.matches("")){
//                JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
//                chooser.setFileFilter(filter);
//                int r = chooser.showOpenDialog(null);
//                if(r != JFileChooser.APPROVE_OPTION) return;
//                robot.importPath = chooser.getSelectedFile().getPath();
//                robot.prop.setProperty("IMPORT/EXPORT", robot.importPath);
//                main.saveConfig();
//                main.infoPanel.settingsPanel.update();
//                file = chooser.getSelectedFile();
//            } else {
//                main.saveConfig();
//                file = new File(robot.importPath);
//            }
//            Import importer = new Import(main);
//            LinkedList<NodeManager> in = importer.read(file);
//            if(getCurrentManager().size() < 1)
//                managers.remove(getCurrentManager());
//            managers.addAll(in);
//
//
//            main.currentM = managers.size()-1;
//            main.currentN = -1;
//            main.infoPanel.setManagerName(getCurrentManager().name);
//            main.drawPanel.renderBackgroundSplines();
//            main.drawPanel.repaint();
//        });
    }

    private double getMultiplier(double v) {
        if (unitIdx == 0) return v;
        return unitIdx == 2 ? v / 23.6 : v * 2.54;
    }

    private String getUnit() {
        if (unitIdx == 0) return "Inches"; // fine to use inches even though they are implied without them
        return unitIdx == 2 ? "FieldTiles" : "Centimeters";
    }

    public void export(){
        if(getCurrentManager().size() <= 0) {
            return;
        }
//        main.infoPanel.editPanel.saveValues();
//        main.infoPanel.markerPanel.saveValues();
        Node node = getCurrentManager().getNodes().get(0);
        double x = main.toInches(node.x);
        double y = main.toInches(node.y);
//        if(!robot.importPath.matches("")){
//            File outputFile = new File(robot.importPath.substring(0,robot.importPath.length()-4) + "backup.java");
//            System.out.println(outputFile.getPath());
//            try {
//                outputFile.createNewFile();
//                FileWriter writer = new FileWriter(outputFile);
//                Scanner reader = new Scanner(new File(robot.importPath));
//
//                writer.close();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//
//        }

        StringBuilder sb = new StringBuilder();

        // Bunyips Changes:
        // Commented out a line and removed the trajectory name being added to the StringBuilder.
        // This means changing the name tab does nothing now.

        // if(main.exportPanel.addDataType) sb.append("TrajectorySequence ");
        // will always convert from in -> cm, and rad -> deg

        // will also (as of bl rrpg 2.1) append option for setting the initial pose and using an implicit start
        sb.append(main.exportPanel.setInitialPose ? "drive.setPose" : "drive.makeTrajectory");
        sb.append(String.format("(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees)%s%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.robotHeading + 90), main.exportPanel.setInitialPose ? ";" : ""));
        if (main.exportPanel.setInitialPose) {
            sb.append("drive.makeTrajectory()\n");
        }

        //sort the markers
//        List<Marker> markers = getCurrentManager().getMarkers();
//        markers.sort(Comparator.comparingDouble(n -> n.displacement));
//        for (Marker marker : markers) {
//            sb.append(String.format("        .UNSTABLE_addTemporalMarkerOffset(%.2f,() -> {%s})%n", marker.displacement, marker.code));
//        }
        boolean prev = false;
        for (int i = 0; i < getCurrentManager().size(); i++) {
            node = getCurrentManager().get(i);
            if(node.equals(getCurrentManager().getNodes().get(0))) {
                if(node.reversed != prev){
                    sb.append(String.format("        .setReversed(%s)%n", node.reversed));
                    prev = node.reversed;
                }
                continue;
            }
            x = main.toInches(node.x);
            y = main.toInches(node.y);


            switch (node.getType()){
                case splineTo:
                    sb.append(String.format("        .splineTo(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.splineHeading +90)));
                    break;
                case splineToSplineHeading:
                    sb.append(String.format("        .splineToSplineHeading(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.robotHeading +90), (node.splineHeading +90)));
                    break;
                case splineToLinearHeading:
                    sb.append(String.format("        .splineToLinearHeading(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.robotHeading +90), (node.splineHeading +90)));
                    break;
                case splineToConstantHeading:
                    sb.append(String.format("        .splineToConstantHeading(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.splineHeading +90)));
                    break;
                case strafeTo:
                    sb.append(String.format("        .strafeTo(new Vector2d(%.2f, %.2f), %s)%n", getMultiplier(x), getMultiplier(-y), getUnit()));
                    break;
                case strafeToSplineHeading:
                    sb.append(String.format("        .strafeToSplineHeading(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.robotHeading +90)));
                    break;
                case strafeToLinearHeading:
                    sb.append(String.format("        .strafeToLinearHeading(new Vector2d(%.2f, %.2f), %s, %.2f, Degrees)%n", getMultiplier(x), getMultiplier(-y), getUnit(), (node.robotHeading +90)));
                    break;
                case strafeToConstantHeading:
                    sb.append(String.format("        .strafeToConstantHeading(new Vector2d(%.2f, %.2f), %s)%n", getMultiplier(x), getMultiplier(-y), getUnit()));
                    break;
//                case addTemporalMarker:
//                    break;
                default:
                    sb.append("couldn't find type");
                    break;
            }
            if(node.reversed != prev){
                sb.append(String.format("        .setReversed(%s)%n", node.reversed));
                prev = node.reversed;
            }
        }
        sb.append(String.format("        .addTask();%n"));
//        if(main.exportPanel.addPoseEstimate) sb.append(String.format("drive.setPoseEstimate(%s.start());", getCurrentManager().name));
        if (main.exportPanel.field != null && Objects.equals(main.exportPanel.field.getText(), sb.toString())) return;
        main.exportPanel.field.setText(sb.toString());
    }

    private NodeManager getCurrentManager() {
        return main.getManagers().get(main.currentM);
    }
}
