package paqxpress.core;

public class EnProceso implements EstadoPq{

    @Override
    public void avanza(Paquete paquete) {
        Enviado enviado = new Enviado();
        paquete.setEstado(enviado);
    }

    @Override
    public String informaEstado() {
        return "en proceso";
    }
    
}
