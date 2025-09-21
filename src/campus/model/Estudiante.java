package campus.model;

/**
 * Representa un estudiante con matrícula y posible descuento.
 */
public class Estudiante extends Persona {
    private double matricula;
    private boolean tieneDescuento;
    private double valorDescuento;

    public Estudiante(String id, String nombres, String apellidos,
                      String tipoDocumento, String numeroDocumento,
                      double matricula, boolean tieneDescuento, double valorDescuento) {
        super(id, nombres, apellidos, tipoDocumento, numeroDocumento);
        this.matricula = matricula;
        this.tieneDescuento = tieneDescuento;
        this.valorDescuento = valorDescuento;
    }

    public double getMatricula() { return matricula; }
    public void setMatricula(double matricula) { this.matricula = matricula; }

    public boolean isTieneDescuento() { return tieneDescuento; }
    public void setTieneDescuento(boolean tieneDescuento) { this.tieneDescuento = tieneDescuento; }

    public double getValorDescuento() { return valorDescuento; }
    public void setValorDescuento(double valorDescuento) { this.valorDescuento = valorDescuento; }

    @Override
    public double calcular() {
        if (tieneDescuento) {
            return Math.max(0.0, matricula - valorDescuento);
        } else {
            return matricula;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Matrícula: %.2f | Descuento?: %s | Final: %.2f",
                matricula, (tieneDescuento ? "Sí":"No"), calcular());
    }
}
