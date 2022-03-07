/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trans;

//import ArbolExpresiones.Arbol;
import Arbol.AnalizadorArbol;
//import Automata.Automata;
import Analizador.TConjunto;
import Analizador.TLexemas;
import static Arbol.AnalizadorArbol.contSiguiente;
import Arbol.TSiguiente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class T_TRANSICIONES {
    //ESTA CLASE VA A TENER UNA FILA DE TRANSICIONES(F_TRANSICION) PARA PODER TENER LA TABLA DE TRANSICIONES, ESTE DEVUELVE LA TABLA DE TRANSICIONES EL CUAL
    //ES UN LISTADO DE FILA DE TRANSICIONES, ESTA CLASE SE INCIALIZA CON EL ESTADO S0 Y RECIBIENDO TAMBIEN LA TABLA DE SIGUIENTES
    //PARA PODER GENERAR LA TABLA DE TRANSICIONES
    public static  int num=0;
    
    public T_TRANSICIONES(String conjunto0,ArrayList<TSiguiente> ts,String nomG){
        TABLA_SIGUIENTES=ts;
        this.NOMBRE_GRAFICAR=nomG;
        INICIALIZAR_CONJUNTO("", conjunto0);
    }

    public void MOSTRAR_TABLA_SIGUIENTES_TRANSICIONES(){
        /*
        System.out.println("**********************MOSTRANDO LA TABLA DE SIGUIENTES DE LA CLASE TRANSICIONES ******************************");
        for(int i=0; i<TABLA_SIGUIENTES.size(); i++){
            System.out.println("VALOR HOJA: "+TABLA_SIGUIENTES.get(i).ValorHoja+" VALOR IDENTIFICADOR HOJA: "+TABLA_SIGUIENTES.get(i).Num_Identificador+"SIGUIENTES: "+TABLA_SIGUIENTES.get(i).Siguientes);
        }*/
    }
    
    public void INICIALIZAR_CONJUNTO(String Estado,String Conjunto){
        F_TRANSICION tempTransicion=new F_TRANSICION();
        tempTransicion.COLOCAR_ESTADO_CONJUNTO_IDENTIFICADOR(Estado, Conjunto);
        tempTransicion.AGREGAR_TABLA_TMP_SIGUIENTES(TABLA_SIGUIENTES);
        this.TABLA_TRANSICIONES.add(tempTransicion);
        for(int i=0; i<TABLA_SIGUIENTES.size(); i++){
            if(!TABLA_SIGUIENTES.get(i).simbolo .equals("#")){
                if(!TABLA_TRANSICIONES.get(TABLA_TRANSICIONES.size()-1).EXISTE_TERMINAL_TRANSICION(TABLA_SIGUIENTES.get(i).simbolo)){
                    TABLA_TRANSICIONES.get(TABLA_TRANSICIONES.size()-1).INICIALIZAR_COLUMNA_TERMINALES_TRANSICIONES(TABLA_SIGUIENTES.get(i).simbolo);
                }
            }
        }
        ARMAR_CONJUNTOS_TABLA_TRANSICION();
        FORMALIZAR_TABLA_TRANSICIONES();
        GRAFICAR_TABLA_TRANSICIONES();
        /*System.out.println("----------------MOSTRANDO PRIMEROS DATOS DE LA TABLA TRANSICIONES --------------------------------------");
        for(int i=0; i<TABLA_TRANSICIONES.size(); i++){
            System.out.println(TABLA_TRANSICIONES.get(i).OBTENER_ESTADO_CONJUNTO_IDENTIFICADO()+"-->"+TABLA_TRANSICIONES.get(i).OBTENER_LISTADO_TERMINAL_TRANSICION());
           // TABLA_TRANSICIONES.get(i).MOSTRAR_TMP_SIGUIENTES2();
        }*/
    }
    
    private void ARMAR_CONJUNTOS_TABLA_TRANSICION(){
        for(int i=0; i<TABLA_TRANSICIONES.size(); i++){
            if(!TABLA_TRANSICIONES.get(i).GET_VALIDAR()){
               ArrayList<String> TMP_CONJU=TABLA_TRANSICIONES.get(i).OBTENER_LISTADO_CONJUNTO_NUEVO();
               TABLA_TRANSICIONES.get(i).SET_VALIDAR(true);
               int temporal=TABLA_TRANSICIONES.size();
               //VALIDO SI EXISTE EL CONJUNTO SI NO EXISTE LO AGREGO A TABLA TRANSICIONES
               for(int j=0; j<TMP_CONJU.size(); j++){
                   //SI ENTRA A ESTE IF EL CONJUNTO NO SE ENCUENTRA INSERTADO ENTONCES SE PUEDE INSERTAR 
                   if(!CONJUNTO_INSERTADO_EN_TABLA(TMP_CONJU.get(j))){
                       //---- CODIGO COPIADO DEL CONSTRUCTOR----
                        F_TRANSICION tempTransicion=new F_TRANSICION();
                        tempTransicion.COLOCAR_ESTADO_CONJUNTO_IDENTIFICADOR("",TMP_CONJU.get(j));
                        tempTransicion.AGREGAR_TABLA_TMP_SIGUIENTES(TABLA_SIGUIENTES);
                        this.TABLA_TRANSICIONES.add(tempTransicion);
                        for(int z=0; z<TABLA_SIGUIENTES.size(); z++){
                            if(!TABLA_SIGUIENTES.get(z).simbolo.equals("#")){
                                if(!TABLA_TRANSICIONES.get(TABLA_TRANSICIONES.size()-1).EXISTE_TERMINAL_TRANSICION(TABLA_SIGUIENTES.get(z).simbolo)){
                                    TABLA_TRANSICIONES.get(TABLA_TRANSICIONES.size()-1).INICIALIZAR_COLUMNA_TERMINALES_TRANSICIONES(TABLA_SIGUIENTES.get(z).simbolo);
                                }
                            }
                        }
                       //--------------------------------------
                   }
               }
               if(temporal!=TABLA_TRANSICIONES.size()){
                   ARMAR_CONJUNTOS_TABLA_TRANSICION();
               }
            }
        }
    }
    
    private boolean CONJUNTO_INSERTADO_EN_TABLA(String conj){
        boolean respuesta=false;
        for(int i=0; (i<TABLA_TRANSICIONES.size()) && (respuesta==false); i++){
            if((TABLA_TRANSICIONES.get(i).OBTENER_CONJUNTO().equals(conj))){
                respuesta=true;
            }
        }
        return respuesta;
    }
    
    private void FORMALIZAR_TABLA_TRANSICIONES(){
        ArrayList<OB_EC> TMP_ESTADO_CONJ=new ArrayList<OB_EC>();
        for(int i=0;i<TABLA_TRANSICIONES.size(); i++){
            TABLA_TRANSICIONES.get(i).COLOCAR_ESTADO("S"+i);
            TMP_ESTADO_CONJ.add(new OB_EC("S"+i, TABLA_TRANSICIONES.get(i).OBTENER_CONJUNTO()));
        }
        for(int j=0; j<TABLA_TRANSICIONES.size(); j++){
            TABLA_TRANSICIONES.get(j).COLOCAR_ESTADOS_CONJUNTOS(TMP_ESTADO_CONJ);
        }
    }
    
    //----METODOS PARA GENERAR LAS GRAFICAS ----------------------
    private void GRAFICAR_TABLA_TRANSICIONES(){
        
        //otro metodos ****************************************************************************
        inicioGrafica("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".dot", "digraph G{ \n node[shape=plaintext];\n");
        CuerpoGrafica("TAB[label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">];\n","C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".dot" );
        for(int i=0; i<TABLA_TRANSICIONES.size(); i++){
            TABLA_TRANSICIONES.get(i).GRAFICAR_TABLA_TRANSICIONES("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".dot",i);
        }
        CuerpoGrafica("</table>>];\n}","C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".dot" );
        Graficar("C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".dot", "C:\\Users\\magdi\\Desktop\\OLC1_PROYECTO1_201801449\\OLC1_201801449\\TRANSICIONES_201801449\\"+NOMBRE_GRAFICAR+".svg");
    }
    
   
    private void Graficar(String pathArbolArchivo,String pathArbolImagen){
       //direccion para dot.exe
            String dotpath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //direccion del archivo .dot
            String fileInputPath = pathArbolArchivo;
            //direccion donde se creara el archivo .svg
            String fileOutputPath = pathArbolImagen;
            
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

        try {
            rt.exec(cmd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
    
    private void inicioGrafica(String pathArbol,String primerTexto){
        try {
            FileWriter fichero1=new FileWriter(pathArbol);
            
           /*fichero1.write("digraph structs{ \n");
            fichero1.write("node[shape=Mrecord];\n");*/
           fichero1.write(primerTexto);
           fichero1.close();
            
        } catch (IOException ex) {
            System.out.println("Error al crear archivo, metodo inicioGrafica");
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
            System.out.println("Error en el metodo CuerpoGrafica del arbol");
        }
         
    }
    //METODOS PARA LOS AUTOMATAS
    public void COLOCAR_CONJUNTOS_LEXEMAS(ArrayList<TConjunto> conj,ArrayList<TLexemas> lex){
        this.TMP_CONJUNTOS=conj;
        this.TMP_LEXEMAS=lex;
    }
    
    public void ANALIZAR_Y_GENERAR_AUTOMATA(){
        //Automata AFD=new Automata(this.TMP_CONJUNTOS,this.TMP_LEXEMAS,TABLA_TRANSICIONES,NOMBRE_GRAFICAR,TABLA_SIGUIENTES.get(TABLA_SIGUIENTES.size()-1).Num_Identificador);
        //AFD.IMPRIME_CONJUNTOS_LEXEMAS();
        //AFD.IMPRIMIENDO_TABLA_TRANSICION();
        //AFD.ANALIZAR_LEXEMAS_AUTOMATA();
        //AFD.GRAFICAR_AUTOMATA();
    }
    
    
    
    //----------------------------------------------------------
    
    //***************************VARIABLES DE ESTA CLASE *********************
    //ESTE LISTADO LO OBTENEMOS DE LA TABLA QUE NOS DEVUELVE EL ARBOL AL CUAL PERTENECE LA TABLA DE SIGUIENTES
    private ArrayList<TSiguiente> TABLA_SIGUIENTES=new ArrayList<TSiguiente>();
    //ESTA TABLA DE TRANSICIONES SE DEBE DEVOLVER AL ARBOL PARA QUE LO TENGA ALMACENADO UNA VEZ SE HALLA ARMADO LA TABLA DE TRANSICIONES
    private ArrayList<F_TRANSICION> TABLA_TRANSICIONES=new ArrayList<F_TRANSICION>();
    private ArrayList<TConjunto> TMP_CONJUNTOS=new ArrayList<TConjunto>();
    private ArrayList<TLexemas> TMP_LEXEMAS=new ArrayList<TLexemas>();
    private String NOMBRE_GRAFICAR="";
}
