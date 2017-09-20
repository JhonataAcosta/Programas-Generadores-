package simulacion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Fibonacci {
    Scanner sc=new Scanner(System.in);
    
    private Integer suma(Integer a, Integer b){
        return a+b;
    }
    
    private Integer resta(Integer a, Integer b){
        return suma(a, -b);
    }
    
    private Integer multiplicacion(Integer a, Integer b){
        return a*b;
    }
    
    private Integer sig_fibonacci(Integer n_1, Integer n_2, int operacion){
        if(operacion==1) return suma(n_1, n_2);
        else if(operacion==2) return resta(n_1, n_2);
        else return multiplicacion(n_1, n_2);
    }
    
    public void generador_Fibonacci(){
        int sem1, sem2, m, operacion=1, rand, cont=0;
        ArrayList<Integer> aleatorios=new ArrayList<>();
        System.out.println("Semilla 1: ");
        sem1=sc.nextInt();
        System.out.println("Semilla 2: ");
        sem2=sc.nextInt();
        do{
            System.out.println("Ingrese un numero m que sea estrictamente mayor a 1");
            m=sc.nextInt();
        }while(m<=1);
        do{
            System.out.println("Seleccione la operacion para el generador");
            System.out.println("1. SUMA");
            System.out.println("2. RESTA");
            System.out.println("3. MULTIPLICACION");
            operacion=sc.nextInt();
        }while(operacion<=0||operacion>3);
        aleatorios.add(sem1);
        rand=sem2;
        do{
            aleatorios.add(rand);
            rand=sig_fibonacci(sem1, sem2, operacion)%m;
            sem1=sem2;
            sem2=rand;
        }while(!aleatorios.contains(rand));
        for(Integer i:aleatorios){
            System.out.println("F["+cont+"]="+i);
            cont++;
        }
    }
    
    public static void main(String[] args) {
        
        Fibonacci gen=new Fibonacci();
        gen.generador_Fibonacci();
        
        Menu menu =new Menu();
        menu.mostrar();
    }
}