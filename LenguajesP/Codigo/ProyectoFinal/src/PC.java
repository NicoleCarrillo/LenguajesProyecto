
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

import javax.swing.SwingUtilities;
//Clase principal 
//Producer-Consumer

public class PC {

    public Producer producers [];
    public Consumer consumers [];
    public Buffer buffer;
    public static javax.swing.JProgressBar JProgressBarS;
    public static javax.swing.JSpinner jSpinner;

    public PC(int producerSize, int consumerSize) {

        this.producers = new Producer[producerSize];
        this.consumers = new Consumer[consumerSize];
    }

    //Inicializa los valores de la GUI -- Mandado a llamar por la GUI
    public void initProcess(int bufferSize, int time1,int time2, int rangoMenor, int rangoMayor, javax.swing.JProgressBar JPBar, javax.swing.JSpinner jSpinner4) {

        Buffer buffer = new Buffer(bufferSize, time1,time2);
        JProgressBarS = JPBar;
        JProgressBarS.setMaximum(100);
        JProgressBarS.setMinimum(0);

        jSpinner = jSpinner4;
        
        //Crea productores
        for (int i = 0; i < this.producers.length; i++) {
            this.producers[i] = new Producer(buffer, rangoMenor, rangoMayor);
            this.producers[i].start();
        }

        //Crea consumidores
        for (int j = 0; j < this.consumers.length; j++) {
            this.consumers[j] = new Consumer(buffer);
            this.consumers[j].start();
        }
    }

    
    //asigna los valores de la progress bar
    public static void setValue(final int progress, final int counter){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
            	JProgressBarS.setValue(progress);
                jSpinner.setValue(counter);
                
            }
        });
    }

    // Detiene todos los procesos
    public void stopAllThreads (){

        for (int i = 0; i < this.producers.length; i++) {
            this.producers [i].stopThread();
        }

        for (int j = 0; j < this.consumers.length; j++) {
            this.consumers [j].stopThread();
        }

    }
    
}
