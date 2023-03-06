package clinicaRibera;

public class Profesional extends Persona{
	//Atributos
		private String especialidad;
		private int turno;
		
		private static final int MAÑANA=0;
		private static final int TARDE=1;
		private static final int GUARDIA=2;
	//Constructor
		//Por defecto
		public Profesional() {
			super();
			this.especialidad = "";
			this.turno = 0;
		}
		public Profesional(String dni, String nombre, String apellidos, String telefono, String especialidad, int turno) {
			super(dni, nombre, apellidos, telefono);
			this.especialidad = especialidad;
			this.turno = turno;
		}
	//Getters y Setters
		public String getEspecialidad() {
			return especialidad;
		}
		public void setEspecialidad(String especialidad) {
			this.especialidad = especialidad;
		}
		public int getTurno() {
			return turno;
		}
		public void setTurno(int turno) {
			this.turno = turno;
		}
	//toString
		@Override
		public String toString() {
			return "Profesional [especialidad=" + especialidad + ", turno=" + turno + ", dni=" + dni + ", nombre="
					+ nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + "]";
		}
		
		
	
	
}
