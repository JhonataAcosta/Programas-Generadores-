package Simulacion;

import java.io.IOException;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Menu {

    public void mostrar() throws ScriptException, IOException {
        String datos_seleccionados;
        JOptionPane.showMessageDialog(null, "\nSimulación\nJhonatan Julian Acosta\n ID: 2009711");
        do {
            datos_seleccionados = JOptionPane.showInputDialog("MENU PRINCIPAL\n"
                    + "Seleccione una opción\n"
                    + "1. Generador Básico\n "
                    + "2. Generador Midsquare \n "
                    + "3. Congruencial lineal\n "
                    + "4. Generador Fibonacci\n "
                    + "5. Wichmann y Hill\n "
                    + "6. L'Ecuyer\n "
                    + "7. Programa Imagen\n"
                    + "8. Salir");
            switch (datos_seleccionados) {
                case "1":
                    realizar_calculos();
                    break;
                    case "2":
                    Midsquare();
                    break;
                    case "3":
                    Congruencial();
                    break;
                    case "4":
                    generador_Fibonacci();
                    break;
                    case "5":
                   generador_Wichmann_Hill();
                    break;
                    case "6":
                    gen_Lecuyer();
                    case "7":
                    calculos_imagen();
                    break;
                    case "8":
                     salida();
                    break;
                    default:
                    opcionInvalida();
                    break;
            }
        } while(!datos_seleccionados.equals("8"));
    }

    private void realizar_calculos() throws ScriptException {
        
        Generador_Basico generador = new Generador_Basico();
        generador.realizar_calculos();
    }
    
    private void Midsquare() {
       Generador_Midsquare mid=new Generador_Midsquare();
        mid.Midsquare();
    }
    
    private void Congruencial() {
        Congruencial_Lineal congruencial=new Congruencial_Lineal();
        congruencial.Congruencial();
    }
    
    private void generador_Fibonacci() {
       simulacion.Fibonacci gen=new simulacion.Fibonacci();
       gen.generador_Fibonacci();
    }
    
    private void generador_Wichmann_Hill() {
        simulacion.Wichmann_y_Hill gen=new simulacion.Wichmann_y_Hill();
        gen.generador_Wichmann_Hill();
    }
    
    private void gen_Lecuyer() {
        simulacion.Lecuyer gen=new simulacion.Lecuyer();
        gen.gen_Lecuyer();
    }
    
    private void calculos_imagen() throws ScriptException, IOException {
        
    simulacion.ProgramaImagen img=new simulacion.ProgramaImagen();
    img.calculos_imagen();
    }
    
    private void salida() {
       JOptionPane.showMessageDialog(null, "Has salido del programa, hasta pronto!!!");
    }
    
    private void opcionInvalida() {
       JOptionPane.showMessageDialog(null, "Opción Invalida");
    }
}