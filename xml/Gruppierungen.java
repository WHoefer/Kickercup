package kickercup.xml;


import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * Write a description of class Gruppierungen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gruppierungen
{
    private String pathFile;

    /**
     * Constructor for objects of class Gruppierungen
     */
    public Gruppierungen(String pathFile)
    {
        this.pathFile = pathFile;
    }

    public void update(List<Gruppierung> liste)
    {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // mannschaften
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("gruppierungen");
            doc.appendChild(rootElement);

            Iterator<Gruppierung> it = liste.iterator();
            while(it.hasNext()){
                Gruppierung grupp = it.next();
                // gruppierung
                Element gruppierung = doc.createElement("gruppierung");
                rootElement.appendChild(gruppierung);
                //datum
                Element datum = doc.createElement("datum");
                datum.appendChild(doc.createTextNode(grupp.getDatum()));
                gruppierung.appendChild(datum);
                //zeit
                Element zeit = doc.createElement("zeit");
                zeit.appendChild(doc.createTextNode(grupp.getZeit()));
                gruppierung.appendChild(zeit);
                // feld
                Element feld = doc.createElement("feld");
                feld.appendChild(doc.createTextNode(Integer.toString(grupp.getFeld())));
                gruppierung.appendChild(feld);
                // mannschaftID1
                Element mannschaftID1 = doc.createElement("mannschaftID1");
                mannschaftID1.appendChild(doc.createTextNode(grupp.getMannschaftID1()));
                gruppierung.appendChild(mannschaftID1);
                // tore1
                Element tore1 = doc.createElement("tore1");
                tore1.appendChild(doc.createTextNode(Integer.toString(grupp.getTore1())));
                gruppierung.appendChild(tore1);
                // punkte1
                Element punkte1 = doc.createElement("punkte1");
                punkte1.appendChild(doc.createTextNode(Long.toString(grupp.getPunkte1())));
                gruppierung.appendChild(punkte1);
                // mannschaftID2
                Element mannschaftID2 = doc.createElement("mannschaftID2");
                mannschaftID2.appendChild(doc.createTextNode(grupp.getMannschaftID2()));
                gruppierung.appendChild(mannschaftID2);
                // tore2
                Element tore2 = doc.createElement("tore2");
                tore2.appendChild(doc.createTextNode(Integer.toString(grupp.getTore2())));
                gruppierung.appendChild(tore2);
                // punkte2
                Element punkte2 = doc.createElement("punkte2");
                punkte2.appendChild(doc.createTextNode(Long.toString(grupp.getPunkte2())));
                gruppierung.appendChild(punkte2);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathFile));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void update(Vector<Gruppierung> vGruppierung)
    {
        Collections.sort(vGruppierung, new ComparatorGruppierung());
        Iterator<Gruppierung> it = vGruppierung.iterator();
        List<Gruppierung> updateList = new ArrayList();
        while(it.hasNext()){
            Gruppierung grupp = it.next();
            if(grupp.getDatum() != null && !grupp.getDatum().isEmpty() &&
            grupp.getZeit() != null && !grupp.getZeit().isEmpty()){
                updateList.add(grupp);
            }
        }    
        if(updateList.isEmpty()){
            updateList.add(new Gruppierung("01.01.2000", "10:00", 1, "000", "000"));
        }    
        update(updateList);
    }

    /**
     * Method selectAll
     *
     * @return The return value
     */
    public List<Gruppierung> selectAll(){
        List<Gruppierung> liste = new ArrayList(); 
        try {

            File fXmlFile = new File(pathFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            if(!fXmlFile.exists()){
                fXmlFile.createNewFile();
                List<Gruppierung> list = new ArrayList();
                list.add(new Gruppierung("01.01.2050", "14:00", 1, "000" , "000", 0, 0, 0, 0));
                update(list);
            }

            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("gruppierung");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String datum  =     eElement.getElementsByTagName("datum").item(0).getTextContent();
                    String zeit =       eElement.getElementsByTagName("zeit").item(0).getTextContent();
                    int feld =          Integer.parseInt(eElement.getElementsByTagName("feld").item(0).getTextContent());
                    String mannschaftID1 = eElement.getElementsByTagName("mannschaftID1").item(0).getTextContent();
                    String mannschaftID2 = eElement.getElementsByTagName("mannschaftID2").item(0).getTextContent();
                    int tore1 =         Integer.parseInt(eElement.getElementsByTagName("tore1").item(0).getTextContent());
                    int tore2 =         Integer.parseInt(eElement.getElementsByTagName("tore2").item(0).getTextContent());
                    int punkte1 =       Integer.parseInt(eElement.getElementsByTagName("punkte1").item(0).getTextContent());
                    int punkte2 =       Integer.parseInt(eElement.getElementsByTagName("punkte2").item(0).getTextContent());
                    Gruppierung grupp = new Gruppierung(datum, zeit, feld, mannschaftID1, mannschaftID2, tore1, tore2, punkte1, punkte2);
                    liste.add(grupp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(liste, new ComparatorGruppierung());
        return liste;
    }

    /**
     * Method selectAllAsVector
     *
     * @return The return value
     */
    public Vector<Gruppierung> selectAllAsVector(){
        Vector<Gruppierung> vGruppierung = new Vector();
        vGruppierung.addAll(selectAll());
        return vGruppierung;
    }

    public static void main(String[] args){
        Gruppierungen m = new Gruppierungen("C:\\temp\\file3.xml");
        List<Gruppierung> liste = new ArrayList();
        liste.add(new Gruppierung("14.07.2016", "14:00", 1, "511", "512"));
        liste.add(new Gruppierung("14.07.2016", "14:10", 1, "521", "522"));
        liste.add(new Gruppierung("14.07.2016", "14:20", 1, "611", "612"));
        m.update(liste);
        List<Gruppierung> listeIn = m.selectAll();
        Iterator<Gruppierung> it = listeIn.iterator();
        while(it.hasNext()){
            Gruppierung grupp = it.next();
            String datum = grupp.getDatum();
            String zeit = grupp.getZeit();
            int feld = grupp.getFeld();
            String mannschaftID1 = grupp.getMannschaftID1();
            String mannschaftID2 = grupp.getMannschaftID2();
            int tore1 = grupp.getTore1();
            int tore2 = grupp.getTore2();
            int punkte1 = grupp.getPunkte1();
            int punkte2 = grupp.getPunkte2();
            System.out.printf("%1$10s %2$5s %3$1s %4$3s %5$2s %6$2s %7$3s %8$2s %9$2s%n", datum, zeit, feld, mannschaftID1, tore1, punkte1, mannschaftID2, tore2, punkte2);
        }
    }

}
