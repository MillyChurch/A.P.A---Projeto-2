
package projeto.pkg2.apda;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class ProjetoAPDA2 {

    public static int encontarMenorValor(ArrayList<Times> tabelaDeTimes, int indiceAtual, int fimDoVetor){
        
        if( indiceAtual == fimDoVetor ){
            return indiceAtual;
        }

        int result = encontarMenorValor(tabelaDeTimes, indiceAtual + 1, fimDoVetor);
        
        if( (tabelaDeTimes.get(indiceAtual).pontos) < (tabelaDeTimes.get(result).pontos)  ){
            result = indiceAtual;
        }   
        return result;
  
    }

    
    public static void selectionSort(ArrayList<Times> tabelaDeTimes, int start, int size){
    
        if(size == start){
            return;
        }
        
        int menorValor = encontarMenorValor(tabelaDeTimes, start, size-1);
        
        if(menorValor != start){
            Collections.swap(tabelaDeTimes, menorValor, start);
        }
        
        selectionSort(tabelaDeTimes, start+1, size);
    }
    
    public static void insertionSort(ArrayList<Times> tabelaDeTimes){
    
        
    }
    
    public static void carregarTimes(ArrayList<Times> tabelaDeTimes){

        
        try{
            
            File tabelaFonte = new File("timePontuacao.txt");
            Scanner scanFile = new Scanner(tabelaFonte);
            
            while(scanFile.hasNextLine()){
                    
                String obj = scanFile.nextLine();
                String[] arrayDeStrings = obj.split(";", 2);
                Times time = new Times(arrayDeStrings[0], Integer.parseInt(arrayDeStrings[1]));
                tabelaDeTimes.add(time);
             }
                   
               selectionSort(tabelaDeTimes, 0, tabelaDeTimes.size());
               
        }
        
        catch(FileNotFoundException e) {
            System.out.println("An error occurred. aaa");
        }
        
    }
    
    public static void procurarPorPontos(ArrayList<Times> tabelaDeTimes, int pontos, int inicioDaBusca){
        //Retornar o Nome e Posição do Time com base na quantidade de pontos
        
        if(tabelaDeTimes.size() <= inicioDaBusca){
            return;
        }
        if(tabelaDeTimes.get(inicioDaBusca).pontos < pontos){
            return;
        }
        if(tabelaDeTimes.get(inicioDaBusca).pontos == pontos){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " " + tabelaDeTimes.get(inicioDaBusca).pontos);
        }
        if(tabelaDeTimes.size() > inicioDaBusca){
            procurarPorPontos(tabelaDeTimes, pontos, inicioDaBusca+1);
        }
        
    }
    
    public static void cincoPrimeiros (ArrayList<Times> tabelaDeTimes, int inicioDaBusca){
        //Exibir os 5 primeiros colocados
        
        if(inicioDaBusca < 5){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " " + tabelaDeTimes.get(inicioDaBusca).pontos);
            cincoPrimeiros(tabelaDeTimes, inicioDaBusca+1);
        }

    }
    
    public static void cincoUltimos(ArrayList<Times> tabelaDeTimes, int inicioDaBusca){
        //Exibir os 5 últimos colocados
        
        if(inicioDaBusca < tabelaDeTimes.size()-5){
            cincoUltimos(tabelaDeTimes, inicioDaBusca+1);
        }
        else if(inicioDaBusca < tabelaDeTimes.size()){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " " + tabelaDeTimes.get(inicioDaBusca).pontos);
            cincoUltimos(tabelaDeTimes, inicioDaBusca+1);
        }

    }
    
    public static void diferenciacaoCincoPrimeiros(ArrayList<Times> tabelaDeTimes, int inicioDaBusca){
        //Exibir a diferença dos 5 primeiros colocados
        
        if(inicioDaBusca < 4){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " Está há uma diferença de " + (tabelaDeTimes.get(inicioDaBusca).pontos -  tabelaDeTimes.get(inicioDaBusca+1).pontos) + " ponto(s) de " + tabelaDeTimes.get(inicioDaBusca+1).nome);
            diferenciacaoCincoPrimeiros(tabelaDeTimes, inicioDaBusca+1);
        }
    }
    
    public static void diferencacincoUltimos(ArrayList<Times> tabelaDeTimes, int inicioDaBusca){
        //Exibir a diferença dos 5 últimos colocados
        
        if(inicioDaBusca < tabelaDeTimes.size()-5){
            diferencacincoUltimos(tabelaDeTimes, inicioDaBusca+1);
        }
        else if(inicioDaBusca < tabelaDeTimes.size()-1){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " Está há uma diferença de " + (tabelaDeTimes.get(inicioDaBusca).pontos -  tabelaDeTimes.get(inicioDaBusca+1).pontos) + " ponto(s) de " + tabelaDeTimes.get(inicioDaBusca+1).nome);
            diferencacincoUltimos(tabelaDeTimes, inicioDaBusca+1);
        }
    }
    
    public static void exibirMetade (ArrayList<Times> tabelaDeTimes, int inicioDaBusca){
        //Reordenar a tabela para exibir apenas metade dos times com base na quantidade de pontos
        
        if(inicioDaBusca < tabelaDeTimes.size()/2){
            System.out.println(tabelaDeTimes.get(inicioDaBusca).nome + " " + tabelaDeTimes.get(inicioDaBusca).pontos);
            exibirMetade(tabelaDeTimes, inicioDaBusca+1);
        }

    }
    
    public static void main(String[] args) {
        
        ArrayList<Times> times = new ArrayList();
        carregarTimes(times); 
        int pontosBuscados = 62;
        
        for(int i =0;i<times.size();i++){
            System.out.println(times.get(i).nome + " " + times.get(i).pontos);
        }
        
        procurarPorPontos(times, pontosBuscados, 0);System.out.println("\n");
        cincoPrimeiros(times, 0);System.out.println("\n");
        cincoUltimos(times, 0);System.out.println("\n");
        diferenciacaoCincoPrimeiros(times, 0);System.out.println("\n");
        diferencacincoUltimos(times, 0);System.out.println("\n");
        exibirMetade(times, 0);
    }
    
}
