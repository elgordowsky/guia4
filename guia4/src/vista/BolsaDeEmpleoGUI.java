package vista;

import controlador.BolsaDeEmpleoControlador;
import modelo.Aspirante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BolsaDeEmpleoGUI extends JFrame {
    private BolsaDeEmpleoControlador controlador;

    private JTextField txtCedula, txtNombre, txtEdad, txtExperiencia, txtProfesion, txtTelefono;
    private JTextArea txtAreaAspirantes;
    private JButton btnAgregar, btnMostrar, btnBuscar, btnEliminar, btnPromedioEdad, btnOrdenarExperiencia;

    private static final String TITLE = "Bolsa de Empleo";
    private static final String CEDULA_LABEL = "Cédula:";
    private static final String NOMBRE_LABEL = "Nombre:";
    private static final String EDAD_LABEL = "Edad:";
    private static final String EXPERIENCIA_LABEL = "Experiencia (años):";
    private static final String PROFESION_LABEL = "Profesión:";
    private static final String TELEFONO_LABEL = "Teléfono:";

    public BolsaDeEmpleoGUI() {
        controlador = new BolsaDeEmpleoControlador();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle(TITLE);
        setSize(1200, 600);  // Aumentar el tamaño de la ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(crearPanelEntrada(), BorderLayout.NORTH);
        add(crearPanelBotones(), BorderLayout.CENTER);
        add(crearAreaResultados(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelEntrada() {
        JPanel panelEntrada = new JPanel(new GridLayout(7, 2));
        panelEntrada.add(new JLabel(CEDULA_LABEL));
        txtCedula = new JTextField();
        panelEntrada.add(txtCedula);
        panelEntrada.add(new JLabel(NOMBRE_LABEL));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);
        panelEntrada.add(new JLabel(EDAD_LABEL));
        txtEdad = new JTextField();
        panelEntrada.add(txtEdad);
        panelEntrada.add(new JLabel(EXPERIENCIA_LABEL));
        txtExperiencia = new JTextField();
        panelEntrada.add(txtExperiencia);
        panelEntrada.add(new JLabel(PROFESION_LABEL));
        txtProfesion = new JTextField();
        panelEntrada.add(txtProfesion);
        panelEntrada.add(new JLabel(TELEFONO_LABEL));
        txtTelefono = new JTextField();
        panelEntrada.add(txtTelefono);

        return panelEntrada;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new GridLayout(1, 6));
        btnAgregar = new JButton("Agregar");
        btnMostrar = new JButton("Mostrar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        btnPromedioEdad = new JButton("Promedio Edad");
        btnOrdenarExperiencia = new JButton("Ordenar por Experiencia");

        agregarActionListener(btnAgregar, this::agregarAspirante);
        agregarActionListener(btnMostrar, this::mostrarAspirantes);
        agregarActionListener(btnBuscar, this::buscarAspirante);
        agregarActionListener(btnEliminar, this::eliminarAspirante);
        agregarActionListener(btnPromedioEdad, this::calcularPromedioEdad);
        agregarActionListener(btnOrdenarExperiencia, this::ordenarPorExperiencia);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnPromedioEdad);
        panelBotones.add(btnOrdenarExperiencia);

        return panelBotones;
    }

    private JScrollPane crearAreaResultados() {
        txtAreaAspirantes = new JTextArea();
        txtAreaAspirantes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaAspirantes);
        scrollPane.setPreferredSize(new Dimension(750, 400)); // Aumentar el tamaño del JScrollPane
        return scrollPane;
    }

    private void agregarActionListener(JButton button, Runnable action) {
        button.addActionListener(e -> action.run());
    }

    private void agregarAspirante() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        int experiencia = Integer.parseInt(txtExperiencia.getText());
        String profesion = txtProfesion.getText();
        String telefono = txtTelefono.getText();

        Aspirante aspirante = new Aspirante(cedula, nombre, edad, experiencia, profesion, telefono);
        if (controlador.agregarAspirante(aspirante)) {
            JOptionPane.showMessageDialog(this, "Aspirante agregado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar aspirante.");
        }
    }

    private void mostrarAspirantes() {
        List<Aspirante> aspirantes = controlador.obtenerAspirantes();
        txtAreaAspirantes.setText("");
        for (Aspirante aspirante : aspirantes) {
            txtAreaAspirantes.append(aspirante.toString() + "\n---------------------\n");
        }
    }

    private void buscarAspirante() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del aspirante:");
        Aspirante aspirante = controlador.buscarAspirantePorCedula(cedula);
        if (aspirante != null) {
            txtAreaAspirantes.setText(aspirante.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Aspirante no encontrado.");
        }
    }

    private void eliminarAspirante() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del aspirante a eliminar:");
        if (controlador.eliminarAspirante(cedula)) {
            JOptionPane.showMessageDialog(this, "Aspirante eliminado correctamente.");
            mostrarAspirantes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar aspirante.");
        }
    }

    private void calcularPromedioEdad() {
        double promedio = controlador.calcularPromedioEdad();
        JOptionPane.showMessageDialog(this, "El promedio de edad de los aspirantes es: " + promedio);
    }

    private void ordenarPorExperiencia() {
        List<Aspirante> aspirantes = controlador.ordenarPorExperiencia();
        txtAreaAspirantes.setText("");
        for (Aspirante aspirante : aspirantes) {
            txtAreaAspirantes.append(aspirante.toString() + "\n---------------------\n");
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtExperiencia.setText("");
        txtProfesion.setText("");
        txtTelefono.setText("");
    }


}