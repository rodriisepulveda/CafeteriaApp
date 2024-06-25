package vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaGestionProductos extends BaseLayout {

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public VistaGestionProductos() {
        super();
        JPanel mainPanel = getMainPanel();


        JLabel lblTitle = new JLabel("Gestión de Productos");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 250, 25);
        mainPanel.add(lblTitle);


        JButton btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.setBackground(new Color(51, 153, 255));
        btnAgregarProducto.setForeground(Color.BLACK);
        btnAgregarProducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarProducto agregarProductoFrame = new AgregarProducto();
                agregarProductoFrame.setVisible(true);
                dispose();
            }
        });
        btnAgregarProducto.setBounds(145, 70, 180, 30);
        mainPanel.add(btnAgregarProducto);


        JButton btnModificarProducto = new JButton("Modificar Producto");
        btnModificarProducto.setBackground(new Color(51, 153, 255));
        btnModificarProducto.setForeground(Color.BLACK);
        btnModificarProducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnModificarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModificarProducto modificarProductoFrame = new ModificarProducto();
                modificarProductoFrame.setVisible(true);
                dispose();
            }
        });
        btnModificarProducto.setBounds(145, 111, 180, 30);
        mainPanel.add(btnModificarProducto);


        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        btnEliminarProducto.setBackground(new Color(51, 153, 255));
        btnEliminarProducto.setForeground(Color.BLACK);
        btnEliminarProducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarProducto eliminarProductoFrame = new EliminarProducto();
                eliminarProductoFrame.setVisible(true);
                dispose();
            }
        });
        btnEliminarProducto.setBounds(145, 151, 180, 30);
        mainPanel.add(btnEliminarProducto);

        JButton btnListarProductos = new JButton("Listar Productos");
        btnListarProductos.setBackground(new Color(51, 153, 255));
        btnListarProductos.setForeground(Color.BLACK);
        btnListarProductos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnListarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaListarProductos vistaListarProductos = new VistaListarProductos();
                vistaListarProductos.setVisible(true);
                dispose();
            }
        });
        btnListarProductos.setBounds(145, 192, 180, 30);
        mainPanel.add(btnListarProductos);

        JButton btnVolver = new JButton("Volver al menu ADMINISTRACION");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaAdministrador vistaAdminFrame = new VistaAdministrador();
                vistaAdminFrame.setVisible(true);
                dispose();
            }
        });
        btnVolver.setBounds(145, 233, 180, 30);
        mainPanel.add(btnVolver);
        
        
    }
    
    
    
    
    
}
