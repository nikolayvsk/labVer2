import org.example.Main;
import org.testng.annotations.*;

import java.util.Map;

import static org.testng.Assert.*;

public class Tests {
    private Main wordCounter;

    @BeforeSuite
    public void setUpAll() {
        System.out.println("Running all tests...");
    }

    @BeforeMethod
    public void setUp() {
        wordCounter = new Main();
    }

    @Test(groups = {"countering"})
    public void testCountWords() {
        String input = "tiger! tiger! burning bright";
        Map<String, Integer> expected = Map.of("tiger", 2, "burning", 1, "bright", 1);
        Map<String, Integer> actual = wordCounter.countWords(input);
        assertEquals(expected, actual);
    }

    @Test(groups = {"countering"})
    public void testCounterWithNumberPunctuationWhiteSpaces() {
        String input = "what!!!!!...             \n\n\n    the hammer?             ((00))0what the chain?";
        Map<String, Integer> expected = Map.of("what", 2, "the", 2, "hammer", 1, "chain", 1);
        Map<String, Integer> actual = wordCounter.countWords(input);
        assertEquals(expected, actual);
    }

    @Test(groups = {"countering"})
    public void testCounter() {
        Main wordCount = new Main();
        String input = "hello hello hello hellohello helo helo";
        assertTrue(wordCount.countWords(input).get("hello") == 3);
    }

    @Test(groups = {"countering"})
    public void testEmptyString() {
        String input = "";
        Map<String, Integer> actualCount = wordCounter.countWords(input);
        assertTrue(actualCount.isEmpty());
    }

    @Test(groups = {"exceptions"})
    public void testNull() {
        String input = null;
        assertThrows(IllegalArgumentException.class, () -> wordCounter.countWords(input));
    }

    @DataProvider(name = "counteringDataProvider")
    public Object[][] provideWordCounts() {
        return new Object[][] {
                {"There are five games here", 5},
                {"hello hello hello hellohello helo helo", 3}
        };
    }

    @Test(dataProvider = "counteringDataProvider", groups = {"countering"})
    public void testCounterWords(String input, int expectedCount) {
        Map<String, Integer> actualCount = wordCounter.countWords(input);
        assertEquals(expectedCount, actualCount.size());
    }

}
