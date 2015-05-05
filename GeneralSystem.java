package lab8;

import java.util.ArrayList;
import java.util.Calendar;

public interface GeneralSystem {
	public gSystem getInstance();
	public Statistic getStatistic();
	public ArrayList<Turniket> getTurnikets();
	public void turniketInit();
	public Card makeCard (Cards type, Calendar calendar, int passQuantity, int validityPeriod );
	public boolean blockCard (int id);
	public Card isRegisteredCard (int cardId);
	public boolean isBlockedCard (int cardId);
}
