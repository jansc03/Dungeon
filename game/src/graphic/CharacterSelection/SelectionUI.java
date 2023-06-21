package graphic.CharacterSelection;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import controller.ScreenController;
import ecs.entities.Charakterklassen.Archer;
import ecs.entities.Charakterklassen.Knight;
import ecs.entities.Charakterklassen.Mage;
import graphic.hud.FontBuilder;
import graphic.hud.ScreenButton;
import graphic.hud.TextButtonListener;
import graphic.hud.TextButtonStyleBuilder;
import starter.Game;
import tools.Constants;
import tools.Point;

import java.util.logging.Logger;

public class SelectionUI<T extends Actor> extends ScreenController<T>{
    private static final Logger LOGGER = Logger.getLogger(SelectionUI.class.getName());
    public SelectionUI() {
        this(new SpriteBatch());
    }
    public SelectionUI(SpriteBatch batch) {
        super(batch);
        createButton(this::createArcher,"Archer",1.7f);
        createButton(this::createMage,"Mage",2f);
        createButton(this::createKnight,"Knight",2.5f);
        /*ScreenButton archer=
            new ScreenButton("Archer", new Point(0f, 0f), new TextButtonListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Game.getEntitiesToRemove().add(Game.getHero().get());
                    Game.setHero(new Archer());
                    hideSelection();
                }
            },new TextButtonStyleBuilder(FontBuilder.DEFAULT_FONT).setFontColor(Color.WHITE).setOverFontColor(Color.BLACK).build());
        archer.setPosition((Constants.WINDOW_WIDTH) / 2f - archer.getWidth() + 20,
            (Constants.WINDOW_HEIGHT) / 2f + archer.getHeight(),
            Align.center | Align.bottom);
        archer.setColor(Color.BROWN);
        ScreenButton night=
            new ScreenButton("night", new Point(0f, 0f), new TextButtonListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Game.getEntitiesToRemove().add(Game.getHero().get());
                    Game.setHero(new Knight());
                    hideSelection();
                }
            },new TextButtonStyleBuilder(FontBuilder.DEFAULT_FONT).setFontColor(Color.WHITE).setOverFontColor(Color.BLACK).build());
        night.setPosition((Constants.WINDOW_WIDTH) / 2f - night.getWidth() + 20,
            (Constants.WINDOW_HEIGHT) / 1.7f + night.getHeight(),
            Align.center | Align.bottom);
        night.setColor(Color.BROWN);
        ScreenButton mage=
            new ScreenButton("Mage", new Point(0f, 0f), new TextButtonListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Game.getEntitiesToRemove().add(Game.getHero().get());
                    Game.setHero(new Mage());
                    hideSelection();
                }
            },new TextButtonStyleBuilder(FontBuilder.DEFAULT_FONT).setFontColor(Color.WHITE).setOverFontColor(Color.BLACK).build());
        mage.setPosition((Constants.WINDOW_WIDTH) / 2f - mage.getWidth() + 20,
            (Constants.WINDOW_HEIGHT) / 2.5f + mage.getHeight(),
            Align.center | Align.bottom);
        //add((T)archer);
        add((T)mage);
        add((T)night);
        hideSelection();*/
    }

    public void showSelection(){
        Game.togglePause();
        this.forEach((Actor s) -> s.setVisible(true));
        LOGGER.info("Game Paused and Selection shown");
    }
    public void hideSelection(){
        Game.togglePause();
        this.forEach((Actor s)->s.setVisible(false));
        LOGGER.info("Game Unpaused and Selection hidden");
    }
    public void createButton(IcreateHero icreateHero,String heroName,float pos){
        ScreenButton temp=
            new ScreenButton(heroName, new Point(0f, 0f), new TextButtonListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Game.getEntitiesToRemove().add(Game.getHero().get());
                    icreateHero.createHero();
                    hideSelection();
                }
            },new TextButtonStyleBuilder(FontBuilder.DEFAULT_FONT).setFontColor(Color.WHITE).setOverFontColor(Color.BLACK).build());
        temp.setPosition((Constants.WINDOW_WIDTH) / 2f - temp.getWidth() + 20,
            (Constants.WINDOW_HEIGHT) / pos + temp.getHeight(),
            Align.center | Align.bottom);
        temp.setColor(Color.BROWN);
        add((T)temp);
        LOGGER.info("Button for "+heroName+" was created and added to "+this.getClass().getSimpleName());
    }
    public void createArcher(){
        Game.setHero(new Archer());
        LOGGER.info("Archer created");
    }
    public void createKnight(){
        Game.setHero(new Knight());
        LOGGER.info("Knight created");
    }
    public void createMage(){
        Game.setHero(new Mage());
        LOGGER.info("Mage created");
    }
}
