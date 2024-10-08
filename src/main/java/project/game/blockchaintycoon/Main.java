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
    private static Scene winScene;
    private static Scene helpScene;

    @Override
    public void start(Stage primaryStage) {
        GamePanel gamePanel = new GamePanel(primaryStage);
        gameScene = new Scene(gamePanel, gamePanel.screenWidth, gamePanel.screenHeight);

        ImageView startBg = new ImageView(new Image("start.png"));
        ImageView upImage = new ImageView(new Image("newgamebutton.png"));
        ImageView loreImage = new ImageView(new Image("lorescreen.png"));
        ImageView startImage = new ImageView(new Image("startbutton.png"));
        startImage.setScaleY(0.5f);
        startImage.setScaleX(0.5f);
        ImageView tutorialImage = new ImageView(new Image("gameinstructions.png"));
        ImageView xButtonImage = new ImageView(new Image("xnappi.png"));

        Button xButton = new Button();
        xButton.setLayoutX(800);
        xButton.setLayoutY(5);
        xButton.setGraphic(xButtonImage);
        xButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        xButton.setOnAction(_ -> primaryStage.setScene(gameScene));

        Pane tutorialPane = new Pane();
        tutorialPane.getChildren().addAll(tutorialImage);
        tutorialPane.getChildren().add(xButton);
        helpScene = new Scene(tutorialPane, 900, 700);
        Button startGameButton = new Button();
        Pane lorePane = new Pane(loreImage, startGameButton);
        Scene loreScene = new Scene(lorePane, 900, 700);

        Button restartButton = new Button();
        restartButton.setLayoutX(500);
        restartButton.setLayoutY(450);
        restartButton.setGraphic(new ImageView(new Image("newgamebutton.png")));
        restartButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        restartButton.setOnAction(_ -> {
            primaryStage.setScene(gameScene);
            gamePanel.startGameThread();
        });
        Pane winPane = new Pane(new ImageView(new Image("congratulations.png")), restartButton);
        winScene = new Scene(winPane, 900, 700);

        startGameButton.setLayoutX(640);
        startGameButton.setLayoutY(580);
        startGameButton.setGraphic(startImage);
        startGameButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        startGameButton.setOnAction(_ -> {
            primaryStage.setScene(gameScene);
            gamePanel.startGameThread();
        });

        Button newGameButton = new Button();
        newGameButton.setLayoutX(270);
        newGameButton.setLayoutY(380);
        newGameButton.setGraphic(upImage);
        newGameButton.setStyle("-fx-background-color: transparent;" + "-fx-background-insets: 0;");
        newGameButton.setOnAction(_ -> primaryStage.setScene(loreScene));

        Pane startPane = new Pane();
        startPane.getChildren().add(startBg);
        startPane.getChildren().add(newGameButton);
        Scene aloitusScene = new Scene(startPane, 900, 700);

        primaryStage.setTitle("Blockchain Tycoon");
        primaryStage.getIcons().add(new Image("applicationicon.png"));
        primaryStage.setScene(aloitusScene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setOnCloseRequest(_ -> System.exit(0));
        primaryStage.show();
    }

    public static Scene getHelpScene() {return helpScene;}

    public static Scene getWinScene() {return winScene;}

    public static void main(String[] args) {
        launch(args);
    }
}
