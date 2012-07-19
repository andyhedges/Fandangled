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
package net.hedges.fandangled.commandline;

import net.hedges.fandangled.bindings.builder.FandangledBindingException;
import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.generic.GenericCodec;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;

public class GenericCli {

    private static Options options;
    private static boolean verbose = false;

    public static void main(String[] args) {
        options = buildOptions();

        try {
            CommandLineParser parser = new GnuParser();
            CommandLine line = parser.parse(options, args);
            verbose = line.hasOption('v');
            if(line.hasOption('h')){
                usage();
                System.exit(0);
            }
            String template = line.getOptionValue("templates");
            String input = line.getOptionValue("input");
            String output = line.getOptionValue("output");

            String prefix = line.getOptionValue("prefix", "");
            String extension = line.getOptionValue("extension", ".txt");

            File inputFile = new File(input);
            File outputDir = new File(output);
            File templateDir = new File(template);

            checkFilesAndDirectories(inputFile, outputDir, templateDir);

            GenericCodec codec = new GenericCodec();
            codec.setTemplatePath(templateDir.getAbsolutePath());
            codec.setExtension(extension);
            codec.setPrefix(prefix);

            Interface _interface = InterfaceBuilder.parse(inputFile);

            codec.encode(_interface, outputDir);

        } catch (ParseException e) {
            System.err.print(e.getMessage() + "\n");
            usage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkFilesAndDirectories(File inputFile, File outputDir, File templateDir){
            if (!inputFile.isFile() || !inputFile.exists()) {
                usage();
                throw new RuntimeException("idl file " + inputFile + " doesn't exist or isn't a file");
            }

            if (!templateDir.isDirectory() || !templateDir.exists()) {
                usage();
                throw new RuntimeException("template directory " + templateDir + " doesn't exist or isn't a directory");
            }

            outputDir.mkdirs();
            if (outputDir.isFile() || !outputDir.exists()) {
                usage();
                throw new RuntimeException("output directory " + outputDir + " is a file or doesn't exist");
            }

            if (verbose) {
                System.out.println("template: " + templateDir.getAbsolutePath());
                System.out.println("input: " + inputFile.getAbsolutePath());
                System.out.println("output: " + outputDir.getAbsolutePath());
            }
    }

    public static Options buildOptions() {
        Options options = new Options();
        options.addOption(
                OptionBuilder.withLongOpt("templates")
                        .withDescription("the directory containing freemarker templates")
                        .hasArg()
                        .isRequired()
                        .withArgName("directory")
                        .create("t"));
        options.addOption(
                OptionBuilder.withLongOpt("output")
                        .withDescription("the directory to which generated files will be outputted")
                        .hasArg()
                        .isRequired()
                        .withArgName("directory")
                        .create("o"));
        options.addOption(
                OptionBuilder.withLongOpt("input")
                        .withDescription("the Fandangled IDL to use")
                        .hasArg()
                        .isRequired()
                        .withArgName("idl")
                        .create("i"));
        options.addOption(
                OptionBuilder.withLongOpt("prefix")
                        .withDescription("the prefix to give each file name")
                        .hasArg()
                        .withArgName("prefix")
                        .create("p"));
        options.addOption(
                OptionBuilder.withLongOpt("extension")
                        .withDescription("the extension to give each file name")
                        .hasArg()
                        .withArgName("extension")
                        .create("e"));
        options.addOption(
                OptionBuilder.withLongOpt("verbose")
                        .withDescription("verbose output")
                        .create("v"));
        options.addOption(
                OptionBuilder.withLongOpt("help")
                        .withDescription("display usage")
                        .create("h"));
        return options;
    }

    public static void usage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("generic", "Fandangled generic cli\n", options, "", true);
    }

}
