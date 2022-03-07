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
public class OB_TT {
    public String Terminal;
    public String Transicion;
    
    public OB_TT(String Term,String Trans){
        this.Terminal=Term;
        this.Transicion=Trans;
    }
    
    public OB_TT(String Term){
        this.Terminal=Term;
    }
    
    public String OBTENER_TERMINAL_TRANSICION(){
        return Terminal+"::"+Transicion;
    }
    
    
}
