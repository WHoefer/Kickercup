package kickercup.xml;


import java.util.List;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
 * Write a description of class Mannschaften here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mannschaften
{
    private List<Mannschaft> liste;
    private String pathFile;

    /**
     * Constructor for objects of class Mannschaften
     */
    public Mannschaften(String pathFile)
    {
        this.pathFile = pathFile;
    }

    public void update(List<Mannschaft> liste)
    {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // mannschaften
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("mannschaften");
            doc.appendChild(rootElement);

            Iterator<Mannschaft> it = liste.iterator();
            while(it.hasNext()){
                Mannschaft man = it.next();
                // mannschaft
                Element mannschaft = doc.createElement("Schulmannschaft");
                rootElement.appendChild(mannschaft);
                // geschlecht
                Element geschlecht = doc.createElement("geschlecht");
                geschlecht.appendChild(doc.createTextNode(man.getGeschlecht()));
                mannschaft.appendChild(geschlecht);
                // name
                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(man.getName()));
                mannschaft.appendChild(name);
                // schule
                Element schule = doc.createElement("schule");
                schule.appendChild(doc.createTextNode(man.getSchule()));
                mannschaft.appendChild(schule);
                // klasse
                Element klasse = doc.createElement("klasse");
                klasse.appendChild(doc.createTextNode(Long.toString(man.getKlasse())));
                mannschaft.appendChild(klasse);
                // team
                Element team = doc.createElement("team");
                team.appendChild(doc.createTextNode(Long.toString(man.getTeam())));
                mannschaft.appendChild(team);
                // gruppe
                Element gruppe = doc.createElement("gruppe");
                gruppe.appendChild(doc.createTextNode(Long.toString(man.getGruppe())));
                mannschaft.appendChild(gruppe);
                // manschaft
                Element mannsch = doc.createElement("mannschaftID");
                mannsch.appendChild(doc.createTextNode(man.getMannschaftID()));
                mannschaft.appendChild(mannsch);
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

    public void update(Vector<Mannschaft> data)
    {
        Collections.sort(data, new ComparatorMannschaft());
        Iterator<Mannschaft> it = data.iterator();
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
        }    
        update(updateList);
    }

    public List<Mannschaft> selectAll(){
        List<Mannschaft> liste = new ArrayList(); 
        try {

            File fXmlFile = new File(pathFile);
            if(!fXmlFile.exists()){
                fXmlFile.createNewFile();
                List<Mannschaft> list = new ArrayList();
                list.add(new Mannschaft("NEUE Schule", "NEUER Klassenname", "w", 5 , 0, 0));
                update(list);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Schulmannschaft");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String geschlecht  = eElement.getElementsByTagName("geschlecht").item(0).getTextContent();
                    String schule =      eElement.getElementsByTagName("schule").item(0).getTextContent();
                    String name =        eElement.getElementsByTagName("name").item(0).getTextContent();
                    int klasse =         Integer.parseInt(eElement.getElementsByTagName("klasse").item(0).getTextContent());
                    int gruppe =         Integer.parseInt(eElement.getElementsByTagName("gruppe").item(0).getTextContent());
                    int team =           Integer.parseInt(eElement.getElementsByTagName("team").item(0).getTextContent());
                    Mannschaft man = new Mannschaft(schule, name, geschlecht, klasse, gruppe, team);
                    liste.add(man);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Map<String, Mannschaft> selectAllAsMap(){
        List<Mannschaft> listMannschaft = selectAll();
        Iterator<Mannschaft> it = listMannschaft.iterator();
        Map mannMap = new HashMap();
        while(it.hasNext()){
            Mannschaft mannschaft = it.next();
            mannMap.put(mannschaft.getMannschaftID(), mannschaft);
            //System.out.println(mannschaft.getMannschaftID());
        }
        return mannMap;
    }

    public static List<Integer> getKlassen(List<Mannschaft> list){
        Map<String,Integer> map = new HashMap(); 
        try {

            for (Iterator<Mannschaft> it = list.iterator(); it.hasNext();) {
                Mannschaft mann = it.next();
                Integer value =  Integer.valueOf(mann.getKlasse());
                String key = value.toString();
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(map != null && !map.isEmpty()){
            List valueList = new ArrayList(map.values());
            Collections.sort(valueList);
            return valueList;
        }
        return null;
    }

    public static void main(String[] args){
        Mannschaften m = new Mannschaften("C:\\temp\\file.xml");
        List<Mannschaft> liste = new ArrayList();

        liste.add(new Mannschaft("Humbolt", "5B", "m", 5 , 1, 1));
        liste.add(new Mannschaft("KGB", "5C", "m" , 5, 1, 2));
        liste.add(new Mannschaft("Berthold Brecht", "5A", "m", 5 , 1, 3));
        m.update(liste);
        List<Mannschaft> listeIn = m.selectAll();
        Iterator<Mannschaft> it = listeIn.iterator();
        while(it.hasNext()){
            Mannschaft man = it.next();
            String schule = man.getSchule();
            String name = man.getName();
            String geschlecht = man.getGeschlecht();
            String mannschaftID = man.getMannschaftID();
            int team = man.getTeam();
            int klasse = man.getKlasse();
            int gruppe = man.getGruppe();
            System.out.printf("%1$2s %2$20s %3$5s %4$4s %5$4s %6$4s %7$4s%n", geschlecht, schule, name, mannschaftID, klasse, gruppe, team);
        }
    }
}