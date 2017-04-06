import javax.swing.*;

/**
 * Created by DanielLund on 12/2/16.
 */
public class FileMenu extends JMenu {
    private JMenuItem loadPuzzleMenuItem;
    private JMenuItem savePuzzleMenuItem;
    private JMenuItem saveAsPuzzleMenuItem;
    UserInterface userInterface;

    public FileMenu(UserInterface userInterface){
        this.userInterface = userInterface;
        this.setText("File");
        initMenuItems();
    }

    private void initMenuItems() {
        loadPuzzleMenuItem = new JMenuItem("Load");
        loadPuzzleMenuItem.addActionListener(e -> userInterface.loadGame());
        savePuzzleMenuItem = new JMenuItem("Save");
        savePuzzleMenuItem.addActionListener(e -> userInterface.saveGame());
        saveAsPuzzleMenuItem = new JMenuItem("Save As");
        saveAsPuzzleMenuItem.addActionListener(e -> userInterface.saveGame());
        this.add(loadPuzzleMenuItem);
        this.add(savePuzzleMenuItem);
        this.add(saveAsPuzzleMenuItem);
    }


}
