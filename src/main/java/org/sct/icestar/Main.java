package org.sct.icestar;

import com.sobte.cqp.jcq.entity.*;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import org.sct.icestar.data.BotData;
import org.sct.icestar.enumeration.PathType;
import org.sct.icestar.manager.FIleManager;
import org.sct.icestar.manager.ListenerManager;

import java.io.IOException;

public class Main extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    public static void main(String[] args) throws IOException {
        CQ = new CQDebug();
        Main bot = new Main();
        bot.startup();
        bot.enable();
        bot.exit();
    }


    @Override
    public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
        return MSG_IGNORE;
    }

    @Override
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {


        ListenerManager.callEvent(fromGroup, fromQQ, msg);
        return MSG_IGNORE;
    }


    @Override
    public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg, String responseFlag) {
        /*
         * REQUEST_ADOPT 通过
         * REQUEST_REFUSE 拒绝
         * REQUEST_GROUP_ADD 群添加
         * REQUEST_GROUP_INVITE 群邀请
         */
		if(subtype == 1){
		    CQ.sendGroupMsg(fromGroup, msg);
		    BotData.setVote(true);
            CQ.sendGroupMsg(fromGroup, "投票模式已启动");
			//CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_ADD, REQUEST_ADOPT, null);// 同意入群
		}
        return MSG_IGNORE;
    }

    @Override
    public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        return MSG_IGNORE;
    }

    @Override
    public int discussMsg(int i, int i1, long l, long l1, String s, int i2) {
        return 0;
    }

    @Override
    public int groupUpload(int i, int i1, long l, long l1, String s) {
        return 0;
    }

    @Override
    public int groupAdmin(int i, int i1, long l, long l1) {
        return 0;
    }

    @Override
    public int groupMemberIncrease(int i, int i1, long l, long l1, long l2) {
        return 0;
    }

    @Override
    public int friendAdd(int i, int i1, long l) {
        return 0;
    }

    @Override
    public int requestAddFriend(int i, int i1, long l, String s, String s1) {
        return 0;
    }

    @Override
    public String appInfo() {
        String AppID = "org.sct.icestar";
        return CQAPIVER + "," + AppID;
    }

    @Override
    public int startup() {
        String appDirectory = CQ.getAppDirectory();
        return 0;
    }

    @Override
    public int exit() {
        return 0;
    }

    @Override
    public int enable() {
        enable = true;

        /*注册监听器*/
        if (BotData.getListeners().size() == 0) {
            ListenerManager.registerListener();
        }

        /*创建目录*/
        try {
            FIleManager.createFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int disable() {
        enable = false;
        return 0;
    }
}
