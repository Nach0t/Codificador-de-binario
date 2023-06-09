
package com.mycompany.taller2;

import java.io.IOException;

/**
 * Esta clase es la clase principal del programa.
 */
public class Taller2 {

    /**
     * El método main es el punto de entrada del programa.
     *
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Main <comando> <argumentos>");
            System.out.println("Comandos válidos: encode, decode");
            return;
        }

        String comando = args[0];

        if (comando.equals("encode")) {
            if (args.length < 4) {
                System.out.println("Uso: java Main encode <mensaje> <input.png> <out.png>");
                return;
            }
            String mensaje = args[1];
            String archivoEntrada = args[2];
            String archivoSalida = args[3];

            Encoder encoder = new Encoder();
            try {
                encoder.codificarMensaje(mensaje, archivoEntrada, archivoSalida);
                System.out.println("Mensaje codificado y guardado en: " + archivoSalida);
            } catch (IOException e) {
                System.out.println("Error al leer/escribir la imagen: " + e.getMessage());
            }
        } else if (comando.equals("decode")) {
            if (args.length < 2) {
                System.out.println("Uso: java Main decode <input.png>");
                return;
            }
            String archivoEntrada = args[1];

            Decoder decoder = new Decoder();
            try {
                String mensajeDecodificado = decoder.decodificarMensaje(archivoEntrada);
                System.out.println("Mensaje oculto en la imagen: " + mensajeDecodificado);
            } catch (IOException e) {
                System.out.println("Error al leer la imagen: " + e.getMessage());
            }
        } else {
            System.out.println("Comando inválido: " + comando);
        }
    }
}