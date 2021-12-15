import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import network.Session;
import tasks.ReceiveTask;
import utils.Log;

public class IndexController {
    private Session session;

    @FXML
    private Label roomTitle;

    @FXML
    private TextField roomInput;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextArea inputArea;

    @FXML
    private VBox roomList;

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTask(ReceiveTask task) {
        task.messageProperty().addListener((observable, oldValue, newValue) -> interpret(newValue));
    }

    @FXML
    private void onRoomInput() {
        String roomName = roomInput.getText();
        if (roomName.length() > 0) {
            changeRoom(roomName);
        }
        roomInput.setText("");
    }

    @FXML
    private void onInputKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && event.isShiftDown() && inputArea.getText().trim().length() > 0) {
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
                addRoomNameIntoList(args[1]);
        }
    }

    private void showChat(String user, String chat) {
        messageArea.appendText("[ " + Log.getTime() + " ] " + user + "：" + chat + "\n");
    }

    private void showRoomTitle(String title) {
        roomTitle.setText(title);
    }

    private void changeRoom(String roomName) {
        if (!roomName.equals(roomTitle.getText())) {
            session.sendMessage("join/" + roomName);
        }
    }

    private void addRoomNameIntoList(String name) {
        for (Node node: roomList.getChildren()) {
            if (node instanceof HBox && ((Label) ((HBox) node).getChildren().get(0)).getText().equals(name)) {
                return;
            }
        }

        HBox box = new HBox();
        box.setStyle("-fx-padding: 5 12; -fx-border-width: 0 0 1 0; -fx-border-color: #E0E0E0;");
        box.setOnMouseClicked((e) -> changeRoom(name));

        Label room = new Label(name);
        box.getChildren().add(room);

        roomList.getChildren().add(box);
    }
}
