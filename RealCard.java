package lab8;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RealCard implements Card{
	private static int cardIdCounter=1000; // Base value, from what counts starts;
	private final Cards cardType;
	private final int cardId;
	private final Calendar validFrom;
	private final Calendar validTo;
	private Boolean isBlocked;
	private int passCounter;
	
	public RealCard(Cards cardType, Calendar validFrom, Calendar validTo, int passCounter){
		 if (validFrom == null || validTo == null) 
		 {
	            throw new IllegalArgumentException("Date is null");
	        }
	     if (validFrom.after(validTo)) 
	     {
	            throw new IllegalArgumentException("Activation time is after expiration");
	        }
	     if (cardType == null) 
		 {
	            throw new IllegalArgumentException("Card Type is null");
	        }
		this.cardType =cardType;
		this.cardId =generateCardId();
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.isBlocked = false;
		this.passCounter = passCounter;
	}
	private int generateCardId(){
		return cardIdCounter++;
	}
	public int getCardId(){
		return cardId;
	}
	public Cards getCardType(){
		return cardType;
	}
	public boolean isBlocked(){
		return isBlocked;
	}
	public void Block(){
		isBlocked=true;
	}
	
	public boolean pass(){
		if (cardType.isLimit() && (passCounter>=1) && isValidForDayTime() && (!isBlocked())) {
				passCounter--;
				return true;
			}
		if (!(cardType.isLimit()) && isValidForDayTime() && (!isBlocked())) 
			return true;
		return false;
	}
	
	private boolean isValidForDayTime(){
		GregorianCalendar calendar = new GregorianCalendar();
		if (calendar.before(validFrom)||calendar.after(validTo)) return false;
		int currentDay = calendar.get(calendar.DAY_OF_WEEK);
		int currentHour= calendar.get(calendar.HOUR_OF_DAY);
		int [] days = this.cardType.validDays();
		int [] hours = this.cardType.validHours();
		if ((days[currentDay-1]==1) && currentHour>=hours[0] && currentHour<hours[1]) return true;
		return false;	
	}
	public String toString(){
		SimpleDateFormat calendarFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		StringBuilder str = new StringBuilder();
		str.append("CardId :"+cardId+ "; Card Type :"+cardType+"; Valid from :"+calendarFormat.format(validFrom.getTime())+
				    "; Valid to : "+calendarFormat.format(validTo.getTime())+"; Pass counter :"+passCounter );
		return str.toString();	
	}
}
