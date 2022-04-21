import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Simulation extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1550;
    static final int GAME_HEIGHT = 800;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 7;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    ArrayList<Person> personArrL;
    
    Simulation(){
        personArrL = new ArrayList<Person>();
        newPerson(1000);
        
    
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();


    }

    public void newPerson(int numberOfPerson){
        random = new Random();

        for(int i = 0; i < numberOfPerson; i++){
            personArrL.add(new Person(randomBetween(0, GAME_WIDTH),randomBetween(0, GAME_HEIGHT),BALL_DIAMETER,BALL_DIAMETER));
        }
        personArrL.get(8).getSick();
        personArrL.get(21).getSick();
        personArrL.get(14).getImmune();
        personArrL.get(32).getImmune();
        personArrL.get(67).getImmune();
    }


    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g){
        for(Person person: personArrL){
            person.draw(g);
        }
    }

    public void move(){
        for(Person person: personArrL){
            person.move();
        }
    }

    public void checkCollision(){
        for(Person person: personArrL){
            
            if(person.y <= 0){
                person.setYDirection(-person.yVelocity);
            }
            if(person.y >= GAME_HEIGHT - BALL_DIAMETER){
                person.setYDirection(-person.yVelocity);
            }
            if(person.x <= 0){
                person.setXDirection(-person.xVelocity);
            }
            if(person.x >= GAME_WIDTH - BALL_DIAMETER){
                person.setXDirection(-person.xVelocity);
            }

        }
    }

    public void updateHealthStatus(){
        for(int i = 0; i < personArrL.size() - 1; i++){
            for(int  j = i ; j < personArrL.size() ; j++){
                int possibility = randomBetween(0, 100);
                if(possibility < 25){
                    if(personArrL.get(i).healthStatus == 1 && personArrL.get(j).healthStatus == 0 && personArrL.get(i).isInRange(personArrL.get(j))){
                        personArrL.get(j).getSick();
                    }
                    else if(personArrL.get(j).healthStatus == 1 && personArrL.get(i).healthStatus == 0 && personArrL.get(i).isInRange(personArrL.get(j))){
                        personArrL.get(i).getSick();
                    }
                }
            }
        }
    }

    public void updateInfection(){
        for(int i = 0; i < personArrL.size() ; i++){
            if(personArrL.get(i).healthStatus == 1){
                personArrL.get(i).immunity ++;
                if(personArrL.get(i).immunity == 100){
                    personArrL.get(i).getImmune();
                }
            }
            else if(personArrL.get(i).healthStatus == 2){
                personArrL.get(i).immunity--;
                if(personArrL.get(i).immunity == 0){
                    personArrL.get(i).loseImmunity();
                }
            }
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks =40.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                updateHealthStatus();
                updateInfection();
                repaint();
                delta--;
            }
        }        
    }

    public int randomBetween(int x, int y){
        return x + (int)(Math.random() * y);
    }

}
