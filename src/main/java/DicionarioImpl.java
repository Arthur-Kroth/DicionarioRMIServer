import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class DicionarioImpl implements RemoteDic {

    private Map<String, String> palavras = new HashMap<>();

    public DicionarioImpl() throws RemoteException {
        carregarArquivo("C:\\Users\\Arthur\\OneDrive\\Documentos\\NetBeansProjects\\DicionarioRMIServer\\src\\main\\java\\palavras.txt");
    }

    // Lê o arquivo e carrega as palavras no mapa
    private void carregarArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    palavras.put(partes[0].trim().toLowerCase(), partes[1].trim());
                }
            }
            System.out.println("Arquivo de dicionário carregado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    @Override
    public String traduzir(String palavraPortugues) throws RemoteException {
        String traducao = palavras.get(palavraPortugues.toLowerCase());
        if (traducao == null) {
            return "Tradução não encontrada!";
        }
        return traducao;
    }
}