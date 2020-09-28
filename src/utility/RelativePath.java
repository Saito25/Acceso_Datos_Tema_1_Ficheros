package utility;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RelativePath {
    private RelativePath(){};

    public static String getRelativePath(File file) {
        String result = "";
        String[] currentAbsolutPath = new File("").getAbsolutePath().split(Pattern.quote(File.separator));
        String[] receivedAbsolutPath = file.getAbsolutePath().split(Pattern.quote(File.separator));
        int smallPath = 0;
        int biggerPath = 0;
        int counter = 0;
        String initialPath = "";

        System.out.println(Arrays.toString(currentAbsolutPath));
        System.out.println(Arrays.toString(receivedAbsolutPath));

        smallPath = Integer.min(currentAbsolutPath.length, receivedAbsolutPath.length);
        biggerPath = Integer.max(currentAbsolutPath.length, receivedAbsolutPath.length);

        for(int i = 0; i < smallPath; i++) {
            if(currentAbsolutPath[i].equals(receivedAbsolutPath[i])) {
                counter++;
            } else {
                break;
            }
        }

        biggerPath -= counter;

        for(int i = 0; i < biggerPath; i++) {
            initialPath += ".." + File.separator;
        }

        for(int i = counter; i < receivedAbsolutPath.length; i++) {
            initialPath += receivedAbsolutPath[i] + File.separator;
        }

        result = initialPath.substring(0, initialPath.length() == 0 ? 0 : initialPath.length() - 1);

        return result;
    }
}
