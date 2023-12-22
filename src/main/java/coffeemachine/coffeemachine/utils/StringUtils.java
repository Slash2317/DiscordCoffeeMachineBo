package coffeemachine.coffeemachine.utils;

public class StringUtils {

    public static boolean containsWordsInSequence(String[] allWords, String... words) {
        outerLoop:
        for (int i = 0; i < allWords.length; i++) {
            if (allWords.length - i < words.length) {
                return false;
            }
            for (int j = 0, k = i; j < words.length; j++, k++) {
                if (!allWords[k].equalsIgnoreCase(words[j])) {
                    continue outerLoop;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean containsAnyWords(String[] allWords, String... words) {
        for (String allWord : allWords) {
            for (String word : words) {
                if (allWord.equalsIgnoreCase(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsAllWords(String[] allWords, String... words) {
        outerLoop:
        for (String word : words) {
            for (String allWord : allWords) {
                if (allWord.equalsIgnoreCase(word)) {
                    continue outerLoop;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean containsAny(String full, String... parts) {
        for (String part : parts) {
            if (containsIgnoreCase(full, part)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAll(String full, String... parts) {
        for (String part : parts) {
            if (!containsIgnoreCase(full, part)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsIgnoreCase(String full, String part) {
        return full.toLowerCase().contains(part.toLowerCase());
    }

    public static String sanitise(String s) {
        return s.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    public static String toUnicode(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append("\\u").append(Integer.toHexString(c));
        }

        return sb.toString();
    }
}
