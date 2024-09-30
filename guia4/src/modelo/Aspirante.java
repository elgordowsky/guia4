package modelo;

//clase que modela los atributos de los aspirantes
public class Aspirante {
    private String cedula;
    private String nombre;
    private int edad;
    private int experiencia;
    private String profesion;
    private String telefono;
    // constructor
    public Aspirante(String cedula, String nombre, int edad, int experiencia, String profesion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.experiencia = experiencia;
        this.profesion = profesion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cédula: " + cedula + "\nNombre: " + nombre + "\nEdad: " + edad +
                "\nExperiencia: " + experiencia + " años\nProfesión: " + profesion +
                "\nTeléfono: " + telefono;
    }
}
