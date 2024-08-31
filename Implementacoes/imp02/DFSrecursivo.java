import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.List;
import java.io.IOException; 

public class DFSrecursivo{
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
        
        public int sucessoresSize(int vertice){
            return grafo.get(vertice).size();
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

    public static class Aresta {
        private int origem;
        private int destino;
    
        public Aresta(int a, int b) {
            this.origem = a;
            this.destino = b;
        }
    
        public int getOrigem() {
            return origem;
        }
    
        public int getDestino() {
            return destino;
        }
    
        @Override
        public String toString() {
            return "[" + origem + ", " + destino + "]";
        }
    }
    

    public static class DFS{
        private int t;
        private int td[];
        private int tt[];
        private int visitados;
        
        List<Aresta> arvore;
        List<Aresta> retorno;
        List<Aresta> avanco;
        List<Aresta> cruzamento;

        public int getT(){
            return t;
        }

        public int getTdAt(int v){
            return td[v];
        } 

        public int getTtAt(int v){
            return tt[v];
        } 
        public boolean visto(int v){
            return td[v] != 0;
        }
        public int cont(){
            return visitados;
        }

        public DFS(Grafo g, int escolhido){
            t = 0;
            td = new int[g.size() + 1];
            tt = new int[g.size() + 1];
            visitados = 0;
            arvore = new ArrayList<>();
            retorno = new ArrayList<>();
            avanco = new ArrayList<>();
            cruzamento = new ArrayList<>();
            search(g, escolhido);

            
        }

        public void search(Grafo g, int escolhido){
            // Enquanto algum TD = 0
            for(int v = 1; v <= 100; v++){ // Posicao 0 não conta
                if(!visto(v)){
                    busca_profundidade(g, 1, escolhido);
                }
                
            }
        }

        private void busca_profundidade(Grafo g, int v, int escolhido){
            List<Integer> suc = g.getSucessores(v);
            suc.sort(Integer::compareTo); // Ordem lexicográfica 
            visitados++;
            t++;
            td[v] = t;
            for(int w : suc){
                Aresta a = new Aresta(v, w);
                if(td[w] == 0){
                    
                    arvore.add(a);
                    
                    busca_profundidade(g, w, escolhido); // Aresta de árvore
                }
                // Adicionar retorno, avanco e cruzamento
                else if(escolhido == v){
                    if(tt[w] == 0){
                        retorno.add(a); // W ainda não terminou
                    }
                    else if(td[v] < td[w]){
                        avanco.add(a); // W foi descoberta depois de V
                    }
                    else{
                        cruzamento.add(a); // W foi descoberta antes de V
                    }
                }
            }
            t++;
            tt[v] = t;
        }

    }
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do arquivo: ");
        String arq = sc.next(); 
        System.out.println("Vértice desejado: ");
        int vertice = sc.nextInt(); // Entradas do usuário

        Grafo grafo = criarGrafo(arq);
        
        DFS dfs = new DFS(grafo, vertice);

        System.out.println("Arestas de árvore: " + dfs.arvore.toString());

        System.out.println("Arestas de retorno: " + dfs.retorno.toString());
        if(dfs.retorno != null){
            for(Aresta a : dfs.retorno){
                System.out.println("Origem " + a.getOrigem() + " TD e TT: " +  dfs.getTdAt(a.getOrigem()) + " " +  dfs.getTtAt(a.getOrigem()));
                System.out.println("Destino " + a.getDestino() + " TD e TT: "+  dfs.getTdAt(a.getDestino()) + " " +  dfs.getTtAt(a.getDestino()));
            }
            
        }
        System.out.println("Arestas de avanco: " + dfs.avanco.toString());
        if(dfs.avanco != null){
            for(Aresta a : dfs.avanco){
                System.out.println("Origem " + a.getOrigem() + " TD e TT: " +  dfs.getTdAt(a.getOrigem()) + " " +  dfs.getTtAt(a.getOrigem()));
                System.out.println("Destino " + a.getDestino() + " TD e TT: "+  dfs.getTdAt(a.getDestino()) + " " +  dfs.getTtAt(a.getDestino()));
            }
            
        }

        System.out.println("Arestas de cruzamento: " + dfs.cruzamento.toString());
        if(dfs.cruzamento != null){
            for(Aresta a : dfs.cruzamento){
                System.out.println("Origem " + a.getOrigem() + " TD e TT: " +  dfs.getTdAt(a.getOrigem()) + " " +  dfs.getTtAt(a.getOrigem()));
                System.out.println("Destino " + a.getDestino() + " TD e TT: "+  dfs.getTdAt(a.getDestino()) + " " +  dfs.getTtAt(a.getDestino()));
            }
        }

        sc.close();
    }
    
}