package campus.ui;

import campus.model.Colaborador;
import campus.model.DataManager;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para registrar colaboradores.
 * Implementa la interfaz Registrable (iniciar, guardar, finalizar).
 */
public class ColaboradorForm extends JDialog implements Registrable {
    private JTextField idField, nombresField, apellidosField, salarioField, numeroDocField, valorMovimientoField;
    private JComboBox<String> tipoDocCombo, tipoMovimientoCombo;
    private JTextArea salidaArea;

    public ColaboradorForm(Frame owner) {
        super(owner, "Registrar Colaborador - Campus", true);
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

        form.add(new JLabel("ID Colaborador:"), c);
        c.gridx = 1; idField = new JTextField(18); form.add(idField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Nombres:"), c);
        c.gridx = 1; nombresField = new JTextField(18); form.add(nombresField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Apellidos:"), c);
        c.gridx = 1; apellidosField = new JTextField(18); form.add(apellidosField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Tipo Doc:"), c);
        c.gridx = 1;
        tipoDocCombo = new JComboBox<>(new String[] {"Cédula", "Tarjeta ID", "Pasaporte"});
        form.add(tipoDocCombo, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Número Doc:"), c);
        c.gridx = 1; numeroDocField = new JTextField(18); form.add(numeroDocField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Salario:"), c);
        c.gridx = 1; salarioField = new JTextField(10); form.add(salarioField, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Tipo Movimiento:"), c);
        c.gridx = 1;
        tipoMovimientoCombo = new JComboBox<>(new String[] {"Descuento", "Bonificación"});
        form.add(tipoMovimientoCombo, c);

        c.gridx = 0; c.gridy++; form.add(new JLabel("Valor (desc/bon):"), c);
        c.gridx = 1; valorMovimientoField = new JTextField(10); form.add(valorMovimientoField, c);

        p.add(form, BorderLayout.NORTH);

        // Botones
        JPanel btns = new JPanel();
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(e -> iniciar());
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            guardar();
        });
        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addActionListener(e -> finalizar());
        btns.add(btnIniciar); btns.add(btnGuardar); btns.add(btnFinalizar);
        p.add(btns, BorderLayout.CENTER);

        // Caja pequeña para mostrar colaborador ingresado
        salidaArea = new JTextArea(6, 40);
        salidaArea.setEditable(false);
        JScrollPane scr = new JScrollPane(salidaArea);
        scr.setBorder(BorderFactory.createTitledBorder("Último colaborador ingresado"));
        p.add(scr, BorderLayout.SOUTH);

        add(p);
    }

    @Override
    public void iniciar() {
        idField.setText("");
        nombresField.setText("");
        apellidosField.setText("");
        salarioField.setText("");
        numeroDocField.setText("");
        valorMovimientoField.setText("");
        tipoDocCombo.setSelectedIndex(0);
        tipoMovimientoCombo.setSelectedIndex(0);
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
            double salario = Double.parseDouble(salarioField.getText().trim());
            String tipoMov = (String) tipoMovimientoCombo.getSelectedItem();
            double valorMov = 0.0;
            if (!valorMovimientoField.getText().trim().isEmpty()) {
                valorMov = Double.parseDouble(valorMovimientoField.getText().trim());
            }

            if (id.isEmpty() || nombres.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombres son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Colaborador c = new Colaborador(id, nombres, apellidos, tipoDoc, numeroDoc, salario, tipoMov, valorMov);
            DataManager.getInstance().addColaborador(c);
            salidaArea.setText(c.toString());
            JOptionPane.showMessageDialog(this, "Colaborador guardado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revise los valores numéricos (salario/valor).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void finalizar() {
        dispose();
    }
}
