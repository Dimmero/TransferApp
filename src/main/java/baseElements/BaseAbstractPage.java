package baseElements;

import core.SeleniumDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseAbstractPage {
    public static SeleniumDriver driver;
    public static final String URL_TRANSFER = "https://www.dell.com/support/assets-transfer/en-vn";
//    public static final String URL_STATS = "https://www.dell.com/support/home/en-vn?app=drivers";
    public static final String URL_STATS = "https://www.dell.com/support/home/en-vn";
    public final String COUNTRY_POLAND = "PL";
    public final String BODY_TAG = "body";

    public Boolean matchFound(String patternValue, String value) {
        Pattern pattern = Pattern.compile(patternValue);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
