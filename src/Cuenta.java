import java.time.LocalDate;

public abstract class Cuenta {
    private String numero_cuenta;
    private double saldo;
    private LocalDate fecha_emision;
    private LocalDate fecha_vencimiento;
    private double porcentaje_interes;

    public Cuenta(String numero_cuenta, double saldo, LocalDate fecha_emision,
                  LocalDate fecha_vencimiento, double porcentaje_interes) {
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.fecha_emision = fecha_emision;
        this.fecha_vencimiento = fecha_vencimiento;
        this.porcentaje_interes = porcentaje_interes;
    }

    // getters y setters
    public String getNumeroCuenta() {
        return numero_cuenta;
    }

    public void setNumeroCuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    // protected: solo esta clase y sus hijas pueden modificar el saldo directamente
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaEmision() {
        return fecha_emision;
    }

    public void setFechaEmision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public LocalDate getFechaVencimiento() {
        return fecha_vencimiento;
    }

    public void setFechaVencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public double getPorcentajeInteres() {
        return porcentaje_interes;
    }

    public void setPorcentajeInteres(double porcentaje_interes) {
        this.porcentaje_interes = porcentaje_interes;
    }

    // revisa si la cuenta ya venció, para bloquear transacciones
    public boolean estaVencida() {
        return !fecha_vencimiento.isAfter(LocalDate.now());
    }

    // cada tipo de cuenta hace el pago siguiendo sus propias reglas de saldo
    public abstract void pagar(double monto);

    // cada tipo de cuenta calcula y aplica el interes a su manera
    public abstract void generarInteres();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cuenta otra = (Cuenta) obj;
        return numero_cuenta.equals(otra.numero_cuenta);
    }

    @Override
    public String toString() {
        return "Cuenta #" + numero_cuenta + ", Saldo: $" + saldo +
                ", Emisión: " + fecha_emision + ", Vencimiento: " + fecha_vencimiento;
    }
}