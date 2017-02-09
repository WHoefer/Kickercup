package kickercup.xml;


import java.util.Comparator;
/**
 * Write a description of class Comparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorMannschaft implements Comparator
{

    @Override public int compare( Object obj1, Object obj2 )
    {
        Mannschaft mann1 = (Mannschaft)obj1;  
        Mannschaft mann2 = (Mannschaft)obj2;

        if(mann1.getGeschlecht() != null && mann2.getGeschlecht() != null){
            if(mann2.getKlasse() != mann1.getKlasse()){
               return mann1.getKlasse() - mann2.getKlasse();
            }else if(!mann2.getGeschlecht().equals(mann1.getGeschlecht())){
               if(!mann2.getGeschlecht().equals("w")){
                   return 1;
                }
                return -1;
            }else if(mann2.getGruppe() != mann1.getGruppe()){
               return mann1.getGruppe() - mann2.getGruppe();
            }else if(mann2.getTeam() != mann1.getTeam()){
               return mann1.getTeam() - mann2.getTeam();
            }
        }
        return 0;
    }

}
