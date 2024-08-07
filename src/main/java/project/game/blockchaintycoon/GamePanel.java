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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DecimalFormat;

public class GamePanel extends Pane {
    /*
        final int originalTileSize = 16; // 16x16 pixels
        final int scale = 3;
            final int tileSize = originalTileSize * scale; // 48x48 pixels
            final int maxScreenCol = 16;
            final int maxScreenRow = 12;
            final int screenWidth = tileSize * maxScreenCol; // 768 pixels
            final int screenHeight = tileSize * maxScreenRow; // 576 pixels */
    final int screenWidth = 900;
    final int screenHeight = 700;

    private double money = 1;
    private int moneyGrowth = 0; // money Growth per second
    private int customers = 1;
    private int nodes = 1;
    private int upgrades = 1;

    private final Canvas canvas;
    //private Button shopButton;
    private final Font customFont, customFont2;


    public GamePanel(Stage primaryStage) {
        canvas = new Canvas(screenWidth, screenHeight);

        customFont = Font.loadFont(getClass().getResourceAsStream("/Molot.otf"), 55);
        customFont2 = Font.loadFont(getClass().getResourceAsStream("/Molot.otf"), 27);

        Image buyButtonImage = new Image("buybutton.png");
        Image gameBgImage = new Image("gamebg.png");
        ImageView gameBg = new ImageView(gameBgImage);
        this.getChildren().add(gameBg);
        this.getChildren().add(canvas);


        Button buyNodeButton = new Button();
        buyNodeButton.setOnAction(_ -> {
            if (money >= 10 * nodes) {
                money -= 10 * nodes;
                nodes++;
            }
        });
        buyNodeButton.setGraphic(new ImageView(buyButtonImage));
        buyNodeButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        buyNodeButton.setLayoutX(285);
        buyNodeButton.setLayoutY(542);
        this.getChildren().add(buyNodeButton);

        Button buyAdButton = new Button();
        buyAdButton.setOnAction(_ -> {
            if (money >= 40 * customers && nodes > customers) {
                money -= 40 * customers;
                customers++;
                moneyGrowth = 4 * customers;
                moneyGrowth += (upgrades * 10);
            }
        });
        buyAdButton.setGraphic(new ImageView(buyButtonImage));
        buyAdButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        buyAdButton.setLayoutX(505);
        buyAdButton.setLayoutY(542);
        this.getChildren().add(buyAdButton);

        Button buyPartsButton = new Button();
        buyPartsButton.setOnAction(_ -> {
            if (money >= 100 * upgrades * upgrades) {
                money -= 100 * upgrades * upgrades;
                upgrades++;
                moneyGrowth = 4 * customers;
                moneyGrowth += (upgrades * 10);
            }
        });
        buyPartsButton.setGraphic(new ImageView(buyButtonImage));
        buyPartsButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        buyPartsButton.setLayoutX(725);
        buyPartsButton.setLayoutY(542);
        this.getChildren().add(buyPartsButton);

        Image helpButtonImage = new Image("helpnappi.png");
        Button helpButton = new Button();
        helpButton.setOnAction(_ -> {
            Scene helpScene = Main.getHelpScene();
            primaryStage.setScene(helpScene);
        });
        helpButton.setGraphic(new ImageView(helpButtonImage));
        helpButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        helpButton.setLayoutX(800);
        helpButton.setLayoutY(5);
        this.getChildren().add(helpButton);


        /* Nappi kauppaan
        Button shopButton = new Button("Shop");
        shopButton.setOnAction(e -> {
            primaryStage.setScene(Main.getShopScene());
            //setShopButtonVisibility(false);
        });
        shopButton.setLayoutX(500); // X-koordinaatti
        shopButton.setLayoutY(10); // Y-koordinaatti
        this.getChildren().add(shopButton); */
    }

    public void startGameThread() {  // tässä on game loop
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(100), _ -> {
            update();
            render();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void update() { // päivittää laskurit ja tulostaa ne
        money += (double) moneyGrowth / 10;
        System.out.println(STR."Rahat: \{money}");
        System.out.println(STR."Asiakkaat: \{customers}");
        System.out.println(STR."Nodet: \{nodes}");
    }

    public void render() { // näkyvät laskurit
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, screenWidth, screenHeight);

        // Lisää rahan määrä näytölle
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);  // fontti ja koko
        DecimalFormat f = new DecimalFormat("##.00");
        gc.fillText(STR."Money: \{f.format(money)}$", 270, 225);
        gc.fillText(STR."Customers: \{customers}", 270, 325);
        gc.fillText(STR."Nodes: \{nodes}", 270, 275);
        //Ad button rendering
        if (money >= 40 * customers && nodes > customers) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{40 * customers}$", 547, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{40 * customers}$", 547, 540);
        }
        //Node button rendering
        if (money >= 10 * nodes) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{10 * nodes}$", 333, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{10 * nodes}$", 333, 540);
        }
        //Upgrade button rendering
        if (money >= 100 * upgrades * upgrades) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{100 * upgrades * upgrades}$", 761, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{100 * upgrades * upgrades}$", 761, 540);
        }
    }

//    public void setShopButtonVisibility(boolean visible) {
//        shopButton.setVisible(visible);
//    }

    public void setMoneyGrowth(int newValue) {
        this.moneyGrowth = newValue;
    }

}
