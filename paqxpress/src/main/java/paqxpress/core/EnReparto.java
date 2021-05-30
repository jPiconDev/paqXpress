package paqxpress.core;

public class EnReparto implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        paquete.setEstado(new Entregado());        
    }

    @Override
    public String informaEstado() {
        return "Paquete en reparto";
    }
    
}
