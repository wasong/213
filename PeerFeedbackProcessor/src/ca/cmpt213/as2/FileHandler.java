package ca.cmpt213.as2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class FileHandler {
    // TODO: recursive searching
    public static Gson gson = new Gson();

    public static File[] getFiles(String path) {
        File filePath = new File(path);
        if (!filePath.isDirectory()) {
            throw new Error("Invalid folder path");
        }
        return filePath.listFiles((f) -> f.getName().endsWith(".json"));
    }

    public static Feedback readJSONFile(File file) {
        try {
            Reader reader = new FileReader(file.getAbsolutePath());
            return gson.fromJson(
                    reader,
                    Feedback.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
