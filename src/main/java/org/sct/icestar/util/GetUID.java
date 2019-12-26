package org.sct.icestar.util;

import org.sct.icestar.Main;

public class GetUID {
    public static long getUID(String str) {
        try {
            return Long.valueOf(str);
        }catch(Exception ex) {
            try {
                return Main.CC.getAt(str);
            }catch(Exception ex2) {
                return -1;
            }
        }
    }
}
