/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class MancareFactory {

    public static void valideazaTip(String tip) throws TipMancareInvalidaException {
        if (!tip.equalsIgnoreCase("masa") && !tip.equalsIgnoreCase("bautura")) {
            throw new TipMancareInvalidaException("Tipul '" + tip + "' nu este recunoscut de fabrica (foloseste masa/bautura).");
        }
    }

    public static Mancare createMancare(String tip, String nume, int calorii, int proteine) {
        if (tip.equalsIgnoreCase("masa")) {
            return new Masa(nume, calorii, proteine);
        }
        if (tip.equalsIgnoreCase("bautura")) {
            return new Masa(nume, calorii, proteine);
        }
        return null;
    }
}
