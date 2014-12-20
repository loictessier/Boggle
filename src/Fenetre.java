import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.ListSelectionModel;

/* BRIOT VINCENT et TESSIER LOÏC Groupe 1 */

public class Fenetre implements ActionListener {
	/*---------Attributs---------*/
	
	private JFrame frmBoggle;
	Jeu jeu;
	protected JPanel CENTRE;
	private JLabel lblChrono;
	private JLabel lblMotEnCours;
	private JButton btnMlanger;
	private JButton btnValider;
	private JLabel lblScoreTotal;
	private JList listmotsTrouvés;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre window = new Fenetre();
					window.frmBoggle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/*---------Constructeur(s)---------*/
	
	public Fenetre() {
		initialize();
		jeu = new Jeu(this);
	}

	/*---------Méthodes---------*/
	
	//Fenetre des scores
	public void afficheScores(String chaineScore){
		JOptionPane.showMessageDialog(null,  chaineScore, "Liste des 10 meilleurs scores", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//fenetre : demande de pseudo
	public String demandePseudo(){
		String pseudo = JOptionPane.showInputDialog(null, "Entrez votre nom de joueur : ", "Demande de pseudonyme", JOptionPane.QUESTION_MESSAGE);
		return pseudo;
	}
	
	public void impListeMotsTrouvés(String[] motsTrouvés){
		 listmotsTrouvés.setListData(motsTrouvés);
	}
	
	public void impMotEnCours(String motActuel){
		lblMotEnCours.setText(motActuel);
	}
	
	public void impScoreTotal(String Score){
		lblScoreTotal.setText(Score);
	}
	
	public void impChrono(int temps){
		String tamponTemps;
		//Format du chrono
		SimpleDateFormat formatHeure = new SimpleDateFormat("mm:ss");
		tamponTemps=formatHeure.format(temps*1000);
		lblChrono.setText(tamponTemps);
	}
	
	public void impBoutonCentre(JButton bt) {
		CENTRE.add(bt);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBoggle = new JFrame();
		frmBoggle.setTitle("Boggle (BRIOT/TESSIER)");
		frmBoggle.setBounds(100, 100, 450, 300);
		frmBoggle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBoggle.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel NORD = new JPanel();
		frmBoggle.getContentPane().add(NORD, BorderLayout.NORTH);
		NORD.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnMlanger = new JButton("M\u00E9langer");
		NORD.add(btnMlanger);
		btnMlanger.addActionListener(this);
		
		lblChrono = new JLabel("Chrono");
		NORD.add(lblChrono);
		
		JPanel SUD = new JPanel();
		frmBoggle.getContentPane().add(SUD, BorderLayout.SOUTH);
		SUD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnValider = new JButton("Valider");
		SUD.add(btnValider);
		btnValider.addActionListener(this);
		
		lblMotEnCours = new JLabel("mot en cours");
		SUD.add(lblMotEnCours);
		
		JPanel EST = new JPanel();
		frmBoggle.getContentPane().add(EST, BorderLayout.EAST);
		EST.setLayout(new BoxLayout(EST, BoxLayout.Y_AXIS));
		
		JLabel lblmotsTrouvés = new JLabel("Mots Trouv\u00E9s");
		EST.add(lblmotsTrouvés);
		
		JScrollPane scrollPmotsTrouves = new JScrollPane();
		scrollPmotsTrouves.setPreferredSize(new Dimension(140, 80));
		scrollPmotsTrouves.setToolTipText("");
		scrollPmotsTrouves.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		EST.add(scrollPmotsTrouves);
		
		listmotsTrouvés = new JList();
		listmotsTrouvés.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPmotsTrouves.setViewportView(listmotsTrouvés);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		EST.add(verticalStrut);
		
		lblScoreTotal = new JLabel("Score Total");
		EST.add(lblScoreTotal);
		
		JPanel OUEST = new JPanel();
		frmBoggle.getContentPane().add(OUEST, BorderLayout.WEST);
		
		CENTRE = new JPanel();
		frmBoggle.getContentPane().add(CENTRE, BorderLayout.CENTER);
		CENTRE.setLayout(new GridLayout(4, 4, 2, 2));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnMlanger){
			jeu.melanger();
			
		}
		if(e.getSource() == btnValider){
			jeu.Valider();
		}
		
	}
}
