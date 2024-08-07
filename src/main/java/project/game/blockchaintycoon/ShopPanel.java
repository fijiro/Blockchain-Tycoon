package project.game.blockchaintycoon;
/*
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShopPanel extends VBox {

    private Stage primaryStage;
    private Button buyAdButton = new Button("Buy Ad");
    private Button buyNodeButton = new Button("Buy Node");

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
        //Buying an ad gives a customer. Customer amount is limited to node amount.
        //Ad price is 40x customer amount
        buyAdButton.setOnAction(_ -> {
            if (GamePanel.money >= 40 * GamePanel.customers && GamePanel.nodes > GamePanel.customers) {
                GamePanel.money -= 40 * GamePanel.customers;
                GamePanel.customers++;
                updateRahojenKasvu();
            }
        });
        buyNodeButton.setOnAction(_ -> {
            if (GamePanel.money >= 10 * GamePanel.nodes) {
                GamePanel.money -= 10 * GamePanel.nodes;
                GamePanel.nodes++;
            }
        });

        VBox shopLayout = new VBox(10, shopTitle, closeButton, buyAdButton, buyNodeButton);
        shopLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");
        this.getChildren().add(shopLayout);
    }

    public void updateRahojenKasvu() {
        int defaultIncrease = 4;
        int customers = GamePanel.customers;
        // priceIncrease is defaultIncrease * customer amount. Customer amount is limited to node amount.
        GamePanel.moneyGrowth = defaultIncrease * customers;
    }
}
*/
