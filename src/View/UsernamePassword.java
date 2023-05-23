package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UsernamePassword {
    private HashMap<String, String> username_pw = new HashMap<>();
    private HashMap<String, String> username_score = new HashMap<>();

    private List<String> fileLines;

    public UsernamePassword() throws FileNotFoundException {
        fileLines = new ArrayList<String>();
        File file = new File("src\\User\\users.txt");
        try (Scanner scanner = new Scanner(file)) {
            String str;
            while (scanner.hasNextLine()) {
                str = scanner.next();
                fileLines.add(str);
            }
        }
        // System.out.println("fileslines arraylist:" + fileLines);
        for (int i = 0; i < fileLines.size(); i++) {
            if (i % 3 == 0) {
                username_pw.put(fileLines.get(i), fileLines.get(i + 1));
            }
        }
        for (int i = 0; i < fileLines.size(); i++) {
            if (i % 3 == 0) {
                username_score.put(fileLines.get(i), fileLines.get(i + 2));
            }
        }

        username_score = sortByScore(username_score);
        // System.out.println(getUsername_pw());
        // System.out.println(getUsername_score());
    }

    public HashMap<String, String> sortByScore(HashMap<String, String> username_score) {
        LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : username_score.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str1).compareTo(str);
            }
        });
        for (String str : list) {
            for (java.util.Map.Entry<String, String> entry : username_score.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sortedMap.put(entry.getKey(), str);
                }
            }
        }
        // System.out.println(sortedMap);
        return sortedMap;
    }

    public void setUsername_pw(String username, String pw) {
        username_pw.put(username, pw);
    }

    public HashMap<String, String> getUsername_pw() {
        return username_pw;
    }

    public void setUsername_score(String username, String score) {
        username_pw.put(username, score);
    }

    public HashMap<String, String> getUsername_score() {
        return username_score;
    }
}
