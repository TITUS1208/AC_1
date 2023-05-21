package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UsernamePassword {
    private HashMap<String, String> username_pw = new HashMap<>();

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
            if (i % 2 == 0) {
                username_pw.put(fileLines.get(i), fileLines.get(i + 1));
            }
        }
        // System.out.println(getUsername_pw());
    }

    public void setUsername_pw(String username, String pw) {
        username_pw.put(username, pw);
    }

    public HashMap<String, String> getUsername_pw() {
        return username_pw;
    }
}
