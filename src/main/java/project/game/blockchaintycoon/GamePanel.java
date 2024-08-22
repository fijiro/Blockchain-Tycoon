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
    Timeline gameLoop;
    private double money = 1;
    private int moneyGrowth = 4; // money Growth per second
    private int customers = 1;
    private int nodes = 1;
    private final int nodesPerCustomer = 2;
    private int upgrades = 1;
    private final Canvas canvas;
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
        buyNodeButton.setLayoutX(163);
        buyNodeButton.setLayoutY(542);

        Button buyAdButton = new Button();
        buyAdButton.setOnAction(_ -> {
            if (money >= 40 * customers && nodes > customers * nodesPerCustomer) {
                money -= 40 * customers;
                customers++;
                moneyGrowth = 4 * customers;
                moneyGrowth += (upgrades * 10);
            }
        });
        buyAdButton.setGraphic(new ImageView(buyButtonImage));
        buyAdButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        buyAdButton.setLayoutX(383);
        buyAdButton.setLayoutY(542);

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
        buyPartsButton.setLayoutX(625);
        buyPartsButton.setLayoutY(542);

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

        Image winButtonImage = new Image("newgamebutton.png");
        Button winButton = new Button();
        winButton.setGraphic(new ImageView(winButtonImage));
        winButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        winButton.setLayoutX(50);
        winButton.setLayoutY(50);
        winButton.setOnAction(_ -> {
            Scene winScene = Main.getWinScene();
            stopGameThread();
            money = 1;
            moneyGrowth = 4;
            customers = 1;
            nodes = 1;
            upgrades = 1;
            primaryStage.setScene(winScene);
        });

        //Render all buttons to the screen
        this.getChildren().addAll(buyNodeButton, buyAdButton, buyPartsButton, helpButton, winButton);
    }

    public void startGameThread() {  // Main game loop. Updates every 0.1 seconds.
        gameLoop = new Timeline(new KeyFrame(Duration.millis(100), _ -> {
            update();
            render();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void stopGameThread() {
        gameLoop.stop();
    }

    public void update() { // Updates current money by its growth
        money += (double) moneyGrowth / 10;
    }

    public void render() { // Render prices for ads, nodes and upgrades to the screen.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, screenWidth, screenHeight);
        gc.setFill(Color.WHITE);
        gc.setFont(customFont);
        DecimalFormat f = new DecimalFormat("##.00");
        gc.fillText(STR."Money: \{f.format(money)}$", 270, 225);
        gc.fillText(STR."Customers: \{customers}", 270, 325);
        gc.fillText(STR."Nodes: \{nodes}", 270, 275);
        //Ad price rendering
        if (money >= 40 * customers && nodes > customers * nodesPerCustomer) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{40 * customers}$", 425, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{40 * customers}$", 425, 540);
        }
        //Node price rendering
        if (money >= 10 * nodes) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{10 * nodes}$", 211, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{10 * nodes}$", 211, 540);
        }
        //Upgrade price rendering
        if (money >= 100 * upgrades * upgrades) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText(STR."\{100 * upgrades * upgrades}$", 663, 540);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText(STR."\{100 * upgrades * upgrades}$", 663, 540);
        }
        if(money >= 1000) {
            gc.setFill(Color.GREEN);
            gc.setFont(customFont2);
            gc.fillText("1000$",200,50);
        }
        else {
            gc.setFill(Color.RED);
            gc.setFont(customFont2);
            gc.fillText("1000$",200,50);
        }
    }

//    public void setShopButtonVisibility(boolean visible) {
//        shopButton.setVisible(visible);
//    }

    public void setMoneyGrowth(int newValue) {
        this.moneyGrowth = newValue;
    }
}
