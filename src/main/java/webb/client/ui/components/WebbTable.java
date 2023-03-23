package webb.client.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WebbTable extends JTable {

    private boolean alternatingColors = false;
    private Color c1, c2;

    private Color headerColor = null;

    private boolean isEditingAllowed = true;

    public WebbTable(String[] columnNames, String[][] data) {
        super(new DefaultTableModel(data, columnNames));
        this.setOpaque(false);
        updateCellRenderer();
    }

    protected final void setColWidth(int col, int width) {
        this.getColumnModel().getColumn(col).setPreferredWidth(width);
        this.getColumnModel().getColumn(col).setMaxWidth(width);
        this.getColumnModel().getColumn(col).setMinWidth(width);
    }

    protected final void removeCellLines() {
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.getTableHeader().setFont(font);
    }

    protected final void setAlternatingColors(Color c1, Color c2) {
        this.alternatingColors = true;
        this.c1 = c1;
        this.c2 = c2;
        updateCellRenderer();
    }

    protected final void setHeaderColor(Color c) {
        this.headerColor = c;
        updateCellRenderer();
    }

    protected final void setTableEditingAllowed(boolean b) {
        isEditingAllowed = b;
        getTableHeader().setResizingAllowed(b);
        getTableHeader().setReorderingAllowed(b);
        updateCellRenderer();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(!isEditingAllowed) {return false;}
        return super.isCellEditable(row, column);
    }

    @Override
    public boolean isCellSelected(int row, int column) {
        if(!isEditingAllowed) {return false;}
        return super.isCellSelected(row, column);
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

            if (!isEditingAllowed) {
                setBorder(noFocusBorder);
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
