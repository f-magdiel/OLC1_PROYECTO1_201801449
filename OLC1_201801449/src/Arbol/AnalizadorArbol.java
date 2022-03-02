package Arbol;
import Arbol.Nodo;
/**
 *
 * @author magdiel
 */
public class AnalizadorArbol{
    public Nodo raiz;
    public Nodo aux1;
    public Nodo aux2;
    
    public int contadorid=0;
    public AnalizadorArbol(){
        this.raiz = null;
        this.aux1=null;
        this.aux2=null;
    }
    
    public void insercion(String cadenaser){
        this.contadorid++;
        this.raiz = insertarArbol(this.raiz,cadenaser,this.contadorid);
        
    }
    
    
    public Nodo insertarArbol(Nodo nodo,String caracter,int id){
        
        if(nodo==null){ // para ver si raiz es null
            nodo = new Nodo(null,null," ",0);
            nodo.valor= " ";
            nodo.nododer = new Nodo(null,null,caracter,id);
            
        }else{// si raiz no es null
            if(caracter.equals(".") || caracter.equals("|")){
                nodo.valor = caracter;//el nodo principal es seteado
            }else if(caracter.equals("+") || caracter.equals("*") || caracter.equals("?")){
                if(nodo==null){//si todavia hay espacio
                    nodo.valor = caracter;
                    //se crea otra rama
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null," ",0);
                    nuevonodo.nododer = aux1;
                    nodo = nuevonodo;
                    aux1=null;
                }else{//ya no hay espacio es necesario crear otra rama
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null,caracter,id);//cuando ya no hay espacio y se setea *,?,+
                    nuevonodo.nododer = aux1;
                    nodo = nuevonodo;
                    aux1=null; //retorna el nodo
                    
                    // se crea nueva rama otra vez
                    aux1 = nodo;
                    Nodo otronodo = new Nodo(null,null," ",0);
                    otronodo.nododer = aux1;
                    nodo = otronodo;
                    aux1= null;
                }
                
            }else{
                if(nodo.nododer==null){
                    nodo.nododer = new Nodo(null,null,caracter,0);
                }else if(nodo.nodoizq==null){
                    nodo.nodoizq = new Nodo(null,null,caracter,0);
                }else{//se crea una rama nuevo
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null," ",id);
                    nuevonodo.nododer = aux1;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,0);
                    nodo = nuevonodo;
                    aux1=null;
                    
                }
            }
            
        }
        return nodo;
    }
    //************************************ANALIZADOR ER***********************
    public void entradaAnalizador(String expresionregular){
        String[] alfabeto = this.analizadorArbol(expresionregular);
       
        System.out.println("SEPERADOS**************************");
        for (int i = alfabeto.length-1; i >= 0; i--) {
            //System.out.println(alfabeto[i]);
            this.insercion(alfabeto[i].replace(" ",""));
        }
        //para cerrar arbol
        Nodo nuevonodo = new Nodo(null,null,".",0);
        nuevonodo.nodoizq = this.raiz;
        nuevonodo.nododer = new Nodo(null,null,"#",0);
        this.raiz = nuevonodo;
        this.raiz = null;
        System.out.println("FIN*********************************");
        //se agrega el . y el # al final del arbol  
    }
    
    public String[] analizadorArbol(String er){
        String resultado="";
        int counterE=0;
        int caractercola=0;
        boolean banderacomilla = false;
        boolean banderasaltolinea = false;
        
        for (int i = 0; i < er.length(); i++) {
            char caracter = er.charAt(i);
            if(this.validacionSym(caracter) && counterE==0){
                resultado+=caracter+",";
            }else if(caracter==125){
                resultado+=",";
            }else if(caracter==123){
            
            }else if(caracter==34){
                int valorant=i;
                int valorsig = valorant+1;
                
                boolean band = valorsig<er.length()?true:false;
                
                if(band && er.charAt(valorsig)=='\"' && counterE==1 && !banderasaltolinea && caracter=='\"'){
                    resultado+=caracter+"'";
                    resultado+=",";
                    i = valorsig;
                    counterE = 0;
                    banderasaltolinea  = false;
                    banderacomilla = false;
                }else{
                    counterE++;
                    resultado+="'";
                    banderacomilla=true;
                }
                
                if(counterE>=2){
                    resultado+=",";
                    counterE = 0;
                    banderasaltolinea = false;
                    banderacomilla = false;
                }
            }else{
                resultado+=caracter;
                if(caracter!=92 && banderacomilla){
                    banderasaltolinea = true;
                }
            }
            
        }
        return resultado.split(",");
    }
    
    public boolean validacionSym(char simbolo){
        if(simbolo==42 || simbolo ==63 || simbolo==43 || simbolo ==124 || simbolo==46){
            return true;
        }else{
            return false;
        }
    }
}



