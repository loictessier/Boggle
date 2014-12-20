
/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class Score implements java.io.Serializable,  Comparable<Score>{

	/*---------Attributs---------*/

	public long valeur;
	private String pseudo;

	/*---------Constructeur(s)---------*/

	public Score(long val, String pseu){
		valeur=val;
		pseudo=pseu;
	}

	/*---------Méthodes---------*/

	public void setValeur(long valeur){
		this.valeur=valeur;
	}
	public void setPseudo(String pseudo){
		this.pseudo=pseudo;
	}

	public long getValeur(){
		return this.valeur;
	}

	public String getPseudo(){
		return this.pseudo;
	}

	//Méthode : compare deux scores
	public int compareTo(Score arg0) {
		if(arg0.valeur == this.valeur){
			return 1;
		}else if(arg0.valeur >= this.valeur){
			return 1;
		}else{
			return -1;
		}
	}
}
