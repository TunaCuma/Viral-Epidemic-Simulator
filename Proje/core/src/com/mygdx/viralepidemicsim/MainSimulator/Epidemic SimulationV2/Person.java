import java.awt.*;

import java.util.*;

public class Person extends Rectangle {
    Random random;
    double xVelocity;
    double yVelocity;
    double initialSpeed = 1.5;
    double angle = (Math.random() * 360);

    int healthStatus; //0 for healthy. 1 for sick. 2 for immune.
    static final int INFECTION_TIME = 600;
    int immunity = 0;


    Person(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);

        setXDirection(randomXDirection * initialSpeed);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);

    }

    public void setXDirection(double randomXDirection) {
        xVelocity = randomXDirection;

    }

    public void setYDirection(double randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += (xVelocity * initialSpeed) ;
        //Math.cos(Math.toRadians(angle))
        y += (yVelocity * initialSpeed) ;
        //* Math.sin(Math.toRadians(angle)
    }

    public void draw(Graphics g) {
        if(healthStatus==0){
            g.setColor(Color.BLUE);
        }
        else if(healthStatus==1){
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.yellow);
        }
        g.fillOval(x, y, height, width);
    }

    public void getSick(){
        healthStatus = 1;
    }

    public void getImmune(){
        healthStatus = 2;
    }

    public void loseImmunity(){
        healthStatus = 0;
    }

    public boolean isInRange(Person other){
        return (Math.pow(this.x - other.x,2) + Math.pow(this.y - other.y , 2) < 144);
    }

    public boolean isIntersect(Person other){
        return (Math.pow(this.x - other.x,2) + Math.pow(this.y - other.y , 2) < 49);
    }

}
