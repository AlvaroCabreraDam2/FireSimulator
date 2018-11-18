package simuladorfuego;

import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

public class MyWindow extends JFrame {

    // Atributes
    private Viewer viewer;
    private Container container = getContentPane();
    private Fire fire;
    private ColorPallette cp;

    public MyWindow() {
        super("Editor de imagenes.");

        //Crear canvas
        this.viewer = new Viewer();

        //Ventana con tamaño de la imagen
        setSize((this.viewer.getImageWidth()) + 20, this.viewer.getImageHeight() + 40);

        // Crear fuego
        this.fire = new Fire(this.viewer.getImageHeight(), this.viewer.getImageWidth());

        // Crear paleta
        this.cp = new ColorPallette();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridBagLayout());

        //Añadir imagenes a la ventana
        container.add(this.viewer);

        this.setVisible(true);

        // Mostrar animacion
        boolean e = false;
        while (e == false) {
            this.fire.doOneLoop();
            for (int row = 0; row < this.viewer.getImageHeight(); row++) {
                for (int col = 0; col < this.viewer.getImageWidth(); col++) {
                    this.viewer.setColorsToPixel(this.viewer.getPixel(row, col), this.cp.getColor(this.fire.getValue(row, col)));
                }
            }
            this.viewer.generateImg();
            this.viewer.paintImg(this.viewer.getGraphics());
            try {
                Thread.sleep(10);
            } catch (Exception exception) {
                
            }
        }

    }

}
