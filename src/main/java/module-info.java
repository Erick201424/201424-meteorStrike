module com.concurrencia.meteorstrike {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.concurrencia.meteorstrike to javafx.fxml;
    exports com.concurrencia.meteorstrike;
    exports com.concurrencia.meteorstrike.controllers;
    opens com.concurrencia.meteorstrike.controllers to javafx.fxml;
}