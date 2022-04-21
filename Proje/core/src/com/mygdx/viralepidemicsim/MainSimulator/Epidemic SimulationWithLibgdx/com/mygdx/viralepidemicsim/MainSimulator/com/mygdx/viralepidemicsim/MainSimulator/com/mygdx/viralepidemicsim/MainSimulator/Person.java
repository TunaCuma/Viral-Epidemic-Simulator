

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Person
 */
public class Person extends Sprite{

    public Person(String name, float x, float y){
        super(new Texture(name));
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }
    
}