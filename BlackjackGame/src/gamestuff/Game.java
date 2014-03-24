package gamestuff;

import gui.Table;

import java.util.Vector;

import javax.swing.JOptionPane;

import participantstuff.AI;
import participantstuff.Dealer;
import participantstuff.Participant;
import participantstuff.Player;

public class Game implements Constants {

	private Table table;
	private Dealer dealer;
	private Player player;
	private AI ai1;
	private Participant ai2;
	private Vector<Participant> participants = new Vector<Participant>();
	
	public Game(){
		initialize();
	}
	
	public void run(){
		do{
			playGame();
		}while (keepPlaying());
		System.exit(0);
	}
	
	private void playGame(){
		newGame();
		refresh();
		dealer.initialDeal(participants);
		refresh();
		for(Participant p : participants){
//			if(!player.isBusted()){
				if(p == dealer){
					dealer.revealHiddenCard();
					refresh();
				}
				while((!p.busting()) && (!p.standing())){
					dealer.deal(p);
					refresh();
				}
//			}
		}
//		checkWinner();
	}
	
	private void totalOpponents(){
		Object[] opponentOptions = { "0", "1", "2" };
		int choice = JOptionPane.showOptionDialog(null, "How many opponents do you want?", "", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, opponentOptions, opponentOptions[0]);
		
		switch (choice){
		case 0:
			break;
		case 1:
			ai1 = new AI("Computer Player");
			participants.add(ai1);
			break;
		case 2:
			ai1 = new AI("Computer Player 1");
			participants.add(ai1);
			ai2 = new AI("Computer Player 2");
			participants.add(ai2);
			break;
		}
	}
	
	private boolean keepPlaying(){
		boolean isPlaying = true;
		boolean checkInput = false;
		do{
			if(JOptionPane.showOptionDialog(null, "Do you want to play again " + player.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
				checkInput = true;
				if(player.getDeposition() == 0){
					JOptionPane.showMessageDialog(null, "You have no money in deposition" + "\nYou have to make a new deposit");
					player.makeDeposition();
				}
			}
			else {
				isPlaying = false;
				checkInput = true;
			}
		} while (!checkInput);
		return isPlaying ;
	}
	
	private void initialize(){
		player = new Player();
		participants.add(player);
		totalOpponents();
		dealer = new Dealer();
		participants.add(dealer);
		
		table = new Table(participants);
		
		for(int i = 0; i < participants.size(); i++){
			participants.get(i).makeDeposition();
		}
	}
	
//	private void checkWinner(){
//		if(player.isBusted()){
//			if(dealer.isBusted()){
//				winner(null);	
//			}
//			else if(!dealer.isBusted()){
//				winner(dealer);
//			}
//		}
//		else if(!player.isBusted()){
//			if(dealer.isBusted()){
//				winner(player);	
//			}
//			else if(!dealer.isBusted()){
//				if(dealer.getScore() > player.getScore()){
//					winner(dealer);
//				}
//				else if(dealer.getScore() == player.getScore()){
//					winner(dealer);
//				}
//				else {
//					winner(player);
//				}
//			}
//		}
//	}
//	
//	private void winner(Participant p){
//		if(p != null){
//			JOptionPane.showMessageDialog(null, p.getName() + " is the winner!");
//		}
//		else{
//			JOptionPane.showMessageDialog(null, "No one won!");
//		}
//	}

	private void refresh(){
		table.refresh();
	}
	
	private void newGame(){
		for(Participant p : participants){
			p.newGame();
		}
		table.newGame();
		for(Participant p : participants){
			p.makeBet();
		}
	}
}
