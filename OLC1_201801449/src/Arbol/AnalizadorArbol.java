package Arbol;
import Arbol.Nodo;
import java.util.LinkedList;
import Arbol.ListaArbol;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author magdiel
 */
public class AnalizadorArbol{
    public LinkedList<Nodo> TablaArbol = new LinkedList<Nodo>();
    public LinkedList<String> TablaNombreArbol = new LinkedList<String>();
    public int contadorAr=0;
    public static int contArbol=0;
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
    public void entradaAnalizador(String expresionregular,String nombreexpresion){
        this.TablaNombreArbol.add(nombreexpresion); //guarda el nombre tanto de la expresion como del arbol
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
        this.graph = this.inicioGrap + this.raiz.getNodosEstructura()+"}\n";
      
        //graficar arbol
        this.graficarArbol(graph);
        this.graph="";
        
        //System.out.println(inicioGrap);
        this.contadorid=0;
        this.TablaArbol.add(this.raiz);// guarda el arbol 
        this.raiz = null;
        
        //System.out.println(this.raiz.getNodosEstructura());
        System.out.println("FIN*********************************");
        //se agrega el . y el # al final del arbol  
    }
    
    public void graficarArbol(String codigoGraphviz){
        contArbol++;
        FileWriter file = null;
        PrintWriter pw = null;
        
        try{
        file = new FileWriter("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\ARBOLES_201801449\\Arbol"+Integer.toString(contArbol) +".dot");
        pw = new PrintWriter(file);
        pw.println(codigoGraphviz);
        }catch(Exception e){
            System.out.println("No se pudo crear el archivo dot");
        }finally{
            try {
                if (null != file) {
                    file.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //para convetir de .dot a .jpg
        try{
            //direccion para dot.exe
            String dotpath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //direccion del archivo .dot
            String fileInputPath = "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\ARBOLES_201801449\\arbol"+Integer.toString(contArbol)+".dot";
            //direccion donde se creara el archivo .svg
            String fileOutputPath = "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\ARBOLES_201801449\\arbol"+Integer.toString(contArbol)+".svg";
            
            //tipo de conversón
            String tParam = "-Tsvg";
            String tOParam = "-o";
            
            String[] cmd = new String[5];
            cmd[0] = dotpath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);
        
        }catch(Exception ex){
             ex.printStackTrace();
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



