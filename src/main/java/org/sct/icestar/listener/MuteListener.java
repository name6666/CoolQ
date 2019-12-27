package org.sct.icestar.listener;

import org.sct.icestar.Main;
import org.sct.icestar.data.BotData;
import org.sct.icestar.enumeration.PathType;
import org.sct.icestar.util.GetText;
import org.sct.icestar.util.JudgeEnable;
import org.sct.icestar.util.Listener;
import org.sct.icestar.util.GetUID;

import java.io.IOException;

public class MuteListener implements Listener {

    @Override
    public boolean execute(long fromGroup, long fromQQ, String msg) throws IOException {

       if (!JudgeEnable.judegeEnable(fromGroup, fromQQ, "mute")) {
           return false;
       }

        /*格式:/mute qq号 时间*/
        String[] strings = null;
        if (msg.contains("/mute")) {
            strings = msg.split(" ");
            if (strings.length != 3) {
                return false;
            }
            if (!strings[1].contains("CQ:at")) {
                Main.CQ.setGroupBan(fromGroup, Long.parseLong(strings[1]), Long.parseLong(strings[2]));
            } else {
                Main.CQ.setGroupBan(fromGroup, GetUID.getUID(strings[1]), Long.parseLong(strings[2]));
            }
        }
        return true;
    }
}
