package paqxpress.core;

public class Enviado implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        paquete.setEstado(new EnReparto());
    }

    @Override
    public String informaEstado() {
        return "Paquete enviado al destinatario.";
    }
    
}
