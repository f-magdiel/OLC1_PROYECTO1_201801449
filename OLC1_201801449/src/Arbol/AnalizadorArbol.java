package Arbol;
import Arbol.Nodo;
import java.util.LinkedList;
import Arbol.ListaArbol;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author magdiel
 */
public class AnalizadorArbol{
    //para guardar las estructuras y nombres
    public LinkedList<Nodo> TablaArbol = new LinkedList<Nodo>();
    public LinkedList<String> TablaNombreArbol = new LinkedList<String>();
    public String [][] tablaFollow;
    public int contadorAr=0;
    public static int contArbol=0;
    public static int contSiguiente=0;
    public int contadornum=0;
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
            nodo = new Nodo(null,null,caracter,this.contadorid,0);
            //Aplicacion Anulable o No
            if(nodo.nodoizq==null && nodo.nododer==null){
                nodo.anulable = "N";
                //nodo.first = Integer.toString(this.contadorid);
                //nodo.last = Integer.toString(this.contadorid);
            }
            
            
        }else{// si raiz no es null
            if(caracter.equals(".") || caracter.equals("|")){
                nodo.valor = caracter;//el nodo principal es seteado
                //Aplicacion Anulable o NO
                if(nodo.valor.equals("|")){
                    if(nodo.nodoizq.anulable.equals("A") || nodo.nododer.anulable.equals("A")){
                        nodo.anulable = "A";
                    }else{
                        nodo.anulable = "N";
                    }
                     //last y first
                    //nodo.first = nodo.nodoizq.first +","+nodo.nododer.first;
                    //nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                }else if(nodo.valor.equals(".")){
                    if(nodo.nodoizq.anulable.equals("A")&& nodo.nododer.anulable.equals("A")){
                        nodo.anulable="A";
                    }else{
                        nodo.anulable="N";
                    }
                    //first y last
                    /*if(nodo.nodoizq.equals("A")){ //first
                        nodo.first = nodo.nodoizq.first+","+nodo.nododer.first;
                    }else{
                        nodo.first = nodo.nodoizq.first;
                    }
                    
                    if(nodo.nododer.anulable.equals("A")){
                        nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                    }else{
                        nodo.last = nodo.nododer.last;
                    }*/
                }
                
               
            }else if(caracter.equals("+") || caracter.equals("*") || caracter.equals("?")){//---------->Seguir aqui
                    
               //siempre se creara otra rama para aislar los simbolos de +,*,?
                    //aux = padre
                    if(nodo.valor.equals("")){
                        nodo.valor = caracter;//se setea el valor para ver si se didive
                    }
                    if(nodo.valor.equals("+") || nodo.valor.equals("*")||nodo.valor.equals("?")){
                        if (nodo.nododer == null && nodo.nodoizq==null){//cuando izq y der son null es hoja
                            this.contadorid++;
                            aux1 = nodo;
                            Nodo nodocaracter = new Nodo(null,null,caracter,this.contadorid,0);//cuando ya no hay espacio y se setea *,?,+
                            nodocaracter.nododer = aux1;
                            //Si es anulable o no
                            if(nodocaracter.nododer.valor.equals("*")){
                                nodocaracter.anulable = "A";
                                //last y first
                                //nodocaracter.first = nodocaracter.nododer.first;
                                //nodocaracter.last = nodocaracter.nododer.last;
                            }else if(nodocaracter.nododer.valor.equals("?")){
                                nodocaracter.anulable = "A";
                                //last y first
                                //nodocaracter.first = nodocaracter.nododer.first;
                                //nodocaracter.last = nodocaracter.nodoizq.last;
                            }else if(nodocaracter.nododer.valor.equals("+")){
                                if(nodocaracter.nododer.anulable.equals("A")){
                                    nodocaracter.anulable="A";
                                }else{
                                    nodocaracter.anulable="N";
                                }
                                
                                //last y first
                                //nodocaracter.first = nodocaracter.nododer.first;
                                //nodocaracter.last = nodocaracter.nodoizq.last;
                            }
                            nodo = nodocaracter;
                            aux1=null;
                        }
                    
                        if(nodo.nododer!=null && nodo.nodoizq!=null){//cuando ya no es hoja sino rama
                            this.contadorid++;
                            aux1 = nodo.nododer;
                            aux2 = nodo.nodoizq;
                            nodo.valor = caracter;//se agrega el simbolo +,*,?
                            Nodo nuevonodo = new Nodo(null,null,"",this.contadorid,0);
                            nodo.nododer =null;
                            nuevonodo.nododer = aux1;
                            nuevonodo.nodoizq = nodo;
                            //Si es anulable o no
                            if(nuevonodo.nodoizq.valor.equals("*")){
                               nuevonodo.nodoizq.anulable="A";
                               //first y last
                               //nuevonodo.first = nuevonodo.nodoizq.first;
                               //nuevonodo.last = nuevonodo.nodoizq.last;
                            }else if(nuevonodo.nodoizq.valor.equals("?")){
                                nuevonodo.nodoizq.valor.equals("A");
                                
                                //first y last
                                //nuevonodo.first = nuevonodo.nodoizq.first;
                                //nuevonodo.last = nuevonodo.nodoizq.last;
                            }else if(nuevonodo.nodoizq.valor.equals("+")){
                                if(nuevonodo.nodoizq.nodoizq.anulable.equals("A")){
                                    nuevonodo.nodoizq.anulable="A";
                                }else{
                                    nuevonodo.nodoizq.anulable="N";
                                }
                                
                                //first y last
                                //nuevonodo.first = nuevonodo.nodoizq.first;
                                //nuevonodo.last = nuevonodo.nododer.last;
                            }
                            nodo = nuevonodo;
                            aux1=null;
                            aux2=null;
                        
                        }
                    }else{
                        this.contadorid++;
                        aux1=nodo;
                        Nodo nuevonodo = new Nodo(null,null,caracter,this.contadorid,0);                        
                        nuevonodo.nododer = aux1;
                        //Si es anulable o no
                        if(nuevonodo.valor.equals("*")){
                           nuevonodo.anulable = "A";
                           //last y first
                           //nuevonodo.first = nuevonodo.nododer.first;
                           //nuevonodo.last = nuevonodo.nododer.last;
                        }else if(nuevonodo.valor.equals("?")){
                            nuevonodo.anulable="A";
                            //last y first
                            //nuevonodo.first = nuevonodo.nododer.first;
                            //nuevonodo.last = nuevonodo.nododer.last;
                        }else if(nuevonodo.valor.equals("+")){
                            if(nuevonodo.nododer.anulable.equals("A")){
                                nuevonodo.anulable="A";
                            }else{
                                nuevonodo.anulable="N";
                            }
                            
                            //last y first
                            //nuevonodo.first = nuevonodo.nododer.first;
                            //nuevonodo.last = nuevonodo.nododer.last;
                        }
                        nodo = nuevonodo;
                        aux1=null;
                    
                    }
                    
                    
                
                
            }else{
                if(nodo.nododer==null && nodo.nodoizq==null){ //cuando es hoja
                    aux1 = nodo;
                    this.contadorid++;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid,0);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid,0);
                    //Si es anulable o no
                    nuevonodo.nodoizq.anulable="N";//como es hoja siempre es N
                    //last y first
                    //nuevonodo.nodoizq.first = Integer.toString(this.contadorid);
                    //nuevonodo.nodoizq.last = Integer.toString(this.contadorid);
                    nodo = nuevonodo;
                    aux1=null;
                    
                }else if(nodo.nodoizq!=null && nodo.nododer!=null){
                    this.contadorid++;
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid,0);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid,0);
                    //Si es anulable o no
                    nuevonodo.nodoizq.anulable="N";//como es hoja siempre es N
                    //last y first
                    //nuevonodo.nodoizq.last = Integer.toString(this.contadorid);
                    //nuevonodo.nodoizq.first = Integer.toString(this.contadorid);
                    nodo = nuevonodo;
                    aux1=null;
                    
                }else{//se crea una rama nueva
                    this.contadorid++;
                    aux1 = nodo;
                    Nodo nuevonodo = new Nodo(null,null,"",this.contadorid,0);
                    nuevonodo.nododer = aux1;
                    this.contadorid++;
                    nuevonodo.nodoizq = new Nodo(null,null,caracter,this.contadorid,0);
                    //Si es anulable o no
                    nuevonodo.nodoizq.anulable="N";//como es hoja siempre es N
                    //first y post
                    //nuevonodo.nodoizq.first = Integer.toString(this.contadorid);
                    //nuevonodo.nodoizq.last = Integer.toString(this.contadorid);
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
        Nodo nuevonodo = new Nodo(null,null,".",this.contadorid,0);
        nuevonodo.nodoizq = this.raiz;
        
        this.contadorid++;
        nuevonodo.nododer = new Nodo(null,null,"#",this.contadorid,0);
        nuevonodo.nododer.anulable="N";
        //nuevonodo.nododer.first = Integer.toString(this.contadorid);
        //nuevonodo.nododer.last = Integer.toString(this.contadorid);
        this.raiz = nuevonodo;
        
        //Si es anulable o no
        if(this.raiz.valor.equals(".")){
            if(this.raiz.nodoizq.anulable.equals("A") && this.raiz.nododer.anulable.equals("A")){
                this.raiz.anulable="A";
            }else{
                this.raiz.anulable="N";
            }
        }
        //para agregar num
        this.enumerarHoja(this.raiz);
        
        //para realizar first y last
        this.firstylast(this.raiz);
        //para follow
        
        this.tablaFollow = new String[2][this.contadornum];
        this.limpiarArray();
        //asignacion de hojas
        this.asignacionHojas();
        this.followpos(this.raiz);
        this.ordenamientoFollow(this.tablaFollow, contadornum);//para ordenar el follow
        
        
        //para transiciones
        
        //diagramathis
        
        //validacion cadena
        
        
        //GRAFICA DE ARBOL*************************
        this.graph = this.inicioGrap + this.raiz.getNodosEstructura()+"}\n";
        //graficar arbol
        this.graficarArbol(graph);
        this.graph="";
        
        //GRAFICA DE LA TABLA DE FOLLOW********************
        this.graficarSuguiente(this.tablaFollow,this.contadornum);
        //System.out.println(inicioGrap);
        this.contadorid=0;
        //****************GUARDAR ARBOL***********************
        this.TablaArbol.add(this.raiz);// guarda el arbol 
        this.raiz = null;
        this.contadornum =0;
        this.tablaFollow=null;
        
        //System.out.println(this.raiz.getNodosEstructura());
        System.out.println("FIN*********************************");
        //se agrega el . y el # al final del arbol  
    }
    
    public void limpiarArray(){
        for (int i = 0; i < this.contadornum; i++) {
            this.tablaFollow[1][i]="";
        }
    }
    public void ordenamientoFollow(String[][] follow,int cont){
        for (int i = 0; i < cont; i++) {
            String valor = follow[1][i];
            follow[1][i] = this.ordenarFollow(valor);
        }
    }
    
    public String ordenarFollow(String cadena){
        String[] palabra = cadena.split(",");
        Arrays.sort(palabra);
        String resultado = Arrays.toString(palabra);
        return resultado;
    }
    
    public void followpos(Nodo nodo){
        //crear la tabla con las hojas
        
        if(nodo.valor.equals("*")){
           String[] hoja = nodo.last.split(",");
           String sig = nodo.first;
            for (int i = 0; i < hoja.length; i++) {
                this.tablaFollow[1][Integer.parseInt(hoja[i])-1] += sig+",";
            }
            //asingacion de follow
            if(nodo.nodoizq!=null && nodo.nododer!=null){
                this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq!=null && nodo.nododer==null){
                this.followpos(nodo.nodoizq);
                //this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq==null && nodo.nododer!=null){
                //this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
        }else if(nodo.valor.equals("+")){
            String[] hoja = nodo.last.split(",");
            String sig = nodo.first;
            for (int i = 0; i < hoja.length; i++) {
                this.tablaFollow[1][Integer.parseInt(hoja[i])-1]+=sig+",";
            }
            //asingacion de follow
            if(nodo.nodoizq!=null && nodo.nododer!=null){
                this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq!=null && nodo.nododer==null){
                this.followpos(nodo.nodoizq);
                //this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq==null && nodo.nododer!=null){
                //this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
        }else if(nodo.valor.equals(".")){
            String[] hoja = nodo.nodoizq.last.split(",");
            String sig = nodo.nododer.first;
            for (int i = 0; i < hoja.length; i++) {
                this.tablaFollow[1][Integer.parseInt(hoja[i])-1] += sig+",";//cambio
            }
            //asingacion de follow
            if(nodo.nodoizq!=null && nodo.nododer!=null){
                this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq!=null && nodo.nododer==null){
                this.followpos(nodo.nodoizq);
                //this.followpos(nodo.nododer);
            }
            if(nodo.nodoizq==null && nodo.nododer!=null){
                //this.followpos(nodo.nodoizq);
                this.followpos(nodo.nododer);
            }
        }
        //asingacion de follow
       
    }
    
    public void asignacionHojas(){
        for (int i = 0; i < this.contadornum; i++) {// columna 0
            this.tablaFollow[0][i]=Integer.toString(i+1);
        }
    }
    
    public void firstylast(Nodo nodo){
            if(nodo.valor.equals(".")){
                if(!nodo.nodoizq.first.equals("") && !nodo.nododer.last.equals("")){//cuando tiene fisr y last
                    if(nodo.nodoizq.anulable.equals("A")){
                        nodo.first = nodo.nodoizq.first+","+nodo.nododer.first;
                        
                    }else{
                        nodo.first = nodo.nodoizq.first;
                    }
                    
                    if(nodo.nododer.anulable.equals("A")){
                        nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                    }else{
                        nodo.last = nodo.nododer.last;
                    }
                }else{//cuando tiene viene vacio ""
                    this.firstylast(nodo.nodoizq);
                    this.firstylast(nodo.nododer);
                    
                    //segunda
                    if(!nodo.nodoizq.first.equals("") && !nodo.nododer.last.equals("")){//cuando tiene fisr y last
                    if(nodo.nodoizq.anulable.equals("A")){
                        nodo.first = nodo.nodoizq.first+","+nodo.nododer.first;
                        
                    }else{
                        nodo.first = nodo.nodoizq.first;
                    }
                    
                    if(nodo.nododer.anulable.equals("A")){
                        nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                    }else{
                        nodo.last = nodo.nododer.last;
                    }
                    }
                }
            }else if(nodo.valor.equals("|")){
                if(!nodo.nodoizq.first.equals("") && !nodo.nododer.last.equals("")){//cuando tiene fisr y last
                    nodo.first = nodo.nodoizq.first+","+nodo.nododer.first;
                    nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                    
                }else{//cuando tiene viene vacio ""
                    this.firstylast(nodo.nodoizq);
                    this.firstylast(nodo.nododer);
                    
                    //segunda
                    if(!nodo.nodoizq.first.equals("") && !nodo.nododer.last.equals("")){//cuando tiene fisr y last
                    nodo.first = nodo.nodoizq.first+","+nodo.nododer.first;
                    nodo.last = nodo.nodoizq.last+","+nodo.nododer.last;
                    
                    }
                }
            }else if(nodo.valor.equals("+")){
                if(nodo.nodoizq!=null && nodo.nododer==null){
                    if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                    }else{
                        this.firstylast(nodo.nodoizq);
                        //segunda
                        if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                        }
                    }
                }
                
                if(nodo.nodoizq==null && nodo.nododer!=null){
                    if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                    }else{
                        this.firstylast(nodo.nododer);
                        //segunda
                        if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                        }
                    }
                }
                
            }else if(nodo.valor.equals("*")){
                 if(nodo.nodoizq!=null && nodo.nododer==null){
                    if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                    }else{
                        this.firstylast(nodo.nodoizq);
                        //segunda
                        if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                        }
                    }
                }
                
                if(nodo.nodoizq==null && nodo.nododer!=null){
                    if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                    }else{
                        this.firstylast(nodo.nododer);
                        //segunda
                        if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                        }
                    }
                }
            }else if(nodo.valor.equals("?")){
                if(nodo.nodoizq!=null && nodo.nododer==null){
                    if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                    }else{
                        this.firstylast(nodo.nodoizq);
                        //segunda
                        if(!nodo.nodoizq.first.equals("") && !nodo.nodoizq.last.equals("")){
                        nodo.first = nodo.nodoizq.first;
                        nodo.last = nodo.nodoizq.last;
                        }
                    }
                }
                
                if(nodo.nodoizq==null && nodo.nododer!=null){
                    if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                    }else{
                        this.firstylast(nodo.nododer);
                        //segunda
                        if(!nodo.nododer.first.equals("") && !nodo.nododer.last.equals("")){
                        nodo.first = nodo.nododer.first;
                        nodo.last = nodo.nododer.last;
                        }
                    }
                }
            }
            
            
    }
    
    public void enumerarHoja(Nodo nodo){
        if(nodo.nodoizq!=null && nodo.nododer!=null){
            this.enumerarHoja(nodo.nodoizq);
            this.enumerarHoja(nodo.nododer);
        }
        if(nodo.nodoizq!=null && nodo.nododer==null){
            this.enumerarHoja(nodo.nodoizq);
        }
        if(nodo.nodoizq==null && nodo.nododer!=null){
            this.enumerarHoja(nodo.nododer);
        }
        if(nodo.nodoizq==null && nodo.nododer==null){
            this.contadornum++;
            nodo.num = this.contadornum;
            nodo.first = Integer.toString(this.contadornum);
            nodo.last = Integer.toString(this.contadornum);
        }
    }
    
    public void graficarSuguiente(String [][]tabla,int contadornum){
        contSiguiente++;
        FileWriter file = null;
        PrintWriter pw = null;
        
        try{
            file = new FileWriter("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\SIGUIENTES_201801449\\Siguiente"+Integer.toString(contSiguiente)+".dot");
            pw = new PrintWriter(file);
            pw.println("digraph D {");
            pw.println("node [shape=plaintext]");
            pw.println("some_node [");
            pw.println("label=<");
            pw.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">");
            pw.println("<tr><td bgcolor=\"yellow\">Hoja</td><td bgcolor=\"yellow\">Siguientes</td></tr>");
            //para agregar la tabla
            for (int j = 0; j < contadornum; j++) {
                pw.println("<tr><td bgcolor=\"yellow\">"+this.tablaFollow[0][j]+"</td><td bgcolor=\"yellow\">"+this.tablaFollow[1][j]+"</td></tr>");
            }
            
            pw.println("</table>>");
            pw.println("];");
            pw.println("}");
            
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
            String fileInputPath = "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\SIGUIENTES_201801449\\Siguiente"+Integer.toString(contSiguiente)+".dot";
            //direccion donde se creara el archivo .svg
            String fileOutputPath = "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\SIGUIENTES_201801449\\Siguiente"+Integer.toString(contSiguiente)+".svg";
            
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



