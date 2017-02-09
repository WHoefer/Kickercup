package kickercup.gui;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Color;

/**
 * Write a description of class GeschlechtTableCellRenderer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GeschlechtTableCellRenderer extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected,
    boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        if (isSelected) {
            cell.setBackground(Color.gray);
        } else {
            if(obj instanceof String){
                String val = (String) obj;
                if (val.equals("w")) {
                    cell.setBackground(new Color(255,180,180));
                } else {
                    cell.setBackground(new Color(180,180,255));
                }
            }
        }
        return cell;
    }
}
