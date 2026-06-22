import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {
    private static final double SALDO_MINIMO = 100.0;

    public CuentaAhorro(String numero_cuenta, double saldo, LocalDate fecha_emision,
                        LocalDate fecha_vencimiento, double porcentaje_interes) {
        super(numero_cuenta, saldo, fecha_emision, fecha_vencimiento, porcentaje_interes);

        if (saldo < SALDO_MINIMO) {
            throw new IllegalArgumentException("La cuenta de ahorro debe iniciar con al menos $" + SALDO_MINIMO);
        }
    }

    // permite depositar dinero a la cuenta
    public void depositar(double monto) {
        if (estaVencida()) {
            System.out.println("Transacción bloqueada: la cuenta #" + getNumeroCuenta() + " está vencida.");
            return;
        }
        setSaldo(getSaldo() + monto);
    }

    @Override
    public void pagar(double monto) {
        if (estaVencida()) {
            System.out.println("Transacción bloqueada: la cuenta #" + getNumeroCuenta() + " está vencida.");
            return;
        }
        if (getSaldo() - monto < SALDO_MINIMO) {
            System.out.println("No se puede realizar el pago: el saldo no puede bajar de $" + SALDO_MINIMO);
            return;
        }
        setSaldo(getSaldo() - monto);
    }

    @Override
    public void generarInteres() {
        double interes = getSaldo() * (getPorcentajeInteres() / 100);
        setSaldo(getSaldo() + interes);
    }

    @Override
    public String toString() {
        return "CuentaAhorro -> " + super.toString();
    }
}