package application.views;

import javax.swing.*;
import javax.swing.table.*;
import application.models.tileState.Map;
import application.models.utility.TileGen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class View extends JPanel implements ActionListener {

	/*public static final Color NORMAL = new Color(102,153,0);
	public static final Color SLOWING = new Color(222,184,135);
	public static final Color IMPASSABLE = new Color(0,0,0);
	*/
	public static final int ROW = 15;
	public static final int COL = 15;

	public static final int PIXELS = 45;
	private static ArrayList<String> keyList = new ArrayList<String>();
    public static final ImageIcon NORMAL = new ImageIcon("TileImages/Normal/Normal.png");
    public static final ImageIcon SLOW = new ImageIcon("TileImages/Slow/Slow.png");
    public static final ImageIcon IMPASSABLE = new ImageIcon("TileImages/Impassable/Impassable.png");
    public static final ImageIcon NORMAL_SELECTED = new ImageIcon("TileImages/Normal/Normal-Highlighted.png");
    public static final ImageIcon[] TERRAIN = {
    		NORMAL_SELECTED,
    		SLOW,
    		IMPASSABLE
    };
	/*public static final Color[] TERRAIN = {
		NORMAL,
		SLOWING,
		IMPASSABLE,
	};*/


	//private final Color[][] Grid;
	//public final JLabel[][] Grid;
	/*
	public View(){
		
		
		this.Grid = new JLabel[ROW][COL];
		
		String terrains2d[][] = new String[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
				terrains2d[i][j] = m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
			}
		}
		
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
				this.Grid[i][j] = tileLabel;
			}
		}
		int prefWidth = COL * PIXELS;
		int prefHeight = ROW * PIXELS;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
	}
	*/
	/*
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Clears the board
		g.clearRect(0,0, getWidth(), getHeight());
		// Drawing the grid
		int rectWidth = getWidth() / COL;
		int rectHeight = getHeight() / ROW;

		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				int x = i * rectWidth;
				int y = j * rectHeight;
				Color terrainColor = Grid[i][j];
				g.setColor(terrainColor);
				g.fillRect(x, y, rectWidth, rectHeight);
			}
		}
	}
	*/
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
		StatusViewPortTable table = new StatusViewPortTable(unitData, unitColumnStats);
		JTable unitOVTable = new JTable(table);

		//Creating Panels for Unit OV Table
		JPanel unitOVTablePanel = new JPanel();
		JPanel armyButtonPanel = new JPanel();
		JButton armyAssembleButton = new JButton("Assemble Selected Units");

		unitOVTablePanel.add(new JScrollPane(unitOVTable));
		armyButtonPanel.add(armyAssembleButton);
		//armyButtonPanel.add(armyDisband);

		armyAssembleButton.setActionCommand("assembleArmy");
		armyAssembleButton.addActionListener(new View());

		JPanel mainUnitOVPanel = new JPanel(new BorderLayout());
		mainUnitOVPanel.add(armyButtonPanel, BorderLayout.SOUTH);
		mainUnitOVPanel.add(unitOVTablePanel, BorderLayout.NORTH);
					
		//Army List Frame
		JFrame armyListFrame = new JFrame("Army List");
		String[] armyColNames = {"Army ID", "Unit ID", "Unit Type"};
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

					StatusViewPortTable table = new StatusViewPortTable(unitData, unitColumnStats);
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
	
	public ArrayList<String> getKeyPressListener(){
		return keyList;
	}
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JLabel[][] Grid = new JLabel[ROW][COL];
				TileGen T = new TileGen(ROW, COL);
				Map m = new Map(T.execute(), ROW, COL);
				String terrains2d[][] = new String[15][15];
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
						terrains2d[i][j] = m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
					}
				}
				
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
				int prefWidth = COL * PIXELS;
				int prefHeight = ROW * PIXELS;
				
				//Initializing JFrame and View
				JFrame mainScreen = new JFrame("Fourtran Game");
				JPanel areaViewPort = new JPanel(new GridLayout(ROW, COL));
				areaViewPort.setPreferredSize(new Dimension(prefWidth, prefHeight));
				for(int i = 0; i < ROW; i++){
					for(int j = 0; j < COL; j++){
						areaViewPort.add(Grid[i][j]);
					}
				}
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
				
				mainScreen.add(areaViewPort);

				//Adding menu bar
				JMenuBar menuBar = new JMenuBar();
				JMenu optionMenu = new JMenu("Options");
				menuBar.add(optionMenu);
				mainScreen.setJMenuBar(menuBar);

				//Adding panel for buttons
				JPanel statusViewPort = new JPanel(new BorderLayout());
				JPanel buttonPanel = new JPanel();

				//Button Stuff
				JButton unitOVButton = new JButton("Unit Overview");
				JButton structureOVButton = new JButton("Structure Overview");
				unitOVButton.setActionCommand("openUnitOV");
				structureOVButton.setActionCommand("openStructOV");
				unitOVButton.addActionListener(new View());
				structureOVButton.addActionListener(new View());

				buttonPanel.add(unitOVButton);
				buttonPanel.add(structureOVButton);
				//mainScreen.add(buttonPanel, BorderLayout.EAST);

				//Making JTable
				String[] unitColumnStats = {"Player Resource", "Offensive Damage", 
											"Defensive Damage", "Armor", "Movement", 
											"Health", "Upkeep"};
				/*String[] structureColumnStats = {"Player Resource", "Offensive Damage", 
												 "Defensive Damage", "Armor", "Production Rate", 
												 "Health", "Upkeep"};*/
				Object[][] unitData = {{new Integer(2000), new Integer(25), new Integer(25),
									   new Integer(10), new Integer(2), new Integer(50),
									   new Integer(50)}};
				StatusViewPortTable table = new StatusViewPortTable(unitData, unitColumnStats);
				JTable statusTable = new JTable(table);
				JPanel statusTablePanel = new JPanel();
				statusTablePanel.add(new JScrollPane(statusTable), BorderLayout.CENTER);
				statusViewPort.add(buttonPanel, BorderLayout.NORTH);
				statusViewPort.add(statusTablePanel);
				mainScreen.add(statusViewPort, BorderLayout.EAST);

				//Setting JFrame properties
				mainScreen.setSize(500, 500);
				mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainScreen.pack();
				mainScreen.setVisible(true);
				
			}
		});
	}
}

class StatusViewPortTable extends DefaultTableModel{
	public StatusViewPortTable(Object[][] tableData, Object[] colNames){
		super(tableData, colNames);
	}
	public boolean isCellEditable(int row, int column){
		return false;
	}
}