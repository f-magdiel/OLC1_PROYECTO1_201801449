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
public class Nodo{
    public String valor;
    public int id;
    public Nodo nodoizq;
    public Nodo nododer;
    public String first;
    public String last;
    public String anulable;
    
    public Nodo(Nodo _nodoizq,Nodo _nododer,String _valor,int _id){
        this.nododer = null;
        this.nodoizq = null;
        this.id = _id;
        this.valor = _valor;
        this.anulable = "";
        this.first  = "";
        this.last = "";
    }
    
    public String getValor(){
        return this.valor;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Nodo getNodoIzquierdo(){
        return this.nodoizq;
    }
    
    public Nodo getNodoDerecho(){
        return this.nododer;
    }
    
    public String getFirst(){
        return this.first;
    }
    
    public String getLast(){
        return this.last;
    }
    
    public String getAnulable(){
        return this.anulable;
    }
    
    public void setValor(String _valor){
        this.valor = _valor;
    }
    
    public void setId(int _id){
        this.id = _id;
    }
    
    public void setNodoIzquierdo(Nodo _nodoizq){
        this.nodoizq = _nodoizq;
    }
    
    public void setNodoDerecho(Nodo _nododer){
        this.nododer = _nododer;
    }
    
    public void setFirst(String _first){
        this.first = _first;
    }
    
    public void setLast(String _last){
        this.last = _last;
    }
    
    public void setAnulable(String _anulable){
        this.anulable = _anulable;
    }
    
    public String getNodosEstructura(){
           String etiqueta="";
        if (nodoizq == null && nododer == null) {
            if(valor.equals("|")){
               etiqueta = "nodo" + id + " [ label =\"{"+anulable+"|{"+first+"|\\"+ valor+"|"+last+"}|"+id+"}"+"\"];\n";
            }else{
                etiqueta = "nodo" + id + " [ label =\"{"+anulable+"|{"+first+"|"+ valor+"|"+last+"}|"+id+"}"+"\"];\n";
            }
            
        } else {
            if(valor.equals("|")){
               etiqueta = "nodo" + id + " [ label =\"{"+anulable+"|{"+first+"|\\"+ valor+"|"+last+"}|"+id+"}"+"\"];\n";
            }else{
                etiqueta = "nodo" + id + " [ label =\"{"+anulable+"|{"+first+"|"+ valor+"|"+last+"}|"+id+"}"+"\"];\n";
            }
            
        }
        if (nodoizq != null) {
            etiqueta = etiqueta + nodoizq.getNodosEstructura()
                    + "nodo" + id + "->nodo" + nodoizq.id + "\n";
        }
        if (nododer != null) {
            etiqueta = etiqueta + nododer.getNodosEstructura()
                    + "nodo" + id + "->nodo" + nododer.id + "\n";
        }
        return etiqueta;
    }
}
