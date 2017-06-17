package model;

import ast.Assay;
import ast.Statement;
import components.Component;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.AquaLexer;
import parser.AquaParser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Main {

    private static final Double VERSION = 1.0;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    /* Errors are defined as something written in the aqua file that is not supposed to
       happen and may result in problem further on. */
    private static List<String> errs = new ArrayList<>();
    /* Warnings are defined as something written in the aqua file that is legal to do, but
       probably doesn't do what the user thinks it has to. */
    private static List<String> warnings = new ArrayList<>();

    public static void main(String args[]) {
        File aquaFile;
        // Handle the different user input
        if (args.length > 0) {
            switch (args[0]) {
                case "help":
                case "-h":
                    printHelp();
                    break;
                case "stream":
                    try {
                        aquaFile = new File(args[1]);
                        stream(aquaFile);
                    } catch (IOException e) {
                        System.err.println(ANSI_RED+e.getMessage()+ANSI_RESET);
                    }
                    break;
                default:
                    // In case there is a constraints file
                    try {
                        aquaFile = new File(args[0]);
                        // Handle if the user specifies some constraints
                        setConstraintsValues(args);
                        // Check file extension
                        checkFileExtension(aquaFile,".aq");
                        compile(aquaFile);
                    } catch (IOException e) {
                        System.err.println(ANSI_RED+e.getMessage()+ANSI_RESET);
                    }
            }
        } else {
            printHelp();
        }
    }

    private static void compile(File file) throws IOException {

        // Main structure of what the compiler handles first.
        // Reading the file and lexing it in order to get a tokenStream
        AquaLexer lex = new AquaLexer(new ANTLRFileStream(file.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        // Simple parsing to get ANTLR's IR
        AquaParser parser = new AquaParser(tokens);
        AquaParser.AssayContext assayContext = parser.assay();

        // Initialize the class to parse ANTLR's IR into a list of operations
        AntlrAquaListener listener = new AntlrAquaListener();

        // Now parse it with ANTLR's parser
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, assayContext);

        // Get the set of operations
        Assay assay = listener.getAssay();
        errs = listener.getErrors();
        warnings = listener.getWarnings();

        // Build the application graph. This is the IR of the operations
        IRSearchTree searchTree = new IRSearchTree(assay.getDeclarations(),assay.getStatements());
        /* ------- Application graph IR -------- */
        Node<Statement> statementTree = searchTree.root;

        // If there were errors with building this application graph, stop the compiler.
        // This way we can be sure to never have null values further on.
        if (errs.size() > 0) {
            for (String err: errs) {
                System.err.println(ANSI_RED + err + ANSI_RESET);
            }
            return;
        }

        for (String warning: warnings) {
            System.err.println(ANSI_YELLOW + warning + ANSI_RESET);
        }

        // Create the component tree. This is the IR of all the components allocated and their direction
        AnalyzerOptimizer analyzerOptimizer = new AnalyzerOptimizer(statementTree);

        /* ------- Component graph IR -------- */
        Node<Component> componentTree = analyzerOptimizer.getComponentTree();
        Synthesize synthesizer = new Synthesize();
        if (!synthesizer.synthesize(assay.getIdentifier(),componentTree)) {
            System.out.println(ANSI_RED+"ERROR: Unable to write file"+ANSI_RESET);
        }
    }

    // Prints out the aqua code in command line.
    private static void stream(File file) throws IOException {
        StringBuilder out = new StringBuilder();
        String sep = System.lineSeparator();
        out.append(new ANTLRFileStream(file.toString())).append(sep);
        System.out.println(out.toString());
    }

    // Prints out instruction of how to run the compiler.
    private static void printHelp() {
        StringBuilder out = new StringBuilder();
        String sep = System.lineSeparator();

        out.append("Aqua -> MHDL compiler").append(sep);
        out.append("Version: ").append(VERSION).append(sep);
        out.append("Usage: ").append(sep);
        out.append("biochip-compiler stream [Aqua file]\t\tPrint Aqua file to CL").append(sep);
        out.append("biochip-compiler [Aqua file]\t\t\tCompile Aqua file with default constraints").append(sep);
        out.append("biochip-compiler [Aqua file] [Constraints file]\tCompile Aqua file with constraints");

        System.out.println(out.toString());
    }

    // Handle new constraints.
    private static void setConstraintsValues(String[] args) throws IOException {
        // If there is a file containing constraints
        if (args.length == 2) {
            File jsonFile = new File(args[1]);
            checkFileExtension(jsonFile,".json");
            InputStream fis = new FileInputStream(jsonFile);

            JsonReader reader = Json.createReader(fis);
            JsonObject constraintsObject = reader.readObject();

            // The json file MUST include all the constraints
            Constraints.setNumberOfFilters(constraintsObject.getInt("numberOfFilters"));
            Constraints.setNumberOfHeaters(constraintsObject.getInt("numberOfHeaters"));
            Constraints.setNumberOfMixers(constraintsObject.getInt("numberOfMixers"));
            Constraints.setNumberOfDetectors(constraintsObject.getInt("numberOfDetectors"));

            if (Constraints.getNumberOfDetectors() == 0 || Constraints.getNumberOfMixers() == 0 || Constraints.getNumberOfHeaters() == 0 || Constraints.getNumberOfFilters() == 0) {
                System.out.println(ANSI_YELLOW+"Warning: Declaration of 0 numbers of a component is ignored"+ANSI_RESET);
            }

            reader.close();
        // Else give a warning and show default constraints
        } else {
            System.out.println(ANSI_YELLOW+"Warning: No constraints file"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"Using the default values of: "+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"Number of filters: "+Constraints.getNumberOfFilters()+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"Number of heaters: "+Constraints.getNumberOfHeaters()+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"Number of mixers: "+Constraints.getNumberOfMixers()+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"Number of detecters: "+Constraints.getNumberOfDetectors()+ANSI_RESET);
        }
    }

    private static void checkFileExtension(File file, String extension) {
        String name = file.getName();
        if (!name.endsWith(extension)) {
            System.out.println(ANSI_RED+"ERROR: "+name+" is not a "+extension+" file");
        }
    }
}
