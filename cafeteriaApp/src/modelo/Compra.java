package modelo;

import java.util.List;

public class Compra {
    private int id;
    private Usuario cliente;
    private List<DetalleCompra> detalles;
    private String estado;


	public Compra(int id, Usuario cliente, List<DetalleCompra> detalles, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.detalles = detalles;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
