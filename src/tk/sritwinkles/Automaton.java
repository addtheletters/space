package tk.sritwinkles;
 //represents an object with size and a direction that has the ability to accelerate itself  
	//v0_1: removed Facing class, added functionality to Automaton using dirFacing
   public class Automaton extends Obstruction{
      protected AccelProfile accelData;
      protected Point dirFacing;
   	
   
      public Automaton(){
         super();
         accelData = new AccelProfile();
      }
      public Automaton(Automaton a){
         super(a);
         accelData = a.getAccelProfile();
         dirFacing = a.getFacingDir().toUnitVector();
      	
      }
      public Automaton(Point p, Point sv, Point fv, double s){//see superclass for explanation
         super(p, sv, s);
         dirFacing = fv.toUnitVector();
         accelData = new AccelProfile();
      }
      public Automaton(Entity e, Point fv, double s){
         super(e, s);
         dirFacing = fv.toUnitVector();
         accelData = new AccelProfile();
      }
      public Automaton(Obstruction o, Point fv, Point sev, double prm, double sum, double rem, double tvm){
         super(o);
         dirFacing = fv.toUnitVector();
         accelData = new AccelProfile(prm, sum, rem, tvm, new Point(), sev);
      }
      
      public Automaton(Obstruction o, Point fv, AccelProfile ap, boolean re){ //set re to true to reset acceleration, but not maxes
         super(o);
         dirFacing = fv.toUnitVector();
         accelData = new AccelProfile(ap, re);
      }
   
      public AccelProfile getAccelProfile(){
         return accelData;
      }
      
   	public Point getFacingDir(){
   		return dirFacing;
   	}
      
      public void setFacingDir(double fx, double fy, double fz){
         dirFacing.setXPos(fx);
         dirFacing.setYPos(fy);
         dirFacing.setZPos(fz);
         dirFacing.setToUnitVector();
      }
      public void setFacingDir(Point p){
         dirFacing = p;
         dirFacing.setToUnitVector();
      }
      public void changeFacingDir(double tx, double ty, double tz){
         dirFacing.setXPos(dirFacing.getXPos() +tx);
         dirFacing.setYPos(dirFacing.getYPos() +ty);
         dirFacing.setZPos(dirFacing.getZPos() +tz);
         dirFacing.setToUnitVector();
      }
      public void changeFacingDir(Point p){
         changeFacingDir(p.getXPos(), p.getYPos(), p.getZPos());
      }
   	
      public boolean withinNoseCone(Point target, double angle, double distance){
      //if p is in the cone centered by the line of facing and bounded by angle then return true
         Point endPoint = new Point((dirFacing.getXPos()/dirFacing.getMagnitude())*distance, (dirFacing.getYPos()/dirFacing.getMagnitude())*distance,(dirFacing.getZPos()/dirFacing.getMagnitude())*distance);
         Point nEnd = endPoint.normalizeTo(this);
         Point nTarget = target.normalizeTo(this);
      	//kinda useful for later
      	//double cosTheta = (nEnd.getXPos() * nTarget.getXPos() + nEnd.getYPos() * nTarget.getYPos() + nEnd.getZPos() * nTarget.getZPos())(nEnd.getDistanceTo(new Point(0,0,0)) * nTarget.getDistanceTo(new Point(0,0,0));
         double phi = Math.acos(nEnd.getXPos() * nTarget.getXPos() + nEnd.getYPos() * nTarget.getYPos() + nEnd.getZPos() * nTarget.getZPos());
         if(phi <= angle)
            return true;
         else 
            return false;
      }
      
   	
      public void step(){
      	//implement acceleration based on directions
      	//addForwardSpeed(accelData.getCurrPrimary() - accelData.getCurrReverse());
      		//above ne marche pas because fwd speed isn't necessarily the direction you're facing
      	//More vector algebra yaaaaa
      	
      	dirFacing.add(accelData.getTurnVector());
      	dirFacing.setToUnitVector();
      	addSpeed(dirFacing, accelData.getCurrPrimary() - accelData.getCurrReverse());
      	addSpeed(accelData.getSecondaryVector(), accelData.getCurrSecondary());
      	
         xPos += speed.getXPos();
         yPos += speed.getYPos();
         zPos += speed.getZPos();
         
      }
      
   	public Automaton getStepped(){
   		Automaton copy = new Automaton(this);
   		copy.step();
   		return copy;
   	}
   	
   	public String toString(){
   		StringBuilder temp = new StringBuilder(super.toString());
   		temp.append(accelData.toString());
   		temp.append("Facing in: " + dirFacing.toString());
   		return temp.toString();
   	}
   
   }