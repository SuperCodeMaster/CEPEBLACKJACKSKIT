package participantstuff;

import gamestuff.Constants;
import cardstuff.Card;


public abstract class Participant implements Constants{

	private String name;
	protected boolean standing = false;
	protected boolean isBusted = false;
	protected Hand hand;
	protected int bet, deposition;
	
	public Participant(String name){
		this.name = name;
		initialize();
	}
	
	private void initialize(){
		hand = new Hand();
		standing = false;
		isBusted = false;
		setBet(0);
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return this.hand.getScore();
	}
	
	public int getBet(){
		return bet;
	}
	
	public void setBet(int bet){
		setDeposition(getDeposition() - bet);
		this.bet = bet;
	}
	
	public int getDeposition(){
		return deposition;
	}
	
	public void setDeposition(int cash){
		this.deposition = cash;
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public void printHand(){
		System.out.println("**** " + this.getName() + "'s hand ****");
		this.hand.printHand();
	}

	public boolean standing(){
		hitOrStand();
		return standing;
	}
	
	public void stand(boolean bool){
		standing = bool;
	}
	
	public boolean isBusted(){
		return isBusted;
	}

	public boolean busting() {
		if(getScore() > BLACKJACK){
			isBusted = true;
		}
		return isBusted;
	}
	
	public abstract PlayerType getType();
	
	public abstract void makeDeposition();
	
	public abstract void makeBet();
	
	protected abstract void hitOrStand();

	public void dealToPlayer(Card card){
		hand.addToHand(card);
		System.out.println(this.getName() + " got " + card.toString() + "\n");
		printHand();
	}
	
	public void newGame(){
		hand.throwCards();
		hand.setScore(0);
		isBusted = false;
		stand(false);
		setBet(0);
	}
}
