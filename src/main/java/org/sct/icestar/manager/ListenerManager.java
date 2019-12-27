package org.sct.icestar.manager;

import org.sct.icestar.listener.*;
import org.sct.icestar.data.BotData;
import org.sct.icestar.util.Listener;

import java.io.IOException;

public class ListenerManager {

    public static void registerListener() {
        Listener[] listeners = {new MuteListener(), new RepeatListener(), new ShowDocListener(),
                new RunServerCommandListener(), new SpyModeListener(), new BilibiliListener(),
        new ShowPlayerListListener()};
        for (Listener listener : listeners) {
            BotData.getListeners().add(listener);
        }
    }

    public static void callEvent(long fromGroup, long fromQQ, String msg) {
        for (Listener listener : BotData.getListeners()) {
            try {
                listener.execute(fromGroup, fromQQ, msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
