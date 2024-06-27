package controlador;

import modelo.BaseDatos;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ControladorCompra {
    private BaseDatos baseDatos;

    public ControladorCompra() {
        baseDatos = new BaseDatos();
    }

    public List<Producto> obtenerProductos() {
        return baseDatos.obtenerProductos();
    }

    public boolean realizarCompra(String nombreCliente, List<Producto> productosSeleccionados) {
        Usuario cliente = baseDatos.obtenerUsuarioPorNombre(nombreCliente);
        if (cliente == null) {
            cliente = baseDatos.crearUsuario(nombreCliente, null, "Cliente");
        }

        List<DetalleCompra> detalles = new ArrayList<>(); 
        for (Producto producto : productosSeleccionados) {
            detalles.add(new DetalleCompra(0, producto, 1)); 
        }

        Compra compra = new Compra(0, cliente, detalles, nombreCliente);
        return baseDatos.registrarCompra(compra);
    }

    public List<String[]> obtenerRegistroVentas() {
        return baseDatos.obtenerRegistroVentas();
    }
}
