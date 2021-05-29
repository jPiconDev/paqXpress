package paqxpress.core;

import java.time.LocalDateTime;

public class Paquete {
    private final int idNumber = 1000;
    private int idPacK;
    private LocalDateTime fAlta;
    private Cliente cliente;
    private String destinatario;
    private String destino;
    private EstadoPq estado;

    public Paquete(Cliente cliente, String destinatario, String destino) {
        this.idPacK = idNumber + 1;
        this.fAlta = LocalDateTime.now();
        this.cliente = cliente;
        this.destinatario = destinatario;
        this.destino = destino;
        this.estado = new Ordenado();
    }

    public void cambiaEstado() {
        this.estado.avanza(this);
    }

    public int getIdPack(){
        return this.idPacK;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setEstado(EstadoPq estado) {
        this.estado = estado;
    }

    public String informaEstado() {
        return estado.informaEstado();
    }
}
