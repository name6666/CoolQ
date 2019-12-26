package org.sct.icestar.listener;

import com.alibaba.fastjson.JSONObject;
import org.sct.icestar.Main;
import org.sct.icestar.util.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BilibiliListener implements Listener {
    @Override
    public boolean execute(long fromGroup, long fromQQ, String msg) throws IOException {

            if (msg.contains("www.bilibili.com/video")) {
                String av = msg.split("/")[4].replace("av", "");
                URL url = new URL("https://api.bilibili.com/x/web-interface/view?aid=" + av);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3 * 1000);
                conn.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String line = reader.readLine();
                conn.disconnect();
                JSONObject dataObject = JSONObject.parseObject(line).getJSONObject("data");

                String title = dataObject.getString("title");
                String description = dataObject.getString("desc");
                String UP = dataObject.getJSONObject("owner").getString("name");
                String uplink = dataObject.getJSONObject("owner").getString("mid");
                String zone = dataObject.getString("tname");
                JSONObject statObject = dataObject.getJSONObject("stat");
                String view = statObject.getString("view");
                String Barrage = statObject.getString("danmaku");
                String reply = statObject.getString("reply");
                String fav = statObject.getString("favorite");
                String coin = statObject.getString("coin");
                String share = statObject.getString("share");
                String like = statObject.getString("like");
                String desc = dataObject.getString("desc");

                Main.CQ.sendGroupMsg(fromGroup, title + "\nUP: " + UP + "(https://space.bilibili.com/" + uplink + ")\n分区: "
                        + zone + "\n播放量: " + view + " 弹幕: " + Barrage + " 评论: "
                        + reply + "\n收藏: " + fav + " 投币: " + coin + " 分享: " + share + " 点赞: " + like + "\n" + desc);
            }
            return true;
    }
}
