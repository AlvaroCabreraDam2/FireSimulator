package simuladorfuego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas {

    // Atributes
    private BufferedImage img;
    private byte[] data;

    // Constructor
    public Viewer() {
        this.loadImage();
        this.data = ((DataBufferByte) this.img.getData().getDataBuffer()).getData();
    }

    // Methods
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(), img.getHeight());
    }

    public void paintImg(Graphics g) {
        if (this.img != null) {
            g.drawImage(this.img, 0, 2, this);
        }
    }

    public int getPixel(int row, int col) {
        int pixel = ((row * this.img.getWidth()) + col) * 3;
        if (pixel < this.data.length) {
            return pixel;
        }
        return 0;
    }

    public void setColorsToPixel(int pixel, int[] color) {
        this.data[pixel] = (byte) color[0];
        this.data[pixel + 1] = (byte) color[1];
        this.data[pixel + 2] = (byte) color[2];
    }

    public void loadImage() {
        try {
            this.img = ImageIO.read(new File("src/img/img.jpg"));
        } catch (IOException e) {
            System.out.println("No se puede encontrar la imagen");
        }
    }
    
    public int getImageHeight() {
        return this.img.getHeight();
    }

    public int getImageWidth() {
        return this.img.getWidth();
    }
    
    public void generateImg() {
        this.img.setData(Raster.createRaster(this.img.getSampleModel(), new DataBufferByte(this.data, this.data.length), new Point()));
    }

}
