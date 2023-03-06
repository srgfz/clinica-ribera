package repaso;

public class Perro extends Mascota{
	//Atributos
	private boolean estaLadrando;
	//Constructor
	public Perro(String nombre, int edad, boolean estaLadrando) {
		super (nombre, edad);
		this.estaLadrando=estaLadrando;
		this.id=this.generarId();//El id lo calcularemos aquí con el método generarId
	}
	
	//Getters y Setters
	public boolean isEstaLadrando() {
		return estaLadrando;
	}
	public void setEstaLadrando(boolean estaLadrando) {
		this.estaLadrando = estaLadrando;
	}
	@Override
	public String toString() {
		return "Perro [estaLadrando=" + estaLadrando + ", edad=" + edad + ", nombre=" + nombre + ", id=" + id + "]";
	}
	//Sobreescribimos el metodo de generar id
	@Override
	public String generarId() {
		return"P"+ this.getNombre().substring(0,2)+(int)(1+Math.random()*25);
	}
	
	

}
