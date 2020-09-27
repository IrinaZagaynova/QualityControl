package lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TriangleTest {
    public static String test(ArrayList<String> args, String expected) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(args);
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();

        if (line.equals(expected)) {
            return "success\n";
        }
        else {
            return "error\n";
        }
    }

    public static void main(String[] args) {
        File input = new File("src/test/java/lab1/tests.txt");
        File output = new File("src/test/java/lab1/result.txt");
        try (FileWriter fw = new FileWriter(output)) {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            String line, expected;
            while ((line = br.readLine()) != null && (expected = br.readLine()) != null) {
                ArrayList<String> list = new ArrayList<>();
                list.add("src/test/java/lab1/triangle.exe");
                Collections.addAll(list, line.split(" "));
                fw.write(test(list, expected));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
