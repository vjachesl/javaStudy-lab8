package lab8;

public enum Cards {
	//         text description for card         /  days, which active/ limit card / start hour&end hour
   LimitWorkMorning ("Card with Limit pasees for work days",new int[]{1,1,1,1,1,0,0},true, new int[]{9,13}), 
   LimitWorkEvening ("Card with Limit pasees for work days",new int[]{1,1,1,1,1,0,0},true, new int[]{13,17}), 
   
   LimitWeekEndMorning ("Card with Limit pasees for weekend", new int[]{0,0,0,0,0,1,1},true,  new int[]{9,13}), 
   LimitWeekEndEvening ("Card with Limit pasees for weekend", new int[]{0,0,0,0,0,1,1},true,  new int[]{13,17}), 
   
   UnlimWorkMorning ("Unlimited passes card for working days",new int[]{1,1,1,1,1,0,0},false, new int[]{9,13}),
   UnlimWorkEvening ("Unlimited passes card for working days",new int[]{1,1,1,1,1,0,0},false, new int[]{13,17}),
   
   UnlimWeekEndMorning ("Unlimited passes card for weekends",new int[]{0,0,0,0,0,1,1},false, new int[]{9,13}),
   UnlimWeekEndEvening ("Unlimited passes card for weekends",new int[]{0,0,0,0,0,1,1},false, new int[]{13,17}),
   SpesialTypeForStatistics ("",new int[]{0,0,0,0,0,0,0},false, new int[]{0,0});
   
   private String descr;
   private int [] validDays;
   private boolean isLimit;
   private int[] validHours;
   
   private Cards(String value, int[] validDays, boolean isLimit,int[] validHours){
	   this.descr = value;
	   this.validDays=validDays;
	   this.isLimit=isLimit;
	   this.validHours = validHours;
   }
   public int[] validDays(){
	   return validDays;
   }
   public boolean isLimit(){
	   return isLimit;
   }
   public int[] validHours(){
	   return validHours;
   }
}
