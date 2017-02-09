package kickercup.xml;


import java.util.Comparator;
/**
 * Write a description of class ComparatorRangliste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorRangliste implements Comparator
{

    @Override public int compare( Object obj1, Object obj2 )
    {

        MannschaftContainer mann1 = (MannschaftContainer)obj1;  
        MannschaftContainer mann2 = (MannschaftContainer)obj2;

        if(mann1.getPunkte() != mann2.getPunkte()){
            return mann2.getPunkte() - mann1.getPunkte();
        }else if(mann1.getTordifferenz() != mann2.getTordifferenz()){
            if(mann2.getTordifferenz() > mann1.getTordifferenz()){
                return 1;
            }else{    
                return -1;
            }
        }else if(mann1.getToreGeschossen() != mann2.getToreGeschossen()){
            if(mann2.getToreGeschossen() > mann1.getToreGeschossen()){
                return 1;
            }else{
                return -1;
            }
        }
        return 0;
    }

}
