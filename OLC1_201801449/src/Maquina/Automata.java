
package Maquina;
import Analizador.*;
import Trans.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Maquina.Json;

/**
 *
 * @author magdiel
 */
public class Automata {
    
    public  ArrayList<Json> json = new ArrayList<Json>();
    private ArrayList<TConjunto> CONJUNTOS=new ArrayList<TConjunto>();
    private ArrayList<TLexemas> LEXEMAS=new ArrayList<TLexemas>();
    private ArrayList<F_TRANSICION> TMP_TABLA_TRANSICIONES=new ArrayList<F_TRANSICION>();
    private String NOMBRE_GRAFICAR="";
    private String Estado_Aceptacion="";
    private String ESTADO_INICIAL_VERIFICACION="";
    
      public Automata(ArrayList<TConjunto> conj, ArrayList<TLexemas> lex,ArrayList<F_TRANSICION> T_TRANS,String id,String aceptacion){
        this.CONJUNTOS=conj;
        this.LEXEMAS=lex;
        this.TMP_TABLA_TRANSICIONES=T_TRANS;
        this.NOMBRE_GRAFICAR=id;
        this.Estado_Aceptacion=aceptacion;   
       
    }

    public void GRAFICAR_AUTOMATA(){
        inicioGrafica("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".dot","digraph automata{ \n rankdir=LR;\n size=\"8,5\" \n node[shape=doublecircle,style=\"filled\", color=\"black\", fillcolor=\"gold1\"]; ");
        String conj="";
        for(int i=0; i<TMP_TABLA_TRANSICIONES.size(); i++){
            conj+=OBTENER_ESTADO_ACEPTACION(TMP_TABLA_TRANSICIONES.get(i).OBTENER_OBJETO_ESTADO_CONJUNTO().GET_ESTADO(),TMP_TABLA_TRANSICIONES.get(i).OBTENER_OBJETO_ESTADO_CONJUNTO().GET_CONJUNTOESTADO());
        }
        CuerpoGrafica(conj+";\n node[shape=circle,style=\"filled\", color=\"black\", fillcolor=\"skyblue\"];", "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".dot");
        ESCRIBIENDO_TRANSICIONES_ESTADO_AUTOMATA();
        CuerpoGrafica("}","C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".dot");
        Graficar("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".dot", "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".jpg");
    }
    
    private String OBTENER_ESTADO_ACEPTACION(String estado,String conjuntos){
        String retornar ="";
        String[] conj=conjuntos.split(",");
        for(int i=0; i<conj.length;i++){
            if(conj[i].equals(Estado_Aceptacion)){
                retornar+=estado+" ";
            }
        }
        return retornar;
    }
    
    private void ESCRIBIENDO_TRANSICIONES_ESTADO_AUTOMATA(){
        for(int i=0; i<TMP_TABLA_TRANSICIONES.size(); i++){
            ArrayList<OB_TT> TMP_TRANSICIONES=TMP_TABLA_TRANSICIONES.get(i).OBTENER_FILAS_COLUMNAS_TERMINAL_TRANSICION();
            for(int j=0; j<TMP_TRANSICIONES.size();j++){
                if(TMP_TRANSICIONES.get(j).Transicion!=null){
                    CuerpoGrafica(TMP_TABLA_TRANSICIONES.get(i).OBTENER_OBJETO_ESTADO_CONJUNTO().GET_ESTADO()+"->"+TMP_TRANSICIONES.get(j).Transicion+"[label=\""+TMP_TRANSICIONES.get(j).Terminal+"\"];\n", "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\AFD_201801449\\"+NOMBRE_GRAFICAR+".dot");
                }
            }
        }
    }
   
    
    String DATOS_LEXEMA=""; //LEXEMA ACTUAL EN LECTURA
    int indiceenlectura =0;
    public void ANALIZAR_LEXEMAS_AUTOMATA(){
       

        for(int i=0; i<LEXEMAS.size(); i++){
            if(LEXEMAS.get(i).getNombre().equals(NOMBRE_GRAFICAR)){//SI ENTRA AQUI SIGNIFICA QUE VA ANALIZAR EL LEXEMA QUE SE SUPONE PERTENECE AL AUTOMATA
                ESTADO_INICIAL_VERIFICACION="S0";
                DATOS_LEXEMA=LEXEMAS.get(i).getLexema();
                boolean EXISTIO_ERROR=false;
                boolean CARACTER_SOLO=false;
                boolean entrovalidaciondecadenas =false;
                //ESTE FOR RECORRERA EL LEXEMA CARACTER POR CARACTER
                //System.out.println("Se verifica: "+DATOS_LEXEMA);
                for(int j=0; (j<DATOS_LEXEMA.length()) && (EXISTIO_ERROR==false); j++){ //si existe error en algun caracter ya no ejecuta
                    boolean NUEVA_ENTRADA=false;
                    boolean VALIDO_ESTADO=false;
                    char CARACTER_LEXEMA=DATOS_LEXEMA.charAt(j);
                    indiceenlectura = j;
                    //-------------------AQUI SE EMPIEZAN HACER LAS VALIDACIONES CON LA TABLA TRANSICIONES CON LA CUAL SE GENERO EL AUTOMATA
                    //PRIMERO SE HACE UN RECORRIDO DE LA TABLA TRANSICIONES PARA AVERIGUAR EN EL ESTADO QUE ESTAMOS
                    for(int h=0; (h<TMP_TABLA_TRANSICIONES.size()) && (NUEVA_ENTRADA==false); h++){
                        //BUSCA EL ESTADO EN EL QUE ESTAMOS UBICADOS PARA LA NUEVA TRANSICION
                        if(TMP_TABLA_TRANSICIONES.get(h).OBTENER_OBJETO_ESTADO_CONJUNTO().GET_ESTADO().equals(ESTADO_INICIAL_VERIFICACION)){
                            
                            //RECUPERAMOS LAS TRANSICIONES QUE SE PUEDEN HACER CON ESE ESTADO QUE ES EN EL QUE NOS ENCONTRAMOS
                            ArrayList<OB_TT> TMP_TT=TMP_TABLA_TRANSICIONES.get(h).OBTENER_FILAS_COLUMNAS_TERMINAL_TRANSICION();
                            //ESTE FOR RECORREO CADA UNA LOS TERMINALES-TRANSICION PARA VALIDAR EL CARACTER DE CADENA
                            for(int k=0; (k<TMP_TT.size()); k++){
                                if(TMP_TT.get(k).Transicion!=null){
                                  
                                    //VALIDA SI LA TRANSICION A OTRO ESTADO DEPENDE DE UN CONJUNTO
                                    if(ES_CONJUNTO(TMP_TT.get(k).Terminal)){
                                      
                                        //VALIDA SI EL CARACTER ES VALIDO CON EL CONJUNTO AL QUE FUE HALLADO
                                        if(VALIDAR_TRANSICION_CONJUNTO(TMP_TT.get(k).Terminal, CARACTER_LEXEMA)){
                                        
                                            ESTADO_INICIAL_VERIFICACION=TMP_TT.get(k).Transicion;
                                            VALIDO_ESTADO=true;
                                            k= TMP_TT.size();
                                            h = TMP_TABLA_TRANSICIONES.size();
                                            if(indiceenlectura>j){
                                                    j=j+1;
                                                 
                                                }
                                        }
                                    }
                                }
                            }
                            //------------------- ESTE CODIGO VALIDA LOS VALORES QUE NO PERTENECEN A UN CONJUNTO-----------------------------------
                            if(VALIDO_ESTADO==false){
                              //  System.out.println("Ingreso a no conjunto");
                                for(int k=0; (k<TMP_TT.size()) && (VALIDO_ESTADO==false); k++){
                                    if(TMP_TT.get(k).Transicion!=null){
                                        //VALIDA SI LA TRANSICION A OTRO ESTADO DEPENDE DE UN CONJUNTO
                                        if(!ES_CONJUNTO(TMP_TT.get(k).Terminal)){
                                            String tmp_TERMINAL=OBTENER_TERMINAL_SIN_COMILLA_SIMPLE(TMP_TT.get(k).Terminal);
                                            tmp_TERMINAL = tmp_TERMINAL ;
                                            int tmp_contador=j;
                                            String tmp_CONCATENA="";
                                            for(int t=0; t<tmp_TERMINAL.length(); t++){
                                                if(tmp_contador<DATOS_LEXEMA.length()){
                                                    tmp_CONCATENA+=DATOS_LEXEMA.charAt(tmp_contador);
                                                    tmp_contador++;
                                                }
                                            }
                                            tmp_contador--;
                                            if(tmp_TERMINAL.equals(tmp_CONCATENA)){
                                               
                                                ESTADO_INICIAL_VERIFICACION=TMP_TT.get(k).Transicion;
                                                VALIDO_ESTADO=true;
                                                j=tmp_contador;
                                            }
                                        }
                                    }
                                }
                            }
                            //--------------------------------------------------------------------
                            
                            if(VALIDO_ESTADO==false){
                              
                                EXISTIO_ERROR=true;
                                ESTADO_INICIAL_VERIFICACION="Sn";
                            }
                        }
                    }
                    //----------------------------------------------------------------------------------------------------------------------
                    
                }
                //---------------AREA DONDE SE VALIDA SI QUEDAMOS EN ESTADO DE ACEPTACION ------------------------------------
                VALIDAR_ESTADO_ACEPTACION(DATOS_LEXEMA);
            }
        }
          
    }
    
    //VERIFICA SI ENCUENTRA EL ID DEL CONJUNTO
    private boolean ES_CONJUNTO(String terminal){
        boolean respuesta=false;
        for(int i=0; (i<CONJUNTOS.size()) && (respuesta==false); i++){
            if(CONJUNTOS.get(i).getNombre().equals(terminal)){
                respuesta=true;
            }
        }
        return respuesta;
    }
    
    //VALIDA SI EL CARACTER PERTENECE A UN CONJUNTO YA SEA RANGO O POR COMAS
    private boolean VALIDAR_TRANSICION_CONJUNTO(String Terminal,char aristaTransicion){
        boolean respuesta=false;
        for(int i=0; (i<CONJUNTOS.size()) && (respuesta==false); i++){      //CONJUNTOS RANGO O POR COMAS
            if(CONJUNTOS.get(i).getNombre().equals(Terminal)){
                if(ES_CONJUNTO_DE_RANGOS(CONJUNTOS.get(i).getConjunto())){
                    
                    if(CARACTER_RANGO_PERMITIDO(CONJUNTOS.get(i).getConjunto(),aristaTransicion)){
                        respuesta=true;
                    }
                    }else{
                    if(CARACTER_COMAS_PERMITIDO(CONJUNTOS.get(i).getConjunto(), aristaTransicion)){
                        respuesta=true;
                    }
                }
            }
        }
        return respuesta;
    }
    
    //BUSCA EL CARACTER ~ PARA VALIDAR QUE ES POR RANGOS
    private boolean ES_CONJUNTO_DE_RANGOS(String conjuntos){
        boolean respuesta=false;
        for(int i=0; (i<conjuntos.length()) && (respuesta==false); i++){
            if(conjuntos.charAt(i)==126){
                respuesta=true;
            }
        }
        return respuesta;
    }
    
    //VALIDA QUE EL CARACTER PERTENECE AL CONJUNTO SELECCIONADO
    private boolean CARACTER_RANGO_PERMITIDO(String conjuntoRangos,char aristaValidar){
        if(conjuntoRangos.length()> 3)
        conjuntoRangos = conjuntoRangos.trim();
        boolean respuesta=false;
        if(conjuntoRangos.length()==3){
            
            if((aristaValidar>=conjuntoRangos.charAt(0)) && (aristaValidar<=conjuntoRangos.charAt(2))){
                
                respuesta=true;
            }
        }
        return respuesta;
    }
    
    private boolean CARACTER_COMAS_PERMITIDO(String conjuntoRangosComas,char aristaValidar){
        boolean respuesta=false;
        conjuntoRangosComas=conjuntoRangosComas.replace("\n", "\\n");
       
       
        String[] CONJUNTOCOMAS=conjuntoRangosComas.split(",");      //SEPARA EL CONJUNTO POR COMAS
        if(CONJUNTOCOMAS.length>=1){
            for(int i=0; (i<CONJUNTOCOMAS.length) && (respuesta==false); i++){
                CONJUNTOCOMAS[i] = CONJUNTOCOMAS[i].replace(" ","").replace("\t",""); //Quita espacios y tabulaciones
                   
                    if(CONJUNTOCOMAS[i].length()>=1){
                                              
                       if(CONJUNTOCOMAS[i].length()==1){    //UN SOLO CARACTER EN COMA
                           if(CONJUNTOCOMAS[i].charAt(0)==aristaValidar){
                          
                            respuesta=true;   
                            }
                       }else if(CONJUNTOCOMAS[i].length()==2){ 
                           if((indiceenlectura+1)<DATOS_LEXEMA.length()){
                            String val;
                            val = ""+DATOS_LEXEMA.charAt(indiceenlectura) +DATOS_LEXEMA.charAt(indiceenlectura+1);
                            
                            if(val.equals("\\n") ||val.equals("\\\"")||val.equals("\\\'")){
                             
                                indiceenlectura= indiceenlectura+1;
                                respuesta =true;
                                
                            }
                       }
                   }
                }else{
                    if(aristaValidar ==','|| aristaValidar==' '){    //EN CASO DE QUE EL CARACTER SEA UNA COMA                      
                        respuesta = true;
                   }
                }
            }
            //System.out.println();
        }
        return respuesta;
    }
    
    private void VALIDAR_ESTADO_ACEPTACION(String lexe){
        boolean aceptacion=false;
        for(int i=0; (i<TMP_TABLA_TRANSICIONES.size()) && (aceptacion==false); i++){
            if(TMP_TABLA_TRANSICIONES.get(i).OBTENER_OBJETO_ESTADO_CONJUNTO().GET_ESTADO().equals(ESTADO_INICIAL_VERIFICACION)){
                String[] conj=TMP_TABLA_TRANSICIONES.get(i).OBTENER_OBJETO_ESTADO_CONJUNTO().OBTENER_CONJUNTOS_ESTADO();
                for(int j=0; (j<conj.length) && (aceptacion==false); j++){
                    if(conj[j].equals(Estado_Aceptacion)){
                        aceptacion=true;
                    }
                }
            }
        }
        
        if(aceptacion){
            System.out.println("Aceptacion");
            Json mac = new Json("{Valor: "+lexe+",\n","ExpresionRegular: "+NOMBRE_GRAFICAR+",\n","Resultado: Cadena valida");
            this.json.add(mac);
        }else{
            System.out.println("No Aceptacion");
            Json mac = new Json("{Valor: "+lexe+",\n","ExpresionRegular: "+NOMBRE_GRAFICAR+",\n","Resultado: Cadena no valida");
            this.json.add(mac);
        }
    }
    
    public void GENERADOR_JSON(){
        System.out.println("Gn");
        System.out.println(this.json.size());
        for (int i = 0; i < this.json.size(); i++) {
            System.out.print("Valor: ");
            System.out.println(this.json.get(i).getValor());
            System.out.print("Expresion: ");
            System.out.println(this.json.get(i).getExpresion());
            System.out.print("Res: ");
            System.out.println(this.json.get(i).getResultado());
        }
    }
    
    private String OBTENER_TERMINAL_SIN_COMILLA_SIMPLE(String Terminal){
        String respuesta="";
        respuesta = Terminal.substring(1, Terminal.length()-1);
        
        return respuesta;
    }
    
    //--------------------METODOS PARA GRAFICAR EL AUTOMATA-------------------------------------------
        private void Graficar(String pathArbolArchivo,String pathArbolImagen){
       //direccion para dot.exe
            String dotpath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //direccion del archivo .dot
            String fileInputPath = pathArbolArchivo;
            //direccion donde se creara el archivo .svg
            String fileOutputPath = pathArbolImagen;
            
            //tipo de conversón
            String tParam = "-Tjpg";
            String tOParam = "-o";
            
            String[] cmd = new String[5];
            cmd[0] = dotpath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

        try {
            rt.exec(cmd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
    
    private void inicioGrafica(String pathArbol,String primerTexto){
        try {
            FileWriter fichero1=new FileWriter(pathArbol);
            
         
           fichero1.write(primerTexto);
            fichero1.close();
            
        } catch (IOException ex) {
            System.out.println("Error al crear archivo, metodo inicioGrafica del automata");
        }
    }
    
    private void CuerpoGrafica(String texto,String pathArbol){
        
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        
        archivo=new File(pathArbol);
        
        try {
            escribir=new FileWriter(archivo,true);
            linea=new PrintWriter(escribir);
            
            linea.println(texto);
            linea.close();
            
        } catch (IOException ex) {
            System.out.println("Error en el metodo CuerpoGrafica del automata");
        }
         
    }
    
}
