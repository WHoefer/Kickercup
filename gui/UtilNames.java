package kickercup.gui;

import kickercup.xml.Mannschaft;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Write a description of class UtilNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UtilNames
{

    public static String getLeft(Mannschaft mannschaft){
        if(mannschaft != null){
            return mannschaft.getName() + "  " + mannschaft.getSchule() + "  " + mannschaft.getGeschlecht() + "  " +  mannschaft.getMannschaftID();
        }
        return "";
    }

    public static String getIdFromLeft(String val){

        if(val != null && !val.isEmpty() && val.length() > 3){
            Pattern pattern = Pattern.compile("[wm]{1}  \\d{3,6}");
            Matcher matcher = pattern.matcher(val);
            if(matcher.find()){
                return val.substring(matcher.start()+3,matcher.end());
            }
        }
        return "";
    }
    
    
    public static String getGeschlechtFromLeft(String val){

        if(val != null && !val.isEmpty() && val.length() > 3){
            Pattern pattern = Pattern.compile("[wm]{1}  \\d{3,6}");
            Matcher matcher = pattern.matcher(val);
            if(matcher.find()){
                return val.substring(matcher.start(),matcher.start()+1);
            }
        }
        return "";
    }
    

    public static String getRight(Mannschaft mannschaft){
        if(mannschaft != null){
            return mannschaft.getMannschaftID() + "  " + mannschaft.getGeschlecht()  + "  " + mannschaft.getSchule() + "  " + mannschaft.getName();
        }
        return "";
    }

    public static String getIdFromRight(String val){
        if(val != null && !val.isEmpty() && val.length() > 3){
            Pattern pattern = Pattern.compile("\\d{3,6}  [wm]{1}");
            Matcher matcher = pattern.matcher(val);
            if(matcher.find()){
                return val.substring(matcher.start(),matcher.end()-3);
            }
        }
        return "";
    }    

    public static String getGeschlechtFromRight(String val){
        if(val != null && !val.isEmpty() && val.length() > 3){
            Pattern pattern = Pattern.compile("\\d{3,6}  [wm]{1}");
            Matcher matcher = pattern.matcher(val);
            if(matcher.find()){
                return val.substring(matcher.end()-1,matcher.end());
            }
        }
        return "";
    }    
    
    
    public static void main(String[] args){
        System.out.println("123=" + getIdFromLeft("gsdgsdgpo w  123")+"XXX");
        System.out.println("w=" + getGeschlechtFromLeft("gsdgsdgpo w  123")+"XXX");
        System.out.println("4234=" + getIdFromLeft("asfjfjnsdgh m  4234")+"XXX");
        System.out.println(getIdFromLeft("fsdf"));
        System.out.println(getIdFromLeft(null));
        System.out.println("422334=" + getIdFromLeft("asfjfjnsdgh m  422334")+"XXX");
        System.out.println("422343=" + getIdFromLeft("asfjfjnsdgh m  4223434")+"XXX");
        System.out.println("1234=" + getIdFromLeft("asf m 1234 jfjnsdgh m  4223434")+"XXX");
        
        System.out.println("123=" + getIdFromRight("123  m dsgsjetwetlk")+"XXX");
         System.out.println("m=" + getGeschlechtFromRight("123  m dsgsjetwetlk")+"XXX");
        System.out.println("1523=" +getIdFromRight( "1523  w dsgsjetwetlk")+"XXX");
        System.out.println(getIdFromRight("gdgsdg"));
        System.out.println(getIdFromRight(""));
        System.out.println(getIdFromRight(null));
        System.out.println("555012=" + getIdFromRight("555012  w sdtijggn")+"XXX");
    }
}
