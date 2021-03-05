import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LSTest {
    private String oneLineOutput = "total 48\ndrwxrwxr-x 8 steve steve 4096 2021-03-03 04:49 build\n";
    private String twoLineOutput = "total 48\ndrwxrwxr-x 8 steve steve 4096 2021-03-03 04:49 build\n-rwxrwxr-x 1 steve steve 5766 2021-03-03 04:49 gradlew\n";

    @Test
    public void ls_faketmp_has_atleast_1_result() throws IOException, InterruptedException {
        Shell mockShell = mock(Shell.class);
        when(mockShell.command(any())).thenReturn(oneLineOutput);
        Ls ls = new Ls(mockShell);
        ls.execute("/tmp/");
        List<LsListingEntry> results = ls.getListings();
        assertTrue(results.size() > 0);
    }

    @Test
    public void ls_faketmp_2results__has_atleast_2_results() throws IOException, InterruptedException {
        Shell mockShell = mock(Shell.class);
        when(mockShell.command(any())).thenReturn(twoLineOutput);
        Ls ls = new Ls(mockShell);
        ls.execute("/tmp/");
        List<LsListingEntry> results = ls.getListings();
        assertTrue(results.size() > 1);
    }


    @Test
    public void ls_faketmp_result_has_filename_and_owner_and_everything() throws IOException, InterruptedException {
        Shell shell = mock(Shell.class);
        when(shell.command(any())).thenReturn(oneLineOutput);
        Ls ls = new Ls(shell);
        ls.execute("/tmp/");
        List<LsListingEntry> listings = ls.getListings();
        assertTrue(listings.get(0).flags.equals("drwxrwxr-x"));
        assertTrue(listings.get(0).hardlinkOrDirectoryCount.equals("8"));
        assertTrue(listings.get(0).owner.equals("steve"));
        assertTrue(listings.get(0).group.equals("steve"));
        assertTrue(listings.get(0).sizeBytes.equals("4096"));
        assertTrue(listings.get(0).ctimeDate.equals("2021-03-03"));
        assertTrue(listings.get(0).ctimeTime.equals("04:49"));


        assertTrue(listings.get(0).filename.equals("build"));
//        assertTrue(listings.get(0).modified.equals("steve"));


    }



    @Test
    public void ls_faketmp_2linesresult_has_filename_and_owner() throws IOException, InterruptedException {
        Shell shell = mock(Shell.class);
        when(shell.command(any())).thenReturn(twoLineOutput);
        Ls ls = new Ls(shell);
        ls.execute("/tmp/");
        List<LsListingEntry> listings = ls.getListings();
        assertTrue(listings.get(0).filename.equals("build"));
        assertTrue(listings.get(0).owner.equals("steve"));

        assertEquals(listings.get(1).filename, "gradlew");
        assertTrue(listings.get(1).owner.equals("steve"));
    }

    @Test
    public void ls_realshell_root_has_home() throws IOException, InterruptedException {
        Ls ls = new Ls();
        ls.execute("/");
        List<LsListingEntry> listings = ls.getListings();
        for (LsListingEntry listing : listings) {
            System.out.println(listing.filename);
        }

    }
}
