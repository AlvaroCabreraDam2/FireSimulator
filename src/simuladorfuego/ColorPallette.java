package simuladorfuego;

public class ColorPallette {

    // Atributes
    private int[][] colors;

    public ColorPallette() {

        this.colors = new int[256][3]; // col 0 = Blue; col 1 = Green; col 2 = Red;
        this.generatePallette();

    }

    // Private Methods
    private void addBaseColors() {
        // Color negro en posicion 0
        this.colors[0][0] = 0;
        this.colors[0][1] = 0;
        this.colors[0][2] = 0;

        // Color granate en posicion 32
        this.colors[32][0] = 30;
        this.colors[32][1] = 45;
        this.colors[32][2] = 100;

        // Color rojo en posicion 64
        this.colors[64][0] = 40;
        this.colors[64][1] = 80;
        this.colors[64][2] = 255;

        // Color naranja en posicion 128
        this.colors[128][0] = 40;
        this.colors[128][1] = 165;
        this.colors[128][2] = 255;

        // Color amarillo en posicion 160
        this.colors[160][0] = 40;
        this.colors[160][1] = 255;
        this.colors[160][2] = 255;

        // Color blanco en posicion 255
        this.colors[255][0] = 255;
        this.colors[255][1] = 255;
        this.colors[255][2] = 255;
    }

    private void meltColors(int ini, int fin) {
        
        double bIni, gIni, rIni;
        double bFin, gFin, rFin;
        double totPasos, actPasos;
        double bDiff, gDiff, rDiff;

        totPasos = (double) fin - (double) (ini + 2);
        
        bIni = (double) this.colors[ini][0];
        gIni = (double) this.colors[ini][1];
        rIni = (double) this.colors[ini][2];
        bFin = (double) this.colors[fin][0];
        gFin = (double) this.colors[fin][1];
        rFin = (double) this.colors[fin][2];

        // Calculate diff blue
        bDiff = (bFin - bIni) / totPasos;
        // Calculate diff green
        gDiff = (gFin - gIni) / totPasos;

        // Calculate diff red
        rDiff = (rFin - rIni) / totPasos;

        for (int pos = ini + 1; pos < fin; pos++) {
            actPasos = (double) (pos - ini);
            this.colors[pos][0] = (int) (bIni + bDiff * actPasos);
            this.colors[pos][1] = (int) (gIni + gDiff * actPasos);
            this.colors[pos][2] = (int) (rIni + rDiff * actPasos);
            
            if (this.colors[pos][0] > 255) {
                this.colors[pos][0] = 255;
            }
            
            if (this.colors[pos][1] > 255) {
                this.colors[pos][1] = 255;
            }
            
            if (this.colors[pos][2] > 255) {
                this.colors[pos][2] = 255;
            }
            
        }
    }

    private void generatePallette() {

        this.addBaseColors();
        this.meltColors(0, 32);
        this.meltColors(32, 64);
        this.meltColors(64, 128);
        this.meltColors(128, 160);
        this.meltColors(160, 255);

    }

    private void printValues() {
        for (int row = 0; row < this.colors.length; row++) {
            for (int col = 0; col < 3; col++) {
                
                if (col == 0) {
                    System.out.print("B ");
                }
                
                if (col == 1) {
                    System.out.print("G ");
                }
                
                if (col == 2) {
                    System.out.print("R ");
                }
                
                if (this.colors[row][col] < 10) {
                    System.out.print(" [ " + this.colors[row][col] + " ] ");
                }
                if (this.colors[row][col] < 100 && this.colors[row][col] >= 10) {
                    System.out.print(" [ " + this.colors[row][col] + "] ");
                }
                if (this.colors[row][col] >= 100) {
                    System.out.print(" [" + this.colors[row][col] + "] ");
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int[] getColor(int pos) {
        if (pos > this.colors.length - 1) {
            pos = this.colors.length - 1;
        }
        int[] color = new int[3];
        for (int inc = 0; inc < 3; inc ++) {
            color[inc] = this.colors[pos][inc];
        }
        return color;
    }

}
