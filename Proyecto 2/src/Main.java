import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Main <comando> <argumentos>");
            System.out.println("Comandos válidos: codificar, decodificar");
            return;
        }

        String comando = args[0];

        if (comando.equals("codificar")) {
            if (args.length < 4) {
                System.out.println("Uso: java Main codificar <mensaje> <input.png> <out.png>");
                return;
            }
            String mensaje = args[1];
            String archivoEntrada = args[2];
            String archivoSalida = args[3];

            codificarMensaje(mensaje, archivoEntrada, archivoSalida);
        } else if (comando.equals("decodificar")) {
            if (args.length < 2) {
                System.out.println("Uso: java Main decodificar <input.png>");
                return;
            }
            String archivoEntrada = args[1];

            decodificarMensaje(archivoEntrada);
        } else {
            System.out.println("Comando inválido: " + comando);
        }
    }

    private static void codificarMensaje(String mensaje, String archivoEntrada, String archivoSalida) {
        try {
            BufferedImage imagen = Estenografia.leerImagen(archivoEntrada);
            Encoder encoder = new Encoder();
            encoder.codificarMensaje(imagen, mensaje);
            Estenografia.guardarImagen(imagen, archivoSalida);
            System.out.println("Mensaje codificado y guardado en: " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al leer/escribir la imagen: " + e.getMessage());
        }
    }

    private static void decodificarMensaje(String archivoEntrada) {
        try {
            BufferedImage imagen = Estenografia.leerImagen(archivoEntrada);
            Decoder decoder = new Decoder();
            String mensajeDecodificado = decoder.decodificarMensaje(imagen);
            System.out.println("Mensaje oculto en la imagen: " + mensajeDecodificado);
        } catch (IOException e) {
            System.out.println("Error al leer la imagen: " + e.getMessage());
        }
    }
}
