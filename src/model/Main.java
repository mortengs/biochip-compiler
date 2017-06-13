package model;

import ast.Assay;
import ast.Mix;
import ast.Statement;
import components.Component;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.AquaLexer;
import parser.AquaParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Main {

    private static final String VERSION = "v0.1";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String args[]) throws Exception {
        File file;
        if (args.length > 0) {
            // TODO: File with constraints? Or Aqua with constraints?
            switch (args[0]) {
                case "help":
                    printHelp();
                    break;
                case "stream":
                    file = new File(args[1]);
                    try {
                        stream(file);
                    } catch (IOException e) {
                        System.out.println("File"+file.getPath()+" not found");
                    }
                    break;
                default:
                    file = new File(args[0]);
                    try {
                        compile(file);
                    } catch (IOException e) {
                        System.out.println("File: "+file.getPath()+" was not found");
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
        List<String> errs = listener.getErrors();
        List<String> warnings = listener.getWarnings();

        IRSearchTree searchTree = new IRSearchTree(assay.getDeclarations(),assay.getStatements());
        Node<Statement> root = searchTree.root;

        if (errs.size() > 0) {
            for (String err: errs) {
                System.err.println(ANSI_RED + err + ANSI_RESET);
            }
            return;
        }

        for (String warning: warnings) {
            System.err.println(ANSI_YELLOW + warning + ANSI_RESET);
        }

        // REMOVE THIS IN FUTURE. ONLY USED FOR DEBUGGING
        Constraints constraints = new Constraints(0,2,2,2);

        AnalyzerOptimizer analyzerOptimizer = new AnalyzerOptimizer();
        analyzerOptimizer.resourceConstrainedListScheduling(root,constraints);
        Synthesize synthesizer = new Synthesize();
        //synthesizer.synthesize(root);
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

}
