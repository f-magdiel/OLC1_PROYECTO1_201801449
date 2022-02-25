package org.menu;
import Analizador.parser;
import Analizador.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
/**
 *
 * @author magdi
 */
public class Menu {
    public static void main(String[]args){
        
        try{
            String entrada = "{\n" +
            "////// CONJUNTOS\n" +
            "CONJ: letra -> a~z;\n" +
            "CONJ: digito -> 0~9;\n" +
            "/////// EXPRESIONES REGULARES\n" +
            "ExpReg1 -> . {letra} * | \"_\" | {letra} {digito};\n" +
            "ExpresionReg2 -> . {digito} . \".\" + {digito};\n" +
            "RegEx3 -> . {digito} * | \"_\" | {letra} {digito};\n" +
            "%%\n" +
            "%%\n" +
            "ExpReg1 : \"primerLexemaCokoa \";\n" +
            "ExpresionReg2 : \"34.44\";\n" +
            "}";
            
           
            System.out.println("Iniciando analisis....");
            scanner scan = new scanner(new BufferedReader(new StringReader(entrada)));
            parser parser = new parser(scan);
            parser.parse();
            System.out.println("Finaliza analisis...");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
