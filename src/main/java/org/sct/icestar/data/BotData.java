package org.sct.icestar.data;

import lombok.Getter;
import lombok.Setter;
import org.sct.icestar.listener.RepeatListener;
import org.sct.icestar.util.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotData {

    static {
        repeatMessage = new HashMap<String, Integer>();
        preventRepeat = new RepeatListener();
        listeners = new ArrayList<>();
        pool= Executors.newFixedThreadPool(5);
    }


    /*监听器列表*/
    @Getter private static List<Listener> listeners;

    /*复读消息计数*/
    @Getter private static Map<String,Integer> repeatMessage;

    /*上一条消息*/
    @Getter @Setter private static String lastMessage = "";

    /*投票模式*/
    @Getter @Setter private static Boolean vote = false;

    /*复读事件*/
    @Getter private static RepeatListener preventRepeat;

    /*线程池*/
    @Getter private static ExecutorService pool;

    /*使用机器人的群聊*/
    @Getter private static long[] groups = {895578145, 659124576, 558529644, 343234268, 659124576};
}
