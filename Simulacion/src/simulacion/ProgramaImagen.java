/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.script.ScriptException;

/**
 *
 * @author Q-antica IT
 */
public class ProgramaImagen {
  
    public void calculos_imagen () throws ScriptException, IOException{
     
    //Imagen imagen =new Imagen();
    //imagen.setVisible(true);
        
    
    InputStream input = new FileInputScream ("falcon.png");
    ImageInputStream imageInput= ImageIO.createImageInputStream(input);
    BufferedImage imagenL= ImageIO.read(imageInput);
    
    int alto  = imageL.getHeight();
    int ancho = imageL.getHeight();
    
        System.err.println("alto"+alto+"ancho"+ancho);
        
        for(int i=0; i<imagenL.getHeight();i++){
            for(int j=0; j<imagenL.getHeight();j++){
            
                int srcPixel = imagenL.getRGB(i, j);
                Color c= new Color(srcPixel);
                
                int valR = c.getRed();
                int valG = c.getGreen();
                int valB = c.getBlue();
                System.err.println("R"+valR+"G"+valG+"B"+valB);
            }
            System.out.println("");
        }
   
    }


public static void main(String[] args) throws ScriptException, IOException {
        
        ProgramaImagen img=new ProgramaImagen();
        img.calculos_imagen();
        
        Menu menu =new Simulacion.Menu();
        menu.mostrar();
    }
}