import java.io.*;
import java.util.*;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class GereScores implements java.io.Serializable{

	/*---------Attributs---------*/

	private File repTravail;
	private File fichierScore;
	private TreeSet<Score> Tab; //Collection de score
	private Jeu monJeu;

	/*---------Constructeur(s)---------*/

	public GereScores(Jeu jeu){
		repTravail = new File(System.getProperty("user.dir"));
		fichierScore = new File(repTravail, "fichierScore.bin");
		Tab = new TreeSet<Score>();
		monJeu=jeu;
		//Création du fichier score si il n'existe pas avec des scores à 0
		if(!fichierScore.exists()){
			for(int i=0;i<10;i++){
				Tab.add(new Score(0, "inconnu"));
			}
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichierScore))){
				oos.writeObject(Tab);
			}catch(IOException e1){
				System.out.println(e1.getMessage());
			}
		}
		chargeScores();
	}

	/*---------Méthodes---------*/
	
	//Méthode : on charge les score dans un treeset
	private void chargeScores(){
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichierScore))){
			Tab = (TreeSet<Score>) ois.readObject();
			//gestion du log
			monJeu.entreeLog("Désérialisation du fichier fichierScore.bin");
		}catch(IOException e1){
			System.out.println(e1.getMessage());  
		}catch(ClassNotFoundException e2){
			System.out.println(e2.getMessage());
		}finally{
			monJeu.entreeLog("Echec : Désérialisation du fichierScore.bin");
		}
	}

	//Méthode : on enregistre les nouveaux scores dans le fichierScore.bin
	private void enregistreScores(){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichierScore))){
			oos.writeObject(Tab);
			//gestion du log
			monJeu.entreeLog("Sérialisation du fichier fichierScore.bin");
		}catch(IOException e1){
			System.out.println(e1.getMessage());
		}finally{
			monJeu.entreeLog("Echec : Sérialisation du fichierScore.bin");
		}
	}
	
	//Méthode : affichage des scores dans la console
	public void afficheScores(){
		Iterator<Score> iter = Tab.iterator();
		Score tampon = null;
		for(int i=0; i<Tab.size();i++){
			if(iter.hasNext()){
				tampon = iter.next();
			}
			System.out.println((i+1)+". "+tampon.getPseudo()+" <> "+tampon.getValeur());
		}
	}
	
	//Méthode : ajout d'un score
	public void nouveauScore(long score, String pseudo){
		if(score>Tab.last().valeur){
			Tab.pollLast();
			Tab.add(new Score(score,pseudo));
		}
		enregistreScores();
	}
	
	//renvoie les scores enregistrés
	public String getChaineScores(){
		String chaineTamponScores=" ";
		String newLine=System.getProperty("line.separator"); 
		Iterator<Score> iter = Tab.iterator();
		Score tampon = null;
		for(int i=0; i<Tab.size();i++){
			if(iter.hasNext()){
				tampon = iter.next();
				if(i==0){
					chaineTamponScores = ((i+1)+". "+tampon.getPseudo()+" <> "+tampon.getValeur());
				}else {
					chaineTamponScores = chaineTamponScores + newLine;
					chaineTamponScores = chaineTamponScores + (i+1)+". "+tampon.getPseudo()+" <> "+tampon.getValeur();
				}
			}


		}

		return chaineTamponScores;
	}

}
