//importaciones
package  Analizador;
import java_cup.runtime.*;
import java.util.LinkedList;

%%

%{
    //code java
    //lista enlaza para los errores lexicos
    public static LinkedList<TError> TablaErrorLexico = new LinkedList<TError>();
%}

%public
%class scanner
%cup
%char
%column
%ignorecase
%line
%unicode

%init{
    yyline = 1;
    yycolumn = 1;
%init}


//palabras reservadas
conj  ="conj"
rango ="~"

//expresiones
espacio = [\ \r\t\f\t]
enter = [\ \n]
entero = [0-9]+
letra = [A-Za-zñÑ]
mayus = [A-ZÑ]
minus = [a-zñ]
identificador = {letra}({letra}|{entero}|"_")*
comentario_s = "//"~(\n|\r)
comentario_d = "<!"~"!>"
HexDigit   = [0-9a-fA-F]
OctDigit   = [0-7]
StringCharacter = [^\u2028\u2029\u000A\u000B\u000C\u000D\u0085\"\\]
EscapeSequence = \\[^\u2028\u2029\u000A\u000B\u000C\u000D\u0085]|\\+u{HexDigit}{4}|\\[0-3]?{OctDigit}{1,2}
cadenasc        = \"({StringCharacter}|{EscapeSequence})*\"

%%

//palabras reservadas en lexico
<YYINITIAL> {conj}   {System.out.println("Encontro: ["+yytext()+"]");
                     return new Symbol(sym.conj,yyline,(int) yychar, yytext());}

//SIMBOLOS
<YYINITIAL> <<EOF>>     {System.out.println("Encontro: ["+yytext()+"]");
                        return new Symbol(sym.EOF,yyline,(int) yychar, yytext());}

<YYINITIAL> "!"     {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.admiracion,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\\""  {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.comdob,yyline,(int) yychar, yytext());}

<YYINITIAL> "\""      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.comdob,yyline,(int) yychar, yytext());}

<YYINITIAL> "#"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.numeral,yyline,(int) yychar, yytext());}

<YYINITIAL> "$"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.dolar,yyline,(int) yychar, yytext());}

<YYINITIAL> "%"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.porcentaje,yyline,(int) yychar, yytext());}

<YYINITIAL> "&"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.ampersand,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\\'"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.comsimp,yyline,(int) yychar, yytext());}

<YYINITIAL> "\'"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.comsimp,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\n"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.nuevalinea,yyline,(int) yychar, yytext());}

<YYINITIAL> "("      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.parizq,yyline,(int) yychar, yytext());}

<YYINITIAL> ")"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.parder,yyline,(int) yychar, yytext());}

<YYINITIAL> "*"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.asterisco,yyline,(int) yychar, yytext());}

<YYINITIAL> "+"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.suma,yyline,(int) yychar, yytext());}

<YYINITIAL> ","      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.coma,yyline,(int) yychar, yytext());}

<YYINITIAL> "-"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.guion,yyline,(int) yychar, yytext());}

<YYINITIAL> "."      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.punto,yyline,(int) yychar, yytext());}

<YYINITIAL> "/"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.diagonal,yyline,(int) yychar, yytext());}

<YYINITIAL> ":"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.doblepto,yyline,(int) yychar, yytext());}

<YYINITIAL> ";"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.ptocoma,yyline,(int) yychar, yytext());}

<YYINITIAL> "<"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.menorque,yyline,(int) yychar, yytext());}

<YYINITIAL> "="      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.igual,yyline,(int) yychar, yytext());}

<YYINITIAL> ">"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.mayorque,yyline,(int) yychar, yytext());}

<YYINITIAL> "?"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.interrogacion,yyline,(int) yychar, yytext());}

<YYINITIAL> "@"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.arroba,yyline,(int) yychar, yytext());}

<YYINITIAL> "["      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.corcheteizq,yyline,(int) yychar, yytext());}

<YYINITIAL> "\\"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.diagonalinv,yyline,(int) yychar, yytext());}

<YYINITIAL> "]"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.corcheteder,yyline,(int) yychar, yytext());}

<YYINITIAL> "^"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.potencia,yyline,(int) yychar, yytext());}

<YYINITIAL> "_"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.guionbajo,yyline,(int) yychar, yytext());}

<YYINITIAL> "`"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.apostrofe,yyline,(int) yychar, yytext());}

<YYINITIAL> "{"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.llaveizq,yyline,(int) yychar, yytext());}

<YYINITIAL> "|"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.barra,yyline,(int) yychar, yytext());}

<YYINITIAL> "}"      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.llaveder,yyline,(int) yychar, yytext());}

<YYINITIAL> {rango}      {System.out.println("Encontro: ["+yytext()+"]");
                         return new Symbol(sym.rango,yyline,(int) yychar, yytext());}

<YYINITIAL> {entero}      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.entero,yyline,(int) yychar, yytext());}

<YYINITIAL> {mayus}      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.mayus,yyline,(int) yychar, yytext());}

<YYINITIAL> {minus}      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.minus,yyline,(int) yychar, yytext());}

<YYINITIAL> {identificador}      {System.out.println("Encontro: ["+yytext()+"]");
                    return new Symbol(sym.identificador,yyline,(int) yychar, yytext());}

<YYINITIAL> {cadenasc}          { System.out.println("Encontro: ["+yytext()+"] Cadena de Texto"); 
                                  return new Symbol(sym.cadenasc,yyline,(int) yychar, yytext());}

//caracteres ignorados o saltados
<YYINITIAL> {enter}      {}
<YYINITIAL> {espacio}  {yychar = 1;}
<YYINITIAL> {comentario_s}  {}
<YYINITIAL> {comentario_d}  {}

//Los errores lexicos
<YYINITIAL> . {
             System.out.println("Lexical error: "+yytext()+", Row: "+yyline+", Col: "+yycolumn);
             TError errores = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe");
             TablaErrorLexico.add(errores);
}

