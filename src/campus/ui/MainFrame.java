package campus.ui;

import campus.model.DataManager;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal con menú y botones para abrir formularios.
 */
public class MainFrame extends JFrame {
    private JTextArea infoArea;

    public MainFrame() {
        setTitle("Gestión de Colaboradores y Estudiantes - Campus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createMenuBar();
        createMainPanel();
        createRightPanel();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Archivo");
        JMenuItem exit = new JMenuItem("Salir");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);
        menuBar.add(file);

        JMenu help = new JMenu("Ayuda");
        JMenuItem about = new JMenuItem("Acerca de");
        about.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Proyecto Campus - Gestión\nSwing + POO\n(c) Tu nombre"));
        help.add(about);
        menuBar.add(help);

        setJMenuBar(menuBar);
    }

    private void createMainPanel() {
        JPanel center = new JPanel(new BorderLayout(8,8));
        center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JTextArea desc = new JTextArea(
                "Gestión de colaboradores y estudiantes de Campus.\n\n" +
                        "Use los botones para abrir el formulario correspondiente.\n" +
                        "Los formularios permiten Iniciar (limpiar), Guardar y Finalizar (cerrar)."
        );
        desc.setEditable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setBackground(getBackground());
        center.add(desc, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnColab = new JButton("Registrar Colaborador");
        btnColab.addActionListener(e -> {
            ColaboradorForm form = new ColaboradorForm(this);
            form.setVisible(true);
            actualizarInfo();
        });

        JButton btnEst = new JButton("Registrar Estudiante");
        btnEst.addActionListener(e -> {
            EstudianteForm form = new EstudianteForm(this);
            form.setVisible(true);
            actualizarInfo();
        });

        botones.add(btnColab);
        botones.add(btnEst);
        center.add(botones, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
    }

    private void createRightPanel() {
        JPanel right = new JPanel(new BorderLayout(5,5));
        right.setPreferredSize(new Dimension(380, 0));
        right.setBorder(BorderFactory.createTitledBorder("Registrados"));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scr = new JScrollPane(infoArea);

        JButton refresh = new JButton("Actualizar");
        refresh.addActionListener(e -> actualizarInfo());

        right.add(scr, BorderLayout.CENTER);
        right.add(refresh, BorderLayout.SOUTH);
        add(right, BorderLayout.EAST);
    }

    private void actualizarInfo() {
        String texto = "Colaboradores:\n" + DataManager.getInstance().getColaboradoresAsString()
                + "\nEstudiantes:\n" + DataManager.getInstance().getEstudiantesAsString();
        infoArea.setText(texto);
    }
}
