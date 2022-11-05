/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora.u2.Utils;

import java.util.ArrayList;

/**
 *
 * @author claua
 */
public class Evaluador {
    
   
    private String resultadoConversion;
    private String resultadoOperacion;
    private int indiceIni;
    private int indiceFin;
    private  String valor = "";
 
    public Evaluador() {
    }
    
    public String analizaCadena(String cadena, String valor) {
        this.valor =  valor;
        cadena = cadena.replaceAll("x", valor);
        
        cadena = quitarEspacios(cadena);
        
        cadena = " " + cadena;
        char[] vectorCadena = cadena.toCharArray();
        if (cadena.contains("^")) {            
            cadena = reemplazaPotencia(vectorCadena, cadena);
        }
        vectorCadena = cadena.toCharArray();
        if (cadena.contains("cos")) {
            cadena = reemplazaTrigonometrica(vectorCadena, cadena, "Math.cos", 'c');
        }
        vectorCadena = cadena.toCharArray();
        if (cadena.contains("sen")) {
            cadena = reemplazaTrigonometrica(vectorCadena, cadena, "Math.sin", 's');
        }
        vectorCadena = cadena.toCharArray();
        if (cadena.contains("tan")) {
            cadena = reemplazaTrigonometrica(vectorCadena, cadena, "Math.tan", 't');
        }
        System.out.println("1");
        return cadena;
    }
 
    public String reemplazaParIzq(char[] cadena, int indice) {
        ArrayList<Character> lista1 = new ArrayList<Character>();
        ArrayList<Character> lista2 = new ArrayList<Character>();
        String res = "";
        int i;
        for (i = indice - 1; i >= 0; i--) {
            if (cadena[i] == ')') {
                lista1.add(cadena[i]);
            } else if (cadena[i] == '(') {
                lista2.add(cadena[i]);
            }
            res = cadena[i] + res;
            if ((lista1.size() == lista2.size()) && (i != (indice - 1))) {
                this.indiceIni = i;
                return res;
            }
        }
        return null;
    }
 
    public String reemplazaParDer(char[] cadena, int indice) {
        ArrayList<Character> lista1 = new ArrayList<Character>();
        ArrayList<Character> lista2 = new ArrayList<Character>();
        String res = "";
        int i;
        for (i = indice + 1; i < cadena.length; i++) {
            if (cadena[i] == '(') {
                lista1.add(cadena[i]);
            } else if (cadena[i] == ')') {
                lista2.add(cadena[i]);
            }
            res += cadena[i];
            if ((lista1.size() == lista2.size()) && (i != (indice + 1))) {
                this.indiceFin = i + 1;
                return res;
            }
        }
        return null;
    }
 
    public String reemplazaNumIzq(char[] cadena, int indice) {
        String resultadoBase = "";
        String res = "";
        for (int i = indice - 1; i >= 0; i--) { //i=2
            res = resultadoBase;
            res += cadena[i]; //res=""+5
            if (isNumeric(res)) { //true
                resultadoBase = cadena[i] + resultadoBase; //=5
                this.indiceIni = i; //=2
            } else {
                break;
            }
        }
        System.out.println("cadena: " + resultadoBase);
        return resultadoBase; //
    }
 
    public String reemplazaNumDer(char[] cadena, int indice) {
        String resultadoBase = "";
        String res = "";
        for (int i = indice + 1; i < cadena.length; i++) {
            res = resultadoBase;
            res += cadena[i];
            if (isNumeric(res)) {
                resultadoBase += cadena[i];
                this.indiceFin = i + 1;
            } else {
                break;
            }
        }
        return resultadoBase;
    }
 
    public boolean isNumeric(String str) {
        if (!str.equals(".")) {
            try {
                Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        } else {
            return true;
        }
    }
 
    public String reemplazaPotencia(char[] vectorCadena, String cadena) {
        String resIzq = "", resDer = "";
        
        for (int indice = 0; indice < vectorCadena.length; indice++) { 
            
            if (vectorCadena[indice] == '^') {
                if (vectorCadena[indice - 1] == ')') {
                    resIzq = reemplazaParIzq(vectorCadena, indice);
                } else {
                    resIzq = reemplazaNumIzq(vectorCadena, indice);
                }
                if (vectorCadena[indice + 1] == '(') {
                    resDer = reemplazaParDer(vectorCadena, indice);
                } else {
                    resDer = reemplazaNumDer(vectorCadena, indice);
                }
 
                vectorCadena = (cadena.substring(0, this.indiceIni) + "Math.pow("
                        + resIzq + "," + resDer + ")" + (cadena.substring(this.indiceFin, cadena.length()))).toCharArray();
                cadena = (cadena.substring(0, this.indiceIni) + "Math.pow("
                        + resIzq + "," + resDer + ")" + (cadena.substring(this.indiceFin, cadena.length())));
                indice = 0;
            }
        }
        return cadena;
    }
 
    public String reemplazaTrigonometrica(char[] vectorCadena, String cadena, String operacion, char caracter) {
        String resDer = "";
        for (int indice = 0; indice < vectorCadena.length; indice++) {
            if ((vectorCadena[indice] == caracter) && ((vectorCadena)[indice - 1] != '.') && (indice != 0)) {
                if (vectorCadena[indice + 3] == '(') {
                    resDer = reemplazaParDer(vectorCadena, indice + 2);
                } else {
                    resDer = reemplazaNumDer(vectorCadena, indice + 2);
                }
 
                vectorCadena = (cadena.substring(0, indice) + operacion + "("
                        + resDer + ")" + (cadena.substring(this.indiceFin, cadena.length()))).toCharArray();
                cadena = (cadena.substring(0, indice) + operacion + "("
                        + resDer + ")" + (cadena.substring(this.indiceFin, cadena.length())));
                indice = 0;
            }
        }
        return cadena;
    }
 
    public String getResultadoConversion() {
        return this.resultadoConversion;
    }
 
    public String getResultadoOperacion() {
        return resultadoOperacion;
    }
 
    public String quitarEspacios(String sTexto) {
        String sCadenaSinBlancos = "";
        for (int x = 0; x < sTexto.length(); x++) {
            if (sTexto.charAt(x) != ' ') {
                sCadenaSinBlancos += sTexto.charAt(x);
            }
        }
        return sCadenaSinBlancos;
    }
 
       
    public static void main(String[] args) {
        
        
    }
}
