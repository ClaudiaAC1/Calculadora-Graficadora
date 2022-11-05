/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora.u2.Utils;

/**
 *
 * @author claua
 */
public class Operaciones {

   
    public double porcentaje(double x, double porcentaje) {
        return x * (porcentaje / 100);
    }

    public double operacion(String operacion) {

        double aux = Double.parseDouble(operacion.charAt(0) + "");
        double resultado = 0.0;

        //switch
        for (int i = 1; i < operacion.length(); i++) {

            switch (operacion.charAt(i)) {
                case '*':
                    resultado = aux * Double.parseDouble(operacion.charAt(i + 1) + "");
                    break;

                case '-':
                    resultado = aux - Double.parseDouble(operacion.charAt(i + 1) + "");
                    break;

                case '+':
                    resultado = aux + Double.parseDouble(operacion.charAt(i + 1) + "");
                    break;

                case '/':
                    resultado = aux / Double.parseDouble(operacion.charAt(i + 1) + "");
                    break;

                default:
                    aux = resultado;
                    break;

            }
        }
        return resultado;
    }

   /**
    * Metodo chidito para hacer operaciones de mas de un digito y con punto decimal
    * @param operacion   cadena con la operacion o resolver
    * @return double resultado
    */
    public double operacion2(String operacion) {
        String operandos[] = operacion.split("(\\+|\\-|\\*|\\/)");
        String operadores[] = operacion.split("[0-9]");
         
        double resultado = Double.parseDouble(operandos[0]);
        int contador = 1;
        
        for(int i = 0; i < operadores.length; i++) {
            switch (operadores[i]) {
                case "*":
                        resultado *= Double.parseDouble(operandos[contador]);
                        contador ++;
                    break;

                case "-":
                        resultado -= Double.parseDouble(operandos[contador]);
                        contador ++;
                    break;

                case "+":
                        resultado += Double.parseDouble(operandos[contador]);
                        contador ++;
                    break;

                case "/":
                        resultado /= Double.parseDouble(operandos[contador]);
                        contador ++;
                    break;

                default:
                    System.out.println("kajfajf "+resultado);
                    break;

            }
        }
        return resultado;
    }
    
    public static void main(String[] args) {
       
    }
}
