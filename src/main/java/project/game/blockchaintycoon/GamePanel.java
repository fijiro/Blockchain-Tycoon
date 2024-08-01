package project.game.blockchaintycoon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;

public class GamePanel extends Pane {

    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public int rahat = 1;
    public int rahojenKasvu = 4; // rahan kasvu per sekunti
    public int asiakkaat = 1;
    public int nodet = 1;

    private Canvas canvas;
    private Timeline gameLoop;
    private Button shopButton;



    public GamePanel(Stage primaryStage) {
        canvas = new Canvas(screenWidth, screenHeight);
        this.getChildren().add(canvas);


        // Nappi kauppaan
        Button shopButton = new Button("Shop");
        shopButton.setOnAction(e -> {
            primaryStage.setScene(Main.getShopScene());
            setShopButtonVisibility(false);
        });
        shopButton.setLayoutX(screenWidth - 100); // X-koordinaatti
        shopButton.setLayoutY(10); // Y-koordinaatti
        this.getChildren().add(shopButton);
    }

    public void startGameThread() {  // tässä on game loop
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            update();
            render();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void update() { // päivittää laskurit ja tulostaa ne
        rahat += rahojenKasvu;
        System.out.println("Rahat: " + rahat);
        System.out.println("Asiakkaat: " + asiakkaat);
        System.out.println("Nodet: " + nodet);
    }

    public void render() { // näkyvät laskurit
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, screenWidth, screenHeight);

        // Lisää rahan määrä näytölle
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));  // fontti ja koko
        gc.fillText("Money: " + rahat, 10, 30);
        gc.fillText("Customers: " + asiakkaat, 10, 60);
        gc.fillText("Nodes: " + nodet, 10, 90);


    }

    public void setShopButtonVisibility(boolean visible) {
        shopButton.setVisible(visible);
    }

}
