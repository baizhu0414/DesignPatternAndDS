package DS;

import java.io.*;

public class InputFaster {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        for (String str : firstLine) {
            System.out.println(str);
        }
    }

}
