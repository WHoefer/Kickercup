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
 * Write a description of class JTableGruppierung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableModelGruppierung extends AbstractTableModel
{

    private String[] columnNames = {"Datum", "Zeit", "Feld", "Mannschaft (Heim)", "Mannschaft (Gast)"};
    private Vector<Gruppierung> data = new Vector();
    private Map<String, Mannschaft> mannMap = new HashMap();
    public final Object[] longValues = {"01.01.3000", "14:55", "10", "123456789012345678901234567890", "123456789012345678901234567890"};
    
    
    /**
     * Constructor for objects of class JTableGruppierung
     */
    public JTableModelGruppierung(Vector<Gruppierung> data, Map<String, Mannschaft> mannMap)
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
                    case 4: return UtilNames.getRight(mann2);
                }
                return "";
            }catch(NullPointerException ex){
                return "";
            }
        }
        return "";
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
        /* if(col == 3 || col == 4 || col == 7 || col == 8){
        return false;
        }*/    
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        Gruppierung grupp = data.elementAt(row);
        if(value instanceof String){
            String val = (String)value;
            switch(col){
                case 0: grupp.setDatum(val); break;
                case 1: grupp.setZeit(val); break;
                //case 2: grupp.setFeld(Integer.parseInt(val)); break;
                case 3: grupp.setMannschaft1(UtilNames.getIdFromLeft(val)); break;
                case 4: grupp.setMannschaft2(UtilNames.getIdFromRight(val)); break;
            }
        }else{
            Integer feld = (Integer)value;
            grupp.setFeld(feld.intValue());
        }   

        fireTableCellUpdated(row, col);
        /*if(col == 5){
        fireTableCellUpdated(row, 4);
        fireTableCellUpdated(row, 3);
        }
        if(col == 6){
        fireTableCellUpdated(row, 7);
        fireTableCellUpdated(row, 8);
        }*/
    }

}

