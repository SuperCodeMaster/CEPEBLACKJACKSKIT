package cardstuff;

import gamestuff.Constants;

import javax.swing.ImageIcon;


public class Card implements Constants {

	private int suit, rank, score, cardIndex;
//	private String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
	private String[] suits = {"♥", "♠", "♦", "♣"};
	private String[] ranks  = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
	private boolean isAce = false;
	private ImageIcon ii;
	
	public Card(int suit, int rank, int cardIndex){
		this.suit = suit;
		this.rank = rank;
		this.cardIndex = cardIndex;
		initialize();
	}
	
	@Override
	public String toString(){
		return ranks[(rank - 1)] + " of " + suits[suit];
	}
	
	public ImageIcon getCardIcon(){
		return ii;
	}
	
	public String getSuits(){
		return suits[suit];
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public boolean isAce(){
		if(rank == 1)
			isAce = true;
		else
			isAce = false;
		return isAce;
	}
	
	private void initialize(){
		ii = new ImageIcon("Images/cards/" + cardIndex + ".png");
		if(isAce())
			setScore(HIGH_ACE);
		else if(rank < 10) 
			setScore(rank);
		else
			setScore(FACE_CARD);
	}
}
