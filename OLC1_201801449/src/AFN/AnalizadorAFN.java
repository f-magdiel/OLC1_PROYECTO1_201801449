
package AFN;

/**
 *
 * @author magdiel
 */
public class AnalizadorAFN {
    public String expresion="";
    public String alfabeto="";
    public String estados="";
   
    public void analizador(String er){
        this.expresion = er.replace("{", ",").replace("}", ",").replace("+", ",").replace("*", ",").replace("|", ",").replace("\"", "").replace("?", ",");
        System.out.println("Analizador expresion");
        System.out.println(this.expresion);
    }
}
