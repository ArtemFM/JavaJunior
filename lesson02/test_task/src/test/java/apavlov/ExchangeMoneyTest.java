package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class ExchangeMoneyTest for class ExchangeMoney.
 *
 * @author Pavlov Artem
 * @since 22.09.2017
 */
public class ExchangeMoneyTest {
    /**
     * The test method for check correct add new values coins.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkAddValuesCoins() throws Exception {
        ExchangeMoney exMoney = new ExchangeMoney(null);
        assertThat(exMoney.getValueCoins().length, is(0));

        exMoney = new ExchangeMoney(new int[]{-1, 0, -10, 0});
        assertThat(exMoney.getValueCoins().length, is(0));

        exMoney = new ExchangeMoney(new int[]{-1, 0, 5, 5});
        assertThat(exMoney.getValueCoins(), is(new int[]{5}));

        exMoney = new ExchangeMoney(new int[]{-1, 0, 10, -2, 10, 2, 5, 5, 1, 1, 10});
        assertThat(exMoney.getValueCoins(), is(new int[]{1, 2, 5, 10}));
    }

    /**
     * The test method check correct add one value coin.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddOneValueCoin() throws Exception {
        int[] result = {1, 2, 5, 10};
        ExchangeMoney exMoney = new ExchangeMoney(new int[]{1, 10, 5});
        assertThat(exMoney.addValueCoins(-1), is(false));
        assertThat(exMoney.addValueCoins(0), is(false));
        assertThat(exMoney.addValueCoins(2), is(true));
        assertThat(exMoney.getValueCoins(), is(result));
    }

    /**
     * The test method check correct return combinations exchange.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetExchangeMoney() throws Exception {
        int sum = 10;
        String[] resultOne = {};
        String[] resultTwo = {
                "[5x2 rub]",
                "[4x2 rub][2x1 rub]",
                "[3x2 rub][4x1 rub]",
                "[2x2 rub][6x1 rub]",
                "[1x2 rub][8x1 rub]",
                "[10x1 rub]"
        };

        String[] resultThree = {
                "[2x5 rub]",
                "[1x5 rub][2x2 rub][1x1 rub]",
                "[1x5 rub][1x2 rub][3x1 rub]",
                "[1x5 rub][5x1 rub]",
                "[5x2 rub]",
                "[4x2 rub][2x1 rub]",
                "[3x2 rub][4x1 rub]",
                "[2x2 rub][6x1 rub]",
                "[1x2 rub][8x1 rub]",
                "[10x1 rub]"
        };

        String[] resultFour = {
                "[1x10 rub]",
                "[2x5 rub]",
                "[1x5 rub][2x2 rub][1x1 rub]",
                "[1x5 rub][1x2 rub][3x1 rub]",
                "[1x5 rub][5x1 rub]",
                "[5x2 rub]",
                "[4x2 rub][2x1 rub]",
                "[3x2 rub][4x1 rub]",
                "[2x2 rub][6x1 rub]",
                "[1x2 rub][8x1 rub]",
                "[10x1 rub]"
        };

        ExchangeMoney exMoney = new ExchangeMoney(new int[]{});
        assertThat(exMoney.getExchangeMoney(sum), is(resultOne));

        exMoney.addAllValueCoins(new int[]{1, 2});
        assertThat(exMoney.getExchangeMoney(sum), is(resultTwo));

        exMoney.addValueCoins(5);
        assertThat(exMoney.getExchangeMoney(sum), is(resultThree));

        exMoney.addValueCoins(10);
        assertThat(exMoney.getExchangeMoney(sum), is(resultFour));
    }
}