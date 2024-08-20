import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException; 

public class Imp01 {
    
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do arquivo: ");
        String file = sc.next(); 
        System.out.println("Vértice desejado: ");
        int vertice = sc.nextInt(); // Entradas do usuário

        FileReader fr = new FileReader(file); 
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();
        linha.trim();

        String numeros[] = linha.split("\\s+");
        int n = Integer.parseInt(numeros[0]); // Número de vértices
        Integer.parseInt(numeros[1]); // Pular o número de arestas

        ArrayList<ArrayList<Integer>> listaSucessores = new ArrayList<>(n);
        ArrayList<ArrayList<Integer>> listaPredecessores = new ArrayList<>(n);
        ArrayList<Integer> antecessores = new ArrayList<>();
        ArrayList<Integer> sucessores = new ArrayList<>();

        // Inicializando as listas internas
        for (int i = 0; i <= n; i++) {
            listaSucessores.add(new ArrayList<>());
            listaPredecessores.add(new ArrayList<>());
        }

        // Adicionando arestas
        while((linha = br.readLine()) != null){
            linha = linha.trim();
                if (!linha.isEmpty()) {
                    String[] teste = linha.split("\\s+");
                    int entrada = Integer.parseInt(teste[0]); // Número de vértices
                    int saida = Integer.parseInt(teste[1]); // Número de arestas
                    listaSucessores.get(entrada).add(saida);
                    listaPredecessores.get(saida).add(entrada);
                }            
        }

        // Lista de antecessores
        antecessores = listaPredecessores.get(vertice);
        
        // Lista de sucessores
        sucessores = listaSucessores.get(vertice);

        System.out.println("(I) Grau de saída: " + sucessores.size());
        System.out.println("(II) Grau de entrada: " + antecessores.size());
        System.out.println("(III) Conjunto de sucessores: " + sucessores.toString());
        System.out.println("(IV) Conjunto de antecessores: " + antecessores.toString());

        sc.close();
        br.close();
    }
    
}