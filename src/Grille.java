import java.awt.event.*;

/* BRIOT VINCENT et TESSIER LO�C Groupe 1 */

public class Grille implements ActionListener {

	/*---------Attributs---------*/

	Bouton grille[][];
	protected int Xprec,Yprec;//Position pr�c�dente jou�
	protected boolean PremierClick;//Un click a eu lieu ou non
	protected String motActuel;//le mot qui est en train d'�tre �crit
	private int positionBtActuel;
	Jeu monJeu;
	Fenetre maFenetre;

	/*---------Constructeur(s)---------*/

	public Grille(Fenetre fen, Jeu jeu){
		monJeu = jeu;
		maFenetre=fen;
		PremierClick=false;
		Bouton grille[][] = new Bouton[4][4];
		motActuel="";
		for(int i=0 ;i<4;i++){
			for(int j=0; j<4 ;j++){
				grille[i][j]=new Bouton(i, j);//cr�ation du bouton
				fen.impBoutonCentre(grille[i][j]);//ajout du bouton � la grille
				ecoute(grille[i][j]);//ajout de l'action listener au bouton
			}
		}
		//Gere Log
		monJeu.entreeLog("Cr�ation de la grille.");
	}

	/*---------M�thodes---------*/
	
	//M�thode : on ajoute des listerner au bouton
	public void ecoute(Bouton bt){
		bt.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Bouton bt = (Bouton)e.getSource();
		Bouton btTampon;
		if(monJeu.chrono.temps !=0){
			if (!PremierClick){//si rien de cliqu� pr�c�demment on stocke sa position
				Xprec=bt.XPos;
				Yprec=bt.YPos;
				colorerAdjacents();
				btTampon = (Bouton)maFenetre.CENTRE.getComponent(4*bt.XPos+ bt.YPos);
				bt.Active();
				btTampon.couleurActuel();
				motActuel+=bt.getLettre();//On r�cup�re la lettre
				PremierClick=true;
				monJeu.ActualiserMotEnCours();
			}
			else{
				if(adjacent(bt.XPos, bt.YPos))//Si le bouton s�lectionn� est adjacent au pr�cedent
				{
					if(!bt.GetJouer()){
						btTampon = (Bouton)maFenetre.CENTRE.getComponent(4*Xprec+ Yprec);
						btTampon.Active();
						Xprec=bt.XPos;
						Yprec=bt.YPos;
						colorerAdjacents();
						btTampon = (Bouton)maFenetre.CENTRE.getComponent(4*bt.XPos+ bt.YPos);
						btTampon.couleurActuel();
						motActuel+=bt.getLettre();
						monJeu.ActualiserMotEnCours();
					}
				}
			}
		}
	}

	//remet les boutons de la grille � z�ro sans changer les lettres
	public void RAZ(){
		Bouton boutonTampon;
		PremierClick=false;
		motActuel="";
		for(int i=0; i<maFenetre.CENTRE.getComponentCount();i++){
			boutonTampon = (Bouton)maFenetre.CENTRE.getComponent(i);
			boutonTampon.RAZ();
		}
	}

	//remet les boutons � z�ros et change les lettres
	public void NouvellePartie(){
		Bouton boutonTampon;
		PremierClick=false;
		motActuel="";
		for(int i=0; i<maFenetre.CENTRE.getComponentCount();i++){
			boutonTampon = (Bouton)maFenetre.CENTRE.getComponent(i);
			boutonTampon.NouvellePartie();
		}
	}

	//Teste si un bouton est adjacent au dernier bouton s�lectionn�
	public boolean adjacent(int X,int Y){
		if(Xprec+1==X||Xprec-1==X||Xprec==X){
			if(Yprec+1==Y||Yprec-1==Y||Yprec==Y){
				return true;
			}
		}
		return false;
	}
	
	public void colorerAdjacents(){
		Bouton boutonTampon;
		for(int i=0; i<maFenetre.CENTRE.getComponentCount();i++){
			boutonTampon = (Bouton)maFenetre.CENTRE.getComponent(i);
			if(adjacent(boutonTampon.XPos, boutonTampon.YPos)){
				boutonTampon.couleurAdjacent();
			}else{
				boutonTampon.CouleurClicker();
			}
		}
	}
}
