import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
        String url = URL("https://www.bbc.com/");
        System.out.print(url);
    }

    private static String URL(String url) throws Exception {
        String result="";
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("href=\"https") && inputLine.contains("news")){
                result += inputLine+"\n";
            }
        }
        in.close();
        return result;
    }

}
