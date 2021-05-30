package paqxpress.core;

public class EnProceso implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        paquete.setEstado(new Enviado());
    }

    @Override
    public String informaEstado() {
        return "Paquete recogido y en proceso de envío.";
    }
    
}
