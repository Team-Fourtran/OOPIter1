package application.views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StructureOverViewScreen {
	private JFrame structOVFrame;
	private String[] structureColumnStats = {"Structures", "Offensive Damage", 
			"Defensive Damage", "Armor", "Movement", 
			"Health", "Upkeep", "Missions"};
	private NonEditableTable table;
	private JTable structureOVTable;
	
	public StructureOverViewScreen(){
		generateStructureOverViewScreen();
	}
	
	private void generateStructureOverViewScreen(){
		
		//Initializing Structure Overview Frame.
		structOVFrame = new JFrame();
		Object[][] structureData = {{new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50), new String("Move, Gather")}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}, {new Integer(2000), new Integer(25), new Integer(25),
			   new Integer(10), new Integer(2), new Integer(50),
			   new Integer(50)}};
		
		//Initializing Structure Overview Table.
		table = new NonEditableTable(structureData, structureColumnStats);
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
