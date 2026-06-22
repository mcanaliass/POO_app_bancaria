import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== DEMOSTRACIÓN DEL SISTEMA BANCARIO =====\n");

        // ----- Administrador -----
        System.out.println("--- Crear Administrador ---");
        Administrador admin = new Administrador("Ana Rodríguez", "1-1111-1111", "ana@banco.com", "Admin123!");
        System.out.println(admin);
        System.out.println("Login válido: " + admin.validarCredenciales("ana@banco.com", "Admin123!"));
        System.out.println();

        // ----- Cliente -----
        System.out.println("--- Crear Cliente ---");
        Cliente cliente = new Cliente("Majo Pérez", "2-2222-2222", "majo@correo.com", "Clave123!",
                "Femenino", "Estudiante", "San José, Costa Rica");
        System.out.println(cliente);
        System.out.println();

        // ----- CuentaAhorro -----
        System.out.println("--- Probar CuentaAhorro ---");
        CuentaAhorro cuenta_ahorro = new CuentaAhorro("AH-001", 150.0,
                LocalDate.of(2026, 1, 1), LocalDate.of(2027, 1, 1), 2.0);
        cuenta_ahorro.depositar(50.0);
        System.out.println(cuenta_ahorro);
        cuenta_ahorro.pagar(30.0);
        System.out.println("Después de pagar $30: " + cuenta_ahorro);
        cuenta_ahorro.pagar(1000.0); // debe fallar, baja del minimo
        cuenta_ahorro.generarInteres();
        System.out.println("Después de generar interés: " + cuenta_ahorro);
        cliente.agregarCuenta(cuenta_ahorro);
        System.out.println();

        // ----- CuentaDebito -----
        System.out.println("--- Probar CuentaDebito ---");
        CuentaDebito cuenta_debito = new CuentaDebito("DB-001", 80.0,
                LocalDate.of(2026, 1, 1), LocalDate.of(2027, 1, 1), 0.5);
        cuenta_debito.depositar(20.0);
        System.out.println(cuenta_debito);
        cuenta_debito.pagar(150.0); // debe fallar, no puede ser negativo
        cuenta_debito.pagar(60.0);
        System.out.println("Después de pagar $60: " + cuenta_debito);
        cliente.agregarCuenta(cuenta_debito);
        System.out.println();

        // ----- CuentaCredito -----
        System.out.println("--- Probar CuentaCredito ---");
        CuentaCredito cuenta_credito = new CuentaCredito("CR-001",
                LocalDate.of(2026, 1, 1), LocalDate.of(2027, 1, 1), 3.0, "Cashback", 500.0);
        System.out.println(cuenta_credito);
        cuenta_credito.pagar(200.0); // compra, sube la deuda
        System.out.println("Después de comprar $200: " + cuenta_credito);
        cuenta_credito.pagar(400.0); // debe fallar, supera el limite
        cuenta_credito.abonar(50.0); // abono, baja la deuda
        System.out.println("Después de abonar $50: " + cuenta_credito);
        cliente.agregarCuenta(cuenta_credito);
        System.out.println();

        // ----- Reporte consolidado del cliente -----
        System.out.println("--- Reporte final del cliente ---");
        System.out.println(cliente.generarReporteCuentas());

        // ----- equals() -----
        System.out.println("\n--- Probando equals() ---");
        CuentaAhorro otra_cuenta_ahorro = new CuentaAhorro("AH-001", 200.0,
                LocalDate.of(2026, 1, 1), LocalDate.of(2027, 1, 1), 2.0);
        System.out.println("¿cuenta_ahorro equals otra_cuenta_ahorro? " + cuenta_ahorro.equals(otra_cuenta_ahorro));
    }
}