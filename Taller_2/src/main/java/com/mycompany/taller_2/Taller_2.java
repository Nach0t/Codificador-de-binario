
package com.mycompany.taller_2;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Taller_2 {
    public static void main (String[] args){
        try {
            // La ruta de la imagen PNG que se desea leer
            File archivo = new File("C:/Users/diazc/OneDrive/Documentos/Imagen png 1080x1080.png");
            
            // Leer la imagen PNG
            BufferedImage imagen = ImageIO.read(archivo);
            
            // Obtener la anchura y altura de la imagen
            int anchura = imagen.getWidth();
            int altura = imagen.getHeight();
            
            // Verificar que la imagen tenga una resolución correcta (920x600)
            if (anchura == 920 && altura == 600) {
                System.out.println("La imagen tiene una resolución de 920x600");
            } else {
                System.out.println("La imagen no tiene una resolución de 920x600");
            }
            
        } catch (Exception e) {
            System.out.println("Error al leer la imagen: " + e.getMessage());
        }
        
        
    }
    
}
