public class Administrador extends Usuario {

    public Administrador(String nombre_completo, String cedula, String correo_electronico, String contrasenia) {
        super(nombre_completo, cedula, correo_electronico, contrasenia);
    }

    @Override
    public String toString() {
        return "Administrador -> " + super.toString();
    }
}