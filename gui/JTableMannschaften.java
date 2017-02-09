
package kickercup.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import kickercup.xml.Mannschaft;
import kickercup.xml.Mannschaften;

public class JTableMannschaften extends JPanel implements ActionListener {
    private boolean DEBUG = false;
    private Vector<Mannschaft> data;
    private JTable table;
    private Mannschaften mannschaften;

    
    public JTableMannschaften(Mannschaften mannschaften) {
        this.setPreferredSize(new Dimension(750,720));
        this.mannschaften = mannschaften;
        data = new Vector();
        data.addAll(mannschaften.selectAll());

        GridBagConstraints constraint = new GridBagConstraints();

        table = new JTable(new JTableModelMannschaft(data));
        table.setPreferredScrollableViewportSize(new Dimension(720, 700));
        table.setFillsViewportHeight(true);
        //table.getSelectedRow()

        JScrollPane scrollPane = new JScrollPane(table);
        //Set up column sizes.
        initColumnSizes(table);
        setGeschlechtColumn(table, table.getColumnModel().getColumn(0));
        setKlasseColumn(table, table.getColumnModel().getColumn(3));
        setColumn(table, table.getColumnModel().getColumn(4));
        setColumn(table, table.getColumnModel().getColumn(5));

        JButton speichern = new JButton("speichern");
        speichern.setActionCommand("speichern");
        speichern.addActionListener(this);
        JButton loeschen = new JButton("löschen");
        loeschen.setActionCommand("loeschen");
        loeschen.addActionListener(this);
        JButton neu = new JButton("neu");
        neu.setActionCommand("neu");
        neu.addActionListener(this);

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

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("speichern")) {
/*            Iterator<Mannschaft> it = data.iterator();
            List<Mannschaft> updateList = new ArrayList();
            while(it.hasNext()){
                Mannschaft man = it.next();
                if(man.getMannschaftID() != null && 
                man.getSchule() != null &&
                !man.getSchule().isEmpty() &&
                man.getName() != null &&
                !man.getName().isEmpty()){
                    updateList.add(man);
                }
            }    
            if(updateList.isEmpty()){
                updateList.add(new Mannschaft("neu", "neu", "w", 0, 0, 0));
            }   */ 
            mannschaften.update(data);
            JOptionPane.showMessageDialog(this, "Daten wurden gespeichert!");
            this.repaint();
        }else if(command.equals("loeschen")){
            int row = table.getSelectedRow();
            String info = "...";
            if(row >= 0){
                info = data.get(row).getSchule();
                data.remove(row);
            }
            this.repaint();
            JOptionPane.showMessageDialog(this, "Die Klasse " + info + " wurde gelöscht!");
        }else if(command.equals("neu")){
            data.add(new Mannschaft("neu", "neu", "w", 0, 0, 0));
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
        JTableModelMannschaft model = (JTableModelMannschaft)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++) {
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

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public void setGeschlechtColumn(JTable table,TableColumn geschlechtColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("w");
        comboBox.addItem("m");
        geschlechtColumn.setCellEditor(new DefaultCellEditor(comboBox));
        geschlechtColumn.setCellRenderer(new GeschlechtTableCellRenderer());
    }

    public void setKlasseColumn(JTable table, TableColumn sportColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(5);
        comboBox.addItem(6);
        comboBox.addItem(7);
        comboBox.addItem(8);
        comboBox.addItem(9);
        comboBox.addItem(10);
        comboBox.addItem(11);
        comboBox.addItem(12);
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        sportColumn.setCellRenderer(new DefaultTableCellRenderer());
    }

    public void setColumn(JTable table, TableColumn column) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        comboBox.addItem(5);
        comboBox.addItem(6);
        comboBox.addItem(7);
        comboBox.addItem(8);
        comboBox.addItem(9);
        comboBox.addItem(10);
        comboBox.addItem(11);
        comboBox.addItem(12);
        column.setCellEditor(new DefaultCellEditor(comboBox));
        column.setCellRenderer(new DefaultTableCellRenderer());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Mannschaften");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mannschaften m = new Mannschaften("C:\\Temp\\mannschaften.xml");
        //Create and set up the content pane.
        JTableMannschaften newContentPane = new JTableMannschaften(m);
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
