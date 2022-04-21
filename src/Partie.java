import java.util.Random;
import java.util.Scanner;

/*
 * Partie.java                                  14 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */

/** TODO commenter la responsabilité de cette classe
 * @author aymer
 *
 */
public class Partie {

    /**
     * TODO commenter l'état initial atteint
     */
    public Partie() {
        // TODO Auto-generated constructor stub
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
        Grid grid = new Grid();
        Scanner entree = new Scanner(System.in); 
        
        System.out.print("Entrez votre pseudo : ");
        Joueur j1 = new Joueur(entree.next(),"T");
        System.out.print("Entrez votre pseudo : ");
        Joueur j2 = new Joueur(entree.next(),"T");
        
        Joueur joueurActuel = j1;
        Joueur joueurAttente = j2;
        Joueur joueurTemporaire;
        
        Random aleatoire = new Random();
        boolean couleur = aleatoire.nextBoolean();
        if (couleur) {
            j1.setValue("R");
            j2.setValue("J");
            joueurActuel = j1;
            joueurAttente = j2;
        } else {
            j1.setValue("J");
            j2.setValue("R");
            joueurActuel = j2;
            joueurAttente = j1;
        }        
        
        int colonneChoisie;
        boolean ok = false;
        while (!ok) {
            grid.afficherGrid();
            System.out.print(joueurActuel.getPseudo() + " colonne : ");
            colonneChoisie = entree.nextInt();
            grid.setGrid(joueurActuel, colonneChoisie);
            
            ok = grid.isAligner(joueurActuel);
            
            joueurTemporaire = joueurAttente;
            joueurAttente = joueurActuel;
            joueurActuel = joueurTemporaire;
            System.out.println(ok);
        }
        
        String vainqueur;
        if (grid.isAligner(joueurActuel)) {
            vainqueur = "Le vainqueur est " + joueurActuel.getPseudo();
        } else {
            vainqueur = "Egalité";
        }
        
    }
}
