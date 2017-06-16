package model;

import ast.Assay;
import ast.Statement;
import components.Component;
import jdk.nashorn.internal.parser.JSONParser;
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

    private static final String VERSION = "v0.1";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static List<String> errs = new ArrayList<>();
    private static List<String> warnings = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        File aquaFile;
        if (args.length > 0) {
            // In case there is a constraints file
            if (args.length == 2) {
                try {
                    File jsonFile = new File(args[1]);
                    checkFileExtension(jsonFile,".json");
                    InputStream fis = new FileInputStream(jsonFile);

                    JsonReader reader = Json.createReader(fis);
                    JsonObject constraintsObject = reader.readObject();
                    Constraints.setNumberOfFilters(constraintsObject.getInt("numberOfFilters"));
                    Constraints.setNumberOfHeaters(constraintsObject.getInt("numberOfHeaters"));
                    Constraints.setNumberOfHeaters(constraintsObject.getInt("numberOfMixers"));
                    Constraints.setNumberOfHeaters(constraintsObject.getInt("numberOfDetectors"));
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(ANSI_YELLOW+"Warning: No constraints file"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"Using the default values of: "+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"Number of filters: "+Constraints.getNumberOfFilters()+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"Number of heaters: "+Constraints.getNumberOfHeaters()+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"Number of mixers: "+Constraints.getNumberOfMixers()+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"Number of detecters: "+Constraints.getNumberOfDetectors()+ANSI_RESET);
            }
            //
            switch (args[0]) {
                case "help":
                    printHelp();
                    break;
                case "stream":
                    aquaFile = new File(args[1]);
                    try {
                        stream(aquaFile);
                    } catch (IOException e) {
                        System.out.println(ANSI_RED+"File: "+aquaFile.getPath()+" not found"+ANSI_RESET);
                    }
                    break;
                default:
                    aquaFile = new File(args[0]);
                    try {
                        checkFileExtension(aquaFile,".aq");
                        compile(aquaFile);
                    } catch (IOException e) {
                        System.out.println(ANSI_RED+"File: "+aquaFile.getPath()+" was not found"+ANSI_RESET);
                    }
            }
        } else {
            printHelp();
        }
    }

    private static void compile(File file) throws IOException {
        AquaLexer lex = new AquaLexer(new ANTLRFileStream(file.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        AquaParser parser = new AquaParser(tokens);

        AquaParser.AssayContext assayContext = parser.assay();

        ParseTreeWalker walker = new ParseTreeWalker();
        AntlrAquaListener listener = new AntlrAquaListener();
        walker.walk(listener, assayContext);

        Assay assay = listener.getAssay();
        errs = listener.getErrors();
        warnings = listener.getWarnings();

        IRSearchTree searchTree = new IRSearchTree(assay.getDeclarations(),assay.getStatements());
        Node<Statement> statementTree = searchTree.root;

        if (errs.size() > 0) {
            for (String err: errs) {
                System.err.println(ANSI_RED + err + ANSI_RESET);
            }
            return;
        }

        for (String warning: warnings) {
            System.err.println(ANSI_YELLOW + warning + ANSI_RESET);
        }

        AnalyzerOptimizer analyzerOptimizer = new AnalyzerOptimizer(statementTree);
        Node<Component> componentTree = analyzerOptimizer.getComponentTree();
        Synthesize synthesizer = new Synthesize();
        synthesizer.synthesize(componentTree);
    }

    private static void stream(File file) throws IOException {
        StringBuilder out = new StringBuilder();
        String sep = System.lineSeparator();
        out.append(new ANTLRFileStream(file.toString())).append(sep);
        System.out.println(out.toString());
    }

    private static void printHelp() {
        StringBuilder out = new StringBuilder();
        String sep = System.lineSeparator();

        out.append("Aqua -> MHDL compiler ").append(VERSION).append(sep);
        out.append("Input:").append(sep);
        out.append("help").append(sep);

        System.out.println(out.toString());
    }

    private static void checkFileExtension(File file, String extension) {
        String name = file.getName();
        if (!name.endsWith(extension)) {
            System.out.println(ANSI_RED+"ERROR: "+name+" is not a .aq file");
        }
    }
}
