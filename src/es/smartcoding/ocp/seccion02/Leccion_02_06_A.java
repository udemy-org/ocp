/**
 * 
 */
package es.smartcoding.ocp.seccion02;

/**
 * 
 * @author pep
 * 
 *         A las clases anidadas que no son estáticas se las llama clases internas. Hay cuatro tipos de clases anidadas:
 *         
 *         1. Una clase interna es una clase no estática definida al mismo nivel que un miembro de instancia (inner class)
 *         
 *         2. Una clase interna local es una clase no estática definida a nivel de método.
 *         
 *         3. Una clase interna anónima es una clase especial de clase interna local que no tiene nombre.
 *         
 *         4. Por último, una clase anidada estática es una clase estática definida al mismo nivel que una miembro de clase.
 *         
 *         Básicamente las clases anidadas son clases de ayuda que definimos dentro de otra clase fáciles de crear y que sólo se utilizarána en ese contexto.
 *         
 *         Aunque si no se utilizan adecuadamente, pueden hacer el código más difícil de leer.
 *         
 */
public class Leccion_02_06_A {
	
	private String msg = "OCP";
	
	private interface InnerInterface {
		public abstract void f();
	}
	
	/*
	 * Ejemplo de clase interna
	 * 
	 * Se crean las clases Leccion_01_06.class y Leccion_01_06$InnerClass.class
	 */
	 protected class InnerClass implements InnerInterface {
		private String msg = "Local OCP";
		public static final int LIMITE = 3;
		public void f() {
			for (int i = 0; i < LIMITE; i++) {
				System.out.println(Leccion_02_06_A.this.msg);
			}
		}
	}

	public static void main(String[] args) {
		Leccion_02_06_A l17 = new Leccion_02_06_A();
		// Como InnerClass es un miembro de la clase Leccion_01_06 se necesita una instancia de esa clase para invocar al constructor de InnerClass.
		Leccion_02_06_A.InnerInterface ic = l17.new InnerClass();
		ic.f();	
	}

}