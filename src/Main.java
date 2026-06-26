import util.FrequencyCounter;
import java.util.Map;

public class Main{
    static void main() {
        String text = "Hello\n" +
                "World";
        FrequencyCounter counter = new FrequencyCounter();
        Map<Character,Integer> frequencyMap = counter.count(text);

        System.out.println(frequencyMap);
    }
}