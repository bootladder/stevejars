import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PsTest {
    String twolinesoutput = "USER         PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND\nroot           1  0.0  0.0 171668  6312 ?        Ss   Feb09   0:33 /sbin/init s\nroot           2  0.0  0.0      0     0 ?        S    Feb09   0:00 [kthreadd]\n";
    @Test
    public void twolines_has_2_results() throws IOException, InterruptedException {
        Shell mockShell = mock(Shell.class);
        when(mockShell.command(any())).thenReturn(twolinesoutput);
        Ps ps = new Ps(mockShell);
        ps.execute();
        List<PsProcessEntry> results = ps.getProcesses();
        assertTrue(results.size() == 2);
    }

    @Test
    public void twolines_has_correct_results() throws IOException, InterruptedException {
        Shell mockShell = mock(Shell.class);
        when(mockShell.command(any())).thenReturn(twolinesoutput);
        Ps ps = new Ps(mockShell);
        ps.execute();
        List<PsProcessEntry> results = ps.getProcesses();
        assertEquals(results.get(0).user, "root");
        assertEquals(results.get(0).pid, "1");
        assertEquals(results.get(0).cpu, "0.0");
        assertEquals(results.get(0).mem, "0.0");
        assertEquals(results.get(0).vsz, "171668");
        assertEquals(results.get(0).rss, "6312");
        assertEquals(results.get(0).tty, "?");
        assertEquals(results.get(0).stat, "Ss");
        assertEquals(results.get(0).start, "Feb09");
        assertEquals(results.get(0).time, "0:33");
        assertEquals(results.get(0).command, "/sbin/init s");
    }

}
