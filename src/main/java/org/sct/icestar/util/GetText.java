package org.sct.icestar.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetText {

    public static List<Long> getUserList(String filepath) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(filepath));
        List<Long> userList = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            userList.add(Long.parseLong(line));
        }
        return userList;
    }

    public static boolean getEnable(String filepath) throws IOException{
        Scanner scanner = new Scanner(new FileReader(new File(filepath)));
        if ("true".equalsIgnoreCase(scanner.nextLine())) {
            return true;
        } else {
            return false;
        }
    }
}
