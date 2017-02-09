package kickercup.xml;


import kickercup.xml.Mannschaft;
/**
 * Write a description of class ManschaftContainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MannschaftContainer
{
    private String mannschaftID;
    private int punkte;
    private int toreGeschossen;
    private int toreErhalten;
    private int siege;
    private int unentschieden;
    private int niederlagen;
    //private int tordifferenz;
    private int spiele;
    private int klasse;
    private String geschlecht;
    private String schule;
    private String name;

    /**
     * Constructor for objects of class ManschaftContainer
     */
    public MannschaftContainer(Mannschaft mannschaft)
    {
        this.mannschaftID = mannschaft.getMannschaftID();
        this.geschlecht = mannschaft.getGeschlecht();
        this.klasse = mannschaft.getKlasse();
        this.schule = mannschaft.getSchule();
        this.name = mannschaft.getName();
        this.punkte = 0;
        this.toreGeschossen = 0;
        this.toreErhalten = 0;
        this.spiele = 0;
        this.siege = 0;
        this.unentschieden = 0;
        this.niederlagen = 0;
    }

    /*public void setMannschaftID(String mannschaftID ){
    this.mannschaftID  = mannschaftID;
    }*/

    public String getMannschaftID(){
        return this.mannschaftID;
    }    

    public void addPunkte(int punkte){
        this.punkte = this.punkte + punkte;
    }

    /*public void setPunkte(int punkte){
    this.punkte = punkte;
    }*/

    public int getPunkte(){
        return this.punkte;
    }    

    public void addToreGeschossen(int toreGeschossen){
        this.toreGeschossen = this.toreGeschossen + toreGeschossen;
    }

    /*public void setToreGeschossen(int toreGeschossen){
    this.toreGeschossen = toreGeschossen;
    }*/

    public int getToreGeschossen(){
        return this.toreGeschossen;
    }    

    public void addToreErhalten(int toreErhalten){
        this.toreErhalten = this.toreErhalten + toreErhalten;
    }

    /*public void setToreErhalten(int toreErhalten){
    this.toreErhalten = toreErhalten;
    }*/

    public int getToreErhalten(){
        return this.toreErhalten;
    }    

    /*public void setTordifferenz(int tordifferenz){
    this.tordifferenz;
    }*/

    public int getTordifferenz(){
        return this.toreGeschossen - this.toreErhalten;
    }    

    public void addSpiel(){
        this.spiele++;
    }

    public void addSiege(){
        this.siege++;
    }

    public void addNiederlagen(){
        this.niederlagen++;
    }

    public void addUnentschieden(){
        this.unentschieden++;
    }

    public int getSpiele(){
        return this.spiele;
    }

    public int getSiege(){
        return this.siege;
    }    

    public int getUnentschieden(){
        return this.unentschieden;
    }

    public int getNiederlagen(){
        return this.niederlagen;
    }  

    public String getSchule(){
        return this.schule;
    }  

    public String getGeschlecht(){
        return this.geschlecht;
    }  

    public String getName(){
        return this.name;
    }      

    public int getKlasse(){
        return this.klasse;
    }

}
