package paqxpress.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Es la clase encargada de la gestión de los clientes y envíos
 *
 */
public class PqManager {
    
    private List<Cliente> clientes = new ArrayList<>(){};
    private Map<Integer, Paquete> paquetes = new TreeMap<>(){};
    private static int idNumber = 1000;

    public static int getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber() {
        idNumber++;
    }


    public void altaCliente(String idCli, String nombre) {
        clientes.add(new Cliente(idCli, nombre));
    }

    public List<Cliente> listaClientes() {
        return clientes;
    }

    public Map<String, String> mapClientes() {
        Map<String, String> map = new TreeMap<>();
        for (Cliente cliente : clientes) {
            map.put(cliente.getIdCli(), cliente.getNombre());
        }
        return map;
    } 

    public int altaEnvio(Cliente cliente, String destinatario, String destino) {
        Paquete nuevoEnvio = new Paquete(cliente, destinatario, destino);
        paquetes.put(nuevoEnvio.getIdPack(), nuevoEnvio);
        return nuevoEnvio.getIdPack();
    }

    public String estadoPedido(int idPacK) {
        return paquetes.get(idPacK).informaEstado();
    }

    public List<Paquete> listaReparto() {
        List<Paquete> listaReparto = new ArrayList<Paquete>(){};
        for (Paquete  paquete: paquetes.values()) {
            if(paquete.getEstado() instanceof EnReparto);
            listaReparto.add(paquete);
            if(paquete.getEstado() instanceof Entregado);
            listaReparto.remove(paquete);
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

    public Map<Integer, Paquete> getPaquetes() {
        return this.paquetes;
    }
}
