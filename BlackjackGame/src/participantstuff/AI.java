package participantstuff;

import java.util.Random;

public class AI extends Participant{
	public AI(String name){
		super(name);
	}

	@Override
	protected void hitOrStand() {
		printHand();
		if(this.getScore() >= AISTAND && this.getScore() <= BLACKJACK){
			stand(true);
		}
		else if(this.getScore() < AISTAND){
			standing = false;
		}
	}

	@Override
	public void makeDeposition() {
		int d = new Random().nextInt((10 - 5) + 1) + 5;
		setDeposition(d * 100);
		System.out.println("A deposition of " + getDeposition() + " was added to " + this.getName());
	}

	@Override
	public void makeBet() {
		boolean validBet = false;
		do{
			int placedBet = 0;
			int b = new Random().nextInt(7);
			switch (b){
			case 0:
				placedBet = 20;
				break;
			case 1:
				placedBet = 40;
				break;
			case 2:
				placedBet = 100;
				break;
			case 3:
				placedBet = 200;
				break;
			case 4:
				placedBet = 400;
				break;
			case 5:
				placedBet = 800;
				break;
			case 6:
				placedBet = 1000;
				break;
			}
			if(placedBet <= getDeposition() && placedBet > 0){
				setBet(placedBet);
				validBet = true;
			}
		}while(!validBet);
		System.out.println(getName() + " placed a bet: " + getBet());
	}
	
	@Override
	public PlayerType getType() {
		return PlayerType.AI;
	}
}
