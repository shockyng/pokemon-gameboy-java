package me.shockyng.pokemongameboyjava;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

public class MainHeroKeyboardListener {

    private MoveHeroListener moveHeroListener;

    // 16 for around 60 fps
    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> processKeyInputs(moveHeroListener)));
    private final Set<KeyCode> keysPressed = new HashSet<>();

    public MainHeroKeyboardListener(MoveHeroListener moveHeroListener) {
        this.moveHeroListener = moveHeroListener;
    }

    private void processKeyInputs(MoveHeroListener moveHeroListener) {
        if (keysPressed.contains(KeyCode.RIGHT)) {
            moveHeroListener.moveHeroToRight();
        }
        if (keysPressed.contains(KeyCode.LEFT)) {
            moveHeroListener.moveHeroToLeft();
        }
        if (keysPressed.contains(KeyCode.UP)) {
            moveHeroListener.moveHeroToUp();
        }
        if (keysPressed.contains(KeyCode.DOWN)) {
            moveHeroListener.moveHeroToDown();
        }
    }

    public void setupKeyListener(Scene scene) {
        timeline.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (!keysPressed.contains(code)) {
                keysPressed.add(code);
            }

            if (!timeline.getStatus().equals(Timeline.Status.RUNNING)) {
                timeline.play();
            }
        });

        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            keysPressed.remove(code);

            // Para a timeline caso nenhuma tecla esteja pressionada
            if (keysPressed.isEmpty() && timeline.getStatus().equals(Timeline.Status.RUNNING)) {
                timeline.stop();
            }
        });
    }

    public interface MoveHeroListener {
        void moveHeroToRight();
        void moveHeroToLeft();
        void moveHeroToUp();
        void moveHeroToDown();
    }

}