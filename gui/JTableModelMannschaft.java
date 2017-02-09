package kickercup.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;
import kickercup.xml.Mannschaft;

/**
 * Write a description of class JTableMannschaft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableModelMannschaft  extends AbstractTableModel
{

    private String[] columnNames = {"Geschlecht", "Schule", "Name", "Klasse", "Gruppe", "Team"};
    private Vector<Mannschaft> data; // = new Vector();
    public final Object[] longValues = {"Jane", "Kathy", "None of the above", new Integer(20), Boolean.TRUE};

    public JTableModelMannschaft(Vector<Mannschaft> data){
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Mannschaft mann = data.elementAt(row);
        switch(col){
            case 0: return mann.getGeschlecht();
            case 1: return mann.getSchule();
            case 2: return mann.getName();
            case 3: return mann.getKlasse();
            case 4: return mann.getGruppe();
            case 5: return mann.getTeam();
        }
        return "Fehler";
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (false) {
            System.out.println("Setting value at " + row + "," + col
                + " to " + value
                + " (an instance of "
                + value.getClass() + ")");
        }
        Mannschaft mann = data.elementAt(row);
        if(value instanceof String){
            String val = (String)value;
            switch(col){
                case 0: mann.setGeschlecht(val); break;
                case 1: mann.setSchule(val); break;
                case 2: mann.setName(val); break;
            }
        }else{
            Integer val = (Integer)value;
            switch(col){
                case 3: mann.setKlasse(val.intValue()); break;
                case 4: mann.setGruppe(val.intValue()); break;
                case 5: mann.setTeam(val.intValue()); break;
            }
        }
        fireTableCellUpdated(row, col);
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                // System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}

