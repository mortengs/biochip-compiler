package model;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Main {

    private static final String VERSION = "v0.1";

    public static void main(String args[]) throws Exception {
        if (args.length > 0) {
            switch (args[0]) {
                case "help":
                    printHelp();
                    break;
                default:
                    File file = new File(args[0]);
                    try {
                        compile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
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
    }

    private static void printHelp() {
            StringBuilder out = new StringBuilder();
            String sep = System.lineSeparator();

            out.append("Aqua -> MHDL compiler ").append(VERSION).append(sep);
            out.append("Input:").append(sep);
            out.append("Help").append(sep);

            System.out.println(out.toString());
    }

}
