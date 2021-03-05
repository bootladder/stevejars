import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PsProcessEntryTest {

    String oneline = "root           1  0.0  0.0 171668  6312 ?        Ss   Feb09   0:33 /sbin/init s\n";
    @Test
    public void constructorworks(){
        PsProcessEntry psProcessEntry = new PsProcessEntry(oneline);

        assertEquals(psProcessEntry.user, "root");
        assertEquals(psProcessEntry.pid, "1");
        assertEquals(psProcessEntry.cpu, "0.0");
        assertEquals(psProcessEntry.mem, "0.0");
        assertEquals(psProcessEntry.vsz, "171668");
        assertEquals(psProcessEntry.rss, "6312");
        assertEquals(psProcessEntry.tty, "?");
        assertEquals(psProcessEntry.stat, "Ss");
        assertEquals(psProcessEntry.start, "Feb09");
        assertEquals(psProcessEntry.time, "0:33");
        assertEquals(psProcessEntry.command, "/sbin/init s");

    }
}