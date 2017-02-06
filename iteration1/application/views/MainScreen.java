package application.views;

import javax.swing.*;
import javax.swing.table.*;

import application.controllers.KeyPressInformer;
import application.models.tileState.Map;
import application.models.utility.TileGen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class MainScreen{
    private JFrame mainScreen;
    private JPanel areaViewPort;
    private JPanel statusViewPort;
    private JPanel buttonPanel;
    private JPanel statusTablePanel;
    private JButton unitOVButton;
    private JButton structureOVButton;
    private JTable statusTable;
    
    private final int ROW = 15;
    private final int COL = 15;
    private final int PIXELS = 45;
    private final int prefWidth = COL * PIXELS;
    private final int prefHeight = ROW * PIXELS;

    //Iterator
    public ListIterator unitIterator;
    //KeyPressInformer for Controller
    private KeyPressInformer keyInformer;
    private HashMap<String, Boolean> keyList;

    private JLabel[][] Grid;
    private Map map;
    private final ImageIcon NORMAL = new ImageIcon("TileImages/Normal/Normal.png");
    private final ImageIcon SLOW = new ImageIcon("TileImages/Slow/Slow.png");
    private final ImageIcon IMPASSABLE = new ImageIcon("TileImages/Impassable/Impassable.png");
    private final ImageIcon[] TERRAIN = {
            NORMAL,
            SLOW,
            IMPASSABLE
    };

    private final String[] unitColumnStats = {"Player Resource", "Offensive Damage",
            "Defensive Damage", "Armor", "Movement",
            "Health", "Upkeep"};
    
    public MainScreen(Map map, ListIterator unitIterator){
        this.map = map;
        this.unitIterator = unitIterator;
    }
    public void showMainScreen(){
        mainScreen.setVisible(true);
    }
    public KeyPressInformer getKeyInformer(){
        return keyInformer;
    }
    public void prepareMainScreen(){
        mainScreen = new JFrame("Fourtran Game");
        Grid = new JLabel[ROW][COL];

        //Getting map to generate static terrains.
        
        String terrains2d[][] = new String[ROW][COL];

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                //System.out.println(i + " " + j);
                //System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
                terrains2d[i][j] = map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
            }
        }

        //Initializing the Grid of JLabels.
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                JLabel tileLabel = new JLabel();
                if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                    tileLabel.setIcon(TERRAIN[0]);
                } else if(terrains2d[i][j].toUpperCase().equals("IMPASSABLE")){
                    tileLabel.setIcon(TERRAIN[2]);
                } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                    tileLabel.setIcon(TERRAIN[1]);
                } else{
                    tileLabel.setIcon(TERRAIN[0]);
                }
                Grid[i][j] = tileLabel;
            }
        }

        //Initializing areaViewPort with Grid.
        areaViewPort = new JPanel(new GridLayout(ROW, COL));
        areaViewPort.setPreferredSize(new Dimension(prefWidth, prefHeight));
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                areaViewPort.add(Grid[i][j]);
            }
        }

        //Adding KeyListener for areaViewPort.
        keyList = new HashMap<String, Boolean>();
        keyList.put("UP", false);
        keyList.put("DOWN", false);
        keyList.put("LEFT", false);
        keyList.put("RIGHT", false);
        keyList.put("CONTROL", false);
        keyList.put("ENTER", false);

        keyInformer = new KeyPressInformer(keyList);

        areaViewPort.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){}
            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyInformer.update("RIGHT", false);
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    keyInformer.update("LEFT", false);
                } else if(e.getKeyCode() == KeyEvent.VK_UP){
                    keyInformer.update("UP", false);
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    keyInformer.update("DOWN", false);
                } else if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                    keyInformer.update("CONTROL", false);
                } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyInformer.update("ENTER", false);
                }
            }
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyInformer.update("RIGHT", true);
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    keyInformer.update("LEFT", true);
                } else if(e.getKeyCode() == KeyEvent.VK_UP){
                    keyInformer.update("UP", true);
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    keyInformer.update("DOWN", true);
                } else if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                    keyInformer.update("CONTROL", true);
                } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyInformer.update("ENTER", true);
                }
            }
        });
        areaViewPort.setFocusable(true);
        areaViewPort.requestFocusInWindow();

        //Initializing Status View Port.
        statusViewPort = new JPanel(new BorderLayout());

        //Initializing buttons.
        buttonPanel = new JPanel();
        unitOVButton = new JButton("Unit Overview");
        structureOVButton = new JButton("Structure Overview");
        unitOVButton.setActionCommand("openUnitOV");
        structureOVButton.setActionCommand("openStructOV");
        unitOVButton.addActionListener(new MainScreenAction(unitIterator));
        structureOVButton.addActionListener(new MainScreenAction());
        buttonPanel.add(unitOVButton);
        buttonPanel.add(structureOVButton);

        //Initializing Status Table
        Object[][] unitData = {{new Integer(2000), new Integer(25), new Integer(25),
                new Integer(10), new Integer(2), new Integer(50),
                new Integer(50)}};

        NonEditableTable table = new NonEditableTable(unitData, unitColumnStats);
        statusTable = new JTable(table);
        statusTablePanel = new JPanel();
        statusTablePanel.add(new JScrollPane(statusTable), BorderLayout.CENTER);
        statusViewPort.add(buttonPanel, BorderLayout.NORTH);
        statusViewPort.add(statusTablePanel);

        //Finalizing Main Screen
        mainScreen.add(areaViewPort);
        mainScreen.add(statusViewPort, BorderLayout.EAST);
        mainScreen.setSize(500, 500);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.pack();
    
    }
}
