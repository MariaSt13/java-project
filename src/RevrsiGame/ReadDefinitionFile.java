package RevrsiGame;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class opens a definition file
 * and creates a map of definitions.
 */
public class ReadDefinitionFile {

    private Map<String, String> map;

    /**
     * constructor.
     */
    public ReadDefinitionFile() {
        this.map = new TreeMap<>();
    }

    /**
     * This function gets a definition file name
     * and creates a map of definitions.
     * Return empty map if there is no file.
     * @param fileName - name of the definition file.
     * @return map of definitions.
     */
    public Map<String, String> readFile(String fileName) {
        BufferedReader is;
        try {
            is = new BufferedReader( new InputStreamReader( new FileInputStream(fileName)));
            String line;

            //read a line while the end of the stream has not been reached.
            while ((line = is.readLine()) != null) {
                String s[] = line.split(":");
                map.put(s[0].trim(),s[1].trim());
            }

        //return empty map if there is no file
        } catch (IOException e) {
            map.clear();
            return map;
        }
        return map;
    }
}
