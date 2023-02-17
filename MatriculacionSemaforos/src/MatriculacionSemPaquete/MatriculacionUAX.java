/*Este programa utiliza un semáforo de 3 permisos para simular la asignación de puestos de matrícula.
 Cuando un estudiante llega, solicita un permiso del semáforo y cuando lo obtiene, es asignado un número
  de matrícula único. Después de un segundo, el estudiante libera el permiso y permite que el siguiente
   estudiante en la cola acceda a un puesto.
 */

package MatriculacionSemPaquete;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
public class MatriculacionUAX {
    static int siguienteNIP = 130864;
    static Semaphore mostradores = new Semaphore(3);//3 mostradores
    private static final Semaphore concurrencia = new Semaphore(1);

    public static class Estudiante extends Thread {
        int numeroMatriculacion = 1;

        public Estudiante(int numeroMatriculacion) {
            this.numeroMatriculacion = numeroMatriculacion;
        }

        public void run() {
            try {
                mostradores.acquire();
                concurrencia.acquire();
                numeroMatriculacion = siguienteNIP++;
                System.out.println("El NIP " + numeroMatriculacion + " ha sido asignado al " + this.getName());
                Thread.sleep(500);
                concurrencia.release();
                mostradores.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            System.out.println("#### Programa de matriculación con semáforos ####");
            System.out.println("#### Universidad Alfonso X El Sabio ####");
            System.out.println("Asignación de los números de inscripción:");

/*COLAS
// Para añadir un alumno a la cola
            Student student = new Student();
            colaAlumnos.add(student);
// Para obtener el siguiente alumno de la cola
            Student siguientestudent = colaAlumnos.poll();
*/
            LinkedList<Estudiante> colaAlumnos = new LinkedList<Estudiante>();
            for (int i= 0;i<10;i++){
                Estudiante estudiante = new Estudiante(i);
                colaAlumnos.add(estudiante);
                estudiante.setName("estudiante " + (i + 1));
                Thread.sleep(500);
                Estudiante siguientestudent = colaAlumnos.poll();
                siguientestudent.start();
            }
/*Se puede hacer con un for más normal y creando un array de estudiantes.
            /* Student[] students = new Student[10];
            for (int i = 0; i < 10; i++) {
                students[i] = new Student(i);
                students[i].setName("Student " + (i + 1));
                students[i].start();
            }*/
        }
    }
}