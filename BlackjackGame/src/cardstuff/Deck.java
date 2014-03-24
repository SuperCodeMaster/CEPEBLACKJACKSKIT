package cardstuff;
import java.util.ArrayList;
import java.util.Collections;


public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> usedCards = new ArrayList<Card>();
	
	public Deck(){
		loadDeck();
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
		System.out.println("Dealer is shuffling the deck...");
	}
	
	public Card draw(){
		Card tempCard = null;
		if(deck.isEmpty()){
			restackDeck();
		}
		if(deck.size() > 0){
			tempCard = deck.remove(deck.size() - 1);
			usedCards.add(tempCard);
		}
		return tempCard;
	}
	
	public void listDeck(){
	for(int i = 0; i < deck.size(); i++){
			System.out.println(deck.get(i).toString() + ", Score: " + deck.get(i).getScore());
		}
	}
	
	public int getTotalCards()
	{
		return deck.size();
	}
	
	private void loadDeck() {
		int cardIndex = 1;
		for(int i = 0; i < 2; i++){
			for(int s = 0; s < 4; s++){
				for(int r = 1; r < 14; r++){
					deck.add(new Card(s, r, cardIndex));
					cardIndex++;
				}
			}
		}
	}
	
	private void restackDeck(){
		System.out.println("No more cards\nRestacking old Cards...");
		for(Card c : usedCards){
			deck.add(c);
		}
		usedCards.clear();
		shuffle();
	}
}
