package simulacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Q-antica IT
 */
public class Pruebas_Generador_Basico {

    /* PRUEBAS ESTADISTICAS */
    NormalDistribution nd = new NormalDistribution();
    ChiSquaredDistribution chi;
    DescriptiveStatistics estadistica = new DescriptiveStatistics();
    double H=0.5;
    double alfa=0.05;    
    /* PROMEDIOS */
    public double prom(ArrayList<Double> n){
        for(Double a:n){
            estadistica.addValue(a);
        }
        return estadistica.getMean();
    }
    
    public double calcular_rango_limite(int n){
        double z= nd.inverseCumulativeProbability(1 - alfa/2 );
        System.out.println("Z:        "+z);
        return z/Math.sqrt(12*n);
    }
    
    public boolean prueba_promedios(ArrayList<Double> n){
        double prom=prom(n);
        double rango=calcular_rango_limite(n.size());
        double LI=H-rango;
        double LS=H+rango;
        System.out.println("Mínimo:   "+LI);
        System.out.println("Máximo:   "+LS);
        System.out.println("Promedio: "+prom);
        System.out.println("PRUEBA DE PROMEDIOS:");
        if(prom>=LI&&prom<=LS){
            System.out.println("==========>ACEPTADO<==========");
            return true;
        }else{
            System.out.println("==========>RECHAZADO<==========");
            return false;
        }
    }   
    /* PROMEDIOS */
    
    /*VARIANZA*/
    
    public double varianza(ArrayList<Double> n){
        for(Double a:n){
            estadistica.addValue(a);
        }
        return estadistica.getVariance();
    }
    
    public boolean prueba_varianza(ArrayList<Double> n){
        double varianza=varianza(n);
        chi = new ChiSquaredDistribution(n.size()-1);
        double LI = chi.inverseCumulativeProbability(alfa/2)/(12*(n.size()-1));
        double LS = chi.inverseCumulativeProbability(1-(alfa/2))/(12*(n.size()-1));
        System.out.println("Mínimo:   "+LI);
        System.out.println("Máximo:   "+LS);
        System.out.println("Varianza: "+varianza);
        System.out.println("PRUEBA DE VARIANZA:");
        if(varianza>=LI&&varianza<=LS){
            System.out.println("==========>ACEPTADO<==========");
            return true;
        }else{
            System.out.println("==========>RECHAZADO<==========");
            return false;
        }
    }
    /*VARIANZA*/
    
    /*CHI CUADRADO*/
    public int aproximar(double n){
        double decimal=n-Math.floor(n);
        if(decimal<0.5) return (int) Math.floor(n);
        else return (int) Math.ceil(n);
    }
    
    public boolean chi_cuadrado(ArrayList<Double> n){
        ArrayList<tabla_chi_cuadrado> tabla=new ArrayList<>();
        chi = new ChiSquaredDistribution(n.size()-1);
        int m=aproximar(Math.sqrt(n.size()));
        System.out.println("m="+m);
        for(int i=0; i<m; i++){
            tabla_chi_cuadrado temp=new tabla_chi_cuadrado();
            temp.setInicio(i/(1.0*m));
            temp.setFin((i+1)/(1.0*m));
            int cont=0;
            for(Double t: n){
                if(t>=temp.getInicio()&&t<temp.getFin()) cont++;
            }
            temp.setFo(cont);
            temp.setFe(aproximar(n.size()/(1.0*m)));
            temp.setEstadistico(Math.pow((temp.getFe()-temp.getFo()), 2.0)/temp.getFe());
            tabla.add(temp);
        }
        double sumatoria=0.0;
        System.out.println("|  INTERVALO  | FO | FE | ESTADISTICO |");
        for(tabla_chi_cuadrado t:tabla){
            //System.out.println("|"+t.getInicio()+"-"+t.getFin()+"|");
            sumatoria+=t.getEstadistico();
            System.out.printf("|%.4f-%.4f|%4d|%4d|%13.4f|%n", t.getInicio(), t.getFin(), t.getFo(), t.getFe(), t.getEstadistico());
        }
        double X=chi.inverseCumulativeProbability(alfa);
        System.out.printf("                    SUMA|%13.4f|%n", sumatoria);
        System.out.printf("          TABLA Xa, %4d|%13.4f|%n", n.size(), X);
        System.out.println("");
        if(sumatoria<X){
            System.out.println("==========>ACEPTADO<==========");
            return true;
        }else{
            System.out.println("==========>RECHAZADO<==========");
            return false;
        }
    }
    /*CHI CUADRADO*/
    
    /*KOLMOGOROV*/
    public boolean kolmogorov(ArrayList<Double> n){
        ArrayList<Double> Fn=new ArrayList<>();
        ArrayList<Double> f_Fn=new ArrayList<>();
        Collections.sort(n);
        chi = new ChiSquaredDistribution(n.size()-1);
        double X=chi.inverseCumulativeProbability(alfa);
        double mayor=0.0;
        System.out.printf("|   |  f   |   F  |  F-f   |%n");
        for(int i=0; i<n.size(); i++){
            Fn.add((i+1)/(1.0*n.size()));
            f_Fn.add(Math.abs(n.get(i)-Fn.get(i)));
            if(f_Fn.get(i)>mayor) mayor=f_Fn.get(i);
            System.out.printf("|%3d|%.4f|%.4f|%8.4f|%n", (i+1), n.get(i), Fn.get(i), f_Fn.get(i));
        }
        System.out.printf("             MAYOR|%8.4f|%n", mayor);
        System.out.printf("     TABLA Xa %4d|%8.4f|%n", n.size(), X);
        if(mayor<X){
            System.out.println("==========>ACEPTADO<==========");
        }else{
            System.out.println("==========>RECHAZADO<==========");
        }
        return true;
    }
    /*KOLMOGOROV*/
    
    /* PRUEBAS ESTADISTICAS */
    
    
    
    public static void main(String[] args) throws ScriptException {
        
        
        
        
        // TODO code application logic here
        ArrayList<Double> X=new ArrayList<>();//Lista para guardar Xn
        ArrayList<Double> U=new ArrayList<>();//Lista para guardar Un
        Pruebas_Generador_Basico gen=new Pruebas_Generador_Basico();
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
        double mayor=0.0;
        int cifras=0;
        for(Double d:U){
            System.out.println("U["+cont+"]="+d);
            cont++;
            if(d>mayor) mayor=d;
        }
        do{
            mayor=mayor/Math.pow(10, cifras);
            cifras++;
        }while(mayor>=1);
        for(Double d:U){
            d=d/Math.pow(10, cifras);
        }
        System.out.println("");
        System.out.println("PRUEBA DE PROMEDIOS");
        gen.prueba_promedios(U);
        System.out.println("");
        System.out.println("PRUEBA DE VARIANZA");
        gen.prueba_varianza(U);
        System.out.println("");
        System.out.println("PRUEBA DE CHI CUADRADO");
        gen.chi_cuadrado(U);
        System.out.println("");
        System.out.println("PRUEBA DE KOLMOGOROV");
        gen.kolmogorov(U);
        
        Menu menu =new Simulacion.Menu();
        menu.mostrar();
    }
    
}
