
package manualbd;

/**
 *
 * @author Alejandro Pereiro G
 */
//creamos una clase usuario que usaremos para la tabla de la base de datos
public class Usuario {
    //atributos
    private String dni; 
    private String nombre; 
    private String apellidos;
    private int edad;
    //cpnstructores
    public Usuario() {
    }
    
    public Usuario(String dni, String nombre, String apellidos, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    //geters y seters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
