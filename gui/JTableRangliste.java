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
import kickercup.xml.MannschaftContainer;
import kickercup.xml.Tabelle;

/**
 * Write a description of class JTableSpielergebnisse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JTableRangliste extends JPanel
{
    private boolean DEBUG = false;
    private Vector<Gruppierung> vGruppierung;
    private Map<String, Mannschaft> mannMap;
    private JTable table;
    private List<MannschaftContainer> rangliste;
    private Gruppierungen gruppierungen;

    /**
     * Constructor for objects of class JTableSpielergebnisse
     */
    public JTableRangliste(Mannschaften m, Gruppierungen g, String geschlecht, int klasse)
    {
        this.setPreferredSize(new Dimension(800,300));

        Tabelle tabelle = new Tabelle();
        tabelle.addMannschaften(m);
        tabelle.addGruppierungen(g);
        this.rangliste = tabelle.getTabelleMannschaften(geschlecht, klasse);
        
        GridBagConstraints constraint = new GridBagConstraints();
        JTableModelRangliste tableModel = new JTableModelRangliste(rangliste);
        table = new JTable(tableModel);
        //table.setPreferredScrollableViewportSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 280));
        //Set up column sizes.
        initColumnSizes(table);

        //constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        constraint.gridx = 0;
        constraint.gridy = 0;
        add(scrollPane, constraint);
    }


    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        JTableModelRangliste model = (JTableModelRangliste)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 7; i++) {
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


            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Rangliste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mannschaften m = new Mannschaften("C:\\Temp\\mannschaften.xml");
        Gruppierungen g = new Gruppierungen("C:\\Temp\\gruppierungen.xml");
        //Create and set up the content pane.
        JTableRangliste newContentPane = new JTableRangliste(m, g, "m", 5);
        newContentPane.setOpaque(true); //content panes must be opaque
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
