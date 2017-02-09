package kickercup.gui;

import java.util.Comparator;
import kickercup.xml.Gruppierung;

/**
 * Write a description of class ComparatorGruppierung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorGruppierung implements Comparator
{

    @Override public int compare( Object obj1, Object obj2 )
    {

        StringBuffer time1 = new StringBuffer();
        StringBuffer time2 = new StringBuffer();

        Gruppierung gr1 = (Gruppierung)obj1;  
        Gruppierung gr2 = (Gruppierung)obj2;

        if(gr1.getDatum() != null && !gr1.getDatum().isEmpty() && gr1.getDatum().length() == 10 &&
        gr2.getDatum() != null && !gr2.getDatum().isEmpty() && gr2.getDatum().length() == 10 &&
        gr1.getZeit() != null && !gr1.getZeit().isEmpty() && gr1.getZeit().length() == 5 &&
        gr2.getZeit() != null && !gr2.getZeit().isEmpty() && gr2.getZeit().length() == 5){
            time1.append(gr1.getDatum().substring(6));
            time1.append(gr1.getDatum().substring(3, 5));
            time1.append(gr1.getDatum().substring(0, 2));
            time1.append(gr1.getZeit());
            time2.append(gr2.getDatum().substring(6));
            time2.append(gr2.getDatum().substring(3, 5));
            time2.append(gr2.getDatum().substring(0, 2));
            time2.append(gr2.getZeit());
            int comp = time1.toString().compareTo(time2.toString());
            if(comp != 0){
                return comp;
            }
            return gr1.getFeld() - gr2.getFeld();
        }
        return 1;
    }

}
