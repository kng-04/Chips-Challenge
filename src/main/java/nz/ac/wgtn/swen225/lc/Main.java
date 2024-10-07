/*package nz.ac.wgtn.swen225.lc;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        final Game game = Persistency.loadGame("levels/level1.json");
        final Map<String, Image> images = Persistency.loadImages();
        final List<Tile> tiles = game.getTiles();
        final List<Characters> characters = game.getCharacters();
        final List<CoordinateEntity> entities = new ArrayList<>();
        entities.addAll(tiles);
        // make sure adding characters after tiles, so they are on top
        entities.addAll(characters);

        final int IMG_SIZE = 32; // 32x32

        final Canvas canvas = new Canvas(IMG_SIZE * game.getWidth(), IMG_SIZE * game.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setGlobalBlendMode(BlendMode.SCREEN);

        for (CoordinateEntity entity : entities) {
            int x = entity.getX();
            int y = entity.getY();
            String name = entity.getClass().getSimpleName();
            if (entity instanceof Tile && ((Tile) entity).getColor() != null) {
                Color color = ((Tile) entity).getColor();
                name += "-" + color.toString();
            }
            gc.drawImage(images.get(name), x * IMG_SIZE, y * IMG_SIZE);
        }

        stage.setScene(new Scene(new Group(canvas)));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

} */