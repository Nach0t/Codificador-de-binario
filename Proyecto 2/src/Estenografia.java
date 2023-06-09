import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Esta clase proporciona métodos para leer y guardar imágenes utilizando la biblioteca ImageIO.
 */
public class Estenografia {
    /**
     * Lee una imagen desde el archivo especificado.
     *
     * @param filePath La ruta del archivo de imagen.
     * @return La imagen leída.
     * @throws IOException Si ocurre un error al leer la imagen.
     */
    public static BufferedImage leerImagen(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }
    /**
     * Guarda una imagen en el archivo especificado en formato PNG.
     *
     * @param imagen   La imagen a guardar.
     * @param filePath La ruta del archivo de salida.
     * @throws IOException En caso de que ocurra un error al guardar la imagen.
     */
    public static void guardarImagen(BufferedImage imagen, String filePath) throws IOException {
        ImageIO.write(imagen, "png", new File(filePath));
    }
}
