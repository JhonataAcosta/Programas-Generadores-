package Simulacion;

import javax.script.ScriptException;
import javax.swing.JOptionPane;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Menu {

    public void mostrar() throws ScriptException {
        String datos_seleccionados;
        JOptionPane.showMessageDialog(null, "\nSimulación\nJhonatan Julian Acosta\n ID: 2009711");
        do {
            datos_seleccionados = JOptionPane.showInputDialog("MENU PRINCIPAL\n"
                    + "Seleccione una opción\n"
                    + "1. Generador Básico\n "
                    + "2. Generador Midsquare \n "
                    + "3. Congruencial lineal\n "
                    + "4. Salir");
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
                    salida();
                    break;
                default:
                    opcionInvalida();
                    break;
            }
        } while(!datos_seleccionados.equals("4"));
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
    
    private void salida() {
       JOptionPane.showMessageDialog(null, "Has salido del programa, hasta pronto!!!");
    }
    
    private void opcionInvalida() {
       JOptionPane.showMessageDialog(null, "Opción Invalida");
    }
}