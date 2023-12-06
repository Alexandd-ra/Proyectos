
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Matriz {
    /*
        i avanza abajo
        1 0 0 2 0 j avanza derecha
        0 0 0 0 0
        1 0 3 0 0
        0 0 0 0 0
        1 0 0 0 0
    */
    private int[][] matriz;
    public static int[][] matrizAI;
    private int pacx, pacy;
    
    public Matriz() {
        
        File archivo = new File("src/juegoPacman/nivel");
        try {
            
            FileReader lectorArch = new FileReader(archivo);
            BufferedReader lectorNiv = new BufferedReader(lectorArch);
            
            this.matriz = new int[10][10];
            matrizAI = new int[10][10];
            for (int i = 0; i < 10; i++) {
                String linea = lectorNiv.readLine();
                StringTokenizer strtk = new StringTokenizer(linea);
                for (int j = 0; j < 10; j++) {
                    int item = Integer.parseInt(strtk.nextToken());
                    if (item == 4) {
                        matrizAI[i][j] = item;
                        this.matriz[i][j] = 0;
                    }else {
                        matrizAI[i][j] = 0;
                        this.matriz[i][j] = item;
                    }
                }
            }
            String linea = lectorNiv.readLine();
            StringTokenizer strtk = new StringTokenizer(linea);
            pacx = Integer.parseInt(strtk.nextToken());
            pacy = Integer.parseInt(strtk.nextToken());
            
            lectorNiv.close();//se cierra el archivo
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo No Encontrado!");
            System.exit(1);
        } catch(IOException e){
            System.out.println("Error En Archivos Internos");
            System.exit(1);
        }
    }
    
    public int pacx() {
        return this.pacx;
    }
    
    public void pacx(int x) {
        this.pacx = x;
    }
    
    public int pacy() {
        return this.pacy;
    }
    
    public void pacy(int y) {
        this.pacy = y;
    }
    
    public void moverPacman() {
        this.matriz[pacy][pacx] = 3;
    }
    
    public int[][] getMatriz() {
        return this.matriz;
    }
}
