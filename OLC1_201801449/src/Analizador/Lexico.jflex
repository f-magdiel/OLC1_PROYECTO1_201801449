//importaciones
package  Analizador;
import java_cup.runtime.*;
import java.util.LinkedList;

%%

&{
    //code java
    //lista enlaza para los errores lexicos
    public static LinkedList<TError> TablaErrorLexico = new LinkedList<TError>();
%}

%public
%class Analizador_Lexico
%cupsym Sym
%cup
%char
%column
%full
%ignorecase
%line
%unicode
%init{
    yyline = 1;
    yycolumn = 1;
%init}

%%

//palabras reservadas
CONJUNTO = "CONJ"
RANGO = "~" 

//expresiones
ESPACIO = [\ \r\t\f\t]
ENTER = [\ \n]
ENTERO = [0-9]+
LETRA = [a-zA-ZñÑ]
MAYUS = [A-ZÑ]
MINUS = [a-zñ]
IDENTIFICADOR = {LETRA}({LETRA}|{ENTERO}|"_")*
COMENTARIO_S = "//"~(\n|\r)
COMENTARIO_D = "<!"~"!>"

%%

//palabras reservadas en lexico
<YYINITIAL> {conj}   printL("Encontro: ["+yytext()+"]");
                     return new Symbol(Sym.conj,yyline,(int) yychar, yytext());}

//SIMBOLOS
<YYINITIAL> <<OEF>>     printL("Encontro: ["+yytext()+"]");
                        return new Symbol(Sym.OEF,yyline,(int) yychar, yytext());}

<YYINITIAL> "!"     printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.admiracion,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\\""  printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.comdob,yyline,(int) yychar, yytext());}

<YYINITIAL> "\""      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.comdob,yyline,(int) yychar, yytext());}

<YYINITIAL> "#"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.numeral,yyline,(int) yychar, yytext());}

<YYINITIAL> "$"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.dolar,yyline,(int) yychar, yytext());}

<YYINITIAL> "%"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.porcentaje,yyline,(int) yychar, yytext());}

<YYINITIAL> "&"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.ampersand,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\\'"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.comsimp,yyline,(int) yychar, yytext());}

<YYINITIAL> "\'"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.comsimp,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\n"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.nuevalinea,yyline,(int) yychar, yytext());}

<YYINITIAL> "("      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.parizq,yyline,(int) yychar, yytext());}

<YYINITIAL> ")"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.parder,yyline,(int) yychar, yytext());}

<YYINITIAL> "*"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.asterisco,yyline,(int) yychar, yytext());}

<YYINITIAL> "+"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.mas,yyline,(int) yychar, yytext());}

<YYINITIAL> ","      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.coma,yyline,(int) yychar, yytext());}

<YYINITIAL> "-"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.guion,yyline,(int) yychar, yytext());}

<YYINITIAL> "."      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.punto,yyline,(int) yychar, yytext());}

<YYINITIAL> "/"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.diagonal,yyline,(int) yychar, yytext());}

<YYINITIAL> ":"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.doblepto,yyline,(int) yychar, yytext());}

<YYINITIAL> ";"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.ptocoma,yyline,(int) yychar, yytext());}

<YYINITIAL> "<"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.menorque,yyline,(int) yychar, yytext());}

<YYINITIAL> "="      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.igual,yyline,(int) yychar, yytext());}

<YYINITIAL> ">"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.mayorque,yyline,(int) yychar, yytext());}

<YYINITIAL> "?"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.interrogacion,yyline,(int) yychar, yytext());}

<YYINITIAL> "@"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.arroba,yyline,(int) yychar, yytext());}

<YYINITIAL> "["      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.corcheteizq,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.diagonalinv,yyline,(int) yychar, yytext());}

<YYINITIAL> "]"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.corcheteder,yyline,(int) yychar, yytext());}

<YYINITIAL> "^"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.potencia,yyline,(int) yychar, yytext());}

<YYINITIAL> "_"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.guionbajo,yyline,(int) yychar, yytext());}

<YYINITIAL> "`"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.apostrofe,yyline,(int) yychar, yytext());}

<YYINITIAL> "{"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.llaveizq,yyline,(int) yychar, yytext());}

<YYINITIAL> "|"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.barra,yyline,(int) yychar, yytext());}

<YYINITIAL> "}"      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.llaveder,yyline,(int) yychar, yytext());}

<YYINITIAL> {RANGO}      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.rango,yyline,(int) yychar, yytext());}

<YYINITIAL> {ENTERO}      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.entero,yyline,(int) yychar, yytext());}

<YYINITIAL> {MAYUS}      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.mayus,yyline,(int) yychar, yytext());}

<YYINITIAL> {MINUS}      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.minus,yyline,(int) yychar, yytext());}

<YYINITIAL> {IDENTIFICADOR}      printL("Encontro: ["+yytext()+"]");
                    return new Symbol(Sym.identificador,yyline,(int) yychar, yytext());}

//caracteres ignorados o saltados
<YYINITIAL> {ENTER}      {}
<YYINITIAL> {ESPACIO}  {yychar = 1;}
<YYINITIAL> {COMENTARIO_S}  {}
<YYINITIAL> {COMENTARIO_D}  {}

//Los errores lexicos
<YYINITIAL> . {
             printL("Lexical error: "+yytext()+", Row: "+yyline+", Col: "+yycolumn);
             TError errores = new TError(yytext,yyline,yycolumn,"Error Lexico","Simbolo no existe");
             TablaErrorLexico.add(errores);
}

