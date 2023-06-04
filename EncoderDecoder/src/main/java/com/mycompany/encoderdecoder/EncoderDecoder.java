/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.encoderdecoder;

public class EncoderDecoder {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java EncoderDecoder <comando> <imagen_entrada> <imagen_salida> [mensaje]");
            System.out.println("Comandos: encode, decode");
            return;
        }

        String comando = args[0];
        String imagenEntrada = args[1];
        String imagenSalida = args[2];

        if (comando.equals("encode")) {
            if (args.length < 4) {
                System.out.println("Debe proporcionar un mensaje para codificar.");
                return;
            }
            String mensaje = args[3];
            ImageEncoder encoder = new ImageEncoder();
            encoder.encodeMensaje(imagenEntrada, imagenSalida, mensaje);
        } else if (comando.equals("decode")) {
            ImageDecoder decoder = new ImageDecoder();
            String mensaje = decoder.decodeMensaje(imagenEntrada);
            if (mensaje != null) {
                System.out.println("Mensaje oculto: " + mensaje);
            } else {
                System.out.println("No se encontró ningún mensaje oculto en la imagen.");
            }
        } else {
            System.out.println("Comando no válido.");
        }
    }
}

