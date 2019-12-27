package org.sct.icestar.listener;

import org.sct.icestar.Main;
import org.sct.icestar.data.BotData;
import org.sct.icestar.util.JudgeEnable;
import org.sct.icestar.util.Listener;

import java.io.IOException;

public class RepeatListener implements Listener {
    @Override
    public boolean execute(long fromGroup, long fromQQ, String msg) throws IOException {

        if (!JudgeEnable.judegeEnable(fromGroup, fromQQ, "runcommand")) {
            return false;
        }

        /*如果上一条消息不为空,且与现有消息不同,返回*/
        if (!BotData.getLastMessage().equalsIgnoreCase(msg)) {
            BotData.setLastMessage(msg);
            return false;
        }


        /*如果map中不存在此消息,将此消息设为1*/
        if (BotData.getRepeatMessage().get(msg) == null) {
            BotData.getRepeatMessage().put(msg,1);
        } else {
            int num = BotData.getRepeatMessage().get(msg);
            BotData.getRepeatMessage().put(msg,num+1);



            /*如果次数超过5*/
            if (num >= 5) {
                Main.CQ.sendGroupMsg(fromGroup, "你已涉嫌复读");
            }


        }







        return false;
    }
}
