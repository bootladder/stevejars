import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LsListingEntry {
    public String filename;
    public String owner;
    public String flags;
    public String group;
    public String sizeBytes;
    public String hardlinkOrDirectoryCount;
    public String ctimeDate;
    public String ctimeTime;

    public static LsListingEntry createFromString(String inputStr) {
        String[] fields = getNonBlankFieldsFromListingEntry(inputStr);

        LsListingEntry lsListingEntry = new LsListingEntry();
        lsListingEntry.flags = fields[0].trim();
        lsListingEntry.hardlinkOrDirectoryCount = fields[1].trim();
        lsListingEntry.owner = fields[2];
        lsListingEntry.group = fields[3].trim();
        lsListingEntry.sizeBytes = fields[4].trim();
        lsListingEntry.ctimeDate = fields[5].trim();
        lsListingEntry.ctimeTime = fields[6].trim();
        lsListingEntry.filename = fields[7].trim();

        return lsListingEntry;
    }

    private static String[] getNonBlankFieldsFromListingEntry(String inputStr) {
        String[] fieldsWithBlanks = inputStr.split(" ");
        List<String> fieldsList = Arrays.asList(fieldsWithBlanks)
                .stream()
                .filter(field -> false == field.equals(""))
                .collect(Collectors.toList());

        String[] fields = fieldsList.stream().toArray(String[]::new);
        return fields;
    }
}
