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
package net.hedges.fandangled.codec.docx;

import junit.framework.TestCase;
import net.hedges.fandangled.bindings.builder.StringUtils;


public class StringUtilsTest extends TestCase {
    public void testRepeatedSpace() {
        String input = "\"This is a test with two spaces:  this is a test with three spaces:    !\"";
        String expected = "This is a test with two spaces: this is a test with three spaces: !";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespace() {
        String input = "\"This is a test with two whitespaces: \tthis is a test with three whitespaces:\t \t!\"";
        String expected = "This is a test with two whitespaces: this is a test with three whitespaces: !";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespaceWithSingleReturns() {
        String input = "\"This is a test with two whitespaces: \r\nthis is a test with three whitespaces: \n!\"";
        String expected = "This is a test with two whitespaces: this is a test with three whitespaces: !";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespaceWithSingleReturnsSeparatedByWhitespace00() {
        String input = "\"This is a test with two whitespaces: \r\nthis is a test with three whitespaces:\n \n!\"";
        String expected = "This is a test with two whitespaces: this is a test with three whitespaces:\n\n!";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespaceWithSingleReturnsSeparatedByWhitespace01() {
        String input = "\"This is a test with two whitespaces: \r\nthis is a test with three\t\t\r\n\t\twhitespaces:\n\t\n!\"";
        String expected = "This is a test with two whitespaces: this is a test with three whitespaces:\n\n!";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespaceWithSingleWindowsNLSeparatedByWhitespace01() {
        String input = "\"This is a test with two whitespaces: \r\nthis is a test with three\t\r\n\t\r\n\t\twhitespaces:\n\t\n!\"";
        String expected = "This is a test with two whitespaces: this is a test with three\n\nwhitespaces:\n\n!";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

    public void testRepeatedWhitespaceWithSingleWindowsNLSeparatedByWhitespace02() {
        String input = "\"This is a test with two whitespaces: \t     \r\nthis is a test with three\t\r\n\t  \n\t\twhitespaces:\n\t\n!\"";
        String expected = "This is a test with two whitespaces: this is a test with three\n\nwhitespaces:\n\n!";
        assertEquals("Not what we expected", expected, StringUtils.normalise(input));
    }

}
