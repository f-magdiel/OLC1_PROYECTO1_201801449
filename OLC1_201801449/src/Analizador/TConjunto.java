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
public class TConjunto {
    String nombre,conjunto;
    public TConjunto(String _nombre,String _conjunto){
        this.nombre = _nombre;
        this.conjunto = _conjunto;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getConjunto(){
        return this.conjunto;
    }
}
