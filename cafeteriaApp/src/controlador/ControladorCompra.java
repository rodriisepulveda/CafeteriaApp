package controlador;

import modelo.BaseDatos;
import modelo.Usuario; // Asegúrate de importar Usuario
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControladorCompra {
    private BaseDatos baseDatos;

    public ControladorCompra() {
        baseDatos = new BaseDatos();
    }

    public void realizarCompra() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese su nombre:");
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre del cliente no puede estar vacío.");
            return;
        }

        Usuario cliente = baseDatos.obtenerUsuarioPorNombre(nombreCliente); // 
        if (cliente == null) {
            cliente = baseDatos.crearUsuario(nombreCliente, null, "Cliente"); // Crear un nuevo usuario como cliente
        }

        List<Producto> productos = baseDatos.obtenerProductos();
        List<DetalleCompra> detalles = new ArrayList<>();

        String[] nombresProductos = new String[productos.size()];
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            nombresProductos[i] = producto.getNombre() + " - $" + producto.getPrecio();
        }

        boolean seguirSeleccionando = true;
        while (seguirSeleccionando) {
            JComboBox<String> comboBox = new JComboBox<>(nombresProductos);
            int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione un producto", JOptionPane.OK_CANCEL_OPTION);

            if (result != JOptionPane.OK_OPTION) {
                break;
            }

            Producto productoSeleccionado = productos.get(comboBox.getSelectedIndex());
            String cantidadStr = JOptionPane.showInputDialog("Cuantos " + productoSeleccionado.getNombre() +  " necesita? " + " (Precio: " + productoSeleccionado.getPrecio() + "):");

            if (cantidadStr != null && !cantidadStr.trim().isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0) {
                        detalles.add(new DetalleCompra(0, productoSeleccionado, cantidad));
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                }
            }

            int continuar = JOptionPane.showConfirmDialog(null, "¿Desea seleccionar otro producto?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_NO_OPTION) {
                seguirSeleccionando = false;
            }
        }

        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se seleccionaron productos. Cancelando la compra.");
            return;
        }

        mostrarResumenCompra(detalles);

        Compra compra = new Compra(0, cliente, detalles); // Utilizar Usuario en lugar de Cliente
        boolean exito = baseDatos.registrarCompra(compra);

        if (exito) {
            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al realizar la compra.");
        }
    }

    private void mostrarResumenCompra(List<DetalleCompra> detalles) {
        String resumen = "Resumen de la compra:\n";
        for (DetalleCompra detalle : detalles) {
            resumen += detalle.getProducto().getNombre()
                    + " - Cantidad: "
                    + detalle.getCantidad()
                    + " - Precio: $"
                    + detalle.getProducto().getPrecio().multiply(new java.math.BigDecimal(detalle.getCantidad()))
                    + "\n";
        }

        JOptionPane.showMessageDialog(null, resumen, "Resumen de la compra", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarRegistroVentas() {
        try (Statement stmt = baseDatos.conectar().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vista_ventas")) {

            String registro = "Registro de Ventas:\n";
            boolean hayVentas = false;

            while (rs.next()) {
                hayVentas = true;
                registro += "ID Compra: " + rs.getInt("id_compra") +
                            ", Cliente: " + rs.getString("nombre_cliente") +
                            ", Productos: " + rs.getString("productos") +
                            ", Precio Total: $" + rs.getBigDecimal("precio_total") +
                            ", Fecha: " + rs.getTimestamp("fecha_compra") +
                            "\n";
            }

            if (!hayVentas) {
                JOptionPane.showMessageDialog(null, "No hay ventas.", "Registro de Ventas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, registro, "Registro de Ventas", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
