package project.game.blockchaintycoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene gameScene;
    //private static Scene shopScene;
    private static Scene helpScene;

    @Override
    public void start(Stage primaryStage) {
        GamePanel gamePanel = new GamePanel(primaryStage);
        gameScene = new Scene(gamePanel, gamePanel.screenWidth, gamePanel.screenHeight);

        //ShopPanel shopPanel = new ShopPanel(primaryStage);
        //shopScene = new Scene(shopPanel, gamePanel.screenWidth, gamePanel.screenHeight);

        ImageView startBg = new ImageView(new Image("start.png"));
        ImageView upImage = new ImageView(new Image("newgamebutton.png"));
        ImageView loreImage = new ImageView(new Image("loretesti.png"));
        ImageView startImage = new ImageView(new Image("startbutton.png"));
        ImageView tutorialImage = new ImageView(new Image("ohjeetdemo.png"));
        ImageView xButtonImage = new ImageView(new Image("xnappi.png"));

        Button xButton = new Button();
        xButton.setLayoutX(800);
        xButton.setLayoutY(5);
        xButton.setGraphic(xButtonImage);
        xButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        xButton.setOnAction(_ -> primaryStage.setScene(gameScene));

        Pane tutorialPane = new Pane();
        tutorialPane.getChildren().add(tutorialImage);
        tutorialPane.getChildren().add(xButton);
        helpScene = new Scene(tutorialPane, 900, 700);

        Button startGameButton = new Button();

        Pane lorePane = new Pane(loreImage, startGameButton);
        Scene loreScene = new Scene(lorePane, 900, 700); // scene lorelle

        startGameButton.setLayoutX(625);
        startGameButton.setLayoutY(350);
        startGameButton.setGraphic(startImage);
        startGameButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        startGameButton.setOnAction(_ -> {
            primaryStage.setScene(gameScene);
            gamePanel.setMoneyGrowth(4);  // aloittaa rahojen kasvun
        });

        Button newGameButton = new Button();
        newGameButton.setLayoutX(270);
        newGameButton.setLayoutY(380);
        newGameButton.setGraphic(upImage);
        newGameButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        newGameButton.setOnAction(_ -> primaryStage.setScene(loreScene));

        Pane startPane = new Pane();
        startPane.getChildren().add(startBg);
        startPane.getChildren().add(newGameButton);
        Scene aloitusScene = new Scene(startPane, 900, 700);

        primaryStage.setTitle("Blockchain Tycoon");
        primaryStage.getIcons().add(new Image("applicationicon.png"));
        primaryStage.setScene(aloitusScene); // gameScene on oletuksena
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);  // Koko näytön tila mut emt tullaanko käyttämään
        primaryStage.setOnCloseRequest(_ -> System.exit(0));
        primaryStage.show();

        gamePanel.startGameThread();
    }

//    public static Scene getGameScene() {
//        return gameScene;
//    }

//    public static Scene getShopScene() {
//        return shopScene;
//    }
    public static Scene getHelpScene() { return helpScene; }

    public static void main(String[] args) {
        launch(args);
    }
}
