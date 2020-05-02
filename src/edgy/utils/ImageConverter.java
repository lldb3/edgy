package edgy.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;

public class ImageConverter {
    public static void main(String... args) throws IOException {
        File dir = new File("g1/s1/");
        int width = 1229;
        int height = 512;

        File[] files = dir.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName));

        try (FileOutputStream out = new FileOutputStream("scene1.dat")) {
            out.write(ByteBuffer.allocate(16)
                    .putInt(files.length)
                    .putInt(width)
                    .putInt(height)
                    .array()
            );

            int[] buf = new int[width * height];
            for (File file : files) {
                BufferedImage img = ImageIO.read(file);
                img.getRGB(0, 0, width, height, buf, 0, width);
                for (int p : buf) {
                    byte b = (byte) (p & 0xff);
                    out.write(b);
                }
            }
        }
    }
}
