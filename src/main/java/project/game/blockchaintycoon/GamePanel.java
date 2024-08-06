package project.game.blockchaintycoon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    /*final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels */
    final int screenWidth = 900;
    final int screenHeight = 700;

    public int rahat = 1;
    public int rahojenKasvu = 0; // rahan kasvu per sekunti
    public int asiakkaat = 1;
    public int nodet = 1;

    private Canvas canvas;
    private Timeline gameLoop;
    private Button shopButton;
    private Font customFont;



    public GamePanel(Stage primaryStage) {
        canvas = new Canvas(screenWidth, screenHeight);


        customFont = Font.loadFont(getClass().getResourceAsStream("/Molot.otf"), 55);


        Image pelitausta = new Image("background.png");
        ImageView pelibg = new ImageView(pelitausta);
        this.getChildren().add(pelibg);
        this.getChildren().add(canvas);

        Image helpnappi = new Image("helpnappi.png");
        ImageView helpbtn = new ImageView(helpnappi);
        Button helpButton = new Button();
        helpButton.setOnAction(e -> {
            Scene helpScene = Main.getHelpScene();
            primaryStage.setScene(helpScene);
        });
        helpButton.setGraphic(helpbtn);
        helpButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        helpButton.setLayoutX(800);
        helpButton.setLayoutY(5);
        this.getChildren().add(helpButton);


        // Nappi kauppaan
        Button shopButton = new Button("Shop");
        shopButton.setOnAction(e -> {
            primaryStage.setScene(Main.getShopScene());
            setShopButtonVisibility(false);
        });
        shopButton.setLayoutX(500); // X-koordinaatti
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
        gc.clearRect(0, 0, screenWidth, screenHeight);

        // Lisää rahan määrä näytölle
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);  // fontti ja koko
        gc.fillText("Money: " + rahat, 310, 300);
        gc.fillText("Customers: " + asiakkaat, 310, 400);
        gc.fillText("Nodes: " + nodet, 310, 350);


    }

    public void setShopButtonVisibility(boolean visible) {
        shopButton.setVisible(visible);
    }

    public void setRahojenKasvu(int newValue) {
        this.rahojenKasvu = newValue;
    }

}
