package clinicaRibera;

import java.lang.reflect.Method;

public class Paciente extends Persona{
	//Atributos
		private float peso;
		private int edad;
		private String cita;
		private String historia;
	//Constructor
		//Por defecto
		public Paciente() {
			super();
			this.peso = 0;
			this.edad = 0;
			this.cita = "";
			this.historia = "";		
		}
		//Con parámetros
		public Paciente(String dni, String nombre, String apellidos, String telefono, float peso, int edad, String cita, String historia) {
			super(dni, nombre, apellidos, telefono);
			this.peso = peso;
			this.edad = edad;
			this.cita = cita;
			this.historia = historia;		
		}
	//Getters y Setters
		public float getPeso() {
			return peso;
		}
		public void setPeso(float peso) {
			this.peso = peso;
		}
		public int getEdad() {
			return edad;
		}
		public void setEdad(int edad) {
			this.edad = edad;
		}
		public String getCita() {
			return cita;
		}
		public void setCita(String cita) {
			this.cita = cita;
		}
		public String getHistoria() {
			return historia;
		}
		public void setHistoria(String historia) {
			this.historia = historia;
		}
	//toString
		@Override
		public String toString() {
			return "Paciente [peso=" + peso + ", edad=" + edad + ", cita=" + cita + ", historia=" + historia + ", dni="
					+ dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + "]";
		}
	//MÉTODOS
		//Método para Guardar el Historial
		
		
		
}
