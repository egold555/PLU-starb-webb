package webb.client.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WebbTable extends JTable {

    private boolean alternatingColors = false;
    private Color c1, c2;

    private Color headerColor = null;

    public WebbTable(String[] columnNames, String[][] data) {
        super(new DefaultTableModel(data, columnNames));
        this.setOpaque(false);
        updateCellRenderer();
    }

    protected void setColWidth(int col, int width) {
        this.getColumnModel().getColumn(col).setPreferredWidth(width);
        this.getColumnModel().getColumn(col).setMaxWidth(width);
        this.getColumnModel().getColumn(col).setMinWidth(width);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.getTableHeader().setFont(font);
    }

    protected void setAlternatingColors(Color c1, Color c2) {
        this.alternatingColors = true;
        this.c1 = c1;
        this.c2 = c2;
        updateCellRenderer();
    }

    protected void setHeaderColor(Color c) {
        this.headerColor = c;
        updateCellRenderer();
    }

    protected void setTableEditingAllowed(boolean b) {
        getTableHeader().setResizingAllowed(b);
        getTableHeader().setReorderingAllowed(b);
    }

    private void updateCellRenderer() {
        this.setDefaultRenderer(Object.class, new CellRenderer());
        this.getTableHeader().setDefaultRenderer(new HeaderRenderer());
    }

    private class HeaderRenderer extends WebbCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(headerColor != null) {
                c.setBackground(headerColor);
            }

            return c;
        };
    }

    private class CellRenderer extends WebbCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(alternatingColors) {
                c.setBackground(row % 2 == 0 ? c1 : c2);
            }

            return c;
        };
    }

    private static class WebbCellRenderer extends DefaultTableCellRenderer {
        public WebbCellRenderer() {
            this.setHorizontalAlignment(CENTER);
        }
    }
}
