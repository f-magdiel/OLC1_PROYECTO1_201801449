/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trans;

/**
 *
 * @author USER
 */
public class OB_EC {
    private String Estado;
    private String ConjuntoEstado;
    
    public OB_EC(String Est,String ConEst){
        this.Estado=Est;
        this.ConjuntoEstado=ConEst;
    }
    
    
    public String RETORNAR_ESTADO_CONJUNTO(){
        return Estado+"{"+ConjuntoEstado+"}";
    }
    
    public String GET_CONJUNTOESTADO(){
        return ConjuntoEstado;
    }
    
    public void SET_ESTADO(String est){
        Estado=est;
    }
    
    public String GET_ESTADO(){
        return Estado;
    }
    
    public String[] OBTENER_CONJUNTOS_ESTADO(){
        return ConjuntoEstado.split(",");
    }
    
    
}
