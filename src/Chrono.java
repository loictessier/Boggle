import java.text.SimpleDateFormat;
import java.util.*;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class Chrono extends TimerTask{

	/*---------Attributs---------*/

	protected int temps;
	Fenetre fen;
	boolean finGeree;

	/*---------Constructeur(s)---------*/

	public Chrono(Fenetre maFenetre){
		fen=maFenetre;
		temps=120;
		finGeree = false;
	}

	/*---------Méthodes---------*/

	//méthode run : chaque seconde on décrémente et on met a jour le label
	public void run() {
		if(temps!=0){
			temps--;
			fen.impChrono(temps);
		}if(temps==0 && !finGeree){
			fen.jeu.finPartie();
			finGeree=true;
		}
	}

	//réinitialise le chrono
	public void RAZChrono(){
		temps=120;
		finGeree = false;
	}

}
