package repaso;

public class Gato extends Mascota{
	//Atributos
		private boolean estaMaullando;
	//Constructor
		public Gato(String nombre, int edad, boolean estaMaullando) {
			super (nombre, edad);
			this.estaMaullando=estaMaullando;
			this.id=this.generarId();//El id lo calcularemos aquí con el método generarId
		}
	//Getters y Setters

		public boolean isEstaMaullando() {
			return estaMaullando;
		}

		public void setEstaMaullando(boolean estaMaullando) {
			this.estaMaullando = estaMaullando;
		}
	//toString
		@Override
		public String toString() {
			return "Gato [estaMaullando=" + estaMaullando + ", edad=" + edad + ", nombre=" + nombre + ", id=" + id
					+ "]";
		}
	//Sobreescribimos el método generarId
		public String generarId() {
			return"G"+ this.getNombre().substring(0,2)+(int)(1+Math.random()*20);
		}
	
	//Main de prueba
		public static void main(String[] args) {
			Gato g=new Gato("Missi", 5, true);
			System.out.println(g.generarId());
		}


		
}
