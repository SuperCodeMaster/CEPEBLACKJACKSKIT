package gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cardstuff.Card;
import participantstuff.Participant;
import participantstuff.PlayerType;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Seat extends JPanel implements GUIListener{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	private ArrayList<Card> cardList = new ArrayList<Card>();
	
	private Participant participant;
	private JPanel statusPanel;
	private JPanel cardPanel;
	
	private JLabel name;
	private JLabel deposition = new JLabel("Deposition: ");
	private JLabel bet = new JLabel("Bet: ");
	private JLabel score = new JLabel("Score: ");
	
	public Seat(Participant participant) {
		this.participant = participant;
		initialize();
	}
	
	public boolean isTakenBy(Participant p){
		if(p.equals(participant)){
			return true;
		}
		else {
			return false;
		}
	}

	private void initialize() {
		setLayout(null);
		setOpaque(false);
		initializeStatusPanel();
		this.add(statusPanel);
		initializeCardPanel();
		this.add(cardPanel);
		setLabelsProperties(Color.YELLOW, new Font("Arial", Font.BOLD, 17));
	}
	
	public void addCard(Card c){
		cardList.add(c);
	}
	
	private void initializeStatusPanel(){
		statusPanel = new JPanel();
		statusPanel.setSize(200, 70);
		statusPanel.setOpaque(false);
		statusPanel.setLayout(new GridLayout(4,1));
		
		name  = new JLabel(participant.getName());
		labelList.add(name);
		statusPanel.add(name);
		
		if(participant.getType().equals(PlayerType.DEALER)){
			statusPanel.setLocation(5, 5);
		}
		else {
			labelList.add(deposition);
			labelList.add(bet);
			statusPanel.add(deposition);
			statusPanel.add(bet);
			statusPanel.setLocation(5, 308);
		}

		labelList.add(score);
		statusPanel.add(score);
	}
	
	private void initializeCardPanel(){
		cardPanel = new JPanel();
		cardPanel.setSize(285, 300);
		cardPanel.setOpaque(false);
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		
		if(participant.getType().equals(PlayerType.DEALER)){
			cardPanel.setLocation(5, 80);
		}
		else {
			cardPanel.setLocation(5, 5);
		}
	}
	
	private void setLabelsProperties(Color color, Font font){
		for(JLabel jl : labelList){
			jl.setForeground(color);
			jl.setFont(font);
		}
	}
	
	private void updateHand(){
		cardPanel.removeAll();
		if(participant.getHand().getNumCards() != 0){
			for(int i = 0; i < participant.getHand().getNumCards(); i++){
				Card c = participant.getHand().getCard(i);
				JLabel temp = new JLabel(c.toString());
				temp.setFont(new Font("Arial", Font.BOLD, 40));
				if(c.getSuits().equals("♠") || c.getSuits().equals("♣")){
					temp.setForeground(Color.BLACK);
				}
				else if(c.getSuits().equals("♥") || c.getSuits().equals("♦")){
					temp.setForeground(Color.RED);
				}
				cardPanel.add(temp);
			}
		}
		if(participant.isBusted()){
			JLabel bustLabel = new JLabel("BUSTED!");
			bustLabel.setFont(new Font("Arial", Font.BOLD, 50));
			bustLabel.setForeground(Color.ORANGE);
			cardPanel.add(bustLabel);
		}
		else if(participant.standing()){
			
		}
	}
	
	private void updateStatus(){
		score.setText("Score: " + participant.getScore());
		deposition.setText("Deposition: " + participant.getDeposition());
		bet.setText("Bet: " + participant.getBet());
	}

	@Override
	public void refresh() {
		updateStatus();
		updateHand();
		updateUI();
	}

	@Override
	public void newGame() {
		cardPanel.removeAll();
		updateStatus();
		updateUI();
	}
}
