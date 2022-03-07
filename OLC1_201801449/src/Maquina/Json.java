
package Maquina;

/**
 *
 * @author magdiel
 */
public class Json {
    public String valor;
    public String expresion;
    public String resultado;
    public Json(String _valor, String _expresion, String _resultado){
        this.valor = _valor;
        this.expresion = _expresion;
        this.resultado = _resultado;
    }
    
    public String getValor(){
        return this.valor;
    }
    
    public String getExpresion(){
        return this.expresion;
    }
    
    public String getResultado(){
        return this.resultado;
    }
    
    public void setValor(String _valor){
        this.valor = _valor;
    }
    
    public void setExpresion(String _expresion){
        this.expresion = _expresion;
    }
    
    public void setResultado(String _resultado){
        this.resultado = _resultado;
    
    }
    
}

