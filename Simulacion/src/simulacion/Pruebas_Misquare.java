package simulacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Pruebas_Misquare {
    
   double [] datos = { 0.0449, 0.6015, 0.63, 0.5514, 0.0207,
0.1733, 0.6694, 0.2531, 0.3116, 0.1067,
0.5746, 0.3972, 0.8297, 0.3587, 0.3587,
0.049, 0.7025, 0.6483, 0.7041, 0.1746,
0.8406, 0.1055, 0.6972, 0.5915, 0.3362,
0.8349, 0.1247, 0.9582, 0.2523, 0.1589,
0.92,  0.1977, 0.9085, 0.2545, 0.3727,
0.2564, 0.0125, 0.8524, 0.3044, 0.4145};
    
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
    
    
    public String gen_Midsquare(int semilla){
       int x=(int)Math.pow(semilla, 2);
       String cad=String.valueOf(x);
        while(cad.length()%4!=0){
            cad="0"+cad;
        }
        int inicio=(int) (cad.length()/4.0);
        int fin=cad.length()-(int) (cad.length()/4.0);
        cad=cad.substring(inicio, fin);
       return cad;
    }
    
    public void Midsquare(){
        ArrayList<Integer> numeros=new ArrayList<>();
        ArrayList<Double> n=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        int x0, cont=0;
        boolean repetido=false;
        System.out.println("Ingrese la semilla");
        do{
            if(cont>0) System.out.println("La semilla debe tener un numero par de cifras, preferiblemente 4");
            x0=sc.nextInt();
            cont++;
        }while(String.valueOf(x0).length()%2!=0);
        cont=1;
        numeros.add(x0);
        n.add(Double.parseDouble(("0."+String.valueOf(x0))));
        System.out.println("x"+cont+"="+x0+"  u"+cont+"=0."+x0);
        do{
            x0=Integer.parseInt(gen_Midsquare(x0));
            if(numeros.contains(x0)) repetido=true;
            else{
                numeros.add(x0);
                n.add(Double.parseDouble(("0."+String.valueOf(x0))));
                System.out.println("x"+cont+"="+x0+"  u"+cont+"=0."+x0);
            }
            cont++;
        }while(numeros.get(numeros.size()-1)!=0&&!repetido);
        System.out.println("");
        System.out.println("PRUEBA DE PROMEDIOS");
        prueba_promedios(n);
        System.out.println("");
        System.out.println("PRUEBA DE VARIANZA");
        prueba_varianza(n);
        System.out.println("");
        System.out.println("PRUEBA DE CHI CUADRADO");
        chi_cuadrado(n);
        System.out.println("");
        System.out.println("PRUEBA DE KOLMOGOROV");
        kolmogorov(n);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Pruebas_Misquare mid=new Pruebas_Misquare();
        mid.Midsquare();
        
        Menu menu =new Simulacion.Menu();
        menu.mostrar();
    }
}