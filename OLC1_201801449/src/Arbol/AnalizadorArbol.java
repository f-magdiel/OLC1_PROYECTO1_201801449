package Arbol;

/**
 *
 * @author magdiel
 */
public class AnalizadorArbol {
    
    public void entradaAnalizador(String expresionregular){
        String[] alfabeto = this.analizadorArbol(expresionregular);
        System.out.println("SEPERADOS**************************");
        for (int i = 0; i < alfabeto.length; i++) {
            System.out.println(alfabeto[i]);
        }
        System.out.println("FIN*********************************");
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
