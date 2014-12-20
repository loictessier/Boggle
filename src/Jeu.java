import java.util.*;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

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

	/*---------Méthodes---------*/

	public void lancerChrono(){
		//Gere Log
		entreeLog("Lancement du chrono (timer.schedule).");
		
		Timer timer = new Timer();
		timer.schedule (chrono,0,1000);
	}
	
	//On ajoute à la liste le mot trouvé
	public void ActualiserMotsTrouvés(){
		String[] tableauTampon = new String[motsTrouves.size()];
		Iterator<String> iter = motsTrouves.iterator();
		int i=0;
		while(iter.hasNext()){
			tableauTampon[i] = iter.next();
			i++;
		}
		maFenetre.impListeMotsTrouvés(tableauTampon);
	}
	
	public void ActualiserMotEnCours(){
		motEnCours=grille.motActuel;
		maFenetre.impMotEnCours(motEnCours);
	}

	public void ActualiserScoreTotal(){
		Integer scoreTampon = (Integer) scoreTotal;
		maFenetre.impScoreTotal(scoreTampon.toString());
	}
	
	// on mélange ,on remet à zero les mots trouvé , le score actuel ...
	public void melanger(){
		motsTrouves.clear();
		scoreTotal=0;
		grille.NouvellePartie();
		chrono.RAZChrono();
		ActualiserScoreTotal();
		ActualiserMotsTrouvés();
		ActualiserMotEnCours();
		//Gere Log
		entreeLog("Lancement d'une nouvelle partie.");
	}

	public void Valider(){
		//on vérifie l'existence du mot
		if(monGereDico.motExiste(grille.motActuel) && grille.motActuel.length()>2 && !motsTrouves.contains(grille.motActuel)){ 
			motsTrouves.add(grille.motActuel);//ajoue du mot trouvé
			scoreTotal=scoreTotal+monGereDico.calculeValeur(grille.motActuel); // calcul du score à ajouter
			ActualiserScoreTotal();
			ActualiserMotsTrouvés();
			//Gere Log
			entreeLog("Mot validé.");
		}
		if(chrono.temps!=0){
			grille.RAZ();
			ActualiserMotEnCours();
		}
	}
	
	//On actualise à la fin de la partie.
	public void finPartie(){
		String pseudoJoueur;
		Valider();
		pseudoJoueur = maFenetre.demandePseudo();
		monGereScores.nouveauScore((long) scoreTotal, pseudoJoueur);
		maFenetre.afficheScores(monGereScores.getChaineScores());
		//Gere Log
		entreeLog("Partie terminée.");
	}

	public void entreeLog(String nouvelleLigne){
		monGereLog.ajoute(nouvelleLigne);
	}
}
