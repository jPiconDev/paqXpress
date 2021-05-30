package paqxpress.core;

public class Ordenado implements EstadoPq {

    @Override
    public void avanza(Paquete paquete) {
        paquete.setEstado(new EnProceso());
    }

    @Override
    public String informaEstado() {
        return "Envío ordenado pero el paquete aún no ha sido recogido";
    }
}
