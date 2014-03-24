package participantstuff;

import java.util.Vector;

import cardstuff.Card;
import cardstuff.Deck;




public class Dealer extends Participant {

	private Deck deck;
	private Card hiddenCard;
	
	public Dealer(){
		super("Dealer");
		initialize();
	}
	
	public void shuffleDeck(){
		deck.shuffle();
	}
	
	public void deal(Participant p) {
		Card c = deck.draw();
		p.dealToPlayer(c);
	}

	public void initialDeal(Vector<Participant> vector){
		for(int i = 0; i < 2; i++){
			for (Participant p : vector){
				if(p == this && i == 0){
					dealHiddenCard(p);
				}
				else {
					deal(p);
				}
			}
		}
	}
	
	public void revealHiddenCard(){
		this.hand.addToHand(hiddenCard);
		System.out.println(this.getName() + "'s hidden card is " + hiddenCard.toString() + "\n Score is now " + this.getScore());
	}

	public void payTo(Participant p){
		int payout = (int) (p.getBet() * 1.5);
		p.setDeposition(getDeposition() + payout);
	}
	
	@Override
	protected void hitOrStand(){
		printHand();
		if(this.getScore() >= DEALERSTAND){
			stand(true);
		} 
		else {
			standing = false;
		}
	}
	
	private void dealHiddenCard(Participant p) {
		hiddenCard = deck.draw();
		System.out.println(p.getName() + " got a hidden card\n");
	}

	private void initialize(){
		deck = new Deck();
		deck.shuffle();
	}

	@Override
	public void makeDeposition() {
		setDeposition(MAX_DEPOSIT * 10);
	}

	@Override
	public void makeBet() {
		System.out.println("The dealer does not bet...");
	}
	
	@Override
	public PlayerType getType() {
		return PlayerType.DEALER;
	}
}
