/*
   Copyright 2010 Andy Hedges <andy@hedges.net>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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
