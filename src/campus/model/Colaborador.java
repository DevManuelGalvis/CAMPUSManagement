package campus.model;

/**
 * Representa un colaborador con salario y movimiento (descuento/bonificación).
 */
public class Colaborador extends Persona {
    private double salario;
    private String tipoMovimiento; // "Descuento" o "Bonificación"
    private double valorMovimiento; // cantidad del descuento o bonificación

    public Colaborador(String id, String nombres, String apellidos,
                       String tipoDocumento, String numeroDocumento,
                       double salario, String tipoMovimiento, double valorMovimiento) {
        super(id, nombres, apellidos, tipoDocumento, numeroDocumento);
        this.salario = salario;
        this.tipoMovimiento = tipoMovimiento;
        this.valorMovimiento = valorMovimiento;
    }

    // Constructor sobrecargado (ejemplo de sobrecarga)
    public Colaborador(String id, String nombres, String apellidos, double salario) {
        super(id, nombres, apellidos);
        this.salario = salario;
        this.tipoMovimiento = "Descuento";
        this.valorMovimiento = 0.0;
    }

    public double getSalario() { return salario; }

    // Sobrecarga: setSalario con double y con String (parseo)
    public void setSalario(double salario) { this.salario = salario; }

    public void setSalario(String salarioStr) {
        try {
            this.salario = Double.parseDouble(salarioStr);
        } catch (NumberFormatException e) {
            this.salario = 0.0;
        }
    }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public double getValorMovimiento() { return valorMovimiento; }
    public void setValorMovimiento(double valor) { this.valorMovimiento = valor; }

    @Override
    public double calcular() {
        if ("Bonificación".equalsIgnoreCase(tipoMovimiento)) {
            return salario + valorMovimiento;
        } else { // Descuento o por defecto
            return salario - valorMovimiento;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Salario: %.2f | %s: %.2f | Neto: %.2f",
                salario, tipoMovimiento, valorMovimiento, calcular());
    }
}
