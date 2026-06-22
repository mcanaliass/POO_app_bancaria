import java.util.regex.Pattern;

public abstract class Usuario {
    private String nombre_completo;
    private String cedula;
    private String correo_electronico;
    private String contrasenia;

    public Usuario(String nombre_completo, String cedula, String correo_electronico, String contrasenia) {
        this.nombre_completo = nombre_completo;
        this.cedula = cedula;
        this.correo_electronico = correo_electronico;
        setContrasenia(contrasenia);
    }

    // getters y setters
    public String getNombreCompleto() {
        return nombre_completo;
    }

    public void setNombreCompleto(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correo_electronico;
    }

    public void setCorreoElectronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if (esContraseniaValida(contrasenia)) {
            this.contrasenia = contrasenia;
        } else {
            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.");
        }
    }

    // valida que la contraseña cumpla las reglas del enunciado
    private boolean esContraseniaValida(String contrasenia) {
        if (contrasenia == null || contrasenia.length() < 8) {
            return false;
        }
        boolean tiene_mayuscula = Pattern.compile("[A-Z]").matcher(contrasenia).find();
        boolean tiene_minuscula = Pattern.compile("[a-z]").matcher(contrasenia).find();
        boolean tiene_numero = Pattern.compile("[0-9]").matcher(contrasenia).find();
        boolean tiene_especial = Pattern.compile("[^a-zA-Z0-9]").matcher(contrasenia).find();
        return tiene_mayuscula && tiene_minuscula && tiene_numero && tiene_especial;
    }

    // valida correo y contraseña para el login
    public boolean validarCredenciales(String correo, String contrasenia) {
        return this.correo_electronico.equals(correo) && this.contrasenia.equals(contrasenia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario otro = (Usuario) obj;
        return cedula.equals(otro.cedula);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre_completo + ", Cédula: " + cedula + ", Correo: " + correo_electronico;
    }
}