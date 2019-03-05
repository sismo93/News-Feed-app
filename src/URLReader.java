import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
//        String url = URL("https://www.bbc.com/");
        String url = URL("https://www.bbc.com/news/health-47440622");
        url = Read(url);
        System.out.print(url);
    }

    private static String Read(String text) {
        String result = text;
        result = result.replaceAll("<p>", "\n");
        while(result.contains("<")){
            int startIndex = result.indexOf("<");
            int endIndex = result.indexOf(">");
            String replacement = "";
            String toBeReplaced = result.substring(startIndex, endIndex+1);
            result = result.replace(toBeReplaced, replacement);
        }
        result = result.trim();
        result = result.replaceAll("        ", " ");
        return result;
    }

    private static String URL(String url) throws Exception {
        String result="";
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
//            if(inputLine.contains("href=\"https") && inputLine.contains("news")){
            if(inputLine.contains("<p>")){
                result += inputLine;
            }
//            }
        }
        in.close();
        return result;
    }

}
