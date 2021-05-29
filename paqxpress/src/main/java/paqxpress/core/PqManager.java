package paqxpress.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Es la clase encargada de la gestión de los clientes y envíos
 *
 */
public class PqManager {
    private List<Cliente> clientes;
    private Map<Integer, Paquete> paquetes;

    public void altaCliente(String idCli, String nombre) {
        Cliente cliente = new Cliente(idCli, nombre);
    }

    public List<Cliente> listaClientes() {
        return clientes;
    }

    public void altaEnvio(Cliente cliente, String destinatario, String destino) {
        Paquete nuevoEnvio = new Paquete(cliente, destinatario, destino);
        paquetes.put(nuevoEnvio.getIdPack(), nuevoEnvio);
    }

    public String estadoPedido(int idPacK) {
        return paquetes.get(idPacK).informaEstado();
    }

    public List<Paquete> listaReparto() {
        List<Paquete> listaReparto = new ArrayList<Paquete>(){};
        for (Paquete  paquete: paquetes.values()) {
            if(paquete.informaEstado() == "en reparto");
            listaReparto.add(paquete);
        }
        return listaReparto;
    }

    public List<Paquete> listaEnviosCliente(String idCli) {
        List<Paquete> listaEnviosCliente = new ArrayList<Paquete>(){};
        for (Paquete  envioCli: paquetes.values()) {
            if(envioCli.getCliente().getIdCli() == idCli);
            listaEnviosCliente.add(envioCli);
        }
        return listaEnviosCliente;
    }
}
