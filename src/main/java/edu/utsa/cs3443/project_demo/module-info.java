module edu.utsa.cs3443.project_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens edu.utsa.cs3443.project_demo to javafx.fxml;
    opens edu.utsa.cs3443.project_demo.controller to javafx.fxml;
    opens edu.utsa.cs3443.project_demo.model to javafx.fxml;

    exports edu.utsa.cs3443.project_demo;
    exports edu.utsa.cs3443.project_demo.controller;
    exports edu.utsa.cs3443.project_demo.model;
}