import java.io.*;

public class Shell {
    public String command(String cmd) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

        StringWriter sb = new StringWriter();
        String line = "";
        while ((line = reader.readLine())!= null) {
            sb.append(line + "\n");
        }

        return sb.toString();
    }
}
