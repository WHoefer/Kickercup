package kickercup.xml;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import kickercup.xml.Gruppierungen;
import kickercup.xml.Gruppierung;
import kickercup.xml.Mannschaften;
import kickercup.xml.Mannschaft;

/**
 * Write a description of class Tabelle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tabelle
{

    private Map<String,MannschaftContainer>  tabelleMap;

    /**
     * Constructor for objects of class Tabelle
     */
    public Tabelle()
    {
        tabelleMap = new HashMap<String,MannschaftContainer>();
    }

    private void addMannschaft(MannschaftContainer mannschaftContainer){
        String key = mannschaftContainer.getMannschaftID();
        MannschaftContainer value = mannschaftContainer;
        tabelleMap.put(key, value);
    }

    public void addMannschaften(Mannschaften mannschaften){
        if(mannschaften != null){
            List<Mannschaft> list = mannschaften.selectAll();
            if(list != null && !list.isEmpty()){
                for(Iterator<Mannschaft> it = list.iterator(); it.hasNext();){
                    Mannschaft mannschaft = it.next();
                    MannschaftContainer mannschaftContainer = new MannschaftContainer(mannschaft);
                    addMannschaft(mannschaftContainer);
                }
            }
        }
    }

    
    public void addGruppierungen(Gruppierungen gruppierungen){
        if(gruppierungen != null){
            List<Gruppierung> list = gruppierungen.selectAll();
            if(list != null && !list.isEmpty()){
              setErgebnisse(list);
            }  
        }
    }
    
    public List<MannschaftContainer> getTabelleMannschaften(String geschlecht, int klasse){
        List<MannschaftContainer> newList = new ArrayList<MannschaftContainer>();
        if(!tabelleMap.isEmpty()){
            Collection<MannschaftContainer> coll = tabelleMap.values();
            for(Iterator<MannschaftContainer> it = coll.iterator(); it.hasNext();){
                MannschaftContainer mannschaftContainer = it.next();
                String geschechtC = mannschaftContainer.getGeschlecht();
                int klasseC = mannschaftContainer.getKlasse();
                if(klasse == klasseC && geschlecht.equals(geschechtC)){
                    newList.add(mannschaftContainer);
                }
            }
            Collections.sort(newList, new ComparatorRangliste());
        }
        return newList;
    }

    private void setErgebnisse(List<Gruppierung> lisGruppierung ){
        if(lisGruppierung != null && !lisGruppierung.isEmpty()){
            for(Iterator<Gruppierung> it =  lisGruppierung.iterator(); it.hasNext();){
                Gruppierung gruppierung = it.next();
                String id1 = gruppierung.getMannschaftID1();
                String id2 = gruppierung.getMannschaftID2();
                if(tabelleMap != null && !tabelleMap.isEmpty() && tabelleMap.containsKey(id1) && tabelleMap.containsKey(id1)){
                    MannschaftContainer mann1 =  tabelleMap.get(id1);
                    MannschaftContainer mann2 =  tabelleMap.get(id2);
                    int tore1 = gruppierung.getTore1();
                    int tore2 = gruppierung.getTore2();            
                    if(tore1 >= 0 && tore2 >= 0){
                        mann1.addToreErhalten(tore2);
                        mann1.addToreGeschossen(tore1);
                        mann2.addToreErhalten(tore1);
                        mann2.addToreGeschossen(tore2);
                        mann1.addSpiel();
                        mann2.addSpiel();
                        if(tore1 == tore2){
                            mann1.addPunkte(1);
                            mann2.addPunkte(1);
                            mann1.addUnentschieden();
                            mann2.addUnentschieden();
                        }else if(tore1 > tore2){
                            mann1.addPunkte(3);
                            mann1.addSiege();
                            mann2.addNiederlagen();
                        }else{
                            mann2.addPunkte(3);
                            mann2.addSiege();
                            mann1.addNiederlagen();
                        }
                    }
                }
            }
        }
    }
    
    
    

    public static void main(String[] args){
        Mannschaften m = new Mannschaften("C:\\Temp\\mannschaften.xml");
        Gruppierungen g = new Gruppierungen("C:\\Temp\\gruppierungen.xml");
        Tabelle tabelle = new Tabelle();
        tabelle.addMannschaften(m);
        tabelle.addGruppierungen(g);
        List<MannschaftContainer> listKlasse5Jungs = tabelle.getTabelleMannschaften("m", 5);
        System.out.printf(" %1$s  %7$-40.40s %2$s %3$s %4$s %5$s %6$s %8$s  %n","ID", "G", "Punkte", "ToreGeschossen", "ToreErhalten", "Tordifferenz", "Schule", "Spiele");
        for(Iterator<MannschaftContainer> it = listKlasse5Jungs.iterator(); it.hasNext();){
          MannschaftContainer ma = it.next();
          System.out.printf(" %1$s %7$-40.40s %2$s %3$d %4$d %5$d %6$d %8$s %n",ma.getMannschaftID(), ma.getGeschlecht(), ma.getPunkte(), ma.getToreGeschossen(), ma.getToreErhalten(), ma.getTordifferenz(), ma.getSchule(), ma.getSpiele());
        }

    }

}
