package imp03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FluxoMax{
    static class Grafo{
        private int[][] grafo;
        private int size; // Número de vértices + '0'

        public Grafo(String arq) throws IOException{
            FileReader fr = new FileReader(arq); 
            BufferedReader br = new BufferedReader(fr);

            String linha = br.readLine();
            linha.trim();

            String numeros[] = linha.split("\\s+");
            int n = Integer.parseInt(numeros[0]); // Número de vértices
            Integer.parseInt(numeros[1]); // Pular o número de arestas

            int[][] adjMatrix = new int[n + 1][n + 1];
            this.size = n + 1; // Conta o valor do 0 no size

            // Adicionando arestas
            while((linha = br.readLine()) != null){
                linha = linha.trim();
                    if (!linha.isEmpty()) {
                        String[] teste = linha.split("\\s+");
                        
                        int entrada = Integer.parseInt(teste[0]); 
                        int saida = Integer.parseInt(teste[1]); 
                        int capacidade = Integer.parseInt(teste[2]);
                        adjMatrix[entrada][saida] = capacidade;
                       
                    }            
            }

            this.grafo = adjMatrix;
            br.close();

        }

        public boolean bfs(int s, int t, int pai[]){
            boolean visitados[] = new boolean[size];
            Queue<Integer> fila = new LinkedList<>();
            fila.add(s);
            visitados[s] = true;

            while(!fila.isEmpty()){
                int u = fila.poll();
 
                for (int j = 0; j < size; j++) { // Lista de adjacência de u
                    int capacidade = grafo[u][j]; 
                    if(!visitados[j] && capacidade > 0){

                        fila.add(j);
                        visitados[j] = true;
                        pai[j] = u;
                    }
                }
            }
            return visitados[t];
        } // imp03/teste.txt

        public int edmondsKarp(int s, int t) {
            int[] pai = new int[size];
            int maxFlow = 0;
    
            while (bfs(s, t, pai)) { // Emquanto é possível chegar em t
                int pathFlow = Integer.MAX_VALUE;
                for (int v = t; v != s; v = pai[v]) {
                    int u = pai[v];
                    pathFlow = Math.min(pathFlow, grafo[u][v]);
                }
    
                for (int v = t; v != s; v = pai[v]) {
                    int u = pai[v];
                    grafo[u][v] -= pathFlow;
                    grafo[v][u] += pathFlow;
                }
    
                maxFlow += pathFlow;
    
                // Impressão do caminho encontrado e do fluxo
                LinkedList<Integer> path = new LinkedList<>();
                for (int v = t; v != s; v = pai[v]) {
                    path.add(v);
                }
                path.add(s);
                
                System.out.print("Path: [");

                for(int i = path.size() - 1; i >= 0; i--){
                    System.out.print(path.get(i) + " ");
                }
                System.out.println("]" + ", Flow: " + pathFlow);
            }
    
            return maxFlow;
        }

    }
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        
        String arq = sc.nextLine();
        Grafo grafo = new Grafo(arq);
        
        System.out.println("Digite a origem e o destino: ");
        int s = sc.nextInt();
        int t = sc.nextInt();

        int maxFlow = grafo.edmondsKarp(s, t);

        System.out.println("Fluxo Máximo: " + maxFlow);
        sc.close();
    }
}