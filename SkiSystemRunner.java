package lab8;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class SkiSystemRunner {
 public static void main(String[] args) {
	GeneralSystem system = gSystem.INSTANCE;
	system.turniketInit();
	system.turniketInit();
	system.turniketInit();
	system.turniketInit();
	ArrayList<Turniket> turnikets = system.getTurnikets();
	Statistic stat = system.getStatistic();
	SimpleDateFormat calendarFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	Calendar validFrom = Calendar.getInstance();
	Calendar validTo = Calendar.getInstance();
	validFrom.add(Calendar.DAY_OF_MONTH,-1);
//	System.out.println("Valid From: "+calendarFormat.format(validFrom.getTime()));
	Random rnd = new Random();
	Card[] unRegisteredCards = new Card[10];
	Card[] registeredCards = new Card[10];
	for (int i=0; i<10; i++) {
	validFrom.add(Calendar.DAY_OF_MONTH,rnd.nextInt(5) );
	validTo.add(Calendar.DAY_OF_MONTH, (5+rnd.nextInt(6)));
	//making 10 unregistered cards
	unRegisteredCards[i] = new RealCard(Cards.LimitWorkEvening, validFrom, validTo, 5);
	}
	//returning Data to begining values
	validFrom = Calendar.getInstance();
	validFrom.add(Calendar.DAY_OF_MONTH,-1);
	validTo = Calendar.getInstance();
	validTo.add(Calendar.DAY_OF_MONTH,5);
	
	// making registered cards
	registeredCards[0] = system.makeCard(Cards.UnlimWeekEndEvening, validFrom, 0, 1);
	registeredCards[1] = system.makeCard(Cards.UnlimWorkEvening, validFrom,0, 2);
	registeredCards[2] = system.makeCard(Cards.UnlimWorkEvening, validFrom,0, 2);
	registeredCards[3] = system.makeCard(Cards.LimitWeekEndEvening, validFrom, 3, 5);
	registeredCards[4] = system.makeCard(Cards.LimitWeekEndMorning, validFrom, 5, 5);
	registeredCards[5] = system.makeCard(Cards.LimitWorkEvening, validFrom, 3, 3);
	
	System.out.println(registeredCards[0]);
	System.out.println(registeredCards[1]);
	System.out.println(registeredCards[2]);
	System.out.println(registeredCards[5]);
	
	//Blocking one card
	system.blockCard(registeredCards[0].getCardId());
	
	//Check cards for pass
	System.out.println(turnikets.get(0).checkCard(registeredCards[0].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[0].getCardId()));
	System.out.println(turnikets.get(1).checkCard(unRegisteredCards[0].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[3].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[2].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[5].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[5].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[5].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[5].getCardId()));
	System.out.println(turnikets.get(1).checkCard(registeredCards[5].getCardId()));

	//getting full statistics
	System.out.println("Full statistics");
	String [] stats = stat.getStatistics();;
	for (String s : stats) System.out.println(s);
	
	
	//get statistics for cardType
	System.out.println("One Card Type Statistics");
	String [] stats1 = stat.getStatistics(Cards.LimitWorkEvening);;
	for (String s : stats1) System.out.println(s);
	
 }
}
