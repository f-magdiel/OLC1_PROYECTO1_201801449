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
    
    public String getLexema(){
        return this.lexema;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public int getLinea(){
        return this.linea;
    }
    
    public int getColumna(){
        return this.columna;
    }
}
