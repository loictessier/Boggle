
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class GereLog {
	
	/*---------Attributs---------*/
	
	private File repTravail; 
	private File fichierLog;
	
	/*---------Constructeur(s)---------*/
	
	public GereLog(){
		repTravail = new File(System.getProperty("user.dir"));
		fichierLog = new File(repTravail, calculeNomFichier());
		initialiseFichier();
		
	}
	
	/*---------Méthodes---------*/
	
	//Méthode : on retourne le nom de notre fichier gerelog
	private String calculeNomFichier(){
		String date;
		Date aujourdhui = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat formatHeure = new SimpleDateFormat("HHmmss");
		date = new String("Log_" + formatDate.format(aujourdhui) + "_" + formatHeure.format(aujourdhui) +".log");
		return date;	
	}
	
	//Méthode ; on remplie le fichier avec la première ligne
	private void initialiseFichier(){
		Date aujourdhui = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm:ss");
		
		try(PrintWriter pw = new PrintWriter(fichierLog)){// on ecrit dans le fichier log
			pw.write(fichierLog.getName() + "\t" + formatDate.format(aujourdhui) + "\t" +  formatHeure.format(aujourdhui) + "\r\n");
		}catch(Exception e2){
			System.out.println(e2.getMessage());
		}
	}
	
	//Méthode on ajoute une ligne au fichier log
	public void ajoute(String nouvelleLigne){
		Date aujourdhui = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm:ss");
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(fichierLog, true))){//on écrit dans le fichier log
			pw.write(formatDate.format(aujourdhui) + "\t" + formatHeure.format(aujourdhui) + "\t" + nouvelleLigne +"\r\n");
		}catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	
	
	
	
	
	
	
}
