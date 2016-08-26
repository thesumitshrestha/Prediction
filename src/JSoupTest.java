/**
 * Created by sarasumit on 8/24/2016.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class JSoupTest {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        String title =doc.className();
        System.out.println("title is: " + title);
    }
}
