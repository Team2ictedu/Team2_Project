package admin2;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TEST extends JFrame{
	public TEST() {
		super();
		String[] columnNames = {"First Name", "Last Name", ""};
		Object[][] data =
			{
					{"Homer", "Simpson", "delete Homer"},
					{"Madge", "Simpson", "delete Madge"},
					{"Bart",  "Simpson", "delete Bart"},
					{"Lisa",  "Simpson", "delete Lisa"},
			};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable( model );
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        System.out.println("ss");
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		add(new JScrollPane(table));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
	}
	public static void main(String[] args) {
		new TEST();
	}
}
