package Arbol;
import Arbol.Nodo;
import java.util.LinkedList;
import Arbol.ListaArbol;
/**
 *
 * @author magdiel
 */
public class AnalizadorArbol{
    public LinkedList<Nodo> TablaArbol = new LinkedList<Nodo>();
    public int contadorAr=0;
    public Nodo raiz;
    public Nodo aux1;
    public Nodo aux2;
    public String inicioGrap="digraph G{\n rankdir=UD;\n node[shape=record,style=\"filled\", color=\"black\", fillcolor=\"skyblue\"];\n";
    public String graph="";
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
            this.contadorid++;
            nodo = new Nodo(null,null,caracter,this.contadorid);
            //nodo.nododer = new Nodo(null,null,caracter,id);
            
        }else{// si raiz no es null
            if(caracter.equals(".") || caracter.equals("|")){
                nodo.valor = caracter;//el nodo principal es seteado
            }else if(caracter.equals("+") || caracter.equals("*") || caracter.equals("?")){
               //ya no hay espacio es necesario crear otra rama
               //siempre se creara otra rama para aislar los simbolos de +,*,?
                    //aux = padre
                    if(nodo.valor.equals("")){
                        nodo.valor = caracter;//se setea el valor para ver si se didive
                    }
                    if(nodo.valor.equals("+") || nodo.valor.endsWith("*")||nodo.valor.equals("?")){
                        if (nodo.nododer == null && nodo.nodoizq==null){//cuando izq y der son null es hoja
                            this.contadorid++;
                            aux1 = nodo;
                            Nodo nodocaracter = new Nodo(null,null,caracter,this.contadorid);//cuando ya no hay espacio y se setea *,?,+
                            nodocaracter.nododer = aux1;
                            nodo = nodocaracter;
                        aux1=null;
                        }
                    
                        if(nodo.nododer!=null && nodo.nodoizq!=null){//cuando ya no es hoja sino rama
                            this.contadorid++;
                            aux1 = nodo.nododer;
                            aux2 = nodo.nodoizq;
                            nodo.valor = caracter;
                            Nodo nuevonodo = new Nodo(null,null,"",this.contadorid);
                            nodo.nododer =null;
                            nuevonodo.nododer = aux1;
                            nuevonodo.nodoizq = nodo;
                            nodo = nuevonodo;
                            aux1=null;
                            aux2=null;
                        
                        }
                    }else{
                        this.contadorid++;
                        aux1=nodo;
                        Nodo nuevonodo = new Nodo(null,null,caracter,this.contadorid);
                        nuevonodo.nododer = aux1;
                        nodo = nuevonodo;
                        aux1=null;
                    
                    }
                    
                    
                
                
            }else{
                if(nodo.nododer==null && nodo.nodoizq==null){ //cuando es hoja
                    aux1 = nodo;
                    this.contadorid++;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid);
                    nodo = nuevonodo;
                    aux1=null;
                    
                }else if(nodo.nodoizq!=null && nodo.nododer!=null){
                    this.contadorid++;
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid);
                    nodo = nuevonodo;
                    aux1=null;
                    
                }else{//se crea una rama nuevo
                    this.contadorid++;
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid);
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
        this.contadorid++;
        Nodo nuevonodo = new Nodo(null,null,".",this.contadorid);
        nuevonodo.nodoizq = this.raiz;
        this.contadorid++;
        nuevonodo.nododer = new Nodo(null,null,"#",this.contadorid);
        this.raiz = nuevonodo;
        System.out.println(this.raiz.getNodosEstructura());
        this.contadorid=0;
        //this.recorrerArbol(raiz,"");
        
        this.TablaArbol.add(this.raiz);
        this.raiz = null;
        
        //System.out.println(this.raiz.getNodosEstructura());
        System.out.println("FIN*********************************");
        //se agrega el . y el # al final del arbol  
    }
    
    public void graficarArbol(){
        //recorrer cada arbol para obtener su graph en string
        for (int i = 0; i < this.TablaArbol.size(); i++) {
           //String graphvi = this.recorrerArbol(this.TablaArbol.get(i),"");
        }
        
        //mandar el string para graficar
    }
    
    public void recorrerArbol(Nodo arbol,String res){
        //String resultado="";
        
        if(arbol.nodoizq!=null && arbol.nododer!=null){
            this.contadorAr++;
            //resultado+= "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n";
            recorrerArbol(arbol.nodoizq,graph += "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n");
            recorrerArbol(arbol.nododer,graph += "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n");
        }
        if(arbol.nodoizq!=null){
            this.contadorAr++;
            graph += "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n";
        }
        
        if(arbol.nododer!=null){
            this.contadorAr++;
            graph += "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n";
        }
        if(arbol.nodoizq == null && arbol.nododer == null){
            this.contadorAr++;
            graph += "nodo"+Integer.toString(this.contadorAr)+"[label=\"{"+arbol.anulable+"|{"+arbol.first+"|"+arbol.valor+"|"+arbol.last+"}|"+Integer.toString(arbol.id)+"}\"];\n";
        }
        
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



