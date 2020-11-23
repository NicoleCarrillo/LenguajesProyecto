//PROYECTO 

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    
	Buffer buffer;
	int rangoMenor;
	int rangoMayor;
	boolean ejecucion;
    
    Producer(Buffer buffer, int rMa, int rMe) {
        this.buffer = buffer;
        this.rangoMayor = rMa;
        this.rangoMenor = rMe;
        this.ejecucion = true;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        
        while(this.ejecucion) {
        	
		// CREAMOS LA OPERACION -> NUMEROS RANDOM, OPERACION RANDOM Y LE DAMOS FORMATO DE SCHEME 
        	Operations objetoScheme = new Operations(rangoMayor,rangoMenor);
        	int arreglo[]=objetoScheme.getRandomNumbers();
        	String operacionRandom = objetoScheme.getRandomOperation();
        	String formatOperation = objetoScheme.getFormatOperation(arreglo,operacionRandom);
        	objetoScheme.setOperation(formatOperation);
        	
        	//SE PRODUCEN LAS OPERACIONES -> SE MANDAN AL BUFFER 
        	this.buffer.produce(objetoScheme);
            	Buffer.print("Producer produced " + "ID -> " + objetoScheme.getID() + " Operation -> " + objetoScheme.getOperation());

            try {
                Thread.sleep(this.buffer.time);
            } catch (InterruptedException ex) {
                System.out.println("ERROR AT PRODUCER " + ex);
            }
        	
        }
        
    }
    
	// PARA PARAR CON EL BOTON STOP 
    public void stopThread() {
    	this.ejecucion = false;
    }
    
}
