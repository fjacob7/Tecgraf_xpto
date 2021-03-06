package xpto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class Calculo {
    
    public static int calcular(int numero){
        int resultado, primeiro, segundo, terceiro, quarto;
        primeiro = (numero / 1000) * 5;
        segundo = ((numero % 1000) /100) * 4;
        terceiro = ((numero % 100) /10) * 3;
        quarto = (numero % 10) * 2;
        resultado = (primeiro + segundo + terceiro + quarto) % 16;   
        return resultado;   
    }       
}

public class XPTO {

    public static void main(String[] args) {
        int n = 10, i = 0;  
        int numero, decimal; 
        String hexadecimal;
        String concatena[] = new String[n];
        String palavra[] = new String[n];
        
        // Lê o arquivo matriculasSemDV.txt
        try {    
            FileReader ler = new FileReader("matriculasSemDV.txt");
            BufferedReader lerArquivo = new BufferedReader(ler);
            String linha = lerArquivo.readLine().substring(0, 4);
            
            while (linha.substring(0, 4) != null) {
                numero = Integer.parseInt(linha.substring(0, 4));
                decimal = Calculo.calcular(numero);
                hexadecimal = Integer.toHexString(decimal).toUpperCase();
                concatena[i] = linha.substring(0, 4).concat("-"+hexadecimal);
                System.out.printf("%s\n", concatena[i]);
                linha = lerArquivo.readLine().substring(0, 4);
                i++;    
            }
            ler.close();
                    
        }catch(Exception e){
            System.out.println();
        }
        
        //Escreve o arquivo matriculasComDV.txt 
        try{
            File arquivoVerificador = new File("matriculasComDV.txt");
            arquivoVerificador.createNewFile();
            FileWriter escrever = new FileWriter(arquivoVerificador);
            BufferedWriter escreverArquivo = new BufferedWriter(escrever);
            
            for(int j = 0; j < 10; j++){
                escrever.write(concatena[j]+"\r\n");   
            }
            escrever.close();
            escreverArquivo.close();
        
        }catch(Exception e){
            System.out.println();
        }
        
        // Lê o arquivo matriculasParaVerificar.txt
        try {
            i = 0;
            FileReader ler = new FileReader("matriculasParaVerificar.txt");
            BufferedReader lerArquivo = new BufferedReader(ler);
            String linha = lerArquivo.readLine().substring(0, 4);
                     
            while (linha.substring(0, 4) != null) {
                numero = Integer.parseInt(linha.substring(0, 4));
                decimal = Calculo.calcular(numero);
                hexadecimal = Integer.toHexString(decimal).toUpperCase();
                concatena[i] = linha.substring(0, 4).concat("-"+hexadecimal);
                System.out.printf("%s\n", concatena[i]);
                linha = lerArquivo.readLine().substring(0, 4);
                i++;                
            }
            ler.close();
                    
        }catch(Exception e){
            System.out.println();       
        }
        
        //Lê o arquivo matriculasParaVerificar.txt
        try {
            i = 0;
            FileReader codigo = new FileReader("matriculasParaVerificar.txt");
            BufferedReader lerCodigo = new BufferedReader(codigo);
            String caracter = lerCodigo.readLine();
            
            while(caracter != null){
                palavra[i] = caracter;
                System.out.printf("%s\n", palavra[i]);
                caracter = lerCodigo.readLine();
                i++;
            }
            codigo.close();
        
        }catch(Exception e){
            System.out.println();
        }
        
        //Escreve o arquivo matriculasVerificadas.txt
        try{
            File arquivoValidador = new File("matriculasVerificadas.txt");
            arquivoValidador.createNewFile();
            FileWriter validar = new FileWriter(arquivoValidador);
            BufferedWriter validarArquivo = new BufferedWriter(validar);
            
            for(int j = 0; j < 10; j++){
                if(palavra[j].equals(concatena[j])){
                    validar.write(palavra[j]+" verdadeiro\r\n");
                }else{
                    validar.write(palavra[j]+" falso\r\n");
                }
            }
            validar.close();
            validarArquivo.close();
            
        }catch(Exception e){
            System.out.println();    
        }   
    }
}