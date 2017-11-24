package org.academiadecodigo.balboas.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.balboas.Glory;


/**
 * Created by codecadet on 23/11/17.
 */
public class Hud {

    public Stage stage;
    private Viewport viewport;

    private float timecount;
    private Integer worldTimer;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel1;
    Label scoreLabel2;

    Label fighter2Label;
    Label leveLabel;
    Label timeLabel;
    Label fighter1Label;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timecount = 0;
        score = 0;

        viewport = new FitViewport(Glory.V_WIDTH, Glory.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel1 = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2 = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fighter2Label = new Label(String.format("FIGHTER 2"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(String.format("TIME", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fighter1Label = new Label(String.format("FIGHTER 1", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(fighter1Label).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(fighter2Label).expandX().padTop(10);
        table.row();
        table.add(scoreLabel1).expandX();
        table.add(countDownLabel).expandX();
        table.add(scoreLabel2).expandX();

        stage.addActor(table);


    }


}
