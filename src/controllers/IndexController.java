package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import network.Session;

public class IndexController {
    private Session session;

    @FXML
    private Label roomTitle;

    @FXML
    private TextField roomInput;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField messageInput;

    public void setSession(Session session) {
        this.session = session;
    }

    @FXML
    private void onChangeRoomBtn() {
        String roomName = roomInput.getText();
        if (session.sendMessage("join/" + roomName)) {
            roomTitle.setText("聊天室 " + roomName);
            roomInput.setText("");
        }
    }

    @FXML
    private void onBackToSquareBtn() {
        if (session.sendMessage("join/广场")) {
            roomTitle.setText("聊天室 广场");
        }
    }

    @FXML
    private void onSendMessageBtn() {
        if (session.sendMessage("chat/" + messageInput.getText())) {
            messageInput.setText("");
        }
        messageArea.setText(messageArea.getText() + "\n" + session.receiveMessage());
    }
}
