package Analizador;

/**
 *
 * @author magdi
 */
public class TError {
    String lexema, tipo,descripcion;
    int linea,columna;
    
    public TError(String lexe, int lin,int column, String tip, String des){
        this.lexema = lexe;
        this.linea = lin;
        this.columna = column;
        this.tipo = tip;
        this.descripcion = des;
    }
}
