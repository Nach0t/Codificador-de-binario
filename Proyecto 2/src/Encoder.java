import java.awt.image.BufferedImage;
import java.io.IOException;

public class Encoder {

    public void codificarMensaje(String mensaje, String archivoEntrada, String archivoSalida) throws IOException {
        BufferedImage imagen = Estenografia.leerImagen(archivoEntrada);
        byte[] bytesMensaje = codificarMensajeEnBytes(mensaje);

        int tamañoMensaje = bytesMensaje.length;
        int[] bitsTamaño = enteroABits(tamañoMensaje);

        int índice = 0;
        for (int i = 0; i < bitsTamaño.length; i++) {
            int bit = bitsTamaño[i];
            int x = índice % imagen.getWidth();
            int y = índice / imagen.getWidth();
            int rgb = imagen.getRGB(x, y);
            int nuevoRgb = ocultarBit(rgb, bit);
            imagen.setRGB(x, y, nuevoRgb);
            índice++;
        }

        for (byte b : bytesMensaje) {
            int[] bitsByte = byteABits(b);
            for (int i = 0; i < bitsByte.length; i++) {
                int bit = bitsByte[i];
                int x = índice % imagen.getWidth();
                int y = índice / imagen.getWidth();
                int rgb = imagen.getRGB(x, y);
                int nuevoRgb = ocultarBit(rgb, bit);
                imagen.setRGB(x, y, nuevoRgb);
                índice++;
            }
        }

        Estenografia.guardarImagen(imagen, archivoSalida);
    }

    private int ocultarBit(int rgb, int bit) {
        int máscara = 0xFE;
        int nuevoRgb = (rgb & máscara) | bit;
        return nuevoRgb;
    }

    private int[] enteroABits(int valor) {
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = (valor >> (31 - i)) & 1;
        }
        return bits;
    }

    private int[] byteABits(byte valor) {
        int[] bits = new int[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = (valor >> (7 - i)) & 1;
        }
        return bits;
    }

    private byte[] codificarMensajeEnBytes(String mensaje) {
        return mensaje.getBytes();
    }
}
