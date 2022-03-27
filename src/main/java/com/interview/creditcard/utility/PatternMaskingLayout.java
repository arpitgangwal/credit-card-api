package com.interview.creditcard.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

@Component("patternMaskingLayout")
public class PatternMaskingLayout extends PatternLayout {

    private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) { // invoked for every single entry in the xml
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(String.join("|", maskPatterns), // build pattern using logical OR
                Pattern.MULTILINE);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event)); // calling superclass method is required
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }
        // String builder will contain our log value.
        StringBuilder sb = new StringBuilder(message);
        //matcher will contain the pattern : creditCard=\d+|(\w*password([^\s+]*)"?\s?[(:?)|(=?)])([^\s]*) and matched field name.
        Matcher matcher = multilinePattern.matcher(sb);
        while (matcher.find()) {
            if (matcher.group().contains("cardNumber") || matcher.group().contains("securityCode")) {
                maskFieldData(sb, matcher);
            }
        }
        return sb.toString();
    }

    private void maskFieldData(StringBuilder sb, Matcher matcher) {
        // here is our main logic for masking sensitive data
        String targetExpression = matcher.group();
        String[] split = null;
        if (targetExpression.contains("=")) {
            split = targetExpression.split("=");
        }
        else {
            split = targetExpression.split(":");
        }
        if (split != null) {

            String pan = split[1];
            String maskedPan="";
            if(split[0].equalsIgnoreCase("securityCode"))
             maskedPan = CreditCardUtility.getMaskedPan(pan);
            else if(split[0].equalsIgnoreCase("cardNumber"))
                maskedPan=  CreditCardUtility.getMaskedCreditCardNumber( pan);
            int start = matcher.start() + split[0].length() + 1;
            int end = matcher.end();
            sb.replace(start, end, maskedPan);
        }
    }
}