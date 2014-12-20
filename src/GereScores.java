import java.io.*;
import java.util.*;

/* BRIOT VINCENT et TESSIER LO�C Groupe 1 */

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
		//Cr�ation du fichier score si il n'existe pas avec des scores � 0
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

	/*---------M�thodes---------*/
	
	//M�thode : on charge les score dans un treeset
	private void chargeScores(){
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichierScore))){
			Tab = (TreeSet<Score>) ois.readObject();
			//gestion du log
			monJeu.entreeLog("D�s�rialisation du fichier fichierScore.bin");
		}catch(IOException e1){
			System.out.println(e1.getMessage());  
		}catch(ClassNotFoundException e2){
			System.out.println(e2.getMessage());
		}finally{
			monJeu.entreeLog("Echec : D�s�rialisation du fichierScore.bin");
		}
	}

	//M�thode : on enregistre les nouveaux scores dans le fichierScore.bin
	private void enregistreScores(){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichierScore))){
			oos.writeObject(Tab);
			//gestion du log
			monJeu.entreeLog("S�rialisation du fichier fichierScore.bin");
		}catch(IOException e1){
			System.out.println(e1.getMessage());
		}finally{
			monJeu.entreeLog("Echec : S�rialisation du fichierScore.bin");
		}
	}
	
	//M�thode : affichage des scores dans la console
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
	
	//M�thode : ajout d'un score
	public void nouveauScore(long score, String pseudo){
		if(score>Tab.last().valeur){
			Tab.pollLast();
			Tab.add(new Score(score,pseudo));
		}
		enregistreScores();
	}
	
	//renvoie les scores enregistr�s
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
