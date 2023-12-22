package coffeemachine.coffeemachine.utils;

import java.util.List;
import java.util.function.Predicate;

public class PredicateUtils {

    public static final List<Predicate<String[]>> PRESENT_TENSE_AGREE_PREDICATE = List.of(
            containsAnyWordTrigger("yes"),
            containsAnyWordTrigger("ye"),
            containsAnyWordTrigger("yea"),
            containsAnyWordTrigger("yeah"),
            containsAnyWordTrigger("yup"),
            containsAnyWordTrigger("ok"),
            containsAnyWordTrigger("okay"),
            containsAnyWordTrigger("oki"),
            containsAnyWordTrigger("okii"),
            containsAnyWordTrigger("k"),
            containsAnyWordTrigger("sure"),
            containsAnyWordTrigger("fine"),
            containsAnyWordTrigger("ofc"),
            containsAnyWordTrigger("of", "course"),
            containsAnyWordTrigger("i", "do"),
            containsAnyWordTrigger("yuh"),
            containsAnyWordTrigger("ya"),
            containsAnyWordTrigger("uh", "huh"),
            containsAnyWordTrigger("mhm"),
            containsAnyWordTrigger("ight"),
            containsAnyWordTrigger("aight"),
            containsAnyWordTrigger("alright"),
            containsAnyWordTrigger("alr"),
            containsAnyWordTrigger("please"),
            containsAnyWordTrigger("pls")
    );

    public static final List<Predicate<String[]>> PAST_TENSE_AGREE_PREDICATE = List.of(
            containsAnyWordTrigger("yes"),
            containsAnyWordTrigger("ye"),
            containsAnyWordTrigger("yea"),
            containsAnyWordTrigger("yeah"),
            containsAnyWordTrigger("yup"),
            containsAnyWordTrigger("ok"),
            containsAnyWordTrigger("okay"),
            containsAnyWordTrigger("oki"),
            containsAnyWordTrigger("okii"),
            containsAnyWordTrigger("k"),
            containsAnyWordTrigger("sure"),
            containsAnyWordTrigger("fine"),
            containsAnyWordTrigger("ofc"),
            containsAnyWordTrigger("of", "course"),
            containsAnyWordTrigger("i", "did"),
            containsAnyWordTrigger("yuh"),
            containsAnyWordTrigger("ya"),
            containsAnyWordTrigger("uh", "huh"),
            containsAnyWordTrigger("mhm"),
            containsAnyWordTrigger("ight"),
            containsAnyWordTrigger("aight"),
            containsAnyWordTrigger("alright"),
            containsAnyWordTrigger("alr"),
            containsAnyWordTrigger("please"),
            containsAnyWordTrigger("pls")
    );

    public static Predicate<String> containsTrigger(String text) {
        return s -> StringUtils.containsIgnoreCase(s, text);
    }

    public static Predicate<String> containsAnyTrigger(String... texts) {
        return s -> StringUtils.containsAny(s, texts);
    }

    public static Predicate<String> containsAllTrigger(String... texts) {
        return s -> StringUtils.containsAll(s, texts);
    }

    public static Predicate<String[]> containsWordTrigger(String word) {
        return s -> StringUtils.containsAnyWords(s, word);
    }

    public static Predicate<String[]> containsAnyWordTrigger(String... words) {
        return s -> StringUtils.containsAnyWords(s, words);
    }

    public static Predicate<String[]> containsAllWordsTrigger(String... words) {
        return s -> StringUtils.containsAllWords(s, words);
    }

    public static Predicate<String[]> containsWordsInSequenceTrigger(String... words) {
        return s -> StringUtils.containsWordsInSequence(s, words);
    }
}
