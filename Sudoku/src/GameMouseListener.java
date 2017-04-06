import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by DanielLund on 12/2/16.
 */
public class GameMouseListener extends MouseAdapter {

    int row, col;
    GameInterface game;
    UserInterface userInterface;
    JPopupMenu menu;
    Component component;
    boolean hintIsShowing;

    public GameMouseListener(){
        this.row = -1;
        this.col = -1;
    }

    public GameMouseListener(int row, int col, GameInterface game, UserInterface userInterface){
        this.row = row;
        this.col = col;
        this.game = game;
        this.userInterface = userInterface;
        hintIsShowing = false;
        menu = new JPopupMenu();
        setMenu();
    }

    public void setGame(GameInterface game){
        this.game = game;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void gamePeiceMouseClicked(MouseEvent e){
        if(SwingUtilities.isRightMouseButton(e)){
            menu.show(e.getComponent(), e.getX(), e.getY());


        }else if(SwingUtilities.isLeftMouseButton(e)){
            game.incrementTile(this.row, this.col);
            userInterface.redrawBoard();

        }else{
            System.out.println("No idea what you pressed");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        gamePeiceMouseClicked(e);
    }

    public void setMenu(){
        JMenuItem item = new JMenuItem("Show Hint");
        item.addActionListener((ActionEvent e)->{
            if(!hintIsShowing) {
                userInterface.showHint(row, col);
                hintIsShowing = true;
            }
            else {
                userInterface.redrawBoard();
                hintIsShowing = false;
            }
        });
        menu.add(item);

        item = new JMenuItem("Clear");
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col, 0);
            userInterface.redrawBoard();
        });

        menu.add(item);

        //TODO: Implement loop for construction of menu
        item = new JMenuItem("1");
        item.setAccelerator(KeyStroke.getKeyStroke('1'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,1);
            userInterface.redrawBoard();
        });

        menu.add(item);

        item = new JMenuItem("2");
        item.setAccelerator(KeyStroke.getKeyStroke('2'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,2);
            userInterface.redrawBoard();
        });

        menu.add(item);

        item = new JMenuItem("3");
        item.setAccelerator(KeyStroke.getKeyStroke('3'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,3);
            userInterface.redrawBoard();

        });
        menu.add(item);

        item = new JMenuItem("4");
        item.setAccelerator(KeyStroke.getKeyStroke('4'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,4);
            userInterface.redrawBoard();
        });
        menu.add(item);

        item = new JMenuItem("5");
        item.setAccelerator(KeyStroke.getKeyStroke('5'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,5);
            userInterface.redrawBoard();
        });

        menu.add(item);

        item = new JMenuItem("6");
        item.setAccelerator(KeyStroke.getKeyStroke('6'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,6);
            userInterface.redrawBoard();
        });

        menu.add(item);
        item = new JMenuItem("7");
        item.setAccelerator(KeyStroke.getKeyStroke('7'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,7);
            userInterface.redrawBoard();
        });

        menu.add(item);

        item = new JMenuItem("8");
        item.setAccelerator(KeyStroke.getKeyStroke("8"));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,8);
            userInterface.redrawBoard();
        });

        menu.add(item);
        item = new JMenuItem("9");
        item.setAccelerator(KeyStroke.getKeyStroke('9'));
        item.addActionListener((ActionEvent e)->{
            game.setTileValue(row,col,9);
            userInterface.redrawBoard();
        });

        menu.add(item);
        menu.pack();
    }



}
