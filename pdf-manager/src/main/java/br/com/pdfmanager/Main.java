package br.com.pdfmanager;

import br.com.pdfmanager.core.Biblioteca;
import br.com.pdfmanager.core.Exercicio;

import java.util.ArrayList;

public class Main {    
    public static void main(String[] args) {
        String path = "pdf-manager/src/main/java/br/com/pdfmanager/library";    
        Biblioteca lib = new Biblioteca(path);
        
        ArrayList<String> test = new ArrayList<>();
        test.add("junior");
        
        // Exercicio exercicio = new Exercicio(test, "trabalho unidade 1", "lp2", path);
        // lib.adicionarDocumento(exercicio);
        lib.listarDocumentos();
    }
}