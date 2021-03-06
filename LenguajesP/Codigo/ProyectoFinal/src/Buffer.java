import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private Operations [] buffer;
    static int contConsumer, contProducer; //
    public int bufferSize, time1,time2; //
    static int id, count = 1;
    static Random random = new Random();
    
    Buffer(int maxSize, int maxTime1,int maxTime2) {
        this.buffer = new Operations [maxSize];      
        this.bufferSize = maxSize;
        this.time1 = maxTime1;
        this.time2 = maxTime2;
    }

        //PARA AGARRAR LAS OPERACIONES QUE PRODUJO PRODUCER
    synchronized Operations consume() {
        Operations product = null;
        contConsumer = getRandomNumber(0, this.buffer.length);

        
        //SI EL BUFFER ESTA VACIO VA A ESPERAR A QUE PRODUZCA
        while (this.buffer[contConsumer] == null) {
            try {
                wait(this.time2);
                contConsumer = getRandomNumber(0, this.buffer.length);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        product = this.buffer[contConsumer];
        this.buffer[contConsumer] = null;
        count--;
        GUIFrame.removeTasks(product.operation);
        PC.setValue((int) Math.round((count * 100)/this.bufferSize), id); // BARRA DEL PROGRESO CUANDO VAYA PROCESANDO
        notifyAll();
        return product;
        
        }
    
        //PRODUCE OPERACIONES SI ESTA VACIO
    synchronized void produce(Operations product) {
        contProducer = getRandomNumber(0, this.buffer.length);
        while(this.buffer[contProducer] != null) {
            try {
                wait(this.time1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.buffer[contProducer] = product;
        product.setID(id);
        id++;
        count++;
        GUIFrame.nextTable(product.ID, product.operation); //AGREGA A LA COLA DE LA TABLA DE OPERACIONES PARA RESOLVERLO
        notifyAll();
    }
    
    synchronized static void print(String string) {
        System.out.println(string);
    }

    synchronized Operations [] genBufferArray (int size){
        return this.buffer;
    }
    
    public static int getRandomNumber(int min, int max){
        return random.nextInt(max - min) + min;
    }
}
