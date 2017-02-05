package application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class MainScreenAction implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		//armyButtonPanel.add(armyDisband);

		//Row Selection ActionListener
		/*ListSelectionModel rowSelectModel;
		rowSelectModel = unitOVTable.getSelectionModel();
		rowSelectModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event){
				System.out.println(unitOVTable.getValueAt(unitOVTable.getSelectedRow(), 0).toString());
			}
		});*/
		//JButton armyDisbandButton = new JButton("Disband Selected Army");
		UnitOverViewScreen unitOVScreen = new UnitOverViewScreen();
		StructureOverViewScreen structureOVScreen = new StructureOverViewScreen();
		if("openUnitOV".equals(e.getActionCommand())){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					unitOVScreen.showUnitOverViewScreen();
				}
			});
		} else if("openStructOV".equals(e.getActionCommand())){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					structureOVScreen.showStructureOverViewScreen();
				}
			});
		} /*else if("assembleArmy".equals(e.getActionCommand())){
			
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
		}*/
	}
}
