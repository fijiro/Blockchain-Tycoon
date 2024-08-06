package project.game.blockchaintycoon;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShopPanel extends VBox {

    private Stage primaryStage;

    public ShopPanel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeShop();
    }

    private void initializeShop() {
        Label shopTitle = new Label("Shop");
        shopTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            primaryStage.setScene(Main.getGameScene());
            // Näytä kaupan nappi uudelleen kaupan sulkemisen jälkeen
            //((GamePanel) ((Scene) primaryStage.getScene()).getRoot()).setShopButtonVisibility(true);
        });

        // LISÄÄ TÄHÄN NAPIT JA TOIMINNOT

        VBox shopLayout = new VBox(10, shopTitle, closeButton);
        shopLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");
        this.getChildren().add(shopLayout);
    }
}

