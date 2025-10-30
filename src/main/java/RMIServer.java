import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // Cria instância do dicionário
            DicionarioImpl dic = new DicionarioImpl();

            // Exporta o objeto remoto em porta aleatória
            RemoteDic stub = (RemoteDic) UnicastRemoteObject.exportObject(dic, 0);

            // Cria (ou acessa) o registro RMI
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registra o objeto remoto no registro
            registry.rebind("DicionarioService", stub);

            System.out.println("Servidor de Dicionário RMI iniciado!");
        } catch (Exception e) {
            System.err.println("Erro no servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}