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
public class TExpresiones {
    String nombre,expresion;
    
    public TExpresiones(String _nombre, String _expresion){
        this.nombre = _nombre;
        this.expresion = _expresion;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getExpresion(){
        return this.expresion;
    }
}
