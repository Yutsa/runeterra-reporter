import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "runeterra-reporter",
        description = "Generates meta report for Legends of Runeterra",
        mixinStandardHelpOptions = true,
        version = "1.0-SNAPSHOT")
public class RuneterraReporter implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("Just ran runeterra-reporter !");
        return 0;
    }

    public static void main(String... args) {
        System.exit(new CommandLine(new RuneterraReporter()).execute(args));
    }
}
