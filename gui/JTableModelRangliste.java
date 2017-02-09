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
import java.util.List;
import java.util.ArrayList;
import kickercup.xml.Gruppierung;
import kickercup.xml.Mannschaft;
import kickercup.xml.Tabelle;
import kickercup.xml.MannschaftContainer;

/**
 * Write a description of class JTableRangliste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableModelRangliste extends AbstractTableModel
{
    // instance variables - replace the example below with your own
    private String[] columnNames = {"ID", "Schule", "Name", "Spiele", "Siege", "Unentschieden", "Niederlage", "Torverhältnis", "Tordifferenz", "Punkte"};
    private List<MannschaftContainer> list = new ArrayList<MannschaftContainer>();
    public final Object[] longValues = {"999", "123456789012345678901234567890", "99","99","99","99","99:99","-99","999"}; 

    /**
     * Constructor for objects of class JTableRangliste
     */
    public JTableModelRangliste(List<MannschaftContainer> list)
    {
        this.list = list;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return list.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if(list != null && !list.isEmpty()){
            MannschaftContainer mann = list.get(row);
            if(mann != null){
                //"ID", "Schule", "Name", "Spiele", "Siege", "Unentschieden", "Niederlage", "Torverhältnis", "Tordifferenz", "Punkte"
                try{
                    switch(col){
                        case 0: return mann.getMannschaftID();
                        case 1: return mann.getSchule();
                        case 2: return mann.getName();
                        case 3: return Integer.valueOf(mann.getSpiele());
                        case 4: return Integer.valueOf(mann.getSiege());
                        case 5: return Integer.valueOf(mann.getUnentschieden());
                        case 6: return Integer.valueOf(mann.getNiederlagen());
                        case 7: return Integer.toString(mann.getToreGeschossen())+":"+Integer.toString(mann.getToreErhalten());
                        case 8: return Integer.valueOf(mann.getTordifferenz());
                        case 9: return Integer.valueOf(mann.getPunkte());
                    }
                    return "";
                }catch(NullPointerException ex){
                    return "";
                }
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


}
