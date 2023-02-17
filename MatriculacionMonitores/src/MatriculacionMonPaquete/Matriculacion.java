package MatriculacionMonPaquete;

import java.util.LinkedList;

/*Este programa utiliza un objeto lock para sincronizar el acceso a la variable siguienteNIP.
 Cada vez que un estudiante quiere ser matriculado, adquiere una sección crítica bloqueando el objeto
  y luego es asignado un número de matrícula único. Después de un segundo, el estudiante libera la sección
   crítica y permite que el siguiente estudiante en la cola acceda a un puesto.
 */
public class Matriculacion {
    static int siguienteNIP = 130864;
    static final Object lock = new Object();

    static class Estudiante extends Thread {
        private int numeroMatriculacion;

        public Estudiante(int numeroMatriculacion) {
            this.numeroMatriculacion = numeroMatriculacion;
        }

        public void run() {
            synchronized (lock) {
                numeroMatriculacion = siguienteNIP++;
                System.out.println("El NIP " + numeroMatriculacion + " ha sido asignado al " + this.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("#### Programa de matriculación con monitores ####");
        System.out.println("#### Universidad Alfonso X El Sabio ####");
        System.out.println("Asignación de los números de inscripción:");

        LinkedList<Estudiante> colaAlumnos = new LinkedList<Estudiante>();
        for (int i = 0; i < 10; i++) {
            Estudiante estudiante = new Estudiante(i);
            colaAlumnos.add(estudiante);
            estudiante.setName("estudiante " + (i + 1));
            Thread.sleep(500);
            Estudiante siguienteEstudiante = colaAlumnos.poll();
            if (siguienteEstudiante != null) {
                siguienteEstudiante.start();
            }
        }
    }
}