import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

public class URLReader {

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public static void main(String[] args) throws Exception {
//        ArrayList urls = Homepage("https://www.bbc.com/");
//        ArrayList urls = Homepage("https://www.rtl.be/");
//        ArrayList urls = Homepage("https://www.foxnews.com/");
        ArrayList articles = Article("http://www.bbc.com/future/story/20190306-would-you-want-to-stay-in-a-space-hotel");
        for(int i=0;i<articles.size();++i){
            System.out.println(articles.toArray()[i].toString());
            System.out.println();
        }
//        for(int i=0;i<urls.size();++i){
//            System.out.println(urls.toArray()[i].toString());
//        }
    }

    /** Lit le url(s) donné(es) et renvoie les articles qui les correspendent
     * @param url
     * @return results(Articles sous forme de liste)
     * @throws Exception
     */
    private static ArrayList Article(String url) throws Exception {
        String[] urls = url.split(" ");
        ArrayList results = new ArrayList();
        for(int i=0;i<urls.length;++i) {
            String result = "";
            URL oracle = new URL(urls[i]);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("<title>")) {
                    int startIndex = inputLine.indexOf("<title>");
                    int endIndex = inputLine.indexOf("</title>");
                    result = "Title: "+inputLine.substring(startIndex + 7, endIndex);
                }
                if (inputLine.contains("<p>")) {
                    result += inputLine;
                }
            }
            in.close();
            result = result.replaceAll("<p>", "\n");
            while (result.contains("<")) {
                int startIndex = result.indexOf("<");
                int endIndex = result.indexOf(">");
                String replacement = "";
                String toBeReplaced = result.substring(startIndex, endIndex + 1);
                result = result.replace(toBeReplaced, replacement);
            }
            result = result.trim();
            result = result.replaceAll("        ", " ");
            results.add(result);
        }
        return results;
    }

    /** Prend comme parametre un site web et renvoie une liste avec les urls 
     * @param url
     * @return Liste de url
     * @throws Exception
     */
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
                if(!(temp.contains("jpg") || temp.contains("gif") || temp.contains("png") || temp.contains("htm") ||
                        temp.contains("js") || temp.contains("css") || temp.charAt(temp.length()-1) == ',' ||
                        temp.charAt(temp.length()-1) == '/' || temp.charAt(temp.length()-1) == ')' ||
                        temp.charAt(temp.length()-1) == ';' || temp.charAt(temp.length()-1) == '\'' ||
                        result.contains(temp)) && temp.contains("www") && temp.contains("-")){
                    result.add(temp);
                }
            }
        }
        return result;
    }

}
