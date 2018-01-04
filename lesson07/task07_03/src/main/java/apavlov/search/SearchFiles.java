package apavlov.search;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The class SearchTextToFile - use for search text to files by directory and by mask.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
@ThreadSafe
class SearchTextToFile {
    /**
     * The constant = amount tabs start offer.
     */
    private static final int TAB = 3;

    /**
     * Amount tab to string tabs.
     */
    private String tabs = String.format(String.format("%s%d%s", "%", TAB, "s"), "");

    /**
     * The var - max seconds for searching files.
     */
    private int seconds = 300;

    /**
     * The var check to cause help or not.
     */
    private boolean isHelp;

    /**
     * Collection for result searching.
     */
    @GuardedBy("itself")
    private List<File> listFiles;

    /**
     * Path to directory for searching.
     */
    private String directory;

    /**
     * Array mask for filter files.
     */
    private String[] mask;

    /**
     * Text for search to files.
     */
    private String text;

    /**
     * The constructor for class SearchTextToFile.
     *
     * @param attributes - array parameters for searching;
     */
    SearchTextToFile(String[] attributes) {
        this.listFiles = new ArrayList<>();
        parseAttribute(attributes);
    }

    /**
     * The method parse array parameters to local vars class`s.
     *
     * @param attributes - array parameters;
     */
    private void parseAttribute(String[] attributes) {
        StringBuilder sb = new StringBuilder();
        if (attributes != null && attributes.length > 0) {
            for (String value : attributes) {
                sb.append(value).append(" ");
            }
            if (sb.toString().trim().equals("/?")) {
                this.isHelp = true;
            } else {
                String[] parameters = sb.toString().trim().toLowerCase().split("/");
                if (parameters.length > 0) {
                    this.directory = parameters[0].trim();
                    for (int index = 1; index < parameters.length; index++) {
                        if (parameters[index].charAt(0) == 't') {
                            this.text = parameters[index].substring(1, parameters[index].length()).trim();
                        } else if (parameters[index].charAt(0) == 'm') {
                            this.mask = parameters[index].substring(1, parameters[index].length()).trim().split("\\s+");
                        } else if (parameters[index].charAt(0) == 's') {
                            try {
                                this.seconds = Integer.parseInt(parameters[index].substring(1, parameters[index].length()).trim());
                            } catch (NumberFormatException e) {
                                //skip this block. default value seconds equal 300;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The method text chosen parameters for searching.
     *
     * @return text parameters;
     */
    private String toStringAttribute() {
        StringBuilder sb = new StringBuilder();
        String lineS = System.lineSeparator();
        sb.append(lineS).append("Chosen parameters:").append(lineS);
        sb.append(tabs).append(String.format("%-12s", "directory"));
        sb.append(" : ").append(this.directory).append(lineS);

        sb.append(tabs).append(String.format("%-12s", "text"));
        sb.append(" : ").append(this.text != null ? this.text : "--").append(lineS);

        sb.append(tabs).append(String.format("%-12s", "seconds"));
        sb.append(" : ").append(this.seconds).append(lineS);

        sb.append(tabs).append(String.format("%-12s", "mask"));
        sb.append(" : ").append(this.mask != null ? Arrays.toString(this.mask) : "--").append(lineS);
        return sb.append(lineS).toString();
    }

    /**
     * The method start search files to directory.
     */
    public void search() {
        if (this.isHelp) {
            System.out.println(toStringHelp());
        } else {
            if (directory != null) {
                File dir = new File(directory);
                if (dir.exists() && dir.isDirectory()) {
                    System.out.println(toStringAttribute());
                    System.out.print("Start program [Y/N]: ");
                    String answer = new Scanner(System.in).nextLine().trim().toLowerCase();
                    if (answer.equals("y")) {
                        ExecutorService service = Executors.newFixedThreadPool(10);
                        getFiles(service, dir);
                        service.shutdown();
                        try {
                            service.awaitTermination(this.seconds, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    String msg = String.format("The path [%s] is incorrect or is not directory.", directory);
                    System.out.println(msg);
                }
            } else {
                System.out.println("No parameter [path to directory].");
            }
        }

    }

    /**
     * Recurse method for searching text to files.
     *
     * @param service   - link group thread;
     * @param directory - path to directory, where search;
     */
    private void getFiles(ExecutorService service, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFiles(service, file);
                } else if (file.isFile()) {
                    service.execute(new SearchText(this.listFiles, file, this.text, this.mask));
                }
            }
        }
    }

    /**
     * The method return text help program.
     *
     * @return text help;
     */
    private String toStringHelp() {
        StringBuilder sb = new StringBuilder();
        String lineS = System.lineSeparator();
        sb.append(lineS);
        sb.append("SearchFiles: ").append("[disk or path to directory] ").append("[/T [text]] ").append("[/S [seconds]]");
        sb.append("[/M [mask]]").append(lineS);
        sb.append(lineS);
        sb.append("Usage example:").append(lineS);
        sb.append(tabs).append("SearchFiles C:\\ /t hello /s 1300 /m exe").append(lineS);
        sb.append(tabs).append("SearchFiles C:\\ /t hello world /m exe txt doc").append(lineS);
        sb.append(tabs).append("SearchFiles C:\\windows /t hello world").append(lineS);
        sb.append(lineS);
        sb.append("Description attributes:").append(lineS);

        sb.append(tabs).append(String.format("%-30s", "[disk or path to directory]"));
        sb.append(" : path to directory or tom; [parameter is obligatory to use]").append(lineS);

        sb.append(tabs).append(String.format("%-30s", "[/T [text]]"));
        sb.append(" : text for search; [parameter is possible not to use] ").append(lineS);

        sb.append(tabs).append(String.format("%-30s", "[/S [seconds]]"));
        sb.append(" : max seconds for searching; [parameter is possible not to use (default value equal 300)] ").append(lineS);

        sb.append(tabs).append(String.format("%-30s", "[/M [mask]]"));
        sb.append(" : mask for filter files; [parameter is possible not to use").append(lineS);
        return sb.toString();
    }

    /**
     * The inner class SearchText - search text ot file.
     *
     * @author Pavlov Artem
     * @since 19.12.2017
     */
    private class SearchText implements Runnable {
        /**
         * The constant = amount tabs start offer.
         */
        private static final int TAB = 3;

        /**
         * Amount tab to string tabs.
         */
        private String tabs = String.format(String.format("%s%d%s", "%", TAB, "s"), "");

        /**
         * The link to collection searching files.
         */
        private final List<File> list;

        /**
         * The path to file.
         */
        private final File file;

        /**
         * The text for search to file.
         */
        private String text;

        /**
         * Filter file by mask.
         */
        private String[] mask;

        /**
         * The constructor for class SearchText.
         *
         * @param list - list for searching files;
         * @param file - path to file;
         * @param text - text for search to file;
         * @param mask - mask for filter files;
         */
        SearchText(final List<File> list, final File file, String text, String[] mask) {
            this.list = list;
            this.file = file;
            this.text = text;
            this.mask = mask;
        }

        @Override
        public void run() {
            if (checkFile(file, mask) && containsText(file, text)) {
                synchronized (this.list) {
                    System.out.printf("%sName: %s; Path: %s;%s", this.tabs, file.getName(), file.getParent(), System.lineSeparator());
                    this.list.add(file);
                }
            }
        }

        /**
         * The method check contains text to file.
         *
         * @param file - file;
         * @param text - text for check;
         * @return true - is contains text or false;
         */
        private boolean containsText(File file, String text) {
            boolean result = false;
            if (file != null) {
                if (text == null || text.length() == 0) {
                    result = true;
                } else {
                    try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = bReader.readLine()) != null) {
                            line = line.toLowerCase();
                            if (line.contains(text)) {
                                result = true;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        result = false;
                    }
                }
            }
            return result;
        }

        /**
         * The method check path is file and by mask.
         *
         * @param file - link object File (path);
         * @param mask - mask file;
         * @return true, if is file and file mask equal mask or false;
         */
        private boolean checkFile(File file, String[] mask) {
            boolean result = mask == null && file != null;
            if (!result) {
                if (file != null && file.isFile()) {
                    for (String ext : mask) {
                        if (file.getName().endsWith(ext)) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }
}

/**
 * The class SearchFiles - use for start search files.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
public class SearchFiles {
    /**
     * The method for start program.
     *
     * @param args - array parameters start;
     */
    public static void main(String[] args) {
        new SearchTextToFile(args).search();
    }
}