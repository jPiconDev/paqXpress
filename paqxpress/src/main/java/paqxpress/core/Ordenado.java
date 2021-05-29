package paqxpress.core;

public class Ordenado implements EstadoPq {

    @Override
    public void avanza(Paquete paquete) {
        EnProceso enProceso = new EnProceso();
        paquete.setEstado(enProceso);
    }

    @Override
    public String informaEstado() {
        return "ordenado";
    }
    
}
