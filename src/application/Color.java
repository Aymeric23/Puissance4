/*
 * Color.java                                  28 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package application;

/** TODO commenter la responsabilité de cette classe
 * @author Romain
 *
 */
public class Color {

    private int colorId;
    private String colorHexa;
    
    private final int[] VALID_COLOR_ID = {1, 2};
    private final String[] VALID_COLOR_HEXA = {"#e45555", "#fbfd87"};

    /**
     * TODO commenter l'état initial atteint
     * @param colorId
     * @param colorHexa 
     * @throws IllegalArgumentException si colorId invalide
     */
    public Color(int colorId, String colorHexa) {
        if (!isValidColor(colorId)) {
            throw new IllegalArgumentException("Couleur invalide");
        }
        this.colorId = colorId;
        if (!isValidColorHexa(colorHexa)) {
            throw new IllegalArgumentException("CouleurHexa invalide");
        }
        this.colorHexa = colorHexa;
    }

    private boolean isValidColor(int colorId) {
        boolean colorOk = false;
        for (int element : VALID_COLOR_ID) {
            if (colorId == element) {
                colorOk = true;
            }
        }
        return colorOk;
    }
    
    private boolean isValidColorHexa(String colorHexa) {
        boolean colorHexaOk = false;
        for (String element : VALID_COLOR_HEXA) {
            if (colorHexa.equals(element)) {
                colorHexaOk = true;
            }
        }
        return colorHexaOk;
    }
    
    
    /**
     * @return the colorId
     */
    public int getColorId() {
        return colorId;
    }
    
    /**
     * @return the colorHexa
     */
    public String getColorHexa() {
        return colorHexa;
    }
    
    
    
}
