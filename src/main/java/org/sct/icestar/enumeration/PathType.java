package org.sct.icestar.enumeration;

import lombok.Getter;
import org.sct.icestar.Main;

/**
 * @author icestar
 */

public enum PathType {

    /*数据文件存放路径*/
    Files_PAths(Main.CQ.getAppDirectory() + "/org.sct.icestar");



    @Getter String path;

    private PathType(String path) {
        this.path = path;
    }
}
