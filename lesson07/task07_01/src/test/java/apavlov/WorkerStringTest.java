package apavlov;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class WorkerStringTest for class`s WorkerString use junit4.
 *
 * @author Pavlov Artem
 * @since 21.10.2017
 */
public class WorkerStringTest {
    /**
     * The constant - offer for check work methods.
     */
    private static final String OFFER = "1111,   2222,3333... 44444. 55555...  66666!!! 777777? ";

    /**
     * The method check correct output to console result (count spaces to offer).
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetCountSpacesToOffer() throws Exception {
        String result = String.format("Count spaces: 9;%sThe thread [%s] is end work.%s", System.lineSeparator(), "ThreadCountSpaces", System.lineSeparator());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread threadCountSpaces = new WorkerString().getCountSpacesToOffer(OFFER);
        threadCountSpaces.start();
        threadCountSpaces.join();
        assertThat(out.toString(), is(result));
    }

    /**
     * The method check correct output to console result (count words to offer).
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetCountWordsToOffer() throws Exception {
        String result = String.format("Count words: 7;%sThe thread [%s] is end work.%s", System.lineSeparator(), "ThreadCountWords", System.lineSeparator());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread threadCountWords = new WorkerString().getCountWordsToOffer(OFFER);
        threadCountWords.start();
        threadCountWords.join();
        assertThat(out.toString(), is(result));
    }

    /**
     * The method check parallel work methods (print count spaces and words to offer).
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckParallelWorkMethods() throws Exception {
        int countTests = 10;
        WorkerString wStr = new WorkerString();
        Thread threadCountWords;
        Thread threadCountSpaces;
        for (int i = 0; i < countTests; i++) {
            threadCountSpaces = wStr.getCountSpacesToOffer(OFFER);
            threadCountWords = wStr.getCountWordsToOffer(OFFER);
            threadCountSpaces.start();
            threadCountWords.start();
            threadCountSpaces.join();
            threadCountWords.join();
            System.out.println();
        }
    }
}
