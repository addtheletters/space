package tk.sritwinkles;
 //Represents an object moving
 //Can also represent a vector starting from anywhere  
	//v0_2: replacing 3 vars for speed with vector represented by a point + origin	
	
	
   public class Entity extends Point implements Actor{
      /*protected double xSpeed;
      protected double ySpeed;
      protected double zSpeed;
      protected double forwardSpeed;
      */
      protected Point speed;
   		
      public Entity(){
         super();
         speed = new Point(0, 0, 0);
         /*xSpeed = 0;
         ySpeed = 0;
         zSpeed = 0;
         forwardSpeed = 0;*/
      } 
   	
      public Entity(Entity e){
         super(e);
         speed = new Point(e.getXSpeed(), e.getYSpeed(), e.getZSpeed());
      	/*
         xSpeed = e.getXSpeed();
         ySpeed = e.getYSpeed();
         zSpeed = e.getZSpeed();
         forwardSpeed = calcForwardSpeed();
      	*/
      }
   	
      public Entity(Point p, Point sv){//p2 represents a vector in xyz form, from the origin
         xPos = p.getXPos();
         yPos = p.getYPos();
         zPos = p.getZPos();
         speed = new Point(sv.getXPos(), sv.getYPos(), sv.getZPos());
      } 
    	
      public Entity(Point p, double xs, double ys, double zs){
      	//belagrgh
         xPos = p.getXPos();
         yPos = p.getYPos();
         zPos = p.getZPos();
         speed = new Point(xs, ys, zs);
      	/*
         xSpeed = xs;
         ySpeed = ys;
         zSpeed = zs;
         forwardSpeed = calcForwardSpeed();
      	*/
      }  
   	
      public Entity(double x, double y, double z){
         super(x, y, z);
         speed = new Point(0, 0, 0);
      }
      public Entity(double x, double y, double z, double xs, double ys, double zs){
         super(x, y, z);
         speed = new Point(xs, ys, zs);
      }
      public double getXSpeed(){
         return speed.getXPos();
      }
      public double getYSpeed(){
         return speed.getYPos();
      }
      public double getZSpeed(){
         return speed.getZPos();
      }
      //Magnitude of speed vector
      public double getForwardSpeed(){
         return speed.getMagnitude();
      }
      
   	//removed in v0_2
      /*public double calcForwardSpeed(){
         return Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2) + Math.pow(zSpeed, 2));
      }*/
      
      //Directions are angles in radians
      public double getXYDirection(){
         return Math.atan(speed.getXPos() / speed.getYPos());
      }
      public double getYZDirection(){
         return Math.atan(speed.getYPos() / speed.getZPos());
      }
      public double getXZDirection(){
         return Math.atan(speed.getXPos() / speed.getZPos());
      }
   	
      public void setXSpeed(double n){
         speed.setXPos(n);
      }
      public void setYSpeed(double n){
         speed.setYPos(n);
      }
      public void setZSpeed(double n){
         speed.setZPos(n);
      }
      
      public Point getSpeedVector(){
         return speed;
      }
      
      public Point copySpeedVector(){
         return new Point(speed);
      }	
      
   	//can change direction using this
      public void addSpeed(double xA, double yA, double zA){
         speed.add(new Point(xA, yA, zA));
      }
   	
      public void addSpeed(Point sv){ //go faster based on vector sv
         speed.add(sv);
      }
   	
      public void addSpeed(Point dirV, double acc){ //go acc faster in dirV direction
         addSpeed(dirV.toUnitVector().multiplyMagnitude(acc));
      }
   	
      //Goes in the same direction, but xyzA farther
      public void addForwardSpeed(double xyzA){
         if(speed.getMagnitude() > 0){
            addSpeed((speed.getXPos() / speed.getMagnitude()) * (speed.getMagnitude() + xyzA),
               (speed.getYPos() / speed.getMagnitude()) * (speed.getMagnitude() + xyzA),
               (speed.getZPos() / speed.getMagnitude()) * (speed.getMagnitude() + xyzA));
         }
      }
      
      public void step(){
         xPos += speed.getXPos();
         yPos += speed.getYPos();
         zPos += speed.getZPos();
      }
      public Entity getStepped(){
         return new Entity(new Point(xPos + speed.getXPos(), yPos + speed.getYPos(), zPos + speed.getZPos()), speed);
      }
   	
     	//gets min distance between travel paths of entities
   	//not yet fixed for v0_2 change
   	/*
      public double getPathMinDistance(Entity e){
         Point amoinsb = new Point(xPos - e.getXPos(), yPos-e.getYPos(), zPos-e.getZPos());
         Entity perp = new Entity(0, 0, 0, (ySpeed*e.getZSpeed()) - (zSpeed*e.getYSpeed()), (zSpeed*e.getXSpeed()) - (xSpeed*e.getZSpeed()), (xSpeed*e.getYSpeed()) - (ySpeed*e.getXSpeed()));
         double num = (amoinsb.getXPos()*perp.getXSpeed()) + (amoinsb.getYPos()*perp.getYSpeed()) + (amoinsb.getZPos()*perp.getZSpeed()); 
         return num/perp.getForwardSpeed();
      }*/
    	
      public String toString(){
         StringBuilder tmp = new StringBuilder();
         tmp.append("Location: ").append(super.toString()+"\n");
         tmp.append("XYZ Speed: ").append(speed.toString() + "\n");
         return tmp.toString();
      }  
   	
   }