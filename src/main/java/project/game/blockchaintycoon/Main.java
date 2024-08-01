package project.game.blockchaintycoon;

import javafx.application.Application;
import javafx.scene.Scene;
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

        primaryStage.setTitle("Blockchain Tycoon");
        primaryStage.setScene(gameScene);
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
