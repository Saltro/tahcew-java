import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import network.Session;
import tasks.ReceiveTask;
import utils.Log;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    private Session session;

    @FXML
    private Label roomTitle;

    @FXML
    private TextField roomInput;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextArea inputArea;

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTask(ReceiveTask task) {
        task.messageProperty().addListener((observable, oldValue, newValue) -> interpret(newValue));
    }

    @FXML
    private void onChangeRoomBtn() {
        String roomName = roomInput.getText();
        if (roomName.length() > 0 && session.sendMessage("join/" + roomName)) {
            roomInput.setText("");
        }
    }

    @FXML
    private void onInputKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && inputArea.getText().trim().length() > 0) {
            if (session.sendMessage("chat/" + inputArea.getText())) {
                inputArea.setText("");
            }
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
        roomTitle.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageArea.setEffect(null);
        inputArea.setEffect(null);
    }
}
