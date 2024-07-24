module project.game.blockchaintycoon {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.game.blockchaintycoon to javafx.fxml;
    exports project.game.blockchaintycoon;
}