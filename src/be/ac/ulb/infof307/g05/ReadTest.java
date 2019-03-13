package be.ac.ulb.infof307.g05;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ReadTest {
    public static void main(String[] args) {







        RSSFeedParser parser = new RSSFeedParser(
                "https://www.lepoint.fr/24h-infos/rss.xml");
        Feed feed = parser.readFeed();
        int i = 0;

        System.out.println(feed);



        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
            System.out.println("AUTHOOOR ;" + message.author);
            System.out.println("DESCRIPTION : " + message.description);
            if (i==2){
                break;
            }
            i++;



        }

    }
}