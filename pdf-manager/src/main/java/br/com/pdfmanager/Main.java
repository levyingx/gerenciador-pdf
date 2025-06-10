package br.com.pdfmanager;

import br.com.pdfmanager.core.Biblioteca;
import br.com.pdfmanager.core.Documento;

public class Main {    
    public static void main(String[] args) {
        String path = "pdf-manager/src/main/java/br/com/pdfmanager/library";    
        Biblioteca biblioteca = new Biblioteca(path);
    }
}