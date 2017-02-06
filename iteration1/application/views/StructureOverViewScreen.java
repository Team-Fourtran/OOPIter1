package application.views;

import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.models.playerAsset.PlayerAsset;

public class StructureOverViewScreen {
	
	private JFrame structOVFrame;
	private String[] structureColumnStats = {"StructuresID", "Structure Type", "Offensive Damage", 
			"Defensive Damage", "Armor", "Maximum Health", 
			"Current Health", "Upkeep", "Location"};
	private Object[][] structData;
	
	//private ListIterator structureIterator;
	private NonEditableTable table;
	private JTable structureOVTable;
	
	public StructureOverViewScreen(Object[][] structData){
		this.structData = structData;
		generateStructureOverViewScreen();
	}
	
	private void generateStructureOverViewScreen(){
		
		//Initializing Structure Overview Frame.
		structOVFrame = new JFrame();
		
		/*
		structureData = new Object[25][9];
		int i = 0;
		while(structureIterator.hasNext()){
			PlayerAsset asset = (PlayerAsset) structureIterator.next();
			structureData[i][0] = asset.getID();
			structureData[i][1] = asset.getType();
			structureData[i][2] = asset.getOffDamage();
			structureData[i][3] = asset.getDefDamage();
			structureData[i][4] = asset.getArmor();
			structureData[i][5] = asset.getMaxHealth();
			structureData[i][6] = asset.getCurrentHealth();
			structureData[i][7] = asset.getUpkeep();
			structureData[i][8] = asset.getLocation();
			if(i == 24){
				i = 0;
			}
			else{
				i++;
			}
		}*/
		
		//Initializing Structure Overview Table.
		table = new NonEditableTable(structData, structureColumnStats);
		structureOVTable = new JTable(table);
		
		
		/*Row selection stuff for later.
		ListSelectionModel rowSelectModel;
		rowSelectModel = structureOVTable.getSelectionModel();

		//Row Selection ActionListener
		rowSelectModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
				System.out.println(structureOVTable.getValueAt(structureOVTable.getSelectedRow(), 0).toString());
			}
		});
		*/
		
		//Finalizing Structure Overview Frame.
		structOVFrame.add(new JScrollPane(structureOVTable));
		structOVFrame.setSize(500, 500);
		structOVFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		structOVFrame.pack();
	}

	public void showStructureOverViewScreen(){
		structOVFrame.setVisible(true);
	}
}
