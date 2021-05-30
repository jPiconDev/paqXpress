package paqxpress.core;

import java.time.LocalDateTime;

public class Paquete {
    
    private int idPacK;
    private LocalDateTime fAlta;
    private Cliente cliente;
    private String destinatario;
    private String destino;
    private EstadoPq estado;

    public Paquete(Cliente cliente, String destinatario, String destino) {
        PqManager.setIdNumber();
        this.idPacK = PqManager.getIdNumber();
        this.fAlta = LocalDateTime.now();
        this.cliente = cliente;
        this.destinatario = destinatario;
        this.destino = destino;
        this.estado = new Ordenado();
    }

    public void cambiaEstado() { 
        this.estado.avanza(this); }

    public int getIdPack(){ return this.idPacK; }

    public Cliente getCliente(){ return this.cliente; }

    public void setEstado(EstadoPq estado) { 
        this.estado = estado; }

    public String informaEstado() { return estado.informaEstado(); }

    public LocalDateTime getfAlta() { return fAlta; }

    public String getDestinatario() { return destinatario; }

    public String getDestino() { return destino; }

    public EstadoPq getEstado() { return estado; }
}
