package repaso;

public class Mascota implements Identificacion{
	//Atributos
	 protected int edad;
	 protected String nombre;
	 protected String id;
	 //Constructor
		 //Constructor por defecto
			public Mascota() {
				this.edad = 0;
				this.nombre = "";
				this.id = "";
			}	
		//Constructor con parámetros	
			public Mascota(int edad, String nombre, String id) {
				this.edad = edad;
				this.nombre = nombre;
				this.id = id;
			}
		//Constructor con edad y nombre
			public Mascota(String nombre, int edad) {
				this.edad = edad;
				this.nombre = nombre;
				this.id = "";
			}
	//Getters y Setters
			public int getEdad() {
				return edad;
			}
			public void setEdad(int edad) {
				this.edad = edad;
			}
			public String getNombre() {
				return nombre;
			}
			public void setNombre(String nombre) {
				this.nombre = nombre;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
	//toString
			@Override
			public String toString() {
				return "mascota [edad=" + edad + ", nombre=" + nombre + ", id=" + id + "]";
			}
	//Método de la interfaz
	@Override
	public String generarId() {
		// TODO Auto-generated method stub
		return "";
	}
			
	 	
}
