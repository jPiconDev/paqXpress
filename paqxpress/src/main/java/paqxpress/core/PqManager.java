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

    // Descomentar para NO testing:
    // private List<Cliente> clientes = new ArrayList<>(){};
    // private Map<Integer, Paquete> paquetes = new TreeMap<>(){};

    //Apartado para testing
    Cliente a1 = new Cliente("A11222333", "Comercial Pepe");
    Cliente a2 = new Cliente("B22333444", "Distribuciones Lolo");
    Cliente a3 = new Cliente("C33444555", "Talleres Louzan");
    private List<Cliente> clientes = new ArrayList<>(){{ add(a1); add(a2); add(a3); }};
    private Map<Integer, Paquete> paquetes = new TreeMap<>(){{
        put(1001, new Paquete(a1, "Talleres Pedro", "Avda. Camiño Francés, s/n"));
        put(1001, new Paquete(a2, "El Descanso", "Avda. De Lugo, s/n"));
        put(1001, new Paquete(a2, "Comercial Lata", "Polígono del Tambre, s/n"));
    }};
    //Apartado para testing


    public void altaCliente(String idCli, String nombre) {
        clientes.add(new Cliente(idCli, nombre));
    }

    public List<Cliente> listaClientes() {
        return clientes;
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
