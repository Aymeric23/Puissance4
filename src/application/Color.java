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

    private String colorName;
    private String colorHexa;
    
    private final String[] VALID_COLOR_NAME = {"R", "J"};
    private final String[] VALID_COLOR_HEXA = {"#e45555", "#fbfd87"};

    /**
     * TODO commenter l'état initial atteint
     * @param colorName
     * @param colorHexa 
     * @throws IllegalArgumentException si colorName invalide
     */
    public Color(String colorName, String colorHexa) {
        if (!isValidColor()) {
            throw new IllegalArgumentException("Couleur invalide");
        }
        if (!isValidColorHexa()) {
            throw new IllegalArgumentException("CouleurHexa invalide");
        }
        
        this.colorName = colorName;
        this.colorHexa = colorHexa;
    }

    private boolean isValidColor() {
        boolean colorOk = false;
        for (String element : VALID_COLOR_NAME) {
            if (colorName == element) {
                colorOk = true;
            }
        }
        return colorOk;
    }
    
    private boolean isValidColorHexa() {
        boolean colorHexaOk = false;
        for (String element : VALID_COLOR_HEXA) {
            if (colorHexa == element) {
                colorHexaOk = true;
            }
        }
        return colorHexaOk;
    }
    
    
    /**
     * @return the colorName
     */
    public String getColorName() {
        return colorName;
    }
    
    /**
     * @return the colorHexa
     */
    public String getColorHexa() {
        return colorHexa;
    }
    
    
    
}
