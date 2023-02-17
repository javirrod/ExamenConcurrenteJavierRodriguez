package paqueteDados;

import java.util.Random;
import java.util.concurrent.Callable;

class TiradaDado implements Callable<Integer> {
    private static final Random RANDOM = new Random();
    private static final int NUM_CARAS = 6;//Se puede cambiar el número de caras del dado
    // para lanzar dados de otro número de caras


    @Override
    public Integer call() {
        return RANDOM.nextInt(NUM_CARAS) + 1;
    }
}