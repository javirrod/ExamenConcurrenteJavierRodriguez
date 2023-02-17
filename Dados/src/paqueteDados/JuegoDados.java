package paqueteDados;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JuegoDados {
    //private static final int NUM_CARAS = 6; // No es necesario, la creo en TiradaDado


    public static int[] tiradaDados(int numDados) {
        ExecutorService executor = Executors.newFixedThreadPool(numDados);
        Future<Integer>[] futures = new Future[numDados];

        for (int i = 0; i < numDados; i++) {
            futures[i] = executor.submit(new TiradaDado());
        }

        int[] resultados = new int[numDados];
        for (int i = 0; i < numDados; i++) {
            try {
                resultados[i] = futures[i].get();// Recoge los valores de todos los dados
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return resultados;
    }

}