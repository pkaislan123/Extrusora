package main.java.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jssc.SerialPort;
import jssc.SerialPortException;
import main.java.gui.TelaPadrao;



	public class ConexaoArduino  {
		private OutputStream serialOut;
		  private int taxa;
		  private String portaCOM;
		  SerialPort serialPort ;
		  public TelaPadrao telaPai;
		  public ConexaoArduino(String portaCOM, int taxa) {
		    this.portaCOM = portaCOM;
		    this.taxa = taxa;
		  }     
		 
		 
		  public boolean iniciar() {
			  serialPort = new SerialPort(portaCOM);
			  try {
				//Open serial port
				serialPort.openPort();

				//Set params.
				serialPort.setParams(taxa, 8, 1, 0);

				new Thread() {
					@Override
					public void run() {
						while(true) {
							try {
								 try {
									//Thread.sleep(45);
									 Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
									String texto;
									texto = serialPort.readString();//Read 10 bytes from serial port

								//serialPort.closePort();//Close serial port
								((TelaPadrao) telaPai).atualizarRecebido(texto);
							
							} catch (SerialPortException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				}.start();
				
				  return true;

				}
				catch (SerialPortException ex) {
				System.out.println(ex);
				return false;
				}
		}
		 
		  /**
		   * Método que fecha a comunicação com a porta serial
		   */
		  public void close() {
			  try {
				serialPort.closePort();
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		 
		  /**
		   * @param opcao - Valor a ser enviado pela porta serial
		   */
		  public boolean enviaDados(String texto){
		    try {
				serialPort.writeBytes(texto.getBytes());
			     return true;

			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   return false;

			}


		  } 
		  
		 
		  public void setTelaPai(TelaPadrao telaPai) {
			  this.telaPai = telaPai;
		  }
		  
	}


