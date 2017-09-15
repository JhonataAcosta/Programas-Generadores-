package Simulacion;

import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Generador_Basico {
    
    public void realizar_calculos() throws ScriptException{
    
    // TODO code application logic here
        ArrayList<Double> X=new ArrayList<>();//Lista para guardar Xn
        ArrayList<Double> U=new ArrayList<>();//Lista para guardar Un
        int cont=0;//Contador para mostrar las listas
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese la Semilla==>");
        int semilla=sc.nextInt();
        System.out.println("Ingrese el numero de Iteraciones==>");
        int iteraciones=sc.nextInt();
        sc.nextLine();//Limpieza de bufer
        System.out.println("Ingrese la funcion X==>");
        String fx=sc.nextLine();
        System.out.println("Ingrese la funcion G==>");
        String gx=sc.nextLine();
        System.out.println("==================================================");
        for(int i=0; i<iteraciones; i++){
            ScriptEngineManager manager = new ScriptEngineManager();//Metodos para poder declarar el eval
            ScriptEngine engine = manager.getEngineByName("js");
            String[] x=fx.split("x");//Se parte la cadena de caracteres de la funcion cada que hay una x
            String cadena="";
            if(fx.equals("x")) cadena+=i;//Caso que la funcion es x
            else{
                for(int j=0; j<x.length; j++){//recoge los pedazo de la funcion y reemplaza las x por el valor de i
                cadena+=x[j];
                if(j!=x.length-1||fx.charAt(fx.length()-1)=='x') cadena+=i;
                }
            }
            X.add(Double.parseDouble(String.valueOf(engine.eval(cadena))));//Añade al arraylist el resultado de evaluar la funcion
            x=gx.split("x");
            cadena="";
            if(gx.equals("x")) cadena+=i;
            else{
                for(int j=0; j<x.length; j++){
                cadena+=x[j];
                if(j!=x.length-1||fx.charAt(fx.length()-1)=='x') cadena+=i;
                }
            }
            U.add(Double.parseDouble(String.valueOf(engine.eval(cadena))));
        }
        System.out.println("X");
        for(Double d:X){
            System.out.println("X["+cont+"]="+d);
            cont++;
        }
        cont=0;
        System.out.println("U");
        for(Double d:U){
            System.out.println("U["+cont+"]="+d);
            cont++;
        }
    }
    
    public static void main(String[] args) throws ScriptException {
        
        Menu menu =new Menu();
        menu.mostrar();
        }
}
    