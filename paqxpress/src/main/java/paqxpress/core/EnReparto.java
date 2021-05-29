package paqxpress.core;

public class EnReparto implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        Entregado entregado = new Entregado();
        paquete.setEstado(entregado);        
    }

    @Override
    public String informaEstado() {
        return "en reparto";
    }
    
}
