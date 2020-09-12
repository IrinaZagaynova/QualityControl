package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static String test(ArrayList<String> args, String expected) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(args);
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();

        if (line.equals(expected)) {
            return "sucсess\n";
        }
        else {
            return "error\n";
        }
    }

    public static void main(String[] args) {
        try {
            File input = new File("tests.txt");
            FileReader fr = new FileReader(input);

            File output = new File("result.txt");
            FileWriter fw = new FileWriter(output);

            BufferedReader br = new BufferedReader(fr);
            String line, expected;
            while ((line = br.readLine()) != null && (expected = br.readLine()) != null) {
                ArrayList<String> list = new ArrayList<>();
                list.add("triangle.exe");
                Collections.addAll(list, line.split(" "));
                fw.write(test(list, expected));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
