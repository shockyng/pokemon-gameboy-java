package me.shockyng.pokemongameboyjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

import static me.shockyng.pokemongameboyjava.AssetsResource.*;

public class HelloApplication extends Application implements MainHeroKeyboardListener.MoveHeroListener {

    private ImageView hero;
    private int heroX = 0;
    private int heroY = 0;
    private int stepSize = 5;
    private Image heroImage;
    Pane box;

    @Override
    public void start(Stage stage) {
        box = new Pane();
        hero = new ImageView();
        updateHeroImage(HERO_IMAGE_RIGHT_PATH);
        hero.setFitHeight(50);
        hero.setFitWidth(50);
        box.getChildren().add(hero);
        Scene scene = new Scene(box, 700, 700);

        new MainHeroKeyboardListener(this).setupKeyListener(scene);

        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(
                        this::loadVariables,
                        1000 / 60,
                        1000 / 60,
                        java.util.concurrent.TimeUnit.MILLISECONDS
                );

        hero.requestFocus();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void moveHeroToDown() {
        heroY += stepSize;
        if (!hero.getImage().getUrl().contains(HERO_IMAGE_DOWN_PATH)) {
            updateHeroImage(HERO_IMAGE_DOWN_PATH);
        }

        System.out.println("Hero moved down - Y :" + heroY);
    }

    public void moveHeroToUp() {
        heroY -= stepSize;
        if (!hero.getImage().getUrl().contains(HERO_IMAGE_UP_PATH)) {
            updateHeroImage(HERO_IMAGE_UP_PATH);
        }

        System.out.println("Hero moved up - Y :" + heroY);
    }

    public void moveHeroToLeft() {
        heroX -= stepSize;
        if (!hero.getImage().getUrl().contains(HERO_IMAGE_LEFT_PATH)) {
            updateHeroImage(HERO_IMAGE_LEFT_PATH);
        }

        System.out.println("Hero moved to left - X :" + heroX);
    }

    @Override
    public void moveHeroToRight() {
        heroX += stepSize;
        if (!hero.getImage().getUrl().contains(HERO_IMAGE_RIGHT_PATH)) {
            updateHeroImage(HERO_IMAGE_RIGHT_PATH);
        }

        System.out.println("Hero moved to right - X :" + heroX);
    }

    public void loadVariables() {
        hero.setX(heroX);
        hero.setY(heroY);
    }

    void updateHeroImage(String heroImagePath) {
        heroImage = new Image(getClass().getResource(heroImagePath).toExternalForm());
        hero.setImage(heroImage);
    }

    public static void main(String[] args) {
        launch();
    }
}