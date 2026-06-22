import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String sexo;
    private String profesion;
    private String direccion;
    private List<Cuenta> cuentas;

    public Cliente(String nombre_completo, String cedula, String correo_electronico, String contrasenia,
                   String sexo, String profesion, String direccion) {
        super(nombre_completo, cedula, correo_electronico, contrasenia);
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
        this.cuentas = new ArrayList<>();
    }

    // getters y setters
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // permite agregar una cuenta nueva al cliente
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // genera el reporte de todas las cuentas del cliente, con saldo final consolidado
    public String generarReporteCuentas() {
        StringBuilder reporte = new StringBuilder();
        double saldo_final = 0;

        reporte.append("Reporte de cuentas de ").append(getNombreCompleto()).append(":\n");

        for (Cuenta cuenta : cuentas) {
            reporte.append(cuenta.toString()).append("\n");
            if (cuenta instanceof CuentaCredito) {
                // el saldo de credito es una deuda, se resta
                saldo_final -= cuenta.getSaldo();
            } else {
                saldo_final += cuenta.getSaldo();
            }
        }

        reporte.append("Saldo final consolidado: $").append(saldo_final);
        return reporte.toString();
    }

    @Override
    public String toString() {
        return "Cliente -> " + super.toString() + ", Sexo: " + sexo + ", Profesión: " + profesion + ", Dirección: " + direccion;
    }
}