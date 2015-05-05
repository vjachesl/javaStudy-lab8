package lab8;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Statistic implements Statistics{
	public static final Statistic INSTANCE = new Statistic() ;
	private ArrayList<Event> events;
		
	private Statistic (){
		events = new ArrayList<Event>();
		events.add(new Event(new RealCard(Cards.SpesialTypeForStatistics, Calendar.getInstance(), Calendar.getInstance(), 0), "Statistics", Calendar.getInstance(), "System was started", true));
	}
	
	public Statistic getInstance(){
		return INSTANCE;
	}

	@Override
	public String[] getStatistics(Cards cardtype) {
		int arrayLength = 0; // counting new array size
		for (int i=0; i<events.size(); i++)
			if (events.get(i).card.getCardType().equals(cardtype)) arrayLength++;
		String [] toReturn = new String[arrayLength];	
		
		for (int i=0, j=0; j<arrayLength; i++){
			if (events.get(i).card.getCardType().equals(cardtype)){
				toReturn[j]=(events.get(i).toString());
				j++;
				}
			}
		return toReturn;
	}

	@Override
	public String[] getStatistics() {
	String [] toReturn = new String[events.size()];
	for (int i=0; i<toReturn.length; i++){
		toReturn[i] = events.get(i).toString();
		}
	return 	toReturn;
	}
	
	@Override
	public boolean addBlockingEvent(Card card, Calendar calendar, String eventCreator) {
		Event event = new Event(card, eventCreator, calendar, "Blocked card by System", false);
		events.add(event);
		return true;
	}
	@Override
	public boolean addEvent(Card card, Calendar calendar, String eventCreator, String issue, boolean flag){
		Event event = new Event(card, eventCreator, calendar, issue, flag);
		events.add(event);
		return true;
	}
		// inner class for adding events as new object
	public class Event {
		private Card card;
		private String eventCreator;
		private Calendar calendar;
		private String issue;
		private boolean sussesFlag;
		
		// One constructor
		public Event(Card card, String eventCreator, Calendar calendar, String issue, boolean sussesFlag){
			this.card = card;
			this.eventCreator = eventCreator;
			this.calendar = calendar;
			this.issue = issue;
			this.sussesFlag = sussesFlag;
			
		}
		//And Overrided method toString for corrected printing;
		@Override
		public String toString(){
			SimpleDateFormat calendarFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			StringBuilder str = new StringBuilder();
			str.append(calendarFormat.format((calendar.getTime()))+" The Card ID: "+card.getCardId()+"; was proccessed with flag: "+sussesFlag+"; by "+eventCreator+"; with additional info: "+issue );
			return str.toString();
		}
	}
	
}
