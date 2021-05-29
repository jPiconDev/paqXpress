package paqxpress.core;

public class Cliente {
    private String idCli;
    private String nombre;

    public Cliente(String idCli, String nombre) {
        this.idCli = idCli;
        this.nombre = nombre;
    }

    public String getIdCli() {
        return this.idCli;
    }

    public String getNombre() {
        return this.nombre;        
    }
}