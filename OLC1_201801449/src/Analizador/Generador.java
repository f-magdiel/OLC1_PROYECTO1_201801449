
package Analizador;

/**
 *
 * @author magdi
 */
public class Generador {
    public static void main(String[] args){
    
        generadorCompilador();
    }
    
    private static void generadorCompilador(){
        try{
            String ruta = "src/Analizador/";
            String opcFlex[] = {ruta+"Lexico.jflex","-d",ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir",ruta,"-parser","parser",ruta+"Sintactico.cup"};
            java_cup.Main.main(opcCUP);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
