package application.views;

import javax.swing.*;
import javax.swing.table.*;
import application.models.tileState.Map;
import application.models.utility.TileGen;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class View extends JPanel implements ActionListener {

	public static final Color NORMAL = new Color(102,153,0);
	public static final Color SLOWING = new Color(222,184,135);
	public static final Color IMPASSABLE = new Color(0,0,0);
	public static final Color FIELD = new Color(255,204,102);
	public static final Color SWAMP = new Color(0,102,0);
	public static final Color WATER = new Color(0,0,153);

	public static final int ROW = 15;
	public static final int COL = 15;

	public static final int PIXELS = 45;
    TileGen T = new TileGen(ROW, COL);
    Map m = new Map(T.execute(), ROW, COL);
    
	public static final Color[] TERRAIN = {
		NORMAL,
		SLOWING,
		IMPASSABLE,
		FIELD,
		SWAMP,
		WATER
	};


	private final Color[][] Grid;

	public View(){
		this.Grid = new Color[ROW][COL];
		
		String terrains2d[][] = new String[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				System.out.println(m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties());
				terrains2d[i][j] = m.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
			}
		}
		
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				//int randomTerrainIndex = r.nextInt(TERRAIN.length);
				//Color randomColor = TERRAIN[randomTerrainIndex];
				Color color;
				if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
					color = TERRAIN[0];
				} else if(terrains2d[i][j].toUpperCase().equals("IMPASSABLE")){
					color = TERRAIN[2];
				} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
					color = TERRAIN[1];
				} else{
					color = TERRAIN[0];
				}
				this.Grid[i][j] = color;
			}
		}
		int prefWidth = COL * PIXELS;
		int prefHeight = ROW * PIXELS;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
	}

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

	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {

				//Initializing JFrame and View
				JFrame mainScreen = new JFrame("Fourtran Game");
				View map = new View();
				JPanel areaViewPort = new JPanel();
				areaViewPort.add(map);
				mainScreen.add(areaViewPort, BorderLayout.CENTER);

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