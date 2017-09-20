package simulacion;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Wichmann_y_Hill{
    Scanner sc=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    
    private Double calcular_U(Integer X, Integer Y, Integer Z){
        return (X/30269.0)+(Y/30307.0)+(Z/30323.0);
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
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Wichmann_y_Hill gen=new Wichmann_y_Hill();
        gen.generador_Wichmann_Hill();
        
        Menu menu =new Menu();
        menu.mostrar();
    }
    
}