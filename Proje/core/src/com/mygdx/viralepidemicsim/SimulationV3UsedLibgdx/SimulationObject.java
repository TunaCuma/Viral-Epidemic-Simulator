package com.mygdx.viralepidemicsim.SimulationV3UsedLibgdx;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class SimulationObject extends Sprite{
	private Rectangle rec;
	
	public SimulationObject(int x, int y, int width, int height){
		rec = new Rectangle(x, y, width, height);
	}
	
	public abstract void initSpeed(int speed);
	
	
	public boolean isOverlappingWith(SimulationObject B){
		return (getX() < (B.getX()+B.getWidth()) && (getX()+getWidth()) > B.getX() && getY() < (B.getY()+B.getHeight()) && (getY()+getHeight()) > B.getY()); 
	}
	
	public boolean isOverlappingWith(Rectangle B){
		return (getX() < (B.x+B.width) && (getX()+getWidth()) > B.x && getY() < (B.y+B.height) && (getY()+getHeight()) > B.y); 
	}
	
	protected void increaseX(int speed){
		rec.x += speed;
	}
	
	protected void decreaseX(int speed){
		rec.x -= speed;
	}
	
	protected void increaseY(int speed){
		rec.y += speed;
	}
	
	protected void decreaseY(int speed){
		rec.y -= speed;
	}
	
	public void setX(int x){
		rec.x = x;
	}
	
	public void setY(int y){
		rec.y = y;
	}
	
	public float getX(){
		return  rec.x;
	}
	
	public float getY(){
		return (int) rec.y;
	}
	
	public float getWidth(){
		return (int) rec.width;
	}
	
	public float getHeight(){
		return (int) rec.height;
	}
	
	public void draw(ShapeRenderer sp){
		sp.rect(getX(), getY(), getWidth(), getHeight());
	}
	
}