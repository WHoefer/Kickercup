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
import java.util.Map;
import java.util.HashMap;
import kickercup.xml.Gruppierung;
import kickercup.xml.Mannschaft;

/**
 * Write a description of class JTableModelSpielergebnisse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableModelSpielergebnisse extends AbstractTableModel
{
    private String[] columnNames = {"Datum", "Zeit", "Feld", "Mannschaft (Heim)", "Tore", "Tore", "Mannschaft (Gast)"};
    private Vector<Gruppierung> data = new Vector();
    private Map<String, Mannschaft> mannMap = new HashMap();
    public final Object[] longValues = {"01.01.3000", "14:55", "10", "123456789012345678901234567890", "999", "999", "123456789012345678901234567890"};

    
    /**
     * Constructor for objects of class JTableModelSpielergebnisse
     */
    public JTableModelSpielergebnisse(Vector<Gruppierung> data, Map<String, Mannschaft> mannMap)
    {
        this.data = data;
        this.mannMap = mannMap;
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

        Gruppierung grupp = data.elementAt(row);
        if(grupp != null){
            String id1 = grupp.getMannschaftID1();
            String id2 = grupp.getMannschaftID2();
            Mannschaft mann1 =  mannMap.get(id1);
            Mannschaft mann2 =  mannMap.get(id2);
            try{
                switch(col){
                    case 0: return grupp.getDatum();
                    case 1: return grupp.getZeit();
                    case 2: return grupp.getFeld();
                    case 3: return UtilNames.getLeft(mann1);
                    case 4: return grupp.getTore1();
                    case 5: return grupp.getTore2();
                    case 6: return UtilNames.getRight(mann2);
                }
                return "";
            }catch(NullPointerException ex){
                return "";
            }
        }
        return "";
    }

    public void setData(Vector<Gruppierung> data, Map<String, Mannschaft> mannMap){
        this.data = data;
        this.mannMap = mannMap;
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
        if(col == 4 || col == 5 ){
            return true;
        }    
        return false;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        Gruppierung grupp = data.elementAt(row);
            switch(col){
                case 4: Integer t1 = (Integer)value;
                        grupp.setTore1(t1.intValue()); 
                        break;
                case 5: Integer t2 = (Integer)value;
                        grupp.setTore2(t2.intValue()); 
                        break;
            }
        fireTableCellUpdated(row, col);
    }

}

