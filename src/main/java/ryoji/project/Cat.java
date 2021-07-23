package ryoji.project;

import picocli.CommandLine;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@CommandLine.Command(name = "cat")
public class Cat implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "FILE")
    List<Path> paths;

    @Override
    public Integer call() throws Exception {
        for (Path path : paths) {
            try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
                lines.forEach(System.out::println);
            }
        }
        return 0;
    }
}
