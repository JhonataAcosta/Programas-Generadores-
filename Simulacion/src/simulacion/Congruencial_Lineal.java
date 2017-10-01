package Simulacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptException;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Congruencial_Lineal {
    
    public void Congruencial(){
        Scanner sc=new Scanner(System.in);
        boolean repetido=false;
        ArrayList<Integer> numeros=new ArrayList<>();
        int x0, a, m, b;
        System.out.println("Ingrese la semilla x0");
        x0=sc.nextInt();
        System.out.println("Ingrese a");
        a=sc.nextInt();
        System.out.println("Ingrese b");
        b=sc.nextInt();
        System.out.println("Ingrese m");
        m=sc.nextInt();
        numeros.add(x0);
        do{
            x0=(a*numeros.get(numeros.size()-1)+b)%m;
            if(numeros.contains(x0)) repetido=true;
            else numeros.add(x0);
        }while(!repetido);
        int cont=0;
        for(Integer i:numeros){
            System.out.println("x"+cont+"="+i);
            cont++;
        }
    }
 
    public static void main(String[] args) throws ScriptException, IOException {
        
        Congruencial_Lineal congruencial=new Congruencial_Lineal();
        congruencial.Congruencial();
        
        Menu menu =new Menu();
        menu.mostrar();
    }
    
}