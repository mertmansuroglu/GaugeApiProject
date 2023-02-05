package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringHelper {
    private static final Logger log = LogManager.getLogger(StringHelper.class);
    // TODO: 11/6/2022 16. buradan baslanilacak anlatmaya


    /**
     * @param originalText Original text to be replaced
     * @param oldPart      The part of the text to be replaced
     * @param replacement  New part of text to replace
     * @param onlyFirst    Will all equal part of the text be change?
     * @return The new text after replacement
     *
     * asagidaki yapilan  eger ihtiyac varsa string fonksyonlari icerir replace regex substring gibi
     * ihtiyacin oldugunda stringde yollayabilirsin regexde yollayabilirsin bizim steptekiol;d bazen string bazen regex alabilir
     * eger oldpartta  length 1 ise buyuk olasilik char gelis onu alip chara parse ediyoruz
     * asagisindaki sadece ilk buldugunu degistieceksen replaceFirst kullanacaksin
     * asagisindaki yollanan karakter regex mi ona bakiyor eger regexse replace all ile degistiriyor cunku regex ile yollanan tum karakterleri degistirir
     * regex degilse normal degistirmek istenen string ile degistiriliyor.
     *
     */
    protected String replace(String originalText, String oldPart, String replacement, boolean... onlyFirst) {
        if (oldPart.length() == 1) {
            return originalText.replace(oldPart.charAt(0), replacement.charAt(0));
        } else if (onlyFirst.length > 0 && onlyFirst[0]) {
            return originalText.replaceFirst(oldPart, replacement);
        } else if (isItRegex(oldPart)) {
            return originalText.replaceAll(oldPart, replacement);
        } else {
            return originalText.replace(oldPart, replacement);
        }
    }

    private boolean isItRegex(String input) {
        try {
            Pattern.compile(input);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    protected String subString(String text, int firstIndex, int... lastIndex) {

        if (lastIndex != null && lastIndex.length == 1) {
            return text.substring(firstIndex, lastIndex[0]);
        } else if (lastIndex.length == 0) {
            return text.substring(firstIndex);
        } else {
            log.warn("You send lastIndex param more then once. Online first could be use");
            return text.substring(firstIndex, lastIndex[0]);
        }

    }
    public static String stringToMd5(String Value)
            throws NoSuchAlgorithmException, NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Value.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();

       return  myHash;
    }

}
