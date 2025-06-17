package br.com.pdfmanager;

import br.com.pdfmanager.core.Biblioteca;
import br.com.pdfmanager.model.Exercicio;

import java.util.ArrayList;

public class Main {    
    public static void main(String[] args) {
        String path = "pdf-manager/src/main/java/br/com/pdfmanager/library";    
        String nome = "testetesteteste123245";
        Biblioteca lib = new Biblioteca(nome, path);
    }
}