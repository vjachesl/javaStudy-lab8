package lab8;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class gSystem implements GeneralSystem {
	private static String systemId = "SystemA012";
	public static final gSystem INSTANCE = new gSystem();
	private Statistic statistic;
	private ArrayList<Turniket> turnikets;
	private Map<Integer, Card> issuedCards = new HashMap<Integer , Card >();
	private Set<Integer> blockedCards = new HashSet<Integer>();
	
	
	private gSystem (){
		turnikets = new ArrayList<Turniket>();
		statistic = statistic.INSTANCE;
	}
	
	public gSystem getInstance(){
		return INSTANCE;
		
	}
	public Statistic getStatistic(){
		return statistic;
	
	}
	
	public ArrayList<Turniket> getTurnikets(){
		return turnikets;
	}
	
	public void turniketInit(){
		turnikets.add(new Turniket());
	}
	
	public Card isRegisteredCard (int cardId){
		return issuedCards.get(cardId);
		
	}
	
	public boolean isBlockedCard (int cardId){
		return blockedCards.contains(cardId);
	}
	
	public boolean RegisteredCardUpdate (Card card){
		if (issuedCards.replace(card.getCardId(), card)==null) {
			issuedCards.remove(card.getCardId());
			throw new IllegalArgumentException("Card is not autorized");
		}
		return true;
		
	}
		
	@Override
	public Card makeCard(Cards type, Calendar calendar, int passQuantity,
			int validityPeriod) {
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE,validityPeriod);
		Card newCard = new RealCard(type, calendar,cal, passQuantity);
		issuedCards.put(newCard.getCardId(), newCard);
		return newCard ;
	}

	@Override
	public boolean blockCard(int id) {
		Card tempcard = issuedCards.get(id);
		if (tempcard !=null){ tempcard.Block();
		statistic.addBlockingEvent(tempcard, Calendar.getInstance(), systemId);
		blockedCards.add(id);
		return true;
		}
		else return false;
		
	}

}
