package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import network.Session;
import tasks.ReceiveTask;
import utils.Log;

public class IndexController {
    private Session session;
    private ReceiveTask task;

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


        new Thread(task).start();
    }

    public void setTask(ReceiveTask task) {
        this.task = task;
        task.messageProperty().addListener((observable, oldValue, newValue) -> interpret(newValue));
    }

    @FXML
    private void onChangeRoomBtn() {
        String roomName = roomInput.getText();
        if (session.sendMessage("join/" + roomName)) {
            roomInput.setText("");
        }
    }

    @FXML
    private void onBackToSquareBtn() {
        session.sendMessage("join/广场");
    }

    @FXML
    private void onSendMessageBtn() {
        if (session.sendMessage("chat/" + messageInput.getText())) {
            messageInput.setText("");
        }
    }

    private void interpret(String message) {
        String[] args = message.split("/");
        switch (args[0]) {
            case "msg":
                showChat(args[1], args[2]);
                break;
            case "room":
                showRoomTitle(args[1]);
        }
    }

    private void showChat(String user, String chat) {
        messageArea.setText(messageArea.getText() + Log.getTime() + "：" + user + "：" + chat + "\n");
    }

    private void showRoomTitle(String title) {
        roomTitle.setText("聊天室 " + title);
    }
}
