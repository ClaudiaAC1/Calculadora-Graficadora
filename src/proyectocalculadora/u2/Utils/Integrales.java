package proyectocalculadora.u2.Utils;

import proyectocalculadora.u2.views.cientifica;
import static proyectocalculadora.u2.Utils.metodos.alto;
import static proyectocalculadora.u2.Utils.metodos.ancho;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author MIGUEL
 */
public class Integrales extends JPanel {

    metodos grafica = new metodos();
    cientifica c = new cientifica();

    JButton BotonGraficar = new JButton("Graficar");
    JButton BotonIntegrar = new JButton("Integrar");
    JLabel Xmax = new JLabel("X max");
    JLabel Xmin = new JLabel("X min");
    JLabel Ymax = new JLabel("Y max");
    JLabel Ymin = new JLabel("Y min");
    JLabel Valores = new JLabel("Área entre:");
    JLabel Integral = new JLabel("El área es:");

    public static JTextField xmax = new JTextField("10");
    public static JTextField xmin = new JTextField("-10");
    public static JTextField ymax = new JTextField("10");
    public static JTextField ymin = new JTextField("-10");
    public static JTextField valor1 = new JTextField("");
    public static JTextField valor2 = new JTextField("");
    public static JTextArea integral1 = new JTextArea();

    int m1 = 0, m2 = 0, m3 = 0, m4 = 0, longitud1 = 0, longitud2 = 0, ejex = 0, ejey = 0;
    int n = 1000;
    double h = 0.01;

    public Integrales() {
        xmax.setVisible(false);
        xmin.setVisible(false);
        ymax.setVisible(false);
        ymin.setVisible(false);

        this.setVisible(true);

        setLayout(null);
        grafica.setBounds(25, 25, metodos.ancho, metodos.alto);
        add(grafica);

        BotonGraficar.setBounds(530, 100, 90, 25);
        BotonIntegrar.setBounds(530, 205, 90, 25);
        Valores.setBounds(540, 135, 90, 25);
        valor1.setBounds(530, 170, 30, 25);
        valor2.setBounds(590, 170, 30, 25);
        Integral.setBounds(530, 240, 90, 25);
        integral1.setBounds(530, 275, 90, 25);
        Xmin.setBounds(25, 415, 30, 20);
        Xmax.setBounds(445, 415, 30, 20);
        Ymax.setBounds(495, 25, 30, 20);
        Ymin.setBounds(495, 380, 30, 20);

        add(BotonGraficar);
        add(BotonIntegrar);
        add(Valores);
        add(valor1);
        add(valor2);
        add(Integral);
        add(integral1);
        add(xmin);
        add(Xmin);
        add(xmax);
        add(Xmax);
        add(ymax);
        add(Ymax);
        add(ymin);
        add(Ymin);

        BotonGraficar.addActionListener((ActionEvent ae) -> {
            m1 = Integer.parseInt(Integrales.xmin.getText());
            m2 = Integer.parseInt(Integrales.xmax.getText());
            m3 = Integer.parseInt(Integrales.ymax.getText());
            m4 = Integer.parseInt(Integrales.ymin.getText());

            longitud1 = (int) ancho / (Math.abs(m1) + Math.abs(m2));
            longitud2 = (int) alto / (Math.abs(m3) + Math.abs(m4));

            double w = (0.004 / 2) * (m3 + Math.abs(m4));
            double p = -(0.50) / ((Math.abs(m2)));
            System.out.println("c.getOp "+c.getOperacion());
            System.out.println("btn graficar"+metodos.evaluarF(c.getOperacion(), p));
            //String funcion= "sen(x)";
            for (int x1 = 0; x1 < 459; x1++) {
                //double y1 = metodos.alto*p * metodos.f(w * (x1 + (m1*longitud1))) + (m3*longitud2);
                double y1 = metodos.alto * p * metodos.evaluarF(c.getOperacion(), w * (x1 + (m1 * longitud1))) + (m3 * longitud2);

                metodos.ejeX[x1] = x1;
                metodos.ejeY[x1] = y1;
            }

            for (int i = 0; i < 459; i++) {
                //double y2 = alto*p*((metodos.f((w*(i+(m1*longitud1)))+h)-metodos.f((i+(m1*longitud1))*w))/h)+(m3*longitud2);
                double y2 = alto * p * ((metodos.evaluarF(c.getOperacion(), (w * (i + (m1 * longitud1))) + h) - metodos.evaluarF(c.getOperacion(), (i + (m1 * longitud1)) * w)) / h) + (m3 * longitud2);

                //System.out.println(i + ">>>" +y2);
                metodos.ejeX1[i] = i;
                metodos.ejeY1[i] = y2;
            }
            grafica.repaint();
        });

        BotonIntegrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m1 = Integer.parseInt(Integrales.xmin.getText());
                m2 = Integer.parseInt(Integrales.xmax.getText());
                m3 = Integer.parseInt(Integrales.ymax.getText());
                m4 = Integer.parseInt(Integrales.ymin.getText());

                longitud1 = (int) ancho / (Math.abs(m1) + Math.abs(m2));
                longitud2 = (int) alto / (Math.abs(m3) + Math.abs(m4));

                double w = (0.004 / 2) * (m3 + Math.abs(m4));
                double p = -(0.50) / ((Math.abs(m2)));

                String texto;
                texto = valor1.getText();
                double x1 = Double.parseDouble(texto);

                texto = valor2.getText();
                double x2 = Double.parseDouble(texto);

                int lim1 = ancho / 2 + ((int) x1 * longitud1);
                int lim2 = ancho / 2 + ((int) x2 * longitud1);

                System.out.println("Los limites son: " + lim1 + " " + lim2);

                double g = (x2 - x1) / n;
                double a, suma = 0;
                //double d = g/2*(metodos.f(x1)+metodos.f(x2));
                double d = g / 2 * (metodos.evaluarF(c.getOperacion(), x1) + metodos.evaluarF(c.getOperacion(), x2));

                for (int i = 1; i < n; i++) {
                    //a = g*metodos.f(x1+i*g);
                    a = g * metodos.evaluarF(c.getOperacion(), x1 + i * g);
                    suma = suma + a;
                }
                if (x1 == x2) {
                    suma = 0;
                }

                double Int = (-1) * (d + suma);
                Integrales.integral1.setText(String.valueOf(Int));

                for (int x = lim1; x <= lim2; x++) {
                    //double y = metodos.alto * p * metodos.f(w*(x+(m1*longitud1))) + (m3*longitud2);
                    double y = metodos.alto * p * metodos.evaluarF(c.getOperacion(), w * (x + (m1 * longitud1))) + (m3 * longitud2);
                    metodos.ejeX2[x] = x;
                    metodos.ejeY2[x] = y;
                }
                grafica.repaint();
            }
        });
    }

}
