package apavlov;

import java.util.Iterator;

/**
 * The class Converter - convert iterator iterators to one iterator..
 *
 * @author Pavlov Artem
 * @since 15.09.2017
 */
public class Converter {
    /**
     * The method convert iterator iterators to one iterator.
     *
     * @param iterators - iterator iterators;
     * @return - one iterator;
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<Integer>() {
            private Iterator<Integer> positionIterator = getNextIterator();

            private Iterator<Integer> getNextIterator() {
                Iterator<Integer> result = iterators != null && iterators.hasNext() ? iterators.next() : null;
                while (result != null && iterators.hasNext() && !result.hasNext()) {
                    result = iterators.next();
                }
                return result;
            }

            @Override
            public boolean hasNext() {
                return positionIterator != null && positionIterator.hasNext();
            }

            @Override
            public Integer next() {
                Integer result = positionIterator.next();
                if (!positionIterator.hasNext()) {
                    positionIterator = getNextIterator();
                }
                return result;
            }
        };
    }
}
