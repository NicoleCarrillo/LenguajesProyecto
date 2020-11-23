import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sdegante
 */
@SuppressWarnings("serial")



public class GUIFrame extends javax.swing.JFrame {
	
	int totalProductor;
	int totalConsumidor;
	int tiempoProductor;
	int tiempoConsumidor;
	int tamBuffer;
	int rangoMenor;
	int rangoMayor;
	PC objeto;

    
    public GUIFrame() {
    	 totalProductor = 0;
    	 totalConsumidor = 0;
    	 tiempoProductor = 0;
    	 tiempoConsumidor = 0;
    	 tamBuffer = 0;
    	 rangoMenor = 0;
    	 rangoMayor = 0;
    	 objeto=new PC(totalProductor, totalConsumidor);
        initComponents();
    }

	//ELIMINA LAS TAREAS DE LA TABLA DE LA GUI 
    public static void removeTasks(String exp){
        DefaultTableModel table1 =  (DefaultTableModel) GUIFrame.jTable1.getModel();  
         for(int i=table1.getRowCount()-1;i>=0;i--){
            if(((String)table1.getValueAt(i, 1)).equals(exp)){
                table1.removeRow(i);
                break;
            }
        }
    }
    
	//TABLA DE LAS TAREAS POR HACER 	
    public static void nextTable(int id, String exp){
        DefaultTableModel table1 = (DefaultTableModel) GUIFrame.jTable1.getModel();
        Object row [] = new Object [2];
        row [0] = id;
        row [1] = exp;
        table1.addRow(row);
        
    }
	
	//TABLA DE LOS RESULTADOS 
    public static void tableComplete(int id, String exp, double value){
        DefaultTableModel table2 =  (DefaultTableModel) GUIFrame.jTable2.getModel();
        Object row[] = new Object[3];
        row[0] = id;
        row[1] = exp;
        row[2] = value;
        table2.addRow(row);
        
    }
   
	//METODO QUE INICIALIZA TODOS LOS COMPONENTES 
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSpinner4 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1.setText(" INICIAR");
        jButton2.setText("TERMINAR");
        
        //BUTTON STOP -> REINICIA TODOS LOS VALORES 
        jButton2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		objeto.stopAllThreads();
           	 	JOptionPane.showMessageDialog(null,"PROCESO FINALIZADO CON EXITO", "INFO", JOptionPane.INFORMATION_MESSAGE); 
           	 	jSpinner1.setValue((Integer) 0);
           	 	jSpinner2.setValue((Integer) 0);
           	 	jTextField1.setText("0");
           	 	jTextField2.setText("0");
           	 	jTextField3.setText("0");
           	 	jTextField4.setText("0");
           	 	jSpinner3.setValue((Integer) 0);
        	}
    	});
        
        //BUTTON INIT -> JALA TODOS LOS VALORES DE LA GUI 
        jButton1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
			
			//VALIDACIONES
        		
        		totalProductor = (Integer)jSpinner1.getValue();
        		if(totalProductor <= 0 || totalProductor > 10){
        			JOptionPane.showMessageDialog(null,"El número de productores debe estar en el rango 1-10", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        	
        		
        		totalConsumidor = (Integer)jSpinner2.getValue();
        		if(totalConsumidor <= 0 || totalConsumidor > 10){
        			JOptionPane.showMessageDialog(null,"El número de consumidores debe estar en el rango 1-10", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        	
        		String temp1 = jTextField1.getText();
        		if(temp1.equals("")) {
        			JOptionPane.showMessageDialog(null,"Faltan argumentos en campo Tiempo Productor ", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}else {
        			tiempoProductor =Integer.parseInt(temp1);
            		if(tiempoProductor < 0 || tiempoProductor > 10000){
            			JOptionPane.showMessageDialog(null,"Tiempo de espera para productor antes de su siguiente ciclo debe estar en el rango 0 ms a 10,000 ms", "ERROR", JOptionPane.ERROR_MESSAGE);
            			return;
            		}
        		}
        	
        		String temp2 = jTextField2.getText();
        		if(temp2.equals("")) {
        			JOptionPane.showMessageDialog(null,"Faltan argumentos en campo Tiempo Consumidor ", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}else {
        			tiempoConsumidor =Integer.parseInt(temp2);
            		if(tiempoConsumidor < 0 || tiempoConsumidor > 10000){
            			JOptionPane.showMessageDialog(null,"Tiempo de espera para consumidor antes de su siguiente ciclo debe estar en el rango 0 ms a 10,000 ms", "ERROR", JOptionPane.ERROR_MESSAGE);
            			return;
            		}
        		}
        		
        		String temp3 = jTextField3.getText();
        		if(temp3.equals("")) {
        			JOptionPane.showMessageDialog(null,"Faltan argumentos en campo Buffer ", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}else {
        			tamBuffer =Integer.parseInt(temp3);
            		if(tamBuffer <= 0 || tamBuffer > 100){
            			JOptionPane.showMessageDialog(null,"El tamaño del buffer debe ser mayor a 0 - 100", "ERROR", JOptionPane.ERROR_MESSAGE);
            			return;
            		}
        		}
        		
        	
        		String temp4 = jTextField4.getText();
        		if(temp4.equals("")) {
        			JOptionPane.showMessageDialog(null,"Faltan argumentos en campo Rango Menor ", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}else {
        			rangoMenor =Integer.parseInt(temp4);
            		if(rangoMenor < 0 || rangoMenor > 10){
            			JOptionPane.showMessageDialog(null,"Rango de valores para las operaciones en scheme: [0, 9]", "ERROR", JOptionPane.ERROR_MESSAGE);
            			return;
            		}
        		}
        		
        		
        		rangoMayor = (Integer)jSpinner3.getValue();
        		if(rangoMayor < 0 || rangoMayor > 10){
        			JOptionPane.showMessageDialog(null,"Rango de valores para las operaciones en scheme: [0, 9]", "ERROR", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		if(rangoMenor > rangoMayor) {
        			JOptionPane.showMessageDialog(null,"Rangos invertidos, revisar inputs", "WARNING", JOptionPane.INFORMATION_MESSAGE);
        			return;
        		}
        		
        		System.out.println(totalProductor);
        		System.out.println(totalProductor);
        		System.out.println(tiempoProductor);
        		System.out.println(tiempoConsumidor);
        		System.out.println(tamBuffer);
        		System.out.println(rangoMenor);
        		System.out.println(rangoMayor);
        		
			// INICIALIZA EL PRODUCTOR-CONSUMIDOR -> ORQUESTADOR DEL PROYECTO 
        		objeto = new PC(totalProductor, totalConsumidor);
        		objeto.initProcess(tamBuffer, 1000, rangoMenor, rangoMayor, jProgressBar1, jSpinner4);
                
                jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"ID", "Operación"}));
                jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"ID", "OP", "Res"}));
                
                JOptionPane.showMessageDialog(null,"PROCESO INICIALIZADO CON EXITO", "INFO", JOptionPane.INFORMATION_MESSAGE); 		
        	} 
        });

	    
	    //METODOS PROPORCIONADOS POR EL PROFE
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel5.setText("Tiempo de Espera (ms)");

        jLabel6.setText("Rango de Valores (n, m)");

        jLabel1.setText("Productores");

        jLabel2.setText("Consumidores");

        jLabel3.setText("Tamaño del Buffer");

        jLabel4.setText("Cantidad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner2)
                    .addComponent(jTextField3)
                    .addComponent(jSpinner1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuración", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        		 new Object [][] {
                 },
                 new String [] {
                     "ID", "Producto"
                 }
             ) {
                 boolean[] canEdit = new boolean [] {
                     true, false
                 };

                 public boolean isCellEditable(int rowIndex, int columnIndex) {
                     return canEdit [columnIndex];
                 }
             });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
        		 new Object [][] {

                 },
                 new String [] {
                     "ID", "Operacion", "Resultado"
                 }
             ) {
                 boolean[] canEdit = new boolean [] {
                     false, true, false
                 };

                 public boolean isCellEditable(int rowIndex, int columnIndex) {
                     return canEdit [columnIndex];
                 }
             });
        
        jScrollPane2.setViewportView(jTable2);

        jLabel7.setText("Tareas por hacer");

        jLabel8.setText("Tareas realizadas");

        jProgressBar1.setValue(0);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jSpinner4))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Procesos", jPanel3);

        jButton1.setFont(new java.awt.Font("Courier New", 1, 24)); 
        jButton1.setForeground(new java.awt.Color(0, 102, 51));
        jButton1.setText("INICIAR");
        
        jButton2.setFont(new java.awt.Font("Courier New", 1, 24)); 
        jButton2.setForeground(Color.RED);
        jButton2.setText("TERMINAR");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(6)
        					.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
        					.addGap(121)
        					.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        					.addGap(49))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(39, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
        			.addGap(23))
        );
        getContentPane().setLayout(layout);

        pack();
    }

 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFrame().setVisible(true);
            }
        });
    }

    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    
}
