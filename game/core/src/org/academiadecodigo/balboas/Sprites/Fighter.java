package org.academiadecodigo.balboas.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.balboas.Glory;

/**
 * Created by codecadet on 24/11/17.
 */
public class Fighter extends Sprite {

    public World world;
    public Body b2body;

    public  Fighter(World world){

        this.world = world;
        definefighter();

    }

    private void definefighter() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(32/Glory.PPM,32/Glory.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/Glory.PPM);


        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
