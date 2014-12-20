
import java.io.*;
import java.util.*;
import java.lang.System;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class GereDico {

	/*---------Attributs---------*/

	private File repTravail;
	private File fichierDico;
	private File dicoBoggle;
	private HashSet<String> collecBoggle; //collection contenant les mots du dictionnaire
	private Jeu monJeu;

	/*---------Constructeur(s)---------*/

	public GereDico(Jeu jeu){
		monJeu=jeu;
		repTravail = new File(System.getProperty("user.dir"));
		fichierDico = new File(repTravail, "DicoSansAccent.txt");
		dicoBoggle = new File(repTravail, "dicoBoggle.txt");
		collecBoggle = new HashSet<String>();
		creeDicoBoggle();
		chargeFichier();	
	}

	/*---------Methodes---------*/

	//Méthode : on lit le fichier dicoboogle et on remplis la collection CollecBoogle
	public void chargeFichier(){
		long temps = System.currentTimeMillis();
		try(Scanner sc = new Scanner(dicoBoggle)){
			while(sc.hasNext()){
				collecBoggle.add(sc.next());
			}
		}catch(Exception e){
			System.out.println("Echec du chargement du fichier dicobBogle.txt !");
		}finally{
			monJeu.entreeLog("Echec : Désérialisation du Dico Boggle.");
		}
		temps = System.currentTimeMillis()-temps;
		//entreeGerelog
		monJeu.entreeLog("Désérialisation du Dico Boggle. Durée : " + temps);
	}//Fin méthode
	
	//Méthode : vérifie l'existence ou non d'un mot
	public boolean motExiste(String motCompare){
		if(collecBoggle.contains(motCompare.toUpperCase())){
			return true;
		}else{
			return false;
		}
	}
	
	//Méthode : on retourne le nombre de points que vaut le mots en paramètre
	public int calculeValeur(String motCalcule){
		String motTampon=motCalcule.toUpperCase();
		int Score=0;
		String cat1="AEILNORSTU", cat2="DGM", cat3="BCP", cat4="FHV", cat5="JQ", cat6="KWXYZ";
		for(int i=0;i<motTampon.length();i++){
			char lettre = motTampon.charAt(i);
			if(cat1.indexOf(lettre)>=0){
				Score++;
			}else if(cat2.indexOf(lettre)>=0){
				Score=Score+2;
			}else if(cat3.indexOf(lettre)>=0){
				Score=Score+3;
			}else if(cat4.indexOf(lettre)>=0){
				Score=Score+4;
			}else if(cat5.indexOf(lettre)>=0){
				Score=Score+8;
			}else if(cat6.indexOf(lettre)>=0){
				Score=Score+10;
			}
		}
		if(motTampon.length()>4||motTampon.length()<7){
			Score=Score+3;
		}else if(motTampon.length()>=7){
			Score=Score+5;
		}
		return Score;
	}
	
	//Méthode ; on retourne un tableau de String contenant chacun une lettre tiré aléatoirement
	public String[] nouvellesLettres(){
		String[] Tab = new String[16];
		String scrabble = "AAAAAAAAABBCCDDDEEEEEEEEEEEEEEEFFGGHHIIIIIIIIJKLLLLLMMMNNNNNNOOOOOOPPQRRRRRRSSSSSSTTTTTTUUUUUUVVWXYZ";
		Random valeur= new Random();
		for(int i=0;i<Tab.length;i++){
			Character lettreTampon=scrabble.charAt(valeur.nextInt(scrabble.length()-1));
			Tab[i]=lettreTampon.toString();
		}
		return Tab;
	}
	
	//Méthode : on crée le fichier DicoBoogle.txt
	public void creeDicoBoggle(){
		long temps = System.currentTimeMillis();
		HashSet<String> dicoTampon = new HashSet<String>(); //on utilise une collection tampon
		String tampon;
		int nbreligneDico1=0, nbreligneDico2=0;
		try(Scanner sc = new Scanner(fichierDico)){//on lit le dictionnaire original
			while(sc.hasNext()){//on remplie la collection avec un dictionnaire modifié
				tampon = sc.next();
				nbreligneDico1++;
				if(tampon.length()>2 && tampon.length()<=16 && !(tampon.contains("-"))){
					dicoTampon.add(tampon.toUpperCase());
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			monJeu.entreeLog("Echec : Lecture du DicoSansAccent.txt");
		}
		
		Iterator<String> iter = dicoTampon.iterator();
		if(!dicoBoggle.exists()){//Si le dictionnaire boogle n'existe pas, on le crée et on replie avec notre collection tampon
			try(PrintWriter pw = new PrintWriter(dicoBoggle)){
				while(iter.hasNext()){
					pw.println(iter.next());
					nbreligneDico2++;
				}
			}catch(IOException e1){
				System.out.println(e1.getMessage());
			}finally{
				monJeu.entreeLog("Echec : Création du Dico Boggle.");
			}

			temps = System.currentTimeMillis()-temps;
			//on appelle le gerelog
			monJeu.entreeLog("Création du fichier du Dico Boggle. Durée : " + temps + "Nombre mots dico 1 : " +  nbreligneDico1 + "Nombre mots dico 2 : " + nbreligneDico2);
		}

	}//fin creeDicoBoggle
}
