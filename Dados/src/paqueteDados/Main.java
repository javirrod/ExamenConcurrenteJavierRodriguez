package paqueteDados;

public class Main {
    public static void main(String[] args) {
        System.out.println("#### Programa de lanzamiento de dados ####");
        int[] resultados = JuegoDados.tiradaDados(9);// Crea numDados procesos que lo que hacen es simular tirar un dado.
        //En este caso, 9 dados.


        for (int i = 0; i < resultados.length; i++) {
            System.out.println("Dado " + (i + 1) + ": " + resultados[i]);// devuelve el array con los valores obtenidos.
        }
    }
}
