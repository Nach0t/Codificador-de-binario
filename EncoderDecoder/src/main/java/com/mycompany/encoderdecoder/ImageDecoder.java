package com.mycompany.encoderdecoder;

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageDecoder {
    public String decodeMensaje(String imagenEntrada) {
        try {
            BufferedImage imagen = ImageIO.read(new File(imagenEntrada));

            StringBuilder mensaje = new StringBuilder();
            boolean encontradoDelimitador = false;

            for (int y = 0; y < imagen.getHeight(); y++) {
                for (int x = 0; x < imagen.getWidth(); x++) {
                    int rgb = imagen.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    // Decodificar el mensaje de los bits menos significativos de cada componente RGB
                    char caracter = (char) (((r & 0x01) << 7) | ((g & 0x01) << 6) | ((b & 0x01) << 5));

                    if (encontradoDelimitador) {
                        if (caracter == '/') {
                            return mensaje.toString();
                        } else {
                            mensaje.append(caracter);
                        }
                    } else {
                        if (caracter == '%') {
                            encontradoDelimitador = true;
                        }
                    }
                }
            }

            return null;
        } catch (IOException e) {
            System.out.println("Error al leer la imagen: " + e.getMessage());
            return null;
        }
    }
}

