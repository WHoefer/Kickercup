package kickercup.xml;


/**
 * Write a description of class Mannschaften here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mannschaft
{

    private String schule;
    private String name;
    private String geschlecht; //m/w
    private int klasse;
    private int team;
    private int gruppe;
    //private String mannschaft; //klasse+gruppe+team

    
    public Mannschaft(String schule, String name, String geschlecht, int klasse, int gruppe, int team)
    {
        this.schule = schule;
        this.name = name;
        this.klasse = klasse;
        this.geschlecht = geschlecht;
        this.klasse = klasse;
        this.team = team;
        this.gruppe = gruppe;
    }

    public String getSchule()
    {
        return schule;
    }
    
    public void setSchule(String schule){
        this.schule = schule;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }    
    
    public String getGeschlecht()
    {
        return geschlecht;
    }
    
    public void setGeschlecht(String geschlecht){
        this.geschlecht = geschlecht;
    }    

    
    public int getKlasse()
    {
        return klasse;
    }
    
    public void setKlasse(int klasse){
        this.klasse = klasse;
    }
    
    public int getGruppe()
    {
        return gruppe;
    }
    
    public void setGruppe(int gruppe){
        this.gruppe = gruppe;
    }
    
    public int getTeam()
    {
        return team;
    }
    
    public void setTeam(int team ){
        this.team = team;
    }

    public String getMannschaftID()
    {
        return Integer.toString(klasse)+Integer.toString(gruppe)+Integer.toString(team);
    }
    
}
