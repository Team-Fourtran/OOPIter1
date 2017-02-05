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

public class MainScreen implements ActionListener{
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

	//KeyPressInformer for Controller
    private KeyPressInformer keyInformer;
	private HashMap<String, Boolean> keyList;

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
	public KeyPressInformer getKeyInformer(){
		return keyInformer;
	}
	public void prepareMainScreen(){
		mainScreen = new JFrame("Fourtran Game");
		Grid = new JLabel[ROW][COL];
		
		//Getting map to generate static terrains.
		TileGen T = new TileGen(ROW, COL);
		Map m = new Map(T.execute(), ROW, COL);
		String terrains2d[][] = new String[ROW][COL];

		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				//System.out.println(i + " " + j);
				//System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
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
		unitOVButton.addActionListener(new MainScreen());
		structureOVButton.addActionListener(new MainScreen());
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
	
	@Override
	public void actionPerformed(ActionEvent e){
		JFrame unitOVFrame = new JFrame("Unit Overview");

		String[] unitColumnStats = {"Units", "Offensive Damage", 
							"Defensive Damage", "Armor", "Movement", 
							"Health", "Upkeep", "Missions"};
		Object[][] unitData = {{new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50), new String("Move, Gather")}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}};
		NonEditableTable table = new NonEditableTable(unitData, unitColumnStats);
		JTable unitOVTable = new JTable(table);

		//Creating Panels for Unit OV Table
		JPanel unitOVTablePanel = new JPanel();
		JPanel armyButtonPanel = new JPanel();
		JButton armyAssembleButton = new JButton("Assemble Selected Units");

		unitOVTablePanel.add(new JScrollPane(unitOVTable));
		armyButtonPanel.add(armyAssembleButton);
		//armyButtonPanel.add(armyDisband);

		armyAssembleButton.setActionCommand("assembleArmy");
		armyAssembleButton.addActionListener(new MainScreen());

		JPanel mainUnitOVPanel = new JPanel(new BorderLayout());
		mainUnitOVPanel.add(armyButtonPanel, BorderLayout.SOUTH);
		mainUnitOVPanel.add(unitOVTablePanel, BorderLayout.NORTH);
					
		//Army List Frame
		JFrame armyListFrame = new JFrame("Army List");
		//String[] armyColNames = {"Army ID", "Unit ID", "Unit Type"};
		JTable armyListTable = new JTable(table);
		armyListFrame.add(new JScrollPane(armyListTable));

		//Row Selection ActionListener
		/*ListSelectionModel rowSelectModel;
		rowSelectModel = unitOVTable.getSelectionModel();
		rowSelectModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
				System.out.println(unitOVTable.getValueAt(unitOVTable.getSelectedRow(), 0).toString());
			}
		});*/
		//JButton armyDisbandButton = new JButton("Disband Selected Army");

		//Initialize Unit OV Frame
		unitOVFrame.add(mainUnitOVPanel);
		unitOVFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		unitOVFrame.pack();

		if("openUnitOV".equals(e.getActionCommand())){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					unitOVFrame.setVisible(true);
				}
			});
		} else if("openStructOV".equals(e.getActionCommand())){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					JFrame structOVFrame = new JFrame();
					String[] unitColumnStats = {"Structures", "Offensive Damage", 
							"Defensive Damage", "Armor", "Movement", 
							"Health", "Upkeep", "Missions"};
					Object[][] unitData = {{new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50), new String("Move, Gather")}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}};

					NonEditableTable table = new NonEditableTable(unitData, unitColumnStats);
					JTable structureOVTable = new JTable(table);
					
					ListSelectionModel rowSelectModel;
					rowSelectModel = structureOVTable.getSelectionModel();

					//Row Selection ActionListener
					rowSelectModel.addListSelectionListener(new ListSelectionListener(){
						public void valueChanged(ListSelectionEvent event){
							System.out.println(structureOVTable.getValueAt(structureOVTable.getSelectedRow(), 0).toString());
						}
					});

					structOVFrame.add(new JScrollPane(structureOVTable));
					structOVFrame.setSize(500, 500);
					structOVFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					structOVFrame.pack();
					structOVFrame.setVisible(true);

				}
			});
		} else if("assembleArmy".equals(e.getActionCommand())){
			ListSelectionModel rowSelectModel;
			rowSelectModel = unitOVTable.getSelectionModel();
			rowSelectModel.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event){
					System.out.println(unitOVTable.getSelectedRow());
					//armyListTable.insertRow(unitOVTable.getSelectedRow());
					//System.out.println(unitOVTable.getValueAt(unitOVTable.getSelectedRow(), 0).toString());
					System.out.println(unitOVTable.getValueAt(unitOVTable.getSelectedRow(), 0).toString());
				}
			});
		}
	}
}
