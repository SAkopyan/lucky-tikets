import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tester {
    private static final String TEST_RESULT_FILE_NAME = "test.%s.out";
    private static final Pattern inputFilePattern = Pattern.compile("^test.(\\d+).in");
    private final Task task;
    private final File file;

    public Tester(Task task, String path) {
        this.task = task;
        this.file = new File(path);
    }

    public void run() {
        String[] filenames = file.list(new FileFilter(inputFilePattern));
        if (filenames == null) {
            System.out.println("No input test files in: " + file.getPath());
            return;
        }
        Arrays.sort(filenames, String.CASE_INSENSITIVE_ORDER);
        for (String fileName : filenames) {
            Matcher matcher = inputFilePattern.matcher(fileName);
            if (matcher.find()) {
                int testNumber = Integer.parseInt(matcher.group(1));
                System.out.println("----------==========Starting test №:" + testNumber + "==========----------");

                List<String> inputData = readFileLines(fileName);
                System.out.println("Test input data: ");
                inputData.forEach(System.out::println);

                long startTime = System.currentTimeMillis();
                String testResult = task.make(inputData);
                long testDuration = System.currentTimeMillis() - startTime;
                System.out.println("Test result: " + testResult + "; durationMillis = " + testDuration);

                String expectedResult = readFileAsString(String.format(TEST_RESULT_FILE_NAME, testNumber)).trim();
                System.out.println("Test expectedResult: " + expectedResult);

                System.out.println("Test is passed: " + expectedResult.equalsIgnoreCase(testResult));
            } else {
                System.out.println("Error, incorrect input file name: " + fileName);
            }
        }
    }

    private List<String> readFileLines(String fileName) {
        Path path = Paths.get(file.getPath() + "/" + fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error while read file " + fileName, e);
        }
    }

    private String readFileAsString(String fileName) {
        try {
            Path path = Paths.get(file.getPath() + "/" + fileName);
            Files.readAllBytes(path);
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException("Error while read file " + fileName, e);
        }
    }

    private static class FileFilter implements FilenameFilter {
        private final Pattern pattern;

        private FileFilter(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }
}
