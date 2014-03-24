package participantstuff;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class Player extends Participant{

	public Player() {
		super(whatsYourName());
	}
	
	@Override
	protected void hitOrStand(){
		printHand();
		if(this.getScore() == BLACKJACK){
			stand(true);
		}
		else if(this.getScore() < BLACKJACK){
			Object[] options = { "HIT", "STAND" };
			if(JOptionPane.showOptionDialog(null, "Hit Or Stand?", "", JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0){
				stand(false);
			}
			else {
				stand(true);
			}
		}
	}
	
	@Override
	public void makeDeposition() {
		boolean validChoice = false;
		do{
			try{
				String[] choices = { String.valueOf(((MAX_DEPOSIT / 10) * 1)), String.valueOf(((MAX_DEPOSIT / 10) * 2)),
						String.valueOf(((MAX_DEPOSIT / 10) * 3)), String.valueOf(((MAX_DEPOSIT / 10) * 4)), 
						String.valueOf(((MAX_DEPOSIT / 10) * 5)), String.valueOf(((MAX_DEPOSIT / 10) * 6)),
						String.valueOf(((MAX_DEPOSIT / 10) * 7)), String.valueOf(((MAX_DEPOSIT / 10) * 8)), 
						String.valueOf(((MAX_DEPOSIT / 10) * 9)), String.valueOf(MAX_DEPOSIT)};
			    
				int input = Integer.parseInt((String) JOptionPane.showInputDialog(null, "How much money do want to deposit?", 
			    		"Deposit", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]));
				
				setDeposition(input);
				validChoice = true;
			} catch(NumberFormatException e) {
				if(JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Warning", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
					System.exit(0);
					validChoice = true;
				}
			}
		} while(!validChoice);			
	}
	
	private static String whatsYourName(){
		boolean validName = false;
		String name;
		do{
			name = JOptionPane.showInputDialog(null, "Enter your name:");
			if(name == null){
				if(JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Warning", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
					System.exit(0);
				}
			}
			else if(Pattern.matches("[a-öA-Ö ]+", name) && name.length() < 16){
				validName = true;
			}
			else if(name.length() > 15){
				JOptionPane.showMessageDialog(null, "Your name can not be longer than 15 characters");
			}
			else if(name.length() < 1){
				JOptionPane.showMessageDialog(null, "You must enter a name..." + "\nIf you don't have one, make one up!");
			}
			else {
				JOptionPane.showMessageDialog(null, "You can only have alphabetic characters in your name");
			}
		}while(!validName);
		String[] stringArr = name.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringArr.length; i++) {
        	sb.append(Character.toUpperCase(stringArr[i].charAt(0))).append(stringArr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
	}

	@Override
	public void makeBet() {
		boolean validBet = false;
		do{
			int placedBet = 0;
			Object[] betOptions = { "20", "40", "100", "200", "400", "800", "1000" };
			int choice = JOptionPane.showOptionDialog(null, "Your deposition is " + getDeposition() + "\nPlace your bet:", "Bet", JOptionPane.YES_NO_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, betOptions, betOptions[0]);
			
			switch (choice){
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
			
			if(placedBet == 0){
				JOptionPane.showMessageDialog(null, "This is not a game for children, you can't just cancel the betting...\n" + "THIS IS MADNESS!");
			}
			else if(placedBet > getDeposition()){
				JOptionPane.showMessageDialog(null, "Your bet can not be higher than your deposition");
			}
			else {
				setBet(placedBet);
				validBet = true;
			}
		} while (!validBet);
	}

	@Override
	public PlayerType getType() {
		return PlayerType.HUMAN;
	}
}
