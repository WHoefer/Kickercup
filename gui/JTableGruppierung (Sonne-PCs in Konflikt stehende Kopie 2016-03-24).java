package gui;

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
import java.util.Map;
import java.util.HashMap;
import xml.Gruppierung;
import xml.Mannschaft;
/**
 * Write a description of class JTableGruppierung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableGruppierung extends AbstractTableModel
{

    /**
     * Constructor for objects of class JTableGruppierung
     */
    public JTableGruppierung(Vector<Gruppierung> data, Map<String, Mannschaft> mannMap)
    {
        this.data = data;
        this.mannMap = mannMap;
    }
    private String[] columnNames = {"Datum", "Zeit", "Feld",
            "Geschlecht", "Schule", "Mannschaft",
            "Mannschaft", "Schule", "Geschlecht"};
    private Vector<Gruppierung> data = new Vector();
    private Map<String, Mannschaft> mannMap = new HashMap();
    public final Object[] longValues = {"Jane", "Kathy",
            "None of the above",
            new Integer(20), Boolean.TRUE};
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
        Gruppierung grupp = data.elementAt(row);
        Mannschaft mann1 =  mannMap.get(grupp.getMannschaftID1());
        Mannschaft mann2 =  mannMap.get(grupp.getMannschaftID2());
        switch(col){
            case 0: return grupp.getDatum();
            case 1: return grupp.getZeit();
            case 2: return grupp.getFeld();
            case 3: return grupp.getKlasse();
            case 4: return grupp.getTeam();
            case 5: return grupp.getGruppe();
            case 6: return grupp.getGruppe();
            case 7: return grupp.getGruppe();
            case 8: return grupp.getGruppe();
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
        if (true) {
            System.out.println("Setting value at " + row + "," + col
                + " to " + value
                + " (an instance of "
                + value.getClass() + ")");
        }
        Mannschaft mann = data.elementAt(row);
        String val = (String)value;
        switch(col){
            case 0: mann.setGeschlecht(val); break;
            case 1: mann.setSchule(val); break;
            case 2: mann.setName(val); break;
            case 3: mann.setKlasse(Integer.parseInt(val)); break;
            case 4: mann.setTeam(Integer.parseInt(val)); break;
            case 5: mann.setGruppe(Integer.parseInt(val)); break;
        }

        fireTableCellUpdated(row, col);
    }

}

