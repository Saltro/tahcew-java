package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IndexController {
    @FXML
    private Label roomTitle;

    @FXML
    private TextField roomInput;

    @FXML
    private Button changeRoomBtn;

    @FXML
    private Button backToSquareBtn;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField messageInput;

    @FXML
    private Button sendMessageBtn;
}
