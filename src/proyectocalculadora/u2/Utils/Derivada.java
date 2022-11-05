/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora.u2.Utils;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author claua
 */
public class Derivada {

    private String funcion = "";

    // DJep es la clase encargada de la derivacion en su escencia
    DJep djep;
    Node nodoFuncion;
    Node nodoDerivada;

    public Derivada() {
    }

    public void setFuncionADerivar(String funcion) {
        this.funcion = funcion;
    }

    public String getFuncionDerivada() {
        return this.funcion;
    }

    public void derivar() {
        try {

            this.djep = new DJep();
            // agrega funciones estandares cos(x), sin(x)
            this.djep.addStandardFunctions();

            // agrega constantes estandares, pi, e, etc
            this.djep.addStandardConstants();

            // por si existe algun numero complejo
            this.djep.addComplex();

            // permite variables no declarables
            this.djep.setAllowUndeclared(true);

            // permite asignaciones
            this.djep.setAllowAssignment(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.setImplicitMul(true);

            // regla de multiplicacion o para sustraccion y sumas
            this.djep.addStandardDiffRules();

            // coloca el nodo con una funcion preestablecida
            this.nodoFuncion = this.djep.parse(this.funcion);

            // deriva la funcion con respecto a x
            Node diff = this.djep.differentiate(nodoFuncion, "x");
            //Node inte = this.djep.

            // Simplificamos la funcion con respecto a x
            this.nodoDerivada = this.djep.simplify(diff);

            // Convertimos el valor simplificado en un String
            this.funcion = this.djep.toString(this.nodoDerivada);

        } catch (ParseException e) {
            this.funcion = "NaN";
            System.out.println("Error: " + e.getErrorInfo());
        }
    }

    
    public static void main(String[] args) {
        Derivada d =  new Derivada();
        d.setFuncionADerivar("cos(2x)");
        d.derivar();
        
        System.out.print("d/dx (cos(2x)) = "); 
        System.out.println(d.getFuncionDerivada());
    }
}
