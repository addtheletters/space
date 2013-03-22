package tk.sritwinkles;
   public class AccelProfile{
      protected double maxPrimary;
      protected double maxSecondary;
      protected double maxReverse;
    	
      protected double currPrimary;
      protected double currSecondary;
      protected double currReverse;
      
      protected Point secondaryVector;
   	
      protected Point turnVector;
      protected double maxTurnMagnitude;
   	
      public AccelProfile(){
         maxPrimary = 1;
         maxSecondary = 1;
         maxReverse = 1;
         maxTurnMagnitude = 1;
         turnVector = new Point();
         secondaryVector = new Point();
         resetAccel();
      }
      
      public AccelProfile(double mp, double ms, double mr, double mtm){
         maxPrimary = mp;
         maxSecondary = ms;
         maxReverse = mr;
         maxTurnMagnitude = mtm;
         turnVector = new Point();
         secondaryVector = new Point();
         resetAccel();
         ensureLimits();
      }
      
      public AccelProfile(double mp, double ms, double mr, double mtm, Point tv, Point sv){
         maxPrimary = mp;
         maxSecondary = ms;
         maxReverse = mr;
         maxTurnMagnitude = mtm;
         turnVector = tv;
         secondaryVector = sv;
         resetAccel();
         ensureLimits();
      }
      
      public AccelProfile(double mp, double ms, double mr, double mtm, double cp, double cs, double cr, Point tv, Point sv){
         maxPrimary = mp;
         maxSecondary = ms;
         maxReverse = mr;
         maxTurnMagnitude = mtm;
         turnVector = tv;
         secondaryVector = sv;
         currPrimary = cp;
         currReverse = cr;
         currSecondary = cs;
         ensureLimits();
      } 
   	
      public AccelProfile(AccelProfile ap, boolean re){
         maxPrimary = ap.getMaxPrimary();
         maxSecondary = ap.getMaxSecondary();
         maxReverse = ap.getMaxReverse();
         maxTurnMagnitude = ap.getMaxTurnMagnitude();
         
         if(re){
            reset();
         }
         else{
            currPrimary = ap.getCurrPrimary();
            currSecondary = ap.getCurrSecondary();
            currReverse = ap.getCurrReverse();
            turnVector = ap.getTurnVector();
            secondaryVector = ap.getSecondaryVector();
         }
         ensureLimits();
      }
   	
   	
      public double getMaxPrimary(){
         return maxPrimary;
      }
      public double getMaxSecondary(){
         return maxSecondary;
      }
      public double getMaxReverse(){
         return maxReverse;
      }
      public double getMaxTurnMagnitude(){
         return maxTurnMagnitude;
      }  
   	
      public void setMaxPrimary(double n){
         maxPrimary = n;
      }
      public void setMaxSecondary(double n){
         maxSecondary = n;
      }
      public void setMaxReverse(double n){
         maxReverse = n;
      }
      public void setMaxTurnMagnitude(double n){
         maxTurnMagnitude = n;
      }
      
      public double getCurrPrimary(){
         return currPrimary;
      }
      public double getCurrSecondary(){
         return currSecondary;
      }
      public double getCurrReverse(){
         return currReverse;
      }	
      public Point getTurnVector(){
         return turnVector;
      }
      public Point getSecondaryVector(){
         return secondaryVector;
      }
   	
      
      public void setCurrPrimary(double n){
         if(n < 0) currPrimary = 0;
         else if(n > maxPrimary) currPrimary = maxPrimary;
         else currPrimary = n;
      }
      public void setCurrSecondary(double n){
         if(n < 0) currSecondary = 0;
         else if(n > maxSecondary) currSecondary = maxSecondary;
         else currSecondary = n;
      }
      public void setCurrReverse(double n){
         if(n < 0) currReverse = 0;
         else if(n > maxReverse) currReverse = maxReverse;
         else currReverse = n;
      }
      public void setTurnVector(Point p){
         setTurnVector(p.getXPos(), p.getYPos(), p.getZPos());
      }
      public void setTurnVector(double x, double y, double z){
         turnVector.setXPos(x);
         turnVector.setYPos(y);
         turnVector.setZPos(z);
         clipTurnVector();
      }
      public void setSecondaryVector(Point p){
         setSecondaryVector(p.getXPos(), p.getYPos(), p.getZPos());
      }
      public void setSecondaryVector(double x, double y, double z){
         secondaryVector.setXPos(x);
         secondaryVector.setYPos(y);
         secondaryVector.setZPos(z);
      }
      
      public void clipTurnVector(){ //sets turnVector so it has magnitude less than maxTurnMagnitude
         if(turnVector.getMagnitude() > maxTurnMagnitude){
            turnVector = turnVector.toUnitVector().multiplyMagnitude(maxTurnMagnitude);
         }
      }
   
   	protected void ensureLimits(){
   		if(currPrimary < 0) currPrimary = 0;
         else if(currPrimary > maxPrimary) currPrimary = maxPrimary;
      	if(currSecondary < 0) currSecondary = 0;
         else if(currSecondary > maxSecondary) currSecondary = maxSecondary;
      	if(currReverse < 0) currPrimary = 0;
         else if(currReverse > maxReverse) currReverse = maxReverse;
      	clipTurnVector();
   	}
   	
      protected void resetAccel(){
         currPrimary = 0;
         currReverse = 0;
         currSecondary = 0;
      }
      protected void resetTurn(){
         turnVector = new Point();
      }
      protected void reset(){
         resetAccel();
         resetTurn();
         secondaryVector = new Point();
      }
      
      
   	public String toString(){
   		StringBuilder temp = new StringBuilder(" Max Ac.: ");
   		temp.append("P:" + (float)maxPrimary + " R:" + (float)maxReverse + " S:" + (float)maxSecondary + "\n");
   		temp.append(" Curr Ac.: " + "P:" + (float)currPrimary + " R:" + (float)currReverse + " S:" + (float)currSecondary + "\n");
   		temp.append(" S.Vector: " + secondaryVector.toString() + "\n");
   		temp.append(" T.Vector: " + turnVector.toString() + "\n");
   		temp.append(" TV Max Mag: " + (float)maxTurnMagnitude + "\n");
   		return temp.toString();
   	}
   }