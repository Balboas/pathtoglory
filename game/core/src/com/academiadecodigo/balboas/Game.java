package com.academiadecodigo.balboas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter implements InputProcessor {
	OrthographicCamera camera;
	SpriteBatch batch;

	TextureRegion textureRegion;
	Fighter fighter;

	@Override
	public void create() {

		camera = new OrthographicCamera(1000, 500);
		//texture2 = new Texture("photo.jpg");
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		fighter = new Fighter();
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		fighter.sprite.draw(batch);
		fighter.getRectangle().setC
		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
//		System.out.println("Sprite x " + sprite2.getX());
		//System.out.println("Sprite y " + sprite2.getY());
		switch (keycode) {
			case Input.Keys.LEFT:
				sprite.translate(-50, 0);
				if (sprite.getX() < -450) {
					sprite.setPosition(-450, sprite.getY());
				}
				break;
			case Input.Keys.RIGHT:
				sprite.translate(50f, 0f);
				if (sprite.getX() > 450) {
					sprite.setPosition(450, sprite.getY());
				}
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
