import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EsteganografiaLSB {

    public static BufferedImage leerImagen(String rutaArchivo) throws IOException {
        return ImageIO.read(new File(rutaArchivo));
    }

    public static void guardarImagen(BufferedImage imagen, String rutaArchivo) throws IOException {
        ImageIO.write(imagen, "png", new File(rutaArchivo));
    }

    public static byte[] codificarMensaje(String message) {
        return message.getBytes();
    }

    public static String decodificarMensaje(byte[] messageBytes) {
        return new String(messageBytes);
    }
}
