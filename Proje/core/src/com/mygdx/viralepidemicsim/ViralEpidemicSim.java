package com.mygdx.viralepidemicsim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ViralEpidemicSim extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, ekremAbi;
	int x, y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		ekremAbi = new Texture("E2OtY_EXoAAxfYb.jpg");
		x = 0; y = 0;
	}

	@Override
	public void render () {
		batch.begin();
		changeCoordinates();
		ScreenUtils.clear(1, 1, 1, 1);
		batch.draw(img, 0, 0);
		batch.draw(ekremAbi, x, y);
		batch.end();
	}
	
	private void changeCoordinates() {
		x++;
		y++;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			batch.draw(ekremAbi, 0, 0);
			x--;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		ekremAbi.dispose();
	}
}
