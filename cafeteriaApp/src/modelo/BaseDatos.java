package modelo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {
    private static final String URL = "jdbc:mysql://127.0.0.1:3308/cafeteriaDBB";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        String query = "SELECT * FROM Usuario WHERE nombre = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("contraseña"), rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario crearUsuario(String nombre, String contraseña, String rol) {
        String query = "INSERT INTO Usuario (nombre, contraseña, rol) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            stmt.setString(3, rol);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return new Usuario(rs.getInt(1), nombre, contraseña, rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getBigDecimal("precio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean registrarCompra(Compra compra) {
        String queryCompra = "INSERT INTO Compra (usuario_id) VALUES (?)";
        String queryDetalle = "INSERT INTO DetalleCompra (compra_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtCompra = conn.prepareStatement(queryCompra, PreparedStatement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);

            stmtCompra.setInt(1, compra.getCliente().getId());
            int affectedRows = stmtCompra.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmtCompra.getGeneratedKeys();
                if (rs.next()) {
                    int compraId = rs.getInt(1);
                    for (DetalleCompra detalle : compra.getDetalles()) {
                        try (PreparedStatement stmtDetalle = conn.prepareStatement(queryDetalle)) {
                            stmtDetalle.setInt(1, compraId);
                            stmtDetalle.setInt(2, detalle.getProducto().getId());
                            stmtDetalle.setInt(3, detalle.getCantidad());
                            stmtDetalle.executeUpdate();
                        }
                    }
                    conn.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String obtenerRegistroVentas() {
        StringBuilder registro = new StringBuilder("Registro de Ventas:\n");
        String query = "SELECT * FROM vista_ventas";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                registro.append("ID Compra: ").append(rs.getInt("id_compra"))
                        .append(", Cliente: ").append(rs.getString("nombre_cliente"))
                        .append(", Productos: ").append(rs.getString("productos"))
                        .append(", Precio Total: $").append(rs.getBigDecimal("precio_total"))
                        .append(", Fecha: ").append(rs.getTimestamp("fecha_compra"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro.toString();
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("contraseña"), rs.getString("rol")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    public boolean eliminarUsuarioPorNombre(String nombre) {
        String query = "DELETE FROM Usuario WHERE nombre = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
         
    }
    public boolean agregarUsuario(String nombre, String contraseña, String rol) {
        String query = "INSERT INTO Usuario (nombre, contraseña, rol) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            stmt.setString(3, rol);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarUsuario(int id, String nombre, String contraseña, String rol) {
        String query = "UPDATE Usuario SET nombre = ?, contraseña = ?, rol = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            stmt.setString(3, rol);
            stmt.setInt(4, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Usuario autenticarUsuario(String nombre, String contraseña) {
        String query = "SELECT * FROM Usuario WHERE nombre = ? AND contraseña = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("contraseña"), rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarProducto(int id) {
        String query = "DELETE FROM Producto WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarProducto(int id, String nombre, BigDecimal precio) {
        String query = "UPDATE Producto SET nombre = ?, precio = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setBigDecimal(2, precio);
            stmt.setInt(3, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarProducto(String nombre, BigDecimal precio) {
        String query = "INSERT INTO Producto (nombre, precio) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setBigDecimal(2, precio);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

