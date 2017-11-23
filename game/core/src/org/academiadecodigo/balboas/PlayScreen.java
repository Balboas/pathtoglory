package org.academiadecodigo.balboas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.balboas.Scenes.Hud;

import java.awt.*;

/**
 * Created by codecadet on 23/11/17.
 */
public class PlayScreen implements Screen {
    private Hud hud;
    private Glory game;


    private OrthographicCamera gameCam;

    private Viewport gamePort;

    public PlayScreen(Glory game){

        this.game = game;

        gameCam  =new OrthographicCamera();
        gamePort = new FitViewport(Glory.V_WIDTH,Glory.V_HEIGHT,gameCam);
        hud = new Hud(game.batch);


    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
      hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width,height);

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
