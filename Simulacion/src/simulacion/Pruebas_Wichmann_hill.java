package simulacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Pruebas_Wichmann_hill{
    Scanner sc=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    
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
    
    private Double calcular_U(Integer X, Integer Y, Integer Z){
        double t=(X/30269.0)+(Y/30307.0)+(Z/30323.0);
        return t-Math.floor(t);
    }
    
    public void generador_Wichmann_Hill(){
        ArrayList<Double> U=new ArrayList<>();
        int cont=1;
        System.out.print("Ingrese un valor para X[0]=");
        Integer x=sc.nextInt();
        System.out.print("Ingrese un valor para Y[0]=");
        Integer y=sc.nextInt();
        System.out.print("Ingrese un valor para Z[0]=");
        Integer z=sc.nextInt();
        do{
            U.add(calcular_U(x, y, z));
            x=(171*x)%30269;
            y=(172*y)%30307;
            z=(170*z)%30323;
            System.out.println("X["+cont+"]="+x+" Y["+cont+"]="+y+" Z["+cont+"]="+z+" U["+cont+"]="+calcular_U(x, y, z));
            cont++;
        }while(!U.contains(calcular_U(x, y, z)));
        System.out.println("");
        System.out.println("PRUEBA DE PROMEDIOS");
        prueba_promedios(U);
        System.out.println("");
        System.out.println("PRUEBA DE VARIANZA");
        prueba_varianza(U);
        System.out.println("");
        System.out.println("PRUEBA DE CHI CUADRADO");
        chi_cuadrado(U);
        System.out.println("");
        System.out.println("PRUEBA DE KOLMOGOROV");
        kolmogorov(U);
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Pruebas_Wichmann_hill gen=new Pruebas_Wichmann_hill();
        gen.generador_Wichmann_Hill();
    }
    
}