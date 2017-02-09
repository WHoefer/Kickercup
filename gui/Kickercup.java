package kickercup.gui;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import kickercup.xml.Mannschaft;
import kickercup.xml.Mannschaften;
import kickercup.xml.Gruppierung;
import kickercup.xml.Gruppierungen;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

/**
 * Write a description of class Kickercup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kickercup extends JPanel implements ActionListener 
{
    // instance variables - replace the example below with your own
    JTabbedPane tabpane;
    Mannschaften m;
    Gruppierungen g;
    JTableSpielergebnisse contentPaneSpielergebnisseVorrunde;
    JPanel contentRanglisten;

    /**
     * Constructor for objects of class Kickercup
     */
    public Kickercup()
    {
        // Daten einlesen
        String dir = System.getProperty("user.dir");
        m = new Mannschaften(dir + "\\mannschaften.xml");
        g = new Gruppierungen(dir + "\\gruppierungen.xml");
        // Content für Spielergebnisse erszeugen
        contentPaneSpielergebnisseVorrunde = new JTableSpielergebnisse(m, g, this);
        // Content für Ranglisten erzeugen
        contentRanglisten  = new JPanel();
        contentPaneSpielergebnisseVorrunde.setContentRangliste(contentRanglisten);
        setContentRanglisten();
        /*contentRanglisten.setLayout(new BoxLayout(contentRanglisten, BoxLayout.PAGE_AXIS));
        contentRanglisten.removeAll();
        List list = Mannschaften.getKlassen(m.selectAll());
        for(Iterator<Integer> it = list.iterator(); it.hasNext();){
        Integer klasse = it.next();
        contentRanglisten.add(new JLabel("Mädchen Klasse " + klasse.toString()));
        contentRanglisten.add(new JTableRangliste(m, g, "w", klasse.intValue()));
        contentRanglisten.add(new JLabel("Jungen Klasse " + klasse.toString()));
        contentRanglisten.add(new JTableRangliste(m, g, "m",  klasse.intValue()));
        }*/
        JScrollPane contentScrollPaneRanglisten = new JScrollPane(contentRanglisten);
        contentScrollPaneRanglisten.setPreferredSize(new Dimension(800,600));
        // Erzeugung eines JTabbedPane-Objektes
        tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
        tabpane.addTab("Spielergebnisse Vorrunde", contentPaneSpielergebnisseVorrunde);
        tabpane.addTab("Ranglisten", contentScrollPaneRanglisten);
    }

    public JTabbedPane getContentPane(){
        return tabpane;
    }

    public void setContentRanglisten(){
        contentRanglisten.removeAll();
        contentRanglisten.setLayout(new BoxLayout(contentRanglisten, BoxLayout.PAGE_AXIS));
        List list = Mannschaften.getKlassen(m.selectAll());
        for(Iterator<Integer> it = list.iterator(); it.hasNext();){
            Integer klasse = it.next();
            contentRanglisten.add(new JLabel("Mädchen Klasse " + klasse.toString()));
            contentRanglisten.add(new JTableRangliste(m, g, "w", klasse.intValue()));
            contentRanglisten.add(new JLabel("Jungen Klasse " + klasse.toString()));
            contentRanglisten.add(new JTableRangliste(m, g, "m",  klasse.intValue()));
        }

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Verzeichnis")) {
            JDialog dialog = new JDialog();
            dialog.setSize(800, 600);
            dialog.setModal(true);
            dialog.setVisible(true);
            this.repaint();
        }else if(command.equals("Gruppierung")){
            JDialog dialog = new JDialog();
            dialog.add(new JTableGruppierung(m, g));
            dialog.setTitle("Gruppierungen verwalten!");
            dialog.setSize(800, 850);
            dialog.setModal(true);
            dialog.setVisible(true);
            contentPaneSpielergebnisseVorrunde.setData();
            this.repaint();
        }else if(command.equals("Mannschaft")){
            JDialog dialog = new JDialog();
            dialog.add(new JTableMannschaften(m));
            dialog.setTitle("Mannschaften verwalten!");
            dialog.setSize(800, 850);
            dialog.setModal(true);
            dialog.setVisible(true);
            contentPaneSpielergebnisseVorrunde.setData();
            this.repaint();
        }else if(command.equals("Info")){
            JOptionPane.showMessageDialog(this, "Version 1.0");
            this.repaint();
        }else{
        }

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Kickercup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        Kickercup newContentPane = new Kickercup();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane.getContentPane());
        // MenüItem erstellen
        JMenuItem itemVerzeichnis = new JMenuItem("Verzeichnis suchen...");
        itemVerzeichnis.setActionCommand("Verzeichnis");
        itemVerzeichnis.addActionListener(newContentPane);
        JMenuItem itemMannschaften = new JMenuItem("Mannschaften...");
        itemMannschaften.setActionCommand("Mannschaft");
        itemMannschaften.addActionListener(newContentPane);
        JMenuItem itemGruppierungen = new JMenuItem("Gruppierung...");
        itemGruppierungen.setActionCommand("Gruppierung");
        itemGruppierungen.addActionListener(newContentPane);
        JMenuItem itemInfo = new JMenuItem("Version");
        itemInfo.setActionCommand("Info");
        itemInfo.addActionListener(newContentPane);
        
        Border bo = new LineBorder(Color.yellow);
        JMenuBar bar = new JMenuBar();
        bar.setBorder(bo);
        
        JMenu menuDatei = new JMenu("Datei");
        menuDatei.add(itemVerzeichnis);
        JMenu menuStasmmdaten = new JMenu("Stammdaten");
        menuStasmmdaten.add(itemMannschaften);
        menuStasmmdaten.add(itemGruppierungen);
        JMenu menuInfo = new JMenu("Info");
        menuInfo.add(itemInfo);
        bar.add(menuDatei);
        bar.add(menuStasmmdaten);
        bar.add(menuInfo);
        frame.setJMenuBar(bar);
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
    }
}
