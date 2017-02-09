package kickercup.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultRowSorter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import kickercup.xml.Mannschaft;
import kickercup.xml.Mannschaften;
import kickercup.xml.Gruppierung;
import kickercup.xml.Gruppierungen;
/**
 * Write a description of class JTableGruppierung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableGruppierung extends JPanel implements ActionListener 
{
    private boolean DEBUG = false;
    private Vector<Gruppierung> vGruppierung;
    private Map<String, Mannschaft> mannMap;
    private JTable table;
    private Mannschaften mannschaften;
    private Gruppierungen gruppierungen;

    /**
     * Constructor for objects of class JTableGruppierung
     */
    public JTableGruppierung(Mannschaften mannschaften, Gruppierungen gruppierungen)
    {
        this.setPreferredSize(new Dimension(750,720));
        this.mannschaften = mannschaften;
        this.gruppierungen = gruppierungen;
 
        vGruppierung = gruppierungen.selectAllAsVector();
        List<Mannschaft> listMannschaft = mannschaften.selectAll();
        Map mannMap = mannschaften.selectAllAsMap();

        JTableModelGruppierung tableModel = new JTableModelGruppierung(vGruppierung, mannMap);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(720, 700));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        //Set up column sizes.
        initColumnSizes(table);
        setMannschaftColumnLeft(table, table.getColumnModel().getColumn(3), listMannschaft);
        setMannschaftColumnRight(table, table.getColumnModel().getColumn(4), listMannschaft);

        JButton speichern = new JButton("speichern");
        speichern.setActionCommand("speichern");
        speichern.addActionListener(this);
        JButton loeschen = new JButton("löschen");
        loeschen.setActionCommand("loeschen");
        loeschen.addActionListener(this);
        JButton neu = new JButton("neu");
        neu.setActionCommand("neu");
        neu.addActionListener(this);

        GridBagConstraints constraint = new GridBagConstraints();
        //constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        //constraint.weightx = 0.5;
        add(speichern, constraint);
        constraint.gridx = 1;
        constraint.gridy = 0;
        add(loeschen, constraint);
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        add(neu, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 3;
        constraint.anchor = GridBagConstraints.PAGE_END;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, constraint);
    }

    /*
    public Vector<Gruppierung> getVectorGruppierung1(){
        return vGruppierung;
    }    
    */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("speichern")) {
            gruppierungen.update(vGruppierung);
            JOptionPane.showMessageDialog(this, "Daten wurden gespeichert!");
            this.repaint();
        }else if(command.equals("loeschen")){
            int row = table.getSelectedRow();
            String info = "";
            if(row >= 0){
                info = vGruppierung.get(row).getMannschaftID1() + " : " + vGruppierung.get(row).getMannschaftID2();
                vGruppierung.remove(row);
            }
            this.repaint();
            JOptionPane.showMessageDialog(this, "Die Gruppierung " + info + " wurde gelöscht!");
        }else if(command.equals("neu")){
            vGruppierung.add(new Gruppierung("01.01.2000", "10:00", 1, "000", "000"));
            this.repaint();
        }else{
        }

    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        JTableModelGruppierung model = (JTableModelGruppierung)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 3; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                null, column.getHeaderValue(),
                false, false, 0, 0);

            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
            getTableCellRendererComponent(
                table, longValues[i],
                false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column "
                    + i + ". "
                    + "headerWidth = " + headerWidth
                    + "; cellWidth = " + cellWidth);
            }

            //column.setPreferredWidth(Math.max(headerWidth, cellWidth));
            column.setMaxWidth(80);

        }
    }

    /**
     * Auswahlliste für Mannschaften wird gefüllt.
     */
    private void setMannschaftColumnLeft(JTable table, TableColumn sportColumn, List<Mannschaft> listMannschaft) {
        JComboBox comboBox = new JComboBox();
        Iterator<Mannschaft> it = listMannschaft.iterator();
        Map mannMap = new HashMap();
        while(it.hasNext()){
            Mannschaft mannschaft = it.next();
            comboBox.addItem(UtilNames.getLeft(mannschaft));
        }
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        sportColumn.setCellRenderer(new GruppierungLeftTableCellRenderer());
    }

    private void setMannschaftColumnRight(JTable table, TableColumn sportColumn, List<Mannschaft> listMannschaft) {
        JComboBox comboBox = new JComboBox();
        Iterator<Mannschaft> it = listMannschaft.iterator();
        Map mannMap = new HashMap();
        while(it.hasNext()){
            Mannschaft mannschaft = it.next();
            comboBox.addItem(UtilNames.getRight(mannschaft));
        }
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        sportColumn.setCellRenderer(new GruppierungRightTableCellRenderer());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Gruppierung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Mannschaften m = new Mannschaften("C:\\Temp\\mannschaften.xml");
        Gruppierungen g = new Gruppierungen("C:\\Temp\\gruppierungen.xml");
        //Create and set up the content pane.
        JTableGruppierung newContentPane = new JTableGruppierung(m, g);
        //newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }*/
}
