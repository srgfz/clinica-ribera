package clinicaRibera;

public abstract class Persona {
	//Atributos
		protected String dni;
		protected String nombre;
		protected String apellidos;
		protected String telefono;
	//Constructor
		//Por defecto
		public Persona() {
			this.dni = "";
			this.nombre = "";
			this.apellidos = "";
			this.telefono = "";
		}
		//Con parámetros
		public Persona(String dni, String nombre, String apellidos, String telefono) {
			this.dni = dni;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.telefono = telefono;
		}
	//Getters y Setters
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
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
	//toString
		@Override
		public String toString() {
			return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
					+ "]";
		}
		
		
}
