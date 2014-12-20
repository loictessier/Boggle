import java.util.*;

/* BRIOT VINCENT et TESSIER LO�C Groupe 1 */

public class Jeu {

	/*---------Attributs---------*/

	protected Grille grille;
	protected GereDico monGereDico;
	protected GereLog monGereLog;
	protected GereScores monGereScores;
	protected Bouton[][] maGrille;
	protected Fenetre maFenetre;
	protected String motEnCours;
	protected int scoreTotal;
	protected LinkedHashSet<String> motsTrouves;
	Chrono chrono;

	/*---------Constructeur(s)---------*/

	public Jeu(Fenetre fen){
		maFenetre=fen;
		monGereLog = new GereLog();
		grille= new Grille(fen, this);
		monGereDico = new GereDico(this);
		monGereScores = new GereScores(this);
		chrono = new Chrono(fen);
		motsTrouves = new LinkedHashSet<String>();
		scoreTotal=0;
		lancerChrono();
	}

	/*---------M�thodes---------*/

	public void lancerChrono(){
		//Gere Log
		entreeLog("Lancement du chrono (timer.schedule).");
		
		Timer timer = new Timer();
		timer.schedule (chrono,0,1000);
	}
	
	//On ajoute � la liste le mot trouv�
	public void ActualiserMotsTrouv�s(){
		String[] tableauTampon = new String[motsTrouves.size()];
		Iterator<String> iter = motsTrouves.iterator();
		int i=0;
		while(iter.hasNext()){
			tableauTampon[i] = iter.next();
			i++;
		}
		maFenetre.impListeMotsTrouv�s(tableauTampon);
	}
	
	public void ActualiserMotEnCours(){
		motEnCours=grille.motActuel;
		maFenetre.impMotEnCours(motEnCours);
	}

	public void ActualiserScoreTotal(){
		Integer scoreTampon = (Integer) scoreTotal;
		maFenetre.impScoreTotal(scoreTampon.toString());
	}
	
	// on m�lange ,on remet � zero les mots trouv� , le score actuel ...
	public void melanger(){
		motsTrouves.clear();
		scoreTotal=0;
		grille.NouvellePartie();
		chrono.RAZChrono();
		ActualiserScoreTotal();
		ActualiserMotsTrouv�s();
		ActualiserMotEnCours();
		//Gere Log
		entreeLog("Lancement d'une nouvelle partie.");
	}

	public void Valider(){
		//on v�rifie l'existence du mot
		if(monGereDico.motExiste(grille.motActuel) && grille.motActuel.length()>2 && !motsTrouves.contains(grille.motActuel)){ 
			motsTrouves.add(grille.motActuel);//ajoue du mot trouv�
			scoreTotal=scoreTotal+monGereDico.calculeValeur(grille.motActuel); // calcul du score � ajouter
			ActualiserScoreTotal();
			ActualiserMotsTrouv�s();
			//Gere Log
			entreeLog("Mot valid�.");
		}
		if(chrono.temps!=0){
			grille.RAZ();
			ActualiserMotEnCours();
		}
	}
	
	//On actualise � la fin de la partie.
	public void finPartie(){
		String pseudoJoueur;
		Valider();
		pseudoJoueur = maFenetre.demandePseudo();
		monGereScores.nouveauScore((long) scoreTotal, pseudoJoueur);
		maFenetre.afficheScores(monGereScores.getChaineScores());
		//Gere Log
		entreeLog("Partie termin�e.");
	}

	public void entreeLog(String nouvelleLigne){
		monGereLog.ajoute(nouvelleLigne);
	}
}
