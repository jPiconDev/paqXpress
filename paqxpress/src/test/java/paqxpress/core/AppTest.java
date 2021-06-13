package paqxpress.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        Cliente a1 = new Cliente("A11222333", "Comercial Pepe");
        Cliente a2 = new Cliente("B22333444", "Distribuciones Lolo");
        Cliente a3 = new Cliente("C33444555", "Talleres Louzan");
        // List<Cliente> clientes = new ArrayList<Cliente>(){{ add(a1); add(a2); add(a3); }};
        // Map<Integer, Paquete> paquetes = new TreeMap<Integer, Paquete>(){{
        //     put(1001, new Paquete(a1, "Talleres Pedro", "Avda. Camiño Francés, s/n"));
        //     put(1002, new Paquete(a1, "El Descanso", "Avda. De Lugo, s/n"));
        //     put(1003, new Paquete(a2, "Comercial Lata", "Polígono del Tambre, s/n"));
        // }};
        // pqXpress.altaCliente("A11222333", "Comercial Pepe");
        // pqXpress.altaCliente("B22333444", "Distribuciones Lolo");
        // pqXpress.altaCliente("C33444555", "Talleres Louzan");
        
        PqManager pqXpress = new PqManager();
        pqXpress.altaEnvio(a1, "Talleres Pedro", "Avda. Camiño Francés, s/n");
        pqXpress.altaEnvio(a1, "El Descanso", "Avda. De Lugo, s/n");
        pqXpress.altaEnvio(a2, "Comercial Lata", "Polígono del Tambre, s/n");
        pqXpress.altaEnvio(a3, "Comercial Pipo", "Polígono de la Grela, s/n");
        String p1 = (pqXpress.getPaquetes().get(1001).getEstado().informaEstado());
        pqXpress.getPaquetes().get(1001).setEstado(new Enviado());
        pqXpress.getPaquetes().get(1002).setEstado(new EnReparto());
        pqXpress.getPaquetes().get(1003).setEstado(new EnReparto());
        String p2 = (pqXpress.getPaquetes().get(1001).getEstado().informaEstado());
        String p3 = (pqXpress.getPaquetes().get(1002).getEstado().informaEstado());
        String p4 = (pqXpress.getPaquetes().get(1003).getEstado().informaEstado());

        assertEquals("Envío ordenado pero el paquete aún no ha sido recogido", p1);
        assertEquals("Paquete enviado al destinatario.", p2);
        assertEquals("Paquete en reparto", p3);
        assertEquals("Paquete en reparto", p4);
    }
}
