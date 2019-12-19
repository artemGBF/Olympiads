package utils;

import model.Pupil;

import java.io.*;
import java.util.ArrayList;

public class DataWriter {
    private String filename;
    private ArrayList<Pupil> lst;
    private BufferedWriter br;


    public DataWriter(String filename, ArrayList<Pupil> lst) throws IOException {
        this.lst = lst;
        this.setFilename(filename);
    }

    private void setFilename(String filename) throws IOException {
        this.filename = filename;
        try {
            this.br = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            if(br!=null)
                br.close();
            throw new IOException(e.getMessage());
        }
    }

    public  void printData() throws IOException {
        try {
            for (Pupil aLst : lst) {
                br.write(aLst.toString());
                br.write('\n');
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (br != null)
                br.close();
        }
    }
}
