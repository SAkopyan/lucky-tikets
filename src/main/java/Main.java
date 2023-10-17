import java.io.IOException;
import java.net.URLDecoder;

public class Main {
    public static void main(String[] args) throws IOException {
        Tester tester = new Tester(
                new RecurrentSearch(),
                URLDecoder.decode(Main.class.getClassLoader().getResource("tickets").getPath(), "UTF-8")
        );
        tester.run();
    }
}
