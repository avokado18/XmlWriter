package patternGenerator;

import java.util.*;
import java.util.regex.Pattern;

public class PatternGenerator {
    public static Pattern generateRegexp(String prototype) {
        return Pattern.compile(generateRegexpFrom(prototype));
    }

    private static String generateRegexpFrom(String prototype) {
        StringBuilder stringBuilder = new StringBuilder();

        if (prototype.length() > 0){
            char prev = prototype.charAt(0);
            int count = 1;

            for (int i = 1; i < prototype.length(); i++) {
                char c = prototype.charAt(i);

                if (Character.isDigit(c)) {
                    if (count > 0 && Character.isDigit(prev)){
                        count++;
                        prev = c;
                    }
                    if (count > 0 && !Character.isDigit(prev)){
                        stringBuilder.append(Character.isLetter(prev) ? "\\w{" + count + "}" : "\\s{" + count + "}");
                        count = 1;
                        prev = c;
                    }
                    if (count == 0){
                        count++;
                        prev = c;
                    }
                } else if (Character.isLetter(c)) {
                    if (count > 0 && Character.isLetter(prev)){
                        count++;
                        prev = c;
                    }
                    if (count > 0 && !Character.isLetter(prev)){
                        stringBuilder.append(Character.isDigit(prev) ? "\\d{" + count + "}" : "\\s{" + count + "}");
                        count = 1;
                        prev = c;
                    }
                    if (count == 0){
                        count++;
                        prev = c;
                    }
                } else if (Character.isWhitespace(c)) {
                    if (count > 0 && Character.isWhitespace(prev)){
                        count++;
                        prev = c;
                    }
                    if (count > 0 && !Character.isWhitespace(prev)){
                        stringBuilder.append(Character.isDigit(prev) ? "\\d{" + count + "}" : "\\w{" + count + "}");
                        count = 1;
                        prev = c;
                    }
                    if (count == 0){
                        count++;
                        prev = c;
                    }
                } else {
                    if (count > 0){
                        if (Character.isWhitespace(prev)){
                            stringBuilder.append("\\s{" + count + "}");
                        } else {
                            stringBuilder.append(Character.isDigit(prev) ? "\\d{" + count + "}" : "\\w{" + count + "}");
                        }
                    }
                    stringBuilder.append(c);
                    count = 0;
                    prev = c;
                }
            }
            if (count > 0){
                if (Character.isWhitespace(prev)){
                    stringBuilder.append("\\s{" + count + "}");
                } else {
                    stringBuilder.append(Character.isDigit(prev) ? "\\d{" + count + "}" : "\\w{" + count + "}");
                }
            }
        }
        return stringBuilder.toString();
    }

    private static Map<String, Integer> getAllRegex(String[] words){
        Map<String, Integer> countRegex = new HashMap<String, Integer>();
        String regexp = generateRegexpFrom(words[0]);
        countRegex.put(regexp, 1);
        for (int i = 1; i < words.length; i++){
            String currentRegexp = generateRegexpFrom(words[i]);
            if (countRegex.containsKey(currentRegexp)){
                countRegex.put(currentRegexp, countRegex.get(currentRegexp) + 1);
            } else {
                countRegex.put(currentRegexp, 1);
            }
        }
        return countRegex;
    }

    public static void main(String[] args) {
        String[] prototypes = {
                "qwe@dfg.com",
                "qwe@qwe.com",
                "2009/11/12",
                "I'm a test",
                "me too!!!",
                "124.323.232.112",
                "ISBN      332212"
        };

        System.out.println(getAllRegex(prototypes));
    }
}
