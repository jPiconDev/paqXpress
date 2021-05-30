package paqxpress.core;

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
        List<Cliente> clientes = new ArrayList<Cliente>(){{ add(a1); add(a2); add(a3); }};
        Map<Integer, Paquete> paquetes = new TreeMap<Integer, Paquete>(){{
            put(1001, new Paquete(a1, "Talleres Pedro", "Avda. Camiño Francés, s/n"));
            put(1002, new Paquete(a1, "El Descanso", "Avda. De Lugo, s/n"));
            put(1003, new Paquete(a2, "Comercial Lata", "Polígono del Tambre, s/n"));
        }};

        PqManager pqXpress = new PqManager();
        
        pqXpress.getPaquetes().get(1001).setEstado(new Enviado());
        pqXpress.getPaquetes().get(1002).setEstado(new EnReparto());
        pqXpress.getPaquetes().get(1003).setEstado(new EnReparto());

        assertFalse( false );
    }
}
