/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trans;
import Arbol.TSiguiente;
import Arbol.AnalizadorArbol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class F_TRANSICION {
    private OB_EC OBJ_ESTADO_CONJUNTO;
    private boolean OBJ_VALIDACION;
    private ArrayList<OB_TT> LISTADO_OBJ_TERMINAL_TRANSICION=new ArrayList<OB_TT>();
    private ArrayList<TSiguiente> TEMP_TABLA_SIGUIENTES_2=new ArrayList<TSiguiente>();
    private ArrayList<String> CONJUNTOS_NUEVOS=new ArrayList<String>();
    
    public void COLOCAR_ESTADO_CONJUNTO_IDENTIFICADOR(String estado,String conjunto){
        this.OBJ_ESTADO_CONJUNTO=new OB_EC(estado, conjunto);
        this.OBJ_VALIDACION=false;
    }
    
    public String OBTENER_ESTADO_CONJUNTO_IDENTIFICADO(){
        return this.OBJ_ESTADO_CONJUNTO.RETORNAR_ESTADO_CONJUNTO();
    }
    
    public String OBTENER_LISTADO_TERMINAL_TRANSICION(){
        String respuesta="";
        for(int i=0; i<LISTADO_OBJ_TERMINAL_TRANSICION.size(); i++){
            respuesta+=LISTADO_OBJ_TERMINAL_TRANSICION.get(i).OBTENER_TERMINAL_TRANSICION()+"    ";
        }
        return respuesta;
    }
    
    public void INICIALIZAR_COLUMNA_TERMINALES_TRANSICIONES(String terminal){
        this.LISTADO_OBJ_TERMINAL_TRANSICION.add(new OB_TT(terminal));
    }
    
    public boolean GET_VALIDAR(){
        return OBJ_VALIDACION;
    }
    
    public void SET_VALIDAR(boolean vali){
        this.OBJ_VALIDACION=vali;
    }
    
    public void AGREGAR_TABLA_TMP_SIGUIENTES(ArrayList<TSiguiente> TMP){
        this.TEMP_TABLA_SIGUIENTES_2=TMP;
    }
    
    public boolean EXISTE_TERMINAL_TRANSICION(String terminal){
        boolean respuesta=false;
        
        for(int i=0; (i<LISTADO_OBJ_TERMINAL_TRANSICION.size()) && (respuesta==false); i++){
            if(LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Terminal.equals(terminal)){
                respuesta=true;
            }
        }
        
        return respuesta;
    }
    
    public ArrayList<String> OBTENER_LISTADO_CONJUNTO_NUEVO(){
        ArrayList<String> NUEVOS_CONJUNTOS=new ArrayList<String>();
        for(int i=0; i<LISTADO_OBJ_TERMINAL_TRANSICION.size(); i++){
            String conjunto=BUSCAR_OBTENER_CONJUNTO(LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Terminal,OBJ_ESTADO_CONJUNTO.OBTENER_CONJUNTOS_ESTADO());
            if(!conjunto.equals("")){
                LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion=conjunto;
                NUEVOS_CONJUNTOS.add(conjunto); 
            }
        }
        return FILTRAR_CONJUNTOS_RETORNAR(NUEVOS_CONJUNTOS);
    }
    
    
    private ArrayList<String> FILTRAR_CONJUNTOS_RETORNAR(ArrayList<String> tmpco)
    {
        ArrayList<String> NUEVO_CONJUNTOS=new ArrayList<String>();
        for(int i=0; i<tmpco.size(); i++){
            if(!YA_EXISTE_CONJUNTO_FILTRAR(tmpco.get(i), NUEVO_CONJUNTOS)){
                NUEVO_CONJUNTOS.add(tmpco.get(i));
            }
        }
        return NUEVO_CONJUNTOS;
    }
    
    private boolean YA_EXISTE_CONJUNTO_FILTRAR(String valor,ArrayList<String> NVC){
        boolean respuesta=false;
        for(int i=0; (i<NVC.size()) && (respuesta==false); i++){
            if(NVC.get(i).equals(valor)){
                respuesta=true;
            }
        }
        return respuesta;
    }
    
    private String OBTENER_VALOR_HOJA(String numHoja){
        String hoja="";
        boolean encontrada=false;
        for(int i=0; (i<TEMP_TABLA_SIGUIENTES_2.size())&&(encontrada==false); i++){
            if(TEMP_TABLA_SIGUIENTES_2.get(i).hoja.equals(numHoja)){
                encontrada=true;
                hoja=TEMP_TABLA_SIGUIENTES_2.get(i).simbolo;
            }
        }
        return hoja;
    }
    
    private String OBTENER_CONJUNTO(String numHoja){
        String conjuntoHoja="";
        boolean encontrada=false;
        for(int i=0; (i<TEMP_TABLA_SIGUIENTES_2.size())&&(encontrada==false); i++){
            if(TEMP_TABLA_SIGUIENTES_2.get(i).hoja.equals(numHoja)){
                encontrada=true;
                conjuntoHoja=TEMP_TABLA_SIGUIENTES_2.get(i).siguiente;
            }
        }
        return conjuntoHoja;
    }
    
    private String BUSCAR_OBTENER_CONJUNTO(String valorHoja,String[] conjunto){
        String retornarConjunto="";
        for(int i=0; i<conjunto.length; i++){
            if(valorHoja.equals(OBTENER_VALOR_HOJA(conjunto[i]))){
                retornarConjunto+=","+OBTENER_CONJUNTO(conjunto[i]);
            }
        }
        String[] tmp_conj=retornarConjunto.split(",");
        String nuevoConjunto="";
        for(int i=0; i<tmp_conj.length; i++){
            if(!VALIDAR_CONJUNTO_FILTRADO(tmp_conj[i], nuevoConjunto)){
                if(nuevoConjunto.equals("")){
                    nuevoConjunto+=tmp_conj[i];
                }else{
                    nuevoConjunto+=","+tmp_conj[i];
                }
            }
        }
        
        return nuevoConjunto;
    }
    
    private boolean VALIDAR_CONJUNTO_FILTRADO(String numero,String conjunto){
        boolean encontrado=false;
        String[] tmpCon=conjunto.split(",");
        
        for(int i=0; (i<tmpCon.length) && (encontrado==false); i++){
            if(tmpCon[i].equals(numero)){
                encontrado=true;
            }
        }
        
        return encontrado;
    }
    
    
    
    public String OBTENER_CONJUNTO(){
        return OBJ_ESTADO_CONJUNTO.GET_CONJUNTOESTADO();
    }
    
    public void COLOCAR_ESTADO(String est){
        OBJ_ESTADO_CONJUNTO.SET_ESTADO(est);
    }
    
    public void COLOCAR_ESTADOS_CONJUNTOS(ArrayList<OB_EC> tmp_conest){
        for(int i=0; i<LISTADO_OBJ_TERMINAL_TRANSICION.size(); i++){
           // System.out.println(LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion);
            for(int j=0; j<tmp_conest.size(); j++){
                if(LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion!=null){
                    if(LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion.equals(tmp_conest.get(j).GET_CONJUNTOESTADO())){
                        LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion=tmp_conest.get(j).GET_ESTADO();
                    }
                }
            }
        }
    }
    
    //---------------------------- METODOS PARA GRAFICAR LA TABLA TRANSICIONES-----------------------------------------
    public void GRAFICAR_TABLA_TRANSICIONES(String path_nombre_tabla,int id){
        if(id==0){
            CuerpoGrafica( "<th><td bgcolor=\"green\">ESTADOS</td>\n",path_nombre_tabla);
            for(int i=0; i<LISTADO_OBJ_TERMINAL_TRANSICION.size(); i++){
                CuerpoGrafica("<td bgcolor=\"green\">"+LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Terminal+"</td>\n",path_nombre_tabla);
            }
            CuerpoGrafica("</th>\n",path_nombre_tabla);
        }
        CuerpoGrafica("<tr><td bgcolor=\"green\">"+OBJ_ESTADO_CONJUNTO.RETORNAR_ESTADO_CONJUNTO()+"</td>\n",path_nombre_tabla);
        for(int i=0; i<LISTADO_OBJ_TERMINAL_TRANSICION.size(); i++){
            CuerpoGrafica("<td bgcolor=\"green\">"+LISTADO_OBJ_TERMINAL_TRANSICION.get(i).Transicion+"</td>\n",path_nombre_tabla);
        }
        CuerpoGrafica("</tr>\n",path_nombre_tabla);
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
            linea.println("fuera");
            linea.close();
            
        } catch (IOException ex) {
            System.out.println("Error en el metodo CuerpoGrafica del arbol");
        }
         
    }
    
   
    
    public OB_EC OBTENER_OBJETO_ESTADO_CONJUNTO(){
        return OBJ_ESTADO_CONJUNTO;
    }
    
    public ArrayList<OB_TT> OBTENER_FILAS_COLUMNAS_TERMINAL_TRANSICION(){
        return LISTADO_OBJ_TERMINAL_TRANSICION;
    }
        
    
    
}
