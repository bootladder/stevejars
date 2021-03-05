import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LsListingEntryTest {

    @Test
    public void takesStringProperly() {
        String str = "drwxrwxr-x 8 steve steve 4096 2021-03-03 04:49 build\n";
        LsListingEntry result = LsListingEntry.createFromString(str);

        assertEquals(result.filename, "build");
    }
    @Test
    public void aDifferentString() {
        String str = "-rw-------   1 steve steve      2575 2021-03-01 14:33 nscopy-5.tmp\n";
        LsListingEntry result = LsListingEntry.createFromString(str);

        assertEquals(result.flags, "-rw-------");
        assertEquals(result.hardlinkOrDirectoryCount, "1");
        assertEquals(result.owner, "steve");
        assertEquals(result.group, "steve");
        assertEquals(result.sizeBytes, "2575");
        assertEquals(result.ctimeDate, "2021-03-01");
        assertEquals(result.ctimeTime, "14:33");
        assertEquals(result.filename, "nscopy-5.tmp");
    }


}
