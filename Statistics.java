package lab8;

import java.util.Calendar;

public interface Statistics {
	public String[] getStatistics(Cards cards);
	public String[] getStatistics();
	public boolean addBlockingEvent(Card card,Calendar calendar, String eventCreator);
	public boolean addEvent(Card card, Calendar calendar, String eventCreator, String issue, boolean flag);
}
