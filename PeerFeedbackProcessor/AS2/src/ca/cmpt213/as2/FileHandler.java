package ca.cmpt213.as2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class FileHandler {
    // TODO: recursive searching
    public static Gson gson = new Gson();
    public static List<String> files = new ArrayList<>();

    private static void search(File f) {
        if (f.isDirectory()) {
            for (File temp : f.listFiles()) search(temp);
        } else if (jsonFilter(f)) {
            files.add(f.getAbsolutePath());
        }
    }

    private static boolean jsonFilter(File f) {
        return f.getName().endsWith(".json");
    }

    public static List<String> getFiles(String path) {
        File folder = new File(path);

        search(folder);

        if (files.size() == 0) ErrorHandler.error("No JSON files found!");

        return files;
    }

    public static Feedback readJSONFile(String filePath) {
        File file = new File(filePath);
        try {
            Reader reader = new FileReader(file.getAbsolutePath());
            Feedback res = gson.fromJson(reader, Feedback.class);

            if (!res.hasValidData()) ErrorHandler.error("Invalid fields in JSON in file: " + file.getName() + " at " + file.getAbsolutePath());

            return res;

        } catch (FileNotFoundException e) {
            ErrorHandler.error("Could not open file: " + file.getName() + " at " + file.getAbsolutePath());
        }
        return null;
    }

    public static String findBetween(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        String result = "";
        while (matcher.find()) {
            result = matcher.group(1).trim();
        }
        return result;
    }
}
