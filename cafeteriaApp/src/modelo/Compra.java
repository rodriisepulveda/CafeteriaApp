package modelo;

import java.util.List;

public class Compra {
    private int id;
    private Usuario cliente; // Cambiado a Usuario
    private List<DetalleCompra> detalles;

    public Compra(int id, Usuario cliente, List<DetalleCompra> detalles) {
        this.id = id;
        this.cliente = cliente;
        this.detalles = detalles;
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
}
