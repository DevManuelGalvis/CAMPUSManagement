package campus.model;

/**
 * Clase abstracta que contiene datos comunes de personas.
 */
public abstract class Persona {
    private String id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;

    // Constructor principal
    public Persona(String id, String nombres, String apellidos, String tipoDocumento, String numeroDocumento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    // Constructor sobrecargado (sobrecarga de métodos: constructor)
    public Persona(String id, String nombres, String apellidos) {
        this(id, nombres, apellidos, "", "");
    }

    // Encapsulamiento: getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTipoDocumento() { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }

    // Sobrecarga de método setDocumento
    public void setDocumento(String tipo, String numero) {
        this.tipoDocumento = tipo;
        this.numeroDocumento = numero;
    }

    // Sobrecarga: aceptar un único string "TIPO:NUMERO"
    public void setDocumento(String documentoCompleto) {
        if (documentoCompleto != null && documentoCompleto.contains(":")) {
            String[] p = documentoCompleto.split(":", 2);
            setDocumento(p[0].trim(), p[1].trim());
        } else {
            this.tipoDocumento = "";
            this.numeroDocumento = documentoCompleto == null ? "" : documentoCompleto;
        }
    }

    // Método abstracto para que cada subclase implemente su cálculo
    public abstract double calcular();

    @Override
    public String toString() {
        return String.format("ID: %s | %s %s | %s: %s",
                id, nombres, apellidos, tipoDocumento, numeroDocumento);
    }
}
