package campus.ui;

import campus.model.Estudiante;
import campus.model.DataManager;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para registrar estudiantes.
 */
public class EstudianteForm extends JDialog implements Registrable {
    private JTextField idField, nombresField, apellidosField, numeroDocField, matriculaField, valorDescuentoField;
    private JComboBox<String> tipoDocCombo;
    private JComboBox<String> tieneDescuentoCombo;
    private JTextArea salidaArea;

    public EstudianteForm(Frame owner) {
        super(owner, "Registrar Estudiante - Campus", true);
        initComponents();
        setSize(520, 420);
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        JPanel p = new JPanel(new BorderLayout(6,6));
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;

        form.add(new JLabel("ID Estudiante:"), c);
        c.gridx = 1; idField = new JTextField(18); form.add(idField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Nombres:"), c);
        c.gridx = 1; nombresField = new JTextField(18); form.add(nombresField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Apellidos:"), c);
        c.gridx = 1; apellidosField = new JTextField(18); form.add(apellidosField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Tipo Doc:"), c);
        c.gridx = 1; tipoDocCombo = new JComboBox<>(new String[] {"Cédula", "Tarjeta ID", "Pasaporte"}); form.add(tipoDocCombo, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Número Doc:"), c);
        c.gridx = 1; numeroDocField = new JTextField(18); form.add(numeroDocField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Matrícula:"), c);
        c.gridx = 1; matriculaField = new JTextField(10); form.add(matriculaField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("¿Tiene descuento?"), c);
        c.gridx = 1;
        tieneDescuentoCombo = new JComboBox<>(new String[] {"No", "Sí"});
        tieneDescuentoCombo.addActionListener(e -> {
            valorDescuentoField.setEnabled(tieneDescuentoCombo.getSelectedIndex() == 1);
        });
        form.add(tieneDescuentoCombo, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Valor descuento:"), c);
        c.gridx = 1; valorDescuentoField = new JTextField(10); valorDescuentoField.setEnabled(false); form.add(valorDescuentoField, c);

        p.add(form, BorderLayout.NORTH);

        // Botones
        JPanel btns = new JPanel();
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(e -> iniciar());
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addActionListener(e -> finalizar());
        btns.add(btnIniciar); btns.add(btnGuardar); btns.add(btnFinalizar);
        p.add(btns, BorderLayout.CENTER);

        // Salida
        salidaArea = new JTextArea(6, 40);
        salidaArea.setEditable(false);
        JScrollPane scr = new JScrollPane(salidaArea);
        scr.setBorder(BorderFactory.createTitledBorder("Último estudiante ingresado"));
        p.add(scr, BorderLayout.SOUTH);

        add(p);
    }

    @Override
    public void iniciar() {
        idField.setText("");
        nombresField.setText("");
        apellidosField.setText("");
        numeroDocField.setText("");
        matriculaField.setText("");
        valorDescuentoField.setText("");
        tipoDocCombo.setSelectedIndex(0);
        tieneDescuentoCombo.setSelectedIndex(0);
        valorDescuentoField.setEnabled(false);
        salidaArea.setText("");
    }

    @Override
    public void guardar() {
        try {
            String id = idField.getText().trim();
            String nombres = nombresField.getText().trim();
            String apellidos = apellidosField.getText().trim();
            String tipoDoc = (String) tipoDocCombo.getSelectedItem();
            String numeroDoc = numeroDocField.getText().trim();
            double matricula = Double.parseDouble(matriculaField.getText().trim());
            boolean tieneDesc = tieneDescuentoCombo.getSelectedIndex() == 1;
            double valorDesc = 0.0;
            if (tieneDesc && !valorDescuentoField.getText().trim().isEmpty()) {
                valorDesc = Double.parseDouble(valorDescuentoField.getText().trim());
            }

            if (id.isEmpty() || nombres.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombres son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Estudiante e = new Estudiante(id, nombres, apellidos, tipoDoc, numeroDoc, matricula, tieneDesc, valorDesc);
            DataManager.getInstance().addEstudiante(e);
            salidaArea.setText(e.toString());
            JOptionPane.showMessageDialog(this, "Estudiante guardado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revise los valores numéricos (matrícula/descuento).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void finalizar() {
        dispose();
    }
}
