package net.hedges.fandangled.bindings.builder;

public class StringUtils {

    public static String normalise(String text) {
        text = text.substring(1, text.length() - 1);
        text.replaceAll("\\\"", "\"");
        String normalisedText = "";

        String appendString = "";
        int countNl = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                normalisedText += appendString;
                normalisedText += c;
                countNl = 0;
                appendString = "";
            } else if(c == '\n'){
                countNl++;
            } else if(Character.isWhitespace(c) && countNl == 0) {
                appendString = " ";
            }

            if(countNl >= 2){
                appendString = "";
                for(int nl = 0; nl < countNl/2; nl++){
                    appendString += "\n\n";
                }
            } else if (countNl == 1){
                appendString = " ";
            }
        }

        return normalisedText;
    }

}
