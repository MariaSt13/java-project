package ReversiGameProject;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ReadDefinitionFile {

    private Map<String, String> map;

    public ReadDefinitionFile() {
        this.map = new TreeMap<>();
    }

    public Map<String, String> readFile(String fileName) {
        BufferedReader is;
        try {
            is = new BufferedReader( new InputStreamReader( new FileInputStream(fileName)));
            String line;
            while ((line = is.readLine()) != null) {
                String s[] = line.split(":");
                map.put(s[0].trim(),s[1].trim());
            }
        } catch (IOException e) {
            map.clear();
            return map;
        }
        return map;
    }
}
