import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class Bouton extends JButton{

	/*---------Attributs---------*/

	public int XPos,YPos; //Position du bouton sur la grille
	protected boolean Jouer; //bouton déja jouer ou non
	protected String lettre; // la lettre associé au bouton

	/*---------Constructeur(s)---------*/

	public Bouton(int Xpos,int Ypos){
		super();
		XPos=Xpos;
		YPos=Ypos;
		Desactiver();
		setLettre();

	}

	/*---------Méthodes---------*/

	//Méthode : tirer une lettre aléatoirement
	private String nouvelleLettre(){
		String scrabble = "AAAAAAAAABBCCDDDEEEEEEEEEEEEEEEFFGGHHIIIIIIIIJKLLLLLMMMNNNNNNOOOOOOPPQRRRRRRSSSSSSTTTTTTUUUUUUVVWXYZ";
		Random valeur= new Random();
		Character lettreTampon=scrabble.charAt(valeur.nextInt(scrabble.length()-1));

		return lettreTampon.toString();
	}

	public void setLettre(){
		lettre=nouvelleLettre();
		setText(lettre);
	}

	public String getLettre(){
		return lettre;
	}

	//Méthode : le bouton est joué
	public void Active(){
		Jouer=true;
		CouleurClicker();
	}

	//Méthode : le bouton n'est plus joué
	public void Desactiver(){
		Jouer=false;
		CouleurClicker();
	}

	public boolean GetJouer(){
		return Jouer;
	}

	//affecter une couleur en fonction du statut du bouton
	public void CouleurClicker(){
		if(Jouer){
			setBackground(Color.pink);
		}else{
			setBackground(null);	
		}
	}

	public void couleurAdjacent(){
		if(!Jouer){
			setBackground(Color.green);
		}
	}

	public void couleurActuel(){
		setBackground(Color.cyan);
	}

	//Méthode : remise a zéro du bouton
	public void RAZ(){
		Desactiver();
	}

	//Méthode : lancer une nouvelle partie 
	public void NouvellePartie(){
		Desactiver();
		CouleurClicker();
		setLettre();
	}
}
