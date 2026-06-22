import java.time.LocalDate;

public class CuentaDebito extends Cuenta {

    public CuentaDebito(String numero_cuenta, double saldo, LocalDate fecha_emision,
                        LocalDate fecha_vencimiento, double porcentaje_interes) {
        super(numero_cuenta, saldo, fecha_emision, fecha_vencimiento, porcentaje_interes);

        if (saldo < 0) {
            throw new IllegalArgumentException("La cuenta de débito no puede iniciar con saldo negativo.");
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
        if (getSaldo() - monto < 0) {
            System.out.println("No se puede realizar el pago: el saldo no puede ser negativo.");
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
        return "CuentaDebito -> " + super.toString();
    }
}