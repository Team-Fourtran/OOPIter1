package application.views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.*;
import application.models.playerAsset.*;

public class UnitOverViewScreen {
	
	private JFrame unitOVFrame;
	private JFrame armyListFrame;
	private JTable armyListTable;
	private String[] unitColumnStats = {"Units", "Offensive Damage", 
			"Defensive Damage", "Armor", 
			"Max Health", "Current Health", "Upkeep", "Location"};
	private NonEditableTable table;
	private JTable unitOVTable;
	private JPanel unitOVTablePanel;
	private JPanel armyButtonPanel;
	private JButton armyAssembleButton;
	private JPanel mainUnitOVPanel;
	private Object[][] unitData;
	private ListIterator unitIterator;
	
	public UnitOverViewScreen(ListIterator unitIterator){
		this.unitIterator = unitIterator;
		generateUnitOverViewScreen();
	}
	
	private void generateUnitOverViewScreen(){
		unitOVFrame = new JFrame("Unit Overview");

		unitData = new Object[25][8];
		int i = 0;
		while(unitIterator.hasNext()){
			PlayerAsset asset = (PlayerAsset) unitIterator.next();
			unitData[i][0] = asset.getID();
			unitData[i][1] = asset.getOffDamage();
			unitData[i][2] = asset.getDefDamage();
			unitData[i][3] = asset.getArmor();
			unitData[i][4] = asset.getMaxHealth();
			unitData[i][5] = asset.getCurrentHealth();
			unitData[i][6] = asset.getUpkeep();
			unitData[i][7] = asset.getLocation();
			if(i == 24){
				i = 0;
			}
			else{
				i++;
			}
		}
		/*Object[][] unitData = {{new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50), new String("Move, Gather")}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}};*/
		
		//Creating Unit table.
		table = new NonEditableTable(unitData, unitColumnStats);
		unitOVTable = new JTable(table);
		
		//Creating Panels for Unit OV Table
		unitOVTablePanel = new JPanel();
		armyButtonPanel = new JPanel();
		armyAssembleButton = new JButton("Assemble Selected Units");
		unitOVTablePanel.add(new JScrollPane(unitOVTable));
		armyButtonPanel.add(armyAssembleButton);
		
		//Actions for assemble command. Implement later.
		armyAssembleButton.setActionCommand("assembleArmy");
		armyAssembleButton.addActionListener(new MainScreenAction());
		
		//Initializing main Unit Overview panel.
		mainUnitOVPanel = new JPanel(new BorderLayout());
		mainUnitOVPanel.add(armyButtonPanel, BorderLayout.SOUTH);
		mainUnitOVPanel.add(unitOVTablePanel, BorderLayout.NORTH);
		
		//Initializing Army List Frame
		armyListFrame = new JFrame("Army List");
		armyListTable = new JTable(table);
		armyListFrame.add(new JScrollPane(armyListTable));
		
		//Final preparing for Unit Overview Frame.
		unitOVFrame.add(mainUnitOVPanel);
		unitOVFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		unitOVFrame.pack();
	}
	public void showUnitOverViewScreen(){
		unitOVFrame.setVisible(true);
	}
}
