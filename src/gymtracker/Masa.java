/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class Masa implements Mancare{
    private String numeMasa;
    private int calorii;
    private int proteine;

    public Masa() {
        this.numeMasa = "masa";
        this.calorii = 0;
        this.proteine = 0;
    }
   
    public Masa(String numeMasa, int calorii, int proteine) {
        this.numeMasa = numeMasa;
        this.calorii = calorii;
        this.proteine = proteine;
    }

    @Override
    public void adaugaCalorii(int calorii) {
        this.calorii += calorii;
    }

    @Override
    public int getCalorii() {
        return this.calorii;
    }

    @Override
    public String getNumeMasa() {
        return this.numeMasa;
    }

    public int getProteine() {
        return proteine;
    }

    public void setNumeMasa(String numeMasa) {
        this.numeMasa = numeMasa;
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }
    
    


    
    
    
}
