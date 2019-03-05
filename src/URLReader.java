import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class URLReader {

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public static void main(String[] args) throws Exception {
        ArrayList urls = Homepage("https://www.bbc.com/");
//        String url = Article("https://www.bbc.com/news/health-47440622");
//        System.out.print(url);
        for(int i=0;i<urls.size();++i){
            System.out.println(urls.toArray()[i]);
        }
    }

    private static String Article(String url) throws Exception {
        String result="";
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("<p>")){
                result += inputLine;
            }
        }
        in.close();
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

    private static ArrayList Homepage(String url) throws Exception {

        ArrayList result = new ArrayList();
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            Matcher matcher = urlPattern.matcher(inputLine);
            while (matcher.find()) {
                int matchStart = matcher.start(1);
                int matchEnd = matcher.end();
                // now you have the offsets of a URL match
                String temp = inputLine.substring(matchStart, matchEnd);
                if(!(temp.contains("jpg") || temp.contains("gif") || temp.contains("png") || temp.contains("js") || temp.contains("css")) && temp.contains("www")){
                    result.add(temp);
                }
            }
        }
        return result;
    }

}
