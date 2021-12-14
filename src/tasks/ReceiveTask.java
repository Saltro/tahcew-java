package tasks;

import javafx.concurrent.Task;
import network.Session;
import network.SessionStatus;
import utils.Log;

public class ReceiveTask extends Task<Void> {
    private Session session;

    public ReceiveTask(Session session) {
        this.session = session;
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        Log.info("接收线程退出");
    }

    @Override
    protected Void call() throws Exception {
        Log.info("接收线程开始工作");
        while (session.getStatus() == SessionStatus.SESSION_BUSY) {
            String message = session.receiveMessage();
            if (message.length() > 0) {
                updateMessage(message);
            }
        }
        return null;
    }
}
