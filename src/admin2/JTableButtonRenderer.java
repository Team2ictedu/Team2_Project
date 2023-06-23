package admin2;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class JTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer defaultRenderer;

	public JTableButtonRenderer(TableCellRenderer renderer) {
		defaultRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof Component)
			return (Component) value;
		return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}