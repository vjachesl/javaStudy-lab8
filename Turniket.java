package lab8;

import java.util.Calendar;

public class Turniket implements Turnikets {
	private static int turniketIdGenerator=0;
	private int turniketId;
	private gSystem system;
	private Statistic statistic;
	
	public Turniket(){
		turniketId = turniketIdGenerator++;
		system=system.INSTANCE;
		statistic = statistic.INSTANCE;
	}
	@Override
	public boolean checkCard(int cardId) {
		if (systemNotAvailable()) return false;
		Card cardForCheck = isRegisteredCard(cardId); 
		if (cardForCheck==null) {
			System.out.println("Card is not registered");
			return false;
		}
		if(isBlockedCard(cardId)) { 
			    statisticsRegistering(cardForCheck, "Card was blocked. Access denied." ,false);
				return false;
		}
		if(cardForCheck.pass()){ 
					if(system.RegisteredCardUpdate(cardForCheck)) openGate(cardId);
					statisticsRegistering(cardForCheck, "Card sussesfully autentificated." ,true);
					return true;
		}
		statisticsRegistering(cardForCheck, "Card hasn't passes or wrong hours for access. Access denied." ,false);
		return false;
	}
	private boolean systemNotAvailable() {
		if (system==null) return true;
		    else return false;
	}

	private Card isRegisteredCard(int cardId) {
		return system.isRegisteredCard(cardId);
	}

	private boolean isBlockedCard(int cardId) {
		return system.isBlockedCard(cardId);
	}
	private boolean openGate(int cardId) {
		System.out.println("The Gate at turniket ID " + turniketId + " was opened for card ID"+ cardId);
		return false;
	}
	private boolean statisticsRegistering(Card card, String issue, boolean flag) {
		if(statistic==null) return false;
		if(card == null) return false;
		statistic.addEvent(card, Calendar.getInstance(), ("Turniket Id: "+turniketId), issue, flag);
		return true;
	}

}
