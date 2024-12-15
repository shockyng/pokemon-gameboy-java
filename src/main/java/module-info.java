module me.shockyng.pokemongameboyjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens me.shockyng.pokemongameboyjava to javafx.fxml;
    exports me.shockyng.pokemongameboyjava;
}