package simuladorfuego;

class Fire {

    // Atributes
    private int[][] values;
    private int maxRow;
    private int maxCol;

    // Constructor
    public Fire(int rows, int cols) {

        this.values = new int[rows][cols];
        this.maxRow = rows - 1;
        this.maxCol = cols - 1;

    }

    // Public Methoods
    // Private Methods
    private void generateEmbers() {
        // Memoria del fuego
        for (int col = this.values[maxRow].length / 4; col < (this.values[maxRow].length / 4) * 3; col++) {
            if (this.values[maxRow][col] == 255 && Math.random() > 0.50) {
                this.values[maxRow][col] = 255;
            } else {
                this.values[maxRow][col] = 0;
            }
        }
        // Generar nuevas por todo el canvas
        for (int col = 0; col < this.values[maxRow].length; col++) {
            
            // Descomenta esto para fuego mas realista
            if (col < this.values[maxRow].length / 8 || col > (this.values[maxRow].length / 8) * 7) {
                if (Math.random() > 0.70) {
                    this.values[maxRow][col] = 255;
                } else {
                    this.values[maxRow][col] = 0;
                }
            }
            if ((col < this.values[maxRow].length / 4) && !(col < this.values[maxRow].length / 8) || (col > (this.values[maxRow].length / 4) * 3) && !(col > (this.values[maxRow].length / 8) * 7)) {
                if (Math.random() > 0.55) {
                    this.values[maxRow][col] = 255;
                } else {
                    this.values[maxRow][col] = 0;
                }
            }
            if ((col > this.values[maxRow].length / 4) && col < (this.values[maxRow].length / 4) * 3) {
                if (Math.random() > 0.40) {
                    this.values[maxRow][col] = 255;
                } else {
                    this.values[maxRow][col] = 0;
                }
            }
            //*/
            
            /* Descomenta esto para fuego standard
            if (Math.random() < 0.60) {
                this.values[maxRow][col] = 255;
            } else {
                if (col > this.values[maxRow].length / 4 && col < (this.values[maxRow].length / 4) * 3 && this.values[maxRow][col] == 255) {
                    this.values[maxRow][col] = 255;
                } else {
                    this.values[maxRow][col] = 0;
                }
            }
            */
            
        }
    }

    private void calculateValues() {
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < this.values[maxRow].length; col++) {
                int px1;
                int px2;
                int px3;
                int px4;
                int px5;
                try {
                    px1 = this.values[row + 1][col - 1];
                } catch (Exception e) {
                    px1 = 0;
                }
                try {
                    px2 = this.values[row + 1][col];
                } catch (Exception e) {
                    px2 = 0;
                }
                try {
                    px3 = this.values[row + 1][col + 1];
                } catch (Exception e) {
                    px3 = 0;
                }
                try {
                    px4 = this.values[row + 2][col];
                } catch (Exception e) {
                    px4 = 0;
                }
                try {
                    px5 = this.values[row + 3][col];
                } catch (Exception e) {
                    px5 = 0;
                }
                this.values[row][col] = ((int) (((double) (px1 + px2 + px3 + px4 + px5)) / 5.1));
            }
        }
    }

    private void printValues() {
        for (int row = 0; row < this.values.length; row++) {
            for (int col = 0; col < this.values[maxRow].length; col++) {
                if (this.values[row][col] < 10) {
                    System.out.print(" [ " + this.values[row][col] + " ] ");
                }
                if (this.values[row][col] < 100 && this.values[row][col] > 10) {
                    System.out.print(" [ " + this.values[row][col] + "] ");
                }
                if (this.values[row][col] > 100) {
                    System.out.print(" [" + this.values[row][col] + "] ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void doOneLoop() {
        this.generateEmbers();
        this.calculateValues();
    }

    public int getValue(int row, int col) {
        if (row > this.values.length - 1) {
            row = this.values.length - 1;
        }
        if (col > this.values[row].length - 1) {
            col = this.values[row].length - 1;
        }
        return this.values[row][col];
    }

}
