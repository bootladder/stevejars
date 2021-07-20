import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ls {
    private final Shell shell;
    private final List<LsListingEntry> listings = new ArrayList<>();

    public Ls() {
        this.shell = new Shell();
    }
    public Ls(Shell shell) {
        this.shell = shell;
    }

    public void execute(String dir) throws IOException, InterruptedException {
        String output = shell.command("ls -al --time-style=long-iso " + dir);

        String[] split = output.split("\n");
        //skip first header line
        //also skip . and .. entries
        for (int i=3; i < split.length; i++) {
            this.listings.add(LsListingEntry.createFromString(split[i]));
        }
    }

    public List<LsListingEntry> getListings() {
        return listings;
    }
}
