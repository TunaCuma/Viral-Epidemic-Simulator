/* package com.mygdx.viralepidemicsim.FirstVersion.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.viralepidemicsim.FirstVersion.ViralEpidemicSim;

public class MainMenu implements Screen{
	private ViralEpidemicSim sim;
	private Stage stage;
	
	public MainMenu(ViralEpidemicSim sim) {
		this.sim = sim;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin();
	}
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
 */