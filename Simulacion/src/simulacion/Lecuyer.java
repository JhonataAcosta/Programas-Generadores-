package simulacion;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Lecuyer {
    Scanner sc=new Scanner(System.in);
    
    public void gen_Lecuyer(){
        int cont=0;
        boolean romper=true;
        Integer x, y, z;
        Double u;
        ArrayList<Double> U=new ArrayList<>();
        ArrayList<Integer> Z=new ArrayList<>();
        do{
            System.out.println("Ingrese la semilla x (No puede ser 0, ni numero par)");
            x=sc.nextInt();
        }while(x%2==0&&x==0);
        do{
            System.out.println("Ingrese la semilla y (No puede ser 0, ni numero par)");
            y=sc.nextInt();
        }while(y%2==0&&y==0);
        do{
            z=(x-y)%2147483562;
            if(Z.contains(z)) romper=false;
            else Z.add(z);
            if(z<0) z*=-1;
            if(z>=0) u=z/2147483563.0;
            else u=2147483562/2147483563.0;
            U.add(u);
            System.out.println("X["+cont+"]="+x+" Y["+cont+"]= "+y+" Z["+cont+"]="+z+" U["+cont+"]="+u);
            x=(40014*x)%2147483563;
            y=(40692*y)%2147483399;
            cont++;
        }while(romper);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Lecuyer gen=new Lecuyer();
        gen.gen_Lecuyer();
        
        Menu menu =new Simulacion.Menu();
        menu.mostrar();
    }
    
}
