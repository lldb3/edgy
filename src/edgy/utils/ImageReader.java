package edgy.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class ImageReader {
    private static final int HEADER_SIZE = 16;

    private final RandomAccessFile buffer;
    private final int length;
    private final int width;
    private final int height;

    public ImageReader(File file) throws IOException {
        buffer = new RandomAccessFile(file, "r");

        ByteBuffer header = ByteBuffer.allocateDirect(HEADER_SIZE);

        buffer.getChannel().read(header);
        header.rewind();

        length = header.getInt();
        width = header.getInt();
        height = header.getInt();
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[][] getFrameBytes(int frame) throws IOException {
        buffer.seek(HEADER_SIZE + frame * width * height);
        byte[][] resultBytes = new byte[height][width];
        for (int y = 0; y < height; y++) {
            buffer.read(resultBytes[y]);
        }
        return resultBytes;
    }

    public boolean[][] getFrameBits(int frame, byte threshold) throws IOException {
        buffer.seek(HEADER_SIZE + frame * width * height);
        boolean[][] reslutBits = new boolean[height][width];
        byte B;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                B = buffer.readByte();
                reslutBits[i][j] = B > threshold;
            }
        }
        return reslutBits;

    }
}
