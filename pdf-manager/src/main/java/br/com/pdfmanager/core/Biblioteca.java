package br.com.pdfmanager.core;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

// a ideia aq é pegar 

public class Biblioteca {
    private String diretorioRaiz;
    private ArrayList<String> subdiretorios;

    public Biblioteca(String diretorioRaiz) {
        this.diretorioRaiz = diretorioRaiz;
        File root = new File(this.diretorioRaiz).mkdirs();
        if (root.createNewFile()) {
            System.out.println("Diretório criado em " + this.diretorioRaiz);
        } else {
            System.out.println("Diretório já foi criado");
        }
    }

    private void adicionarDocumento() {

    }

    private void removerDocumento() {
        
    }

    // public void CriarDiretorioRaiz() {
    //     try {
    //         File root = new File(this.diretorioRaiz).mkdirs();
    //         if (root.createNewFile()) {
    //             System.out.println("Diretório criado em " + this.diretorioRaiz);
    //         } else {
    //             System.out.println("Diretório já foi criado");
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Erro ao tentar criar diretório");
    //     }
    // } 
}