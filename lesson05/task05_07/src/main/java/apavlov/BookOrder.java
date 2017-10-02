package apavlov;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * The class BookOrder - use for cast list book to prices catalog.
 *
 * @author Pavlov Artem
 * @since 03.10.2017
 */
public class BookOrder {
    /**
     * The var - path to file.
     */
    private String fileName;

    /**
     * The collection for storage books by name.
     */
    private TreeMap<String, Order> books;

    /**
     * The var for work with line.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * The constructor for class BookOrder.
     *
     * @param fileName - path to file;
     */
    public BookOrder(String fileName) {
        addNewFile(fileName);
    }

    /**
     * The method edit path to file.
     *
     * @param fileName - path to file;
     * @return false - is incorrect name file;
     */
    public boolean addNewFile(String fileName) {
        boolean result;
        if (result = fileName != null && fileName.length() != 0) {
            this.fileName = fileName;
            this.books = new TreeMap<>();
        }
        return result;
    }

    /**
     * The method read xml file and his writing to collection.
     */
    public void startProcess() {
        try {
            XMLStreamReader xml = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(fileName));
            while (xml.hasNext()) {
                xml.next();
                if (xml.isStartElement()) {
                    if (xml.getLocalName().equals("AddOrder")) {
                        String bookName = xml.getAttributeValue(0);
                        String operation = xml.getAttributeValue(1);
                        Float price = Float.parseFloat(xml.getAttributeValue(2));
                        Integer volume = Integer.parseInt(xml.getAttributeValue(3));
                        Integer id = Integer.parseInt(xml.getAttributeValue(4));
                        Book book = new Book(id, volume, price);
                        this.add(operation, bookName, book);
                    } else if (xml.getLocalName().equals("DeleteOrder")) {
                        String bookName = xml.getAttributeValue(0);
                        Integer id = Integer.parseInt(xml.getAttributeValue(1));
                        this.delete(bookName, id);
                    }
                }
            }
            this.clearPrices();
            xml.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File is not found...");
        } catch (XMLStreamException e) {
            System.out.println("Error: File is busy or incorrect data...");
        }
    }

    /**
     * The method add new book to collection.
     *
     * @param operation - sell or buy;
     * @param nameBook - name book;
     * @param book - object type Book;
     */
    private void add(String operation, String nameBook, Book book) {
        Order order;
        if (this.books.containsKey(nameBook)) {
            order = this.books.get(nameBook);
        } else {
            order = new Order();
            this.books.put(nameBook, order);
        }
        order.idOrder.put(book.id, book);
        if (operation.equals("BUY")) {
            order.buy.put(book.price, book);
        } else if (operation.equals("SELL")) {
            order.sell.put(book.price, book);
        }
    }

    /**
     * The method for delete book to collection by id and name.
     *
     * @param bookName - name book;
     * @param id - order id book;
     */
    private void delete(String bookName, Integer id) {
        if (bookName != null && this.books.containsKey(bookName)) {
            Order order = this.books.get(bookName);
            Book book = order != null ? order.idOrder.remove(id) : null;
            if (book != null) {
                Book temp = order.buy.get(book.price);
                if (temp != null && book.id.equals(temp.id)) {
                    order.buy.remove(book.price);
                }
                temp = order.sell.get(book.price);
                if (temp != null && book.id.equals(temp.id)) {
                    order.sell.remove(book.price);
                }
            }
        }
    }

    @Override
    public String toString() {
        this.sb.delete(0, sb.length());
        for (Map.Entry<String, Order> element : this.books.entrySet()) {
            this.sb.append(String.format("%17s", "Order book : ")).append(element.getKey()).append(System.lineSeparator()).append(System.lineSeparator());
            this.sb.append(element.getValue());
            this.sb.append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        }
        return this.sb.toString();
    }

    /**
     * The method delete all excess prices to all books.
     */
    private void clearPrices() {
        for (Order element : this.books.values()) {
            element.clearExcessPrice();
        }
    }

    /**
     * The inner class Order.
     *
     * @author Pavlov Artem
     * @since 03.10.2017
     */
    private class Order {
        /**
         * The var for empty line.
         */
        private final String prefix = String.format("%8s", " ").replaceAll(" ", "-");

        /**
         * The var for work with lines.
         */
        private StringBuilder sb = new StringBuilder();

        /**
         * Collection for storage book by id.
         */
        private HashMap<Integer, Book> idOrder;

        /**
         * Collection sell for storage book by price.
         */
        private TreeMap<Float, Book> sell;

        /**
         * Collection buy for storage book by price.
         */
        private TreeMap<Float, Book> buy;

        /**
         * The default constructor for inner class Order.
         */
        Order() {
            this.idOrder = new HashMap<>();
            this.sell = new TreeMap<>();
            this.buy = new TreeMap<>(new Comparator<Float>() {
                @Override
                public int compare(Float o1, Float o2) {
                    return o1 != null && o2 != null ? -o1.compareTo(o2) : 0;
                }
            });
        }

        @Override
        public String toString() {
            this.sb.delete(0, this.sb.length());
            this.sb.append(String.format("%8s%15s%s%s", "BID", "ASK", System.lineSeparator(), System.lineSeparator()));
            String head = String.format("%s@%s", "Volume", "Price");
            this.sb.append(String.format("%s - %s%s%s", head, head, System.lineSeparator(), System.lineSeparator()));
            Iterator<Book> iteratorBuy = this.buy.values().iterator();
            Iterator<Book> iteratorSell = this.sell.values().iterator();
            while (iteratorBuy.hasNext() || iteratorSell.hasNext()) {
                this.sb.append(iteratorBuy.hasNext() ? iteratorBuy.next().toString() : String.format("%4s%-8s", "", this.prefix));
                this.sb.append(" - ");
                this.sb.append(iteratorSell.hasNext() ? iteratorSell.next().toString() : String.format("%12s", this.prefix));
                this.sb.append(System.lineSeparator());
            }
            return this.sb.toString();
        }

        /**
         * The private method for delete excess prices.
         */
        private void clearExcessPrice() {
            Iterator<Float> itBuy = this.buy.keySet().iterator();
            Iterator<Float> itSell = this.sell.keySet().iterator();
            Float fBuy = 1f, fSell = 0f;
            while (itBuy.hasNext() && itSell.hasNext() && fBuy > fSell) {
                fBuy = itBuy.next();
                fSell = itSell.next();
                itBuy.remove();
                itSell.remove();
            }
        }
    }

    /**
     * The inner class Book - model element.
     *
     * @author Pavlov Artem
     * @since 03.10.2017
     */
    private class Book {
        /**
         * The order id book.
         */
        private Integer id;

        /**
         * The volume book.
         */
        private Integer volume;

        /**
         * The price book.
         */
        private Float price;

        /**
         * The constructor for inner class Book.
         *
         * @param id - order id book;
         * @param volume - volume book;
         * @param price - price book;
         */
        Book(Integer id, Integer volume, Float price) {
            this.id = id;
            this.volume = volume;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%6s@%5s", this.volume, this.price);
        }
    }
}
