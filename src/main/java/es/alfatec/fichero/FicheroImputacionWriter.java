package es.alfatec.fichero;

import es.alfatec.tiempo.Tiempo;
import java.io.FileWriter;
import java.io.IOException;
/**
 * implementacion de writer con saltos de lineas y fecha
 * @author ricardo
 */
public class FicheroImputacionWriter extends FileWriter {

    public FicheroImputacionWriter(String fileName) throws IOException {
        super(fileName);
    }

    public FicheroImputacionWriter(String fileName, boolean append) throws IOException {
        super(fileName, append);
    }

    @Override
    public void write(String text) throws IOException {
        super.write(Tiempo.nowToString() + " " + text + "\r\n");
    }
}
