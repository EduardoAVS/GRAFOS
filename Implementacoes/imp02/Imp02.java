import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.List;
import java.io.IOException; 

public class Imp02{
    static class Grafo{
       List<List<Integer>> grafo;

        public Grafo(List<List<Integer>> g){
            grafo = g;
        }

        public List<Integer> getSucessores(int vertice){
            return grafo.get(vertice);           
        }
        public int size(){
            return grafo.size(); // Posicao 0 + vértices do grafo
        }
    }
    public static Grafo criarGrafo(String arq) throws IOException{

        FileReader fr = new FileReader(arq); 
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();
        linha.trim();

        String numeros[] = linha.split("\\s+");
        int n = Integer.parseInt(numeros[0]); // Número de vértices
        Integer.parseInt(numeros[1]); // Pular o número de arestas

        List<List<Integer>> listaAdjacencia = new ArrayList<>(n + 1);

        // Inicializando as listas internas
        for (int i = 0; i <= n; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }

        // Adicionando arestas
        while((linha = br.readLine()) != null){
            linha = linha.trim();
                if (!linha.isEmpty()) {
                    String[] teste = linha.split("\\s+");
                    int entrada = Integer.parseInt(teste[0]); // Número de vértices
                    int saida = Integer.parseInt(teste[1]); // Número de arestas
                    listaAdjacencia.get(entrada).add(saida);
                }            
        }

        Grafo grafo = new Grafo(listaAdjacencia);

        br.close();

        return grafo;
    }

    public static class DFS{
      int t;
      int td[];
      int tt[];
      int pai[];
      
      public DFS(Grafo g){
        t = 0;
        td = new int[g.size()];
        tt = new int[g.size()];
        pai = new int[g.size()]; // Vetores inicializados com 0 por padrão
      }

      public void dfs(){
        // Enquanto algum TD = 0
      }
    }
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do arquivo: ");
        String arq = sc.next(); 
        System.out.println("Vértice desejado: ");
        int vertice = sc.nextInt(); // Entradas do usuário

        Grafo grafo = criarGrafo(arq);

        DFS dfs = new DFS(grafo);
        dfs.search();

        System.out.println(grafo.getSucessores(1).toString());

        sc.close();
    }
    
}