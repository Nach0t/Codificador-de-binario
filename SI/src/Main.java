import java.awt.image.BufferedImage;
import java.io.IOException;
public class Main {

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
            String inputFile = args[2];
            String outputFile = args[3];

            Codificar(mensaje, inputFile, outputFile);
        } else if (comando.equals("decode")) {
            if (args.length < 2) {
                System.out.println("Uso: java Main decode <input.png>");
                return;
            }
            String inputFile = args[1];

            Decodificar(inputFile);
        } else {
            System.out.println("Comando inválido: " + comando);
        }
    }

    private static void Codificar(String mensaje, String inputFile, String outputFile) {
        try {
            BufferedImage imagen = EsteganografiaLSB.leerImagen(inputFile);
            Encoder.codificarMensaje(imagen, mensaje);
            EsteganografiaLSB.guardarImagen(imagen, outputFile);
            System.out.println("Mensaje codificado y guardado en: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error al leer/escribir la imagen: " + e.getMessage());
        }
    }

    private static void Decodificar(String inputFile) {
        try {
            BufferedImage imagen = EsteganografiaLSB.leerImagen(inputFile);
            String mensajeDecodificado = Decoder.decodificarMensaje(imagen);
            System.out.println("Mensaje oculto en la imagen: " + mensajeDecodificado);
        } catch (IOException e) {
            System.out.println("Error al leer la imagen: " + e.getMessage());
        }
    }
}

