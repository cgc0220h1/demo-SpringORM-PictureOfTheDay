package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommentUtil {

    private CommentUtil() {
    }

    public static String getBadWords() throws IOException {
        File wordList = new File("word-list.txt");
        if (!wordList.exists()) {
            System.out.println(wordList.createNewFile());
        }
        System.out.println(wordList.exists());
        String badWords = "";
        return badWords;
    }
}
