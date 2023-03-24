package webb.client.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * A wrapper class for JTable that provides some
 * useful methods for setting it up and manipulating it.
 */
public class WebbTable extends JTable {

    private boolean alternatingColors = false;
    private Color c1, c2;

    private Color headerColor = null;

    private boolean isEditingAllowed = true;

    /**
     * Creates a new table with the given column names and data.
     * @param columnNames The column names.
     * @param data The data.
     */
    public WebbTable(String[] columnNames, String[][] data) {
        super(new DefaultTableModel(data, columnNames));
        this.setOpaque(false);
        updateCellRenderer();
    }

    /**
     * Sets the width of the given column.
     * @param col The column.
     * @param width The width.
     */
    protected final void setColWidth(int col, int width) {
        this.getColumnModel().getColumn(col).setPreferredWidth(width);
        this.getColumnModel().getColumn(col).setMaxWidth(width);
        this.getColumnModel().getColumn(col).setMinWidth(width);
    }

    /**
     * Removes the grid lines from the table.
     */
    protected final void removeCellLines() {
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
    }

    /**
     * Sets the font of the table and its header.
     * @param font The font.
     */
    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.getTableHeader().setFont(font);
    }

    /**
     * Enables alternating colors for the table.
     * @param c1 The first color.
     * @param c2 The second color.
     */
    protected final void setAlternatingColors(Color c1, Color c2) {
        this.alternatingColors = true;
        this.c1 = c1;
        this.c2 = c2;
        updateCellRenderer();
    }

    /**
     * Sets the color of the table header.
     * @param c The color.
     */
    protected final void setHeaderColor(Color c) {
        this.headerColor = c;
        updateCellRenderer();
    }

    /**
     * Enables or disables editing of the table.
     * @param b True to enable editing, false to disable.
     */
    protected final void setTableEditingAllowed(boolean b) {
        isEditingAllowed = b;
        getTableHeader().setResizingAllowed(b);
        getTableHeader().setReorderingAllowed(b);
        updateCellRenderer();
    }

    /**
     * Overridden to disable editing if editing is not allowed.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if(!isEditingAllowed) {return false;}
        return super.isCellEditable(row, column);
    }

    /**
     * Overridden to disable editing if editing is not allowed.
     */
    @Override
    public boolean isCellSelected(int row, int column) {
        if(!isEditingAllowed) {return false;}
        return super.isCellSelected(row, column);
    }

    /**
     * Updates the cell and header renderer.
     */
    private void updateCellRenderer() {
        this.setDefaultRenderer(Object.class, new CellRenderer());
        this.getTableHeader().setDefaultRenderer(new HeaderRenderer());
    }

    /**
     * The header renderer.
     * Used to change the header color.
     */
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

    /**
     * The cell renderer.
     * Used to change the cell color, and disable editing.
     */
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

    /**
     * A cell renderer that centers the text.
     */
    private static class WebbCellRenderer extends DefaultTableCellRenderer {
        public WebbCellRenderer() {
            this.setHorizontalAlignment(CENTER);
        }
    }
}
