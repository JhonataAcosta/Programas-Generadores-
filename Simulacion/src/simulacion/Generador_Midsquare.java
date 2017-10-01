package Simulacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptException;
/**
 * Jhonatan Julian Acosta
 * ID: 209711
 */
public class Generador_Midsquare {
    

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
        System.out.println("x"+cont+"="+x0+"  u"+cont+"=0."+x0);
        do{
            x0=Integer.parseInt(gen_Midsquare(x0));
            if(numeros.contains(x0)) repetido=true;
            else{
                numeros.add(x0);
                System.out.println("x"+cont+"="+x0+"  u"+cont+"=0."+x0);
            }
            cont++;
        }while(numeros.get(numeros.size()-1)!=0&&!repetido);
    }
    
    public static void main(String[] args) throws ScriptException, IOException {
        
        Generador_Midsquare mid=new Generador_Midsquare();
        mid.Midsquare();
        
        Menu menu =new Menu();
        menu.mostrar();
    }
    
}
