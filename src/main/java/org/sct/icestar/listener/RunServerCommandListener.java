package org.sct.icestar.listener;

import org.sct.icestar.Main;
import org.sct.icestar.data.BotData;
import org.sct.icestar.util.Listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class RunServerCommandListener implements Listener {

    @Override
    public boolean execute(long fromGroup, long fromQQ, String msg) {

        if (fromGroup == 895578145 ||fromGroup == 343234268) {

            if (msg.contains("/runservercommand")) {
                if (fromQQ != 625924077 && fromQQ != 2122723273) {
                    Main.CQ.sendGroupMsg(fromGroup, "你没有此命令的权限!");
                    return false;
                }


                /*新建线程*/
                BotData.getPool().submit(() -> {
                    try {
                        Socket socket = new Socket("127.0.0.1", 1234);
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        PrintWriter writer = new PrintWriter(outputStream, true);
                        String base64 = Base64.getEncoder().encodeToString(msg.getBytes());

                        writer.println(base64);

                        Scanner scanner = new Scanner(inputStream);
                        while (scanner.hasNextLine()) {
                            String response = scanner.nextLine();
                            Main.CQ.sendGroupMsg(fromGroup, response);

                            /*断开连接*/
                            writer.println("IWNsb3NlIQ==");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            }
        }


        return true;
    }


}
