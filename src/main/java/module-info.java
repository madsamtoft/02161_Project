module hellofx {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens ui to javafx.fxml; // Gives access to fxml files
    exports ui; // Exports the class inheriting from javafx.application.Application
}