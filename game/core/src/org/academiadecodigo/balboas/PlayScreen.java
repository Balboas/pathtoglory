package org.academiadecodigo.balboas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.balboas.Scenes.Hud;
import org.academiadecodigo.balboas.Sprites.Fighter;


/**
 * Created by codecadet on 23/11/17.
 */
public class PlayScreen implements Screen {
    private Glory game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Fighter player;



    public PlayScreen(Glory game){

        this.game = game;
// cam to follow player 1
        gameCam  =new OrthographicCamera();

// viwpor to maintain sapect ratio
        gamePort = new FitViewport(Glory.V_WIDTH/Glory.PPM ,Glory.V_HEIGHT/Glory.PPM,gameCam );

        // game hud
        hud = new Hud(game.batch);
// load map
        mapLoader = new TmxMapLoader();
        map =  mapLoader.load("gloryMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/Glory.PPM);

        // set game camera
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);



        world = new World(new Vector2(0,-10), true);
        b2dr  =new Box2DDebugRenderer();




        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){

            Rectangle rec = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rec.getX()+rec.getWidth() /2)/Glory.PPM, (rec.getY()+rec.getHeight()/2)/Glory.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rec.getWidth()/2)/Glory.PPM, (rec.getHeight()/2)/Glory.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        player = new Fighter(world);
       // world = new World(new Vector2(0,-10),true);

    }

    public void update(float dt){

        handleInput(dt);

        world.step(1/60f,6,2);
        //gameCam.position.x = player.b2body.getPosition().x;

        gameCam.update();

        renderer.setView(gameCam);

    }

    private void handleInput(float dt) {
        if(Gdx.input.isTouched()){
            System.out.println(" " + Gdx.input.getX()/Glory.PPM + " " + Gdx.input.getY()/Glory.PPM);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=2){



            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);


        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2){

            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);


        }
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world,gameCam.combined);

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
