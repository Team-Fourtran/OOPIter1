package application.views;

import javax.swing.table.DefaultTableModel;

public class NonEditableTable extends DefaultTableModel{
	public NonEditableTable (Object[][] tableData, Object[] colNames){
		super(tableData, colNames);
	}
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
