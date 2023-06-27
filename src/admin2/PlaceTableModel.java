package admin2;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class PlaceTableModel extends AbstractTableModel{

	String[] COLUMN_NAMES = {
			"Touristic Place",
			"Location",
			"Description",
			"Price",
			"Edit"
	};
	
	
	ArrayList<PlaceVO> rowData;
	
	public PlaceTableModel(ArrayList<PlaceVO> rowDatas) {
		this.rowData = new ArrayList<>(20);
		for (PlaceVO k : rowDatas) {
			this.rowData.add(k);
		}
	}
	
	 public void add(ArrayList<PlaceVO> pd) {
	        rowData.addAll(pd);
	   }
	
	
	@Override
	public int getRowCount() {
		return rowData.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public PlaceVO getPlaceDataAt(int row) {
		return rowData.get(row);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PlaceVO pd = getPlaceDataAt(rowIndex);
		Object value = null;
		switch (columnIndex) {
		case 0:
			value = pd.getPlacename();
			break;
		case 1:
			value = pd.getPlacelocation();
			break; 
		case 2:
			value = pd.getPlacedescription();
			break;
		case 3:
			value = pd.getPlaceprice();
			break;
		case 4:
			value = "Hello";
			break;
		}
		return value;
	}

}
