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
    private static Scene shopScene;

    @Override
    public void start(Stage primaryStage) {
        GamePanel gamePanel = new GamePanel(primaryStage);
        gameScene = new Scene(gamePanel, gamePanel.screenWidth, gamePanel.screenHeight);

        ShopPanel shopPanel = new ShopPanel(primaryStage);
        shopScene = new Scene(shopPanel, gamePanel.screenWidth, gamePanel.screenHeight);




        Image valikko = new Image("start.png");
        ImageView startbg = new ImageView(valikko);

        Image upnappi = new Image("newgamebutton.png");
        ImageView up = new ImageView(upnappi);

        Image loreteksti = new Image("loretesti.png");
        ImageView lore = new ImageView(loreteksti);

        Image startnappi = new Image("startbutton.png");
        ImageView start = new ImageView(startnappi);

        Button aloita_peli = new Button();

        Pane lorepane = new Pane();
        lorepane.getChildren().add(lore);
        lorepane.getChildren().add(aloita_peli);
        Scene loreScene = new Scene(lorepane, 900, 700); // scene lorelle


        Button uusi_peli = new Button();
        uusi_peli.setLayoutX(270);
        uusi_peli.setLayoutY(380);
        uusi_peli.setGraphic(up);
        uusi_peli.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        uusi_peli.setOnAction(event -> {
            primaryStage.setScene(loreScene);
        });

        aloita_peli.setLayoutX(325);
        aloita_peli.setLayoutY(450);
        aloita_peli.setGraphic(start);
        aloita_peli.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        aloita_peli.setOnAction(event -> {
            primaryStage.setScene(gameScene);
            gamePanel.setRahojenKasvu(4);  // aloittaa rahojen kasvun
        });



        Pane aloitus = new Pane();
        aloitus.getChildren().add(startbg);
        aloitus.getChildren().add(uusi_peli);
        Scene aloitusScene = new Scene(aloitus, 900, 700);



        primaryStage.setTitle("Blockchain Tycoon");
        primaryStage.setScene(aloitusScene); // gameScene on oletuksena
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);  // Koko näytön tila mut emt tullaanko käyttämään
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        primaryStage.show();


        gamePanel.startGameThread();


    }

    public static Scene getGameScene() {
        return gameScene;
    }

    public static Scene getShopScene() {
        return shopScene;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
