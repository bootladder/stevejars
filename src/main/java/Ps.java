import jdk.internal.icu.text.UnicodeSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ps {
    private final Shell shell;
    private List<PsProcessEntry> processEntries = new ArrayList<>();

    public Ps() {
        this.shell = new Shell();
    }

    public Ps(Shell shell) {
        this.shell = shell;
    }

    public void execute() throws IOException, InterruptedException {
        String output = shell.command("ps aux");

        String[] split = output.split("\n");
        //skip first header line
        for (int i=1; i < split.length; i++) {
            this.processEntries.add(new PsProcessEntry(split[i]));
        }
    }

    public List<PsProcessEntry> getProcesses() {
        return processEntries;
    }
}
