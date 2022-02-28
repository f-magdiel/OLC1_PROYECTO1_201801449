/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

/**
 *
 * @author magdi
 */
public class TLexemas {
    String nombre,lexema;
    public TLexemas(String _nombre,String _lexema){
        this.nombre = _nombre;
        this.lexema = _lexema;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getLexema(){
        return this.lexema;
    }
}
