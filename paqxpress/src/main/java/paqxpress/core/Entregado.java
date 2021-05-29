package paqxpress.core;

public class Entregado implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        
    }

    @Override
    public String informaEstado() {
        return "entregado";
    }
    
}
