package com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Scenes.*;

public class GameMain extends Game{

    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this));
        
        
    }

    public void render() {
        super.render();

    }

    public SpriteBatch getBatch(){
        return this.batch;
    }
    
}
