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
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class Searcher - use for search text to files by directory and by mask.
 *
 * @author Pavlov Artem
 * @since 04.01.2018
 */
@ThreadSafe
class Searcher {
    /**
     * The constant - amount cpu to computer.
     */
    private static final int AMOUNT_CPU = Runtime.getRuntime().availableProcessors();

    /**
     * The constant - max amount values to collections (queue).
     */
    private static final int MAX_QUEUE = 50;

    /**
     * The group threads for search files to directory.
     */
    private ThreadGroup groupFiles = new ThreadGroup("GroupSearchFiles");

    /**
     * The group threads for search text to files.
     */
    private ThreadGroup groupText = new ThreadGroup("GroupSearchText");

    /**
     * The link object Attribute (attributes for work program).
     */
    private final Attribute attribute;

    /**
     * The queue files for search text.
     */
    @GuardedBy("itself")
    private final Queue<File> listFiles;

    /**
     * The queue directory for search files.
     */
    @GuardedBy("itself")
    private final Queue<File> listDirectory;

    /**
     * The list searching files.
     */
    @GuardedBy("itself")
    private final List<String> resultList;

    /**
     * The amount work threads search files now.
     */
    @GuardedBy("this")
    private int countWorkThreads;

    /**
     * The getter for var countWorkThreads.
     *
     * @return value var countWorkThreads;
     */
    private int getCountWorkThreads() {
        synchronized (this) {
            return countWorkThreads;
        }
    }

    /**
     * The constructor for class Searcher.
     *
     * @param attribute - object attributes for start program.
     */
    Searcher(Attribute attribute) {
        this.listFiles = new LinkedBlockingQueue<>();
        this.listDirectory = new LinkedBlockingQueue<>();
        this.resultList = new ArrayList<>();
        this.attribute = attribute;
    }

    /**
     * The method check correct input attributes.
     *
     * @param attribute - attributes;
     * @return true - correct or false;
     */
    private boolean checkAttributes(Attribute attribute) {
        boolean result = attribute != null;
        if (result) {
            result = attribute.getPath() != null && attribute.getPath().isDirectory();
            if (!result) {
                System.out.println("Incorrect path or path is not directory [disk].");
            }
        } else {
            System.out.println("Attributes not choose.");
        }
        return result;
    }

    /**
     * The method check is empty collection directory or not.
     *
     * @return true - is empty collection or false;
     */
    private boolean isEmptyListDirectory() {
        synchronized (this.listDirectory) {
            return this.listDirectory.isEmpty();
        }
    }

    /**
     * The method check is empty collection files or not.
     *
     * @return true - is empty collection or false;
     */
    private boolean isEmptyListFile() {
        synchronized (this.listFiles) {
            return this.listFiles.isEmpty();
        }
    }

    /**
     * The method start program, used threads for search text to files by directory.
     */
    void start() {
        if (checkAttributes(this.attribute)) {
            long ms = System.currentTimeMillis();
            System.out.println(this.attribute);
            System.out.println("START PROGRAM...");
            synchronized (this.listDirectory) {
                this.listDirectory.add(this.attribute.getPath());
            }
            initThreadFiles(this.groupFiles);
            initThreadText(this.groupText, this.attribute.getMask(), this.attribute.getText());
            while (true) {
                if (this.groupText.activeCount() == 0 && this.groupFiles.activeCount() == 0) {
                    break;
                }
            }
            System.out.println("RESULT:");
            synchronized (this.resultList) {
                this.resultList.forEach(System.out::println);
            }
            ms = System.currentTimeMillis() - ms;
            System.out.printf("%3$sSpent time: %1$ds. %2$dms;%3$s", ms / 1000, ms % 1000, System.lineSeparator());
            System.out.println("STOP PROGRAM.");
        }
    }

    /**
     * The method initialization threads for search files by amount cpu.
     *
     * @param group - group threads;
     */
    private void initThreadFiles(ThreadGroup group) {
        for (int index = 1; index <= AMOUNT_CPU; index++) {
            new ThreadSearchFiles(group, String.format("ThreadFiles[%d]", index)).start();
        }
    }

    /**
     * The method initialization threads for search text by amount cpu.
     *
     * @param group - group threads;
     * @param mask - array masks files;
     * @param text - text for search;
     */
    private void initThreadText(ThreadGroup group, String[] mask, String text) {
        for (int index = 1; index <= AMOUNT_CPU; index++) {
            new ThreadSearchText(group, String.format("ThreadText[%d]", index), mask, text).start();
        }
    }

    /**
     * The method iterate on number counter.
     *
     * @param number - number for iterate;
     */
    private void inc(int number) {
        synchronized (this) {
            this.countWorkThreads += number;
        }
    }


    /**
     * The method add new element (file or directory) to list (queue) files.
     *
     * @param file - file (directory) for addition;
     * @throws InterruptedException - thread interrupted;
     */
    private void addFilesToQueue(File file) throws InterruptedException {
        if (file != null) {
            if (file.isFile()) {
                synchronized (this.listFiles) {
                    while (this.listFiles.size() == MAX_QUEUE) {
                        this.listFiles.wait();
                    }
                    this.listFiles.add(file);
                    this.listFiles.notify();
                }
            } else if (file.isDirectory()) {
                synchronized (this.listDirectory) {
                    this.listDirectory.add(file);
                    this.listDirectory.notify();
                }
            }
        }
    }

    /**
     * The method add file to result list.
     *
     * @param file - file;
     */
    private void addResultFiles(File file) {
        if (file != null) {
            synchronized (this.resultList) {
                this.resultList.add(file.getAbsolutePath());
            }
        }
    }

    /**
     * The method delete file to collection.
     *
     * @return deleting file;
     * @throws InterruptedException - interrupted thread;
     */
    private File deleteFileToQueue() throws InterruptedException {
        File result;
        synchronized (this.listFiles) {
            while (this.listFiles.isEmpty()) {
                this.listFiles.wait();
            }
            result = this.listFiles.poll();
            this.listFiles.notify();
        }
        return result;
    }

    /**
     * The method delete directory to collection.
     *
     * @return deleting directory;
     * @throws InterruptedException - interrupted thread;
     */
    private File deleteDirectoryToQueue() throws InterruptedException {
        File result;
        synchronized (this.listDirectory) {
            while (this.listDirectory.isEmpty()) {
                this.listDirectory.wait();
            }
            result = this.listDirectory.poll();
            this.listDirectory.notify();
        }
        return result;
    }

    /**
     * The inner class ThreadSearchFiles - thread for search files to directory.
     *
     * @author Pavlov Artem
     * @since 04.01.2018
     */
    private class ThreadSearchFiles extends Thread {
        /**
         * The constructor for class ThreadSearchFiles.
         *
         * @param group - group threads;
         * @param name - name thread;
         */
        ThreadSearchFiles(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted() && (!isEmptyListDirectory() || getCountWorkThreads() != 0)) {
                    File directory = deleteDirectoryToQueue();
                    inc(1);
                    File[] files = directory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            addFilesToQueue(file);
                        }
                    }
                    inc(-1);
                }
                getThreadGroup().interrupt();
            } catch (InterruptedException e) {
                //empty block;
            }
        }
    }

    /**
     * The inner class ThreadSearchText - thread for search text to file.
     *
     * @author Pavlov Artem
     * @since 04.01.2018
     */
    private class ThreadSearchText extends Thread {
        /**
         * The array masks files.
         */
        private final String[] mask;

        /**
         * The text for search.
         */
        private final String text;

        /**
         * The constructor for class ThreadSearchText.
         *
         * @param group - group threads;
         * @param name - name thread;
         * @param mask - array masks files;
         * @param text - text for search;
         */
        ThreadSearchText(ThreadGroup group, String name, String[] mask, String text) {
            super(group, name);
            this.mask = mask;
            this.text = text;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted() && (!isEmptyListFile() || getCountWorkThreads() != 0)) {
                    File file = deleteFileToQueue();
                    if (checkFile(file, this.mask) && containsText(file, this.text)) {
                       addResultFiles(file);
                    }
                }
                getThreadGroup().interrupt();
            } catch (InterruptedException e) {
                //empty block
            }
        }

        /**
         * The method check path is file and by mask.
         *
         * @param file - link object File (path);
         * @param mask - mask file;
         * @return true, if is file and file mask equal mask or false;
         */
        private boolean checkFile(File file, String[] mask) {
            boolean result = mask.length == 0 && file != null;
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
                if (text.length() == 0) {
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
    }
}

/**
 * The class Attribute - storage attributes for work program Searcher.
 *
 * @author Pavlov Artem
 * @since 04.01.2018
 */
class Attribute {
    /**
     * The constant - amount tabs to start line.
     */
    private static final int TAB = 4;

    /**
     * The constant - separator for new line.
     */
    private static final String LS = System.lineSeparator();

    /**
     * The path to directory.
     */
    private File path;

    /**
     * The array mask files.
     */
    private String[] mask;

    /**
     * The text for search to file.
     */
    private String text;

    /**
     * The getter for var path.
     *
     * @return path
     */
    File getPath() {
        return path;
    }

    /**
     * The getter for var mask.
     *
     * @return array masks;
     */
    String[] getMask() {
        return mask;
    }

    /**
     * The getter for var text.
     *
     * @return text
     */
    String getText() {
        return text;
    }

    /**
     * The constructor for class Attribute.
     *
     * @param path - path to directory;
     * @param mask - array mask files;
     * @param text - text for search to file;
     */
    Attribute(File path, String[] mask, String text) {
        editFields(path, mask, text);
    }

    /**
     * The method initialization fields to class.
     *
     * @param path - path to directory;
     * @param mask - array masks files;
     * @param text - text for search;
     */
    private void editFields(File path, String[] mask, String text) {
        this.path = path;
        this.mask = mask != null ? mask : new String[0];
        this.text = text != null ? text.toLowerCase() : "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String tabs = String.format(String.format("%s%d%s", "%", TAB, "s"), "");
        sb.append("CHOICE ATTRIBUTES:").append(LS);
        sb.append(tabs).append("path: ").append(this.path != null ? this.path : "").append(";").append(LS);
        sb.append(tabs).append("mask: ").append(Arrays.toString(this.mask)).append(";").append(LS);
        sb.append(tabs).append("text: ").append(this.text).append(";").append(LS);
        return sb.toString();
    }
}

/**
 * The class Main - use for start search files.
 *
 * @author Pavlov Artem
 * @since 04.01.2018
 */
class Main {
    /**
     * The method for start program.
     *
     * @param args - array parameters start;
     */
    public static void main(String[] args) {
        new Searcher(new Attribute(new File("D:"), new String[]{"txt"}, "1500")).start();
    }
}