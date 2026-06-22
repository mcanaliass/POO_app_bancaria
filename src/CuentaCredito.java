import java.time.LocalDate;

public class CuentaCredito extends Cuenta {
    private String tipo;
    private double limite_credito;

    public CuentaCredito(String numero_cuenta, LocalDate fecha_emision, LocalDate fecha_vencimiento,
                         double porcentaje_interes, String tipo, double limite_credito) {
        // las cuentas de credito siempre inician en $0, segun el enunciado
        super(numero_cuenta, 0.0, fecha_emision, fecha_vencimiento, porcentaje_interes);
        this.tipo = tipo;
        this.limite_credito = limite_credito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLimiteCredito() {
        return limite_credito;
    }

    public void setLimiteCredito(double limite_credito) {
        this.limite_credito = limite_credito;
    }

    // abono que el cliente hace para bajar su deuda
    public void abonar(double monto) {
        if (estaVencida()) {
            System.out.println("Transacción bloqueada: la cuenta #" + getNumeroCuenta() + " está vencida.");
            return;
        }
        double nuevo_saldo = getSaldo() - monto;
        setSaldo(Math.max(nuevo_saldo, 0));
    }

    // el "pago" en una cuenta de credito es una compra, que sube la deuda (saldo)
    @Override
    public void pagar(double monto) {
        if (estaVencida()) {
            System.out.println("Transacción bloqueada: la cuenta #" + getNumeroCuenta() + " está vencida.");
            return;
        }
        if (getSaldo() + monto > limite_credito) {
            System.out.println("No se puede realizar el pago: supera el límite de crédito de $" + limite_credito);
            return;
        }
        setSaldo(getSaldo() + monto);
    }

    @Override
    public void generarInteres() {
        double interes = getSaldo() * (getPorcentajeInteres() / 100);
        setSaldo(getSaldo() + interes);
    }

    @Override
    public String toString() {
        return "CuentaCredito -> " + super.toString() + ", Tipo: " + tipo + ", Límite de crédito: $" + limite_credito;
    }
}