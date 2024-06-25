package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import modelo.BaseDatos;

public class AgregarProducto extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;

    public AgregarProducto() {
        super();
        JPanel mainPanel = getMainPanel();

        JLabel lblTitle = new JLabel("Agregar Producto");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 250, 25);
        mainPanel.add(lblTitle);


        JLabel lblNombre = new JLabel("Nombre del Producto:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setBounds(50, 70, 150, 20);
        mainPanel.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(200, 70, 190, 25);
        mainPanel.add(textFieldNombre);


        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrecio.setBounds(50, 110, 150, 20);
        mainPanel.add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(200, 110, 190, 25);
        mainPanel.add(textFieldPrecio);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBackground(new Color(51, 153, 255));
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregar.setBounds(150, 160, 180, 30);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        mainPanel.add(btnAgregar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(150, 210, 180, 30);
        btnVolver.addActionListener(e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);
    }

    private void agregarProducto() {
        String nombre = textFieldNombre.getText().trim();
        String precioStr = textFieldPrecio.getText().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BigDecimal precio = new BigDecimal(precioStr);
            BaseDatos baseDatos = new BaseDatos();
            boolean exito = baseDatos.insertarProducto(nombre, precio);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Producto agregado con éxito.");
                textFieldNombre.setText("");
                textFieldPrecio.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
