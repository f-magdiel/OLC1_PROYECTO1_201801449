/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

/**
 *
 * @author magdiel
 */
public class Nodo {
    public String valor;
    public int id;
    public Nodo nodoizq;
    public Nodo nododer;
    public String first;
    public String last;
    public String anulable;
    
    public Nodo(Nodo _nodoizq,Nodo _nododer,String _valor,int _id){
        this.nododer = _nododer;
        this.nodoizq = _nodoizq;
        this.id = _id;
        this.valor = _valor;
        this.anulable = "";
        this.first  = "";
        this.last = "";
    }
    
}
