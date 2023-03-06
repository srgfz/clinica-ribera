package repaso;

import java.util.ArrayList;

public class Programa {
	//Atributos
		public ArrayList<Mascota> mascota;
	//Constructor
		public Programa() {
		super();
		this.mascota = new ArrayList<>();
	}
	//Getters y Setters
		public ArrayList<Mascota> getMascota() {
			return mascota;
		}



		public void setMascota(ArrayList<Mascota> mascota) {
			this.mascota = mascota;
		}
	//Método que devuelve el nombre de la mascota de mayor edad que esté ladrando o maullando
		public String mayorEdad() {
			String nombreMayor="";
			int edadMayor=0;
			if(this.getMascota().size()>0)
				edadMayor=this.getMascota().get(0).edad;
			for (Mascota x : this.getMascota()) {
				if(x instanceof Perro) { //Con instanceof veo de qué subclase es
					Perro p = (Perro) x;
					if(p.isEstaLadrando())
					if(edadMayor<p.edad) {
						nombreMayor=p.nombre;
						edadMayor=p.edad;
					}
				}else {	
						Gato g = (Gato) x;
						if(g.isEstaMaullando())
						if(edadMayor<g.edad) {
							nombreMayor=g.nombre;
							edadMayor=g.edad;
						
				}
			}
			}
			return nombreMayor;
		}
		
	//Main del programa:
		public static void main(String[] args) {
			Programa p = new Programa();

			p.mascota.add(new Gato("Missi", 5, true));
			p.mascota.add(new Gato("Coco", 10, true));
			p.mascota.add(new Gato("Gato", 12, false));
			p.mascota.add(new Perro("Selby", 6, false));
			p.mascota.add(new Perro("Toby", 13, false));
			p.mascota.add(new Perro("Yacky", 11, true));
			
			System.out.println(p.mascota);
			System.out.println(p.mayorEdad());
		}
		
		

}
