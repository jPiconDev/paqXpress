package paqxpress.core;

public class Enviado implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        EnReparto enReparto = new EnReparto();
        paquete.setEstado(enReparto);
    }

    @Override
    public String informaEstado() {
        return "enviado";
    }
    
}
