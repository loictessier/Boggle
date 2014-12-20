import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;

/* BRIOT VINCENT et TESSIER LO�C Groupe 1 */

public class Bouton extends JButton{

	/*---------Attributs---------*/

	public int XPos,YPos; //Position du bouton sur la grille
	protected boolean Jouer; //bouton d�ja jouer ou non
	protected String lettre; // la lettre associ� au bouton

	/*---------Constructeur(s)---------*/

	public Bouton(int Xpos,int Ypos){
		super();
		XPos=Xpos;
		YPos=Ypos;
		Desactiver();
		setLettre();

	}

	/*---------M�thodes---------*/

	//M�thode : tirer une lettre al�atoirement
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

	//M�thode : le bouton est jou�
	public void Active(){
		Jouer=true;
		CouleurClicker();
	}

	//M�thode : le bouton n'est plus jou�
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

	//M�thode : remise a z�ro du bouton
	public void RAZ(){
		Desactiver();
	}

	//M�thode : lancer une nouvelle partie 
	public void NouvellePartie(){
		Desactiver();
		CouleurClicker();
		setLettre();
	}
}
