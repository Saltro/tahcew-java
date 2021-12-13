package network;

import utils.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Session {
    private final String SERVER_NAME = "127.0.0.1";
    private final int PORT = 3456;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Session() {
        Log.info("尝试启动套接字");
        try {
            socket = new Socket(SERVER_NAME, PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            Log.info("套接字启动成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("套接字启动失败");
        }
    }

    public boolean sendMessage(String message) {
        Log.info("尝试发送消息 " + message);
        try {
            // 合并为一个 360 长度的字符数组
            byte[] msg = new byte[360];
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            System.arraycopy(messageBytes, 0, msg, 0, messageBytes.length);
            out.write(msg);
            Log.info("发送成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("发送失败");
            return false;
        }
    }

    public String receiveMessage() {
        Log.info("尝试接收消息");
        try {
            byte[] message = new byte[360];
            in.read(message);
            String msg = new String(message).trim();
            Log.info("接收成功 " + msg);
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("接收失败");
            return "";
        }
    }

    public void close() {
        Log.info("尝试关闭套接字");
        try {
            socket.close();
            Log.info("套接字关闭成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.info("套接字关闭失败");
        }
    }
}
