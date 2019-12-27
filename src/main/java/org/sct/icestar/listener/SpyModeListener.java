package org.sct.icestar.listener;

import org.sct.icestar.Main;
import org.sct.icestar.data.BotData;
import org.sct.icestar.util.JudgeEnable;
import org.sct.icestar.util.Listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class SpyModeListener implements Listener {
    @Override
    public boolean execute(long fromGroup, long fromQQ, String msg) throws IOException {


        if (!JudgeEnable.judegeEnable(fromGroup, fromQQ, "runcommand")) {
            return false;
        }

        /*如果包含/spy命令*/
        if (msg.contains("/spy")) {
            BotData.getPool().submit(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 1234);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);

                    /*启用情况*/
                    if (msg.equals("/spy on")) {
                        String base64 = Base64.getEncoder().encodeToString(msg.getBytes());
                        writer.println(base64);
                        writer.flush();

                        Scanner scanner = new Scanner(inputStream);
                        while (scanner.hasNextLine()) {
                            String chatString = scanner.nextLine();
                            Main.CQ.sendGroupMsg(fromGroup, chatString);
                        }
                    }

                    if (msg.equals("/spy off")) {
                        String base64 = Base64.getEncoder().encodeToString(msg.getBytes());
                        writer.println(base64);
                        /*断开连接*/
                        writer.println("IWNsb3NlIQ==");
                        writer.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }


        return true;
    }
}
