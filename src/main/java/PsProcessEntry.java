import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PsProcessEntry {
    public String user;
    public String pid;
    public String cpu;
    public String mem;
    public String vsz;
    public String rss;
    public String tty;
    public String stat;
    public String start;
    public String time;
    public String command;


    public PsProcessEntry(String inputStr) {
        String[] fields = getNonBlankFieldsFromListingEntry(inputStr);
        user = fields[0];
        pid = fields[1];
        cpu = fields[2];
        mem = fields[3];
        vsz = fields[4];
        rss = fields[5];
        tty = fields[6];
        stat = fields[7];
        start = fields[8];
        time = fields[9];
        command = fields[10];

        // there may be whitespace in the command  column, ie. arguments
        if(fields.length > 11){
            for(int i = 11; i<fields.length; i++)
                command = command + " " + fields[i].trim();
        }
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
