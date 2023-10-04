import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        LuckyTicketFinder finder = new RecursiveSearch();

        /*for (int N = 1; N <= 10; N++) {
            System.out.println("Result for " + N + ": " + finder.findCount(N));
        }*/

        Tester tester = new Tester(
                null,
                URLDecoder.decode(Main.class.getClassLoader().getResource("tickets").getPath(), "UTF-8")
        );
        tester.run();
    }
}
