package kickercup.xml;


import java.util.Date;
/**
 * Write a description of class Gruppierung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gruppierung
{
    private String datum;
    private String zeit;
    private int feld;
    private String mannschaftID1; //
    private int tore1;
    private int punkte1;
    private String mannschaftID2; //
    private int tore2;
    private int punkte2;

    /**
     * Constructor for objects of class Gruppierung
     */
    public Gruppierung(String datum, String zeit, int feld, String mannschaftID1, String mannschaftID2 )
    {
        this.datum = datum;
        this.zeit = zeit;
        this.feld = feld;
        this.mannschaftID1 = mannschaftID1;
        this.mannschaftID2 = mannschaftID2;
        this.tore1 = -1;
        this.tore2 = -1;
        this.punkte1 = 0;
        this.punkte2 = 0;
    }

    public Gruppierung(String datum, String zeit, int feld, String mannschaftID1, String mannschaftID2, int tore1, int tore2,int punkte1, int punkte2 )
    {
        this.datum = datum;
        this.zeit = zeit;
        this.feld = feld;
        this.mannschaftID1 = mannschaftID1;
        this.mannschaftID2 = mannschaftID2;
        this.tore1 = tore1;
        this.tore2 = tore2;
        this.punkte1 = punkte1;
        this.punkte2 = punkte2;
    }
    
    
    public String getDatum()
    {
        return datum;
    }

    public void setDatum(String datum){
        this.datum = datum;
    }

    public String getZeit()
    {
        return zeit;
    }

    public void setZeit(String zeit){
        this.zeit = zeit;
    }
    
    
    public int getFeld()
    {
        return feld;
    }

    public void setFeld(int feld){
        this.feld = feld;
    }

    public String getMannschaftID1()
    {
        return mannschaftID1;
    }

    public void setMannschaft1(String mannschaftID1){
        this.mannschaftID1 = mannschaftID1;
    }
    
    public String getMannschaftID2()
    {
        return mannschaftID2;
    }

    public void setMannschaft2(String mannschaftID2){
        this.mannschaftID2 = mannschaftID2;
    }
    
    public int getTore1()
    {
        return tore1;
    }

    public void setTore1(int tore1){
        this.tore1 = tore1;
    }

    public int getTore2()
    {
        return tore2;
    }

    public void setTore2(int tore2){
        this.tore2 = tore2;
    }

    public int getPunkte1()
    {
        return punkte1;
    }

    public void setPunkte1(int punkte1){
        this.punkte1 = punkte1;
    }

    public int getPunkte2()
    {
        return punkte2;
    }

    public void setPunkte2(int punkte2){
        this.punkte2 = punkte2;
    }
    
  
    
}
