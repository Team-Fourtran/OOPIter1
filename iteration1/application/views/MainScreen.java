package application.views;

import javax.swing.*;
import application.models.tileState.Map;
import application.models.utility.TileGen;
import java.awt.*;
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
	private ArrayList<String> keyList;
	private JLabel[][] Grid;
	
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
    
	
	public void showMainScreen(){
		mainScreen.setVisible(true);
	}
	public ArrayList<String> getKeyPressListener(){
		return keyList;
	}
	public void generateMainScreen(){
		mainScreen = new JFrame("Fourtran Game");
		Grid = new JLabel[ROW][COL];
		
		//Getting map to generate static terrains.
		TileGen T = new TileGen(ROW, COL);
		Map m = new Map(T.execute(), ROW, COL);
		String terrains2d[][] = new String[ROW][COL];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
				terrains2d[i][j] = m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
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
		keyList = new ArrayList<String>();
		areaViewPort.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					keyList.add("RIGHT");
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					keyList.add("LEFT");
				} else if(e.getKeyCode() == KeyEvent.VK_UP){
					keyList.add("UP");
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					keyList.add("DOWN");
				} else if(e.getKeyCode() == KeyEvent.VK_CONTROL){
					keyList.add("CONTROL");
				} else if(e.getKeyCode() == KeyEvent.VK_ENTER){
					keyList.add("ENTER");
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
		unitOVButton.addActionListener(new MainScreenAction());
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
		
		System.out.println("Main Screen has been generated.");
	}
}
