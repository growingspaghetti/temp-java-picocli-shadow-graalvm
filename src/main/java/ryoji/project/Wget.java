package ryoji.project;

import picocli.CommandLine;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "wget")
public class Wget implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "URL")
    URL url;

    @Override
    public Integer call() throws Exception {
        String fileName = Paths.get(url.getPath()).getFileName().toString();
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
        return 0;
    }
}
