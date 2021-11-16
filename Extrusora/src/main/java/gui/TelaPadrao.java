package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import main.java.controller.ConexaoArduino;
import main.java.controller.TratarDados;


import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;



public class TelaPadrao extends JFrame {

	private final JPanel painelPrincipal = new JPanel();
    private JLabel lblTotalContratosConcluidos, lblTotalContratos, lblTotalContratosAbertos;
    private TelaPadrao isto;
    private JDialog telaPai;
    private Map<Integer, Double> lista_medidas = new HashMap<>();
    private Map<Integer, Double> lista_medidas_global = new HashMap<>();

    private int contador_medidas = 0;
    private JPanel painelPlotter;
    ChartPanel chartPanel = null;
	  private ConexaoArduino arduino;
	  private JTextField entPorta;
	  private JTextField entVelocidade;
	  private 	JLabel lblStatusConexao;
	  private 	JButton btnDesconectar , btnConectar ;
	  private boolean conexao_global = false;
	  private JLabel lblDebug;
	  private JTextField entDebug;
	  private JButton btnEnviar;
	  private JLabel lblRetorno,lblVelocidadeMaxima, lblVelocidadeMinima;
	  private JLabel lblDiametro;
	  private JLabel lblDiametroFilamento;
	  private JLabel lblLeituraasd;
	  private JScrollPane scrollPane;
	  private JEditorPane textAreaMsg;
	  private JScrollPane scrollGrafico;
	  private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	  private	GraficoLinha linha = new GraficoLinha();
	  private JPanel painelStatusDinamico;
	  private JLabel lblStatus;
	  private JLabel lblModoDeOperao;
	  private JLabel lblModoOperacao;
	  private JLabel asdas;
	  private JLabel lblStatusDiametro;
	  private JPanel painelStatusFixo;
	  private JLabel lblStatus_1;
	  private JLabel lblMedidaPadro;
	  private JLabel lblMedidaPadrao;
	  private JLabel lasdasd;
	  private JLabel lblTolerancia;
	  private DefaultCategoryDataset dataSetGlobal = new DefaultCategoryDataset();
	  private JLabel asd;
	  private JLabel lblVelocidadeAtual;
	  private JLabel adasdasdasfqfq1q;
	  private JLabel lblDelayMedicao;
	  private JLabel cvbcvb;
	  private JLabel lblDelayCorrecao;
	  private JLabel asdasdasdasd;
	  private JLabel lblFatorAJuste;
	  private JButton btnMostrarGraficoFinal;
	  private JLabel vaa;
	  private JLabel lblContraMedida;

	  
	public TelaPadrao(Window janela_pai) {

		 isto = this;
		
		setResizable(true);
		setTitle("Extruder Machine");
		
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if ((e.getNewState() & isto.MAXIMIZED_BOTH) == isto.MAXIMIZED_BOTH) {
					// pega a resolucao da tela
					Toolkit tk = Toolkit.getDefaultToolkit();
					Dimension d = tk.getScreenSize();
					System.out.println("Screen width = " + d.width);
					System.out.println("Screen height = " + d.height);
					// pega o tamanho da barra de tarefas
					Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
					java.awt.Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
							.getMaximumWindowBounds();
					int taskBarHeight = scrnSize.height - winSize.height;
					System.out.printf("Altura: %d\n", taskBarHeight);
				}
			}
		});
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		System.out.println("Screen width = " + d.width);
		System.out.println("Screen height = " + d.height);
		
		// pega o tamanho da barra de tarefas
		Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskBarHeight = scrnSize.height - winSize.height;
		System.out.printf("Altura: %d\n", taskBarHeight);
		
		isto = this;
		setResizable(true);
		
		DisplayMode display =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		
		int display_x = display.getWidth();
		int display_y = display.getHeight();

		setBounds(0, 0, d.width, d.height - taskBarHeight);
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		painelPrincipal.setForeground(new Color(255, 255, 204));
		painelPrincipal.setBackground(new Color(0, 153, 153));
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);
		
		painelStatusDinamico = new JPanel();
		painelStatusDinamico.setBackground(Color.WHITE);
		painelStatusDinamico.setBounds(698, 6, 646, 228);
		painelPrincipal.add(painelStatusDinamico);
		painelStatusDinamico.setLayout(null);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(44, 5, 71, 19);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		painelStatusDinamico.add(lblStatus);
		
		lblModoDeOperao = new JLabel("Modo de Opera\u00E7\u00E3o:");
		lblModoDeOperao.setFont(new Font("Arial", Font.BOLD, 16));
		lblModoDeOperao.setBounds(6, 36, 150, 19);
		painelStatusDinamico.add(lblModoDeOperao);
		
		lblModoOperacao = new JLabel("Normal");
		lblModoOperacao.setFont(new Font("Arial", Font.BOLD, 16));
		lblModoOperacao.setBounds(168, 38, 105, 19);
		painelStatusDinamico.add(lblModoOperacao);
		
		asdas = new JLabel("Status Diametro:");
		asdas.setFont(new Font("Arial", Font.BOLD, 16));
		asdas.setBounds(28, 67, 128, 19);
		painelStatusDinamico.add(asdas);
		
		lblStatusDiametro = new JLabel("Grosso");
		lblStatusDiametro.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatusDiametro.setBounds(168, 69, 95, 19);
		painelStatusDinamico.add(lblStatusDiametro);
		
		asd = new JLabel("Velocidade Atual:");
		asd.setFont(new Font("Arial", Font.BOLD, 16));
		asd.setBounds(28, 98, 134, 19);
		painelStatusDinamico.add(asd);
		
		lblVelocidadeAtual = new JLabel("Grosso");
		lblVelocidadeAtual.setFont(new Font("Arial", Font.BOLD, 16));
		lblVelocidadeAtual.setBounds(168, 100, 95, 19);
		painelStatusDinamico.add(lblVelocidadeAtual);
		
		adasdasdasfqfq1q = new JLabel("Delay Medi\u00E7\u00E3o:");
		adasdasdasfqfq1q.setFont(new Font("Arial", Font.BOLD, 16));
		adasdasdasfqfq1q.setBounds(45, 129, 117, 19);
		painelStatusDinamico.add(adasdasdasfqfq1q);
		
		lblDelayMedicao = new JLabel("Grosso");
		lblDelayMedicao.setFont(new Font("Arial", Font.BOLD, 16));
		lblDelayMedicao.setBounds(168, 131, 95, 19);
		painelStatusDinamico.add(lblDelayMedicao);
		
		cvbcvb = new JLabel("Delay Corre\u00E7\u00E3o:");
		cvbcvb.setFont(new Font("Arial", Font.BOLD, 16));
		cvbcvb.setBounds(38, 162, 124, 19);
		painelStatusDinamico.add(cvbcvb);
		
		lblDelayCorrecao = new JLabel("Grosso");
		lblDelayCorrecao.setFont(new Font("Arial", Font.BOLD, 16));
		lblDelayCorrecao.setBounds(168, 162, 95, 19);
		painelStatusDinamico.add(lblDelayCorrecao);
		
		asdasdasdasd = new JLabel("Fator de Ajuste:");
		asdasdasdasd.setFont(new Font("Arial", Font.BOLD, 16));
		asdasdasdasd.setBounds(41, 193, 121, 19);
		painelStatusDinamico.add(asdasdasdasd);
		
		lblFatorAJuste = new JLabel("Grosso");
		lblFatorAJuste.setFont(new Font("Arial", Font.BOLD, 16));
		lblFatorAJuste.setBounds(168, 193, 95, 19);
		painelStatusDinamico.add(lblFatorAJuste);
		
		lblDiametro = new JLabel("Di\u00E2metro do filamento");
		lblDiametro.setBounds(375, 5, 167, 19);
		painelStatusDinamico.add(lblDiametro);
		lblDiametro.setForeground(Color.BLACK);
		lblDiametro.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblDiametroFilamento = new JLabel("0,00");
		lblDiametroFilamento.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDiametroFilamento.setBounds(317, 40, 310, 175);
		painelStatusDinamico.add(lblDiametroFilamento);
		lblDiametroFilamento.setForeground(new Color(0, 51, 0));
		lblDiametroFilamento.setFont(new Font("Arial", Font.PLAIN, 150));
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(52, 12, 59, 28);
		lblPorta.setFont(new Font("Arial", Font.BOLD, 16));
		painelPrincipal.add(lblPorta);
		
		entPorta = new JTextField();
		entPorta.setBounds(127, 15, 253, 28);
		entPorta.setText("COM5");
		painelPrincipal.add(entPorta);
		entPorta.setColumns(10);
		
		lblLeituraasd = new JLabel("Leitura:");
		lblLeituraasd.setBounds(10, 184, 74, 31);
		lblLeituraasd.setFont(new Font("Arial", Font.BOLD, 16));
		painelPrincipal.add(lblLeituraasd);
		
		
		 textAreaMsg = new JEditorPane();
		 textAreaMsg.setBackground(new Color(255, 255, 204));
		textAreaMsg.setEditable(false);
		

		scrollPane = new JScrollPane(textAreaMsg);
		scrollPane.setBackground(new Color(255, 255, 204));
		scrollPane.setBounds(75, 184, 399, 28);
		painelPrincipal.add(scrollPane);

		JLabel lblVelocidade = new JLabel("Velocidade:");
		lblVelocidade.setBounds(10, 51, 107, 28);
		lblVelocidade.setFont(new Font("Arial", Font.BOLD, 16));
		painelPrincipal.add(lblVelocidade);
		
		entVelocidade = new JTextField();
		entVelocidade.setBounds(127, 52, 253, 28);
		entVelocidade.setText("115200");
		entVelocidade.setColumns(10);
		painelPrincipal.add(entVelocidade);
		
		 btnConectar = new JButton("Conectar");
		 btnConectar.setBounds(127, 93, 107, 28);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirConexao();
			}
		});
		painelPrincipal.add(btnConectar);
		
		 btnDesconectar = new JButton("Desconectar");
		 btnDesconectar.setBounds(263, 91, 117, 32);
		 btnDesconectar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(conexao_global) {
		 			arduino.close();
		 			btnConectar.setEnabled(true);
		 			btnDesconectar.setEnabled(false);
		 		}
		 	}
		 });
		btnDesconectar.setEnabled(false);
		painelPrincipal.add(btnDesconectar);
		 
		 JLabel lblNewLabel = new JLabel("Status da Conex\u00E3o:");
		 lblNewLabel.setBounds(10, 140, 170, 32);
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		 painelPrincipal.add(lblNewLabel);
		
		 lblStatusConexao = new JLabel("Fechado");
		 lblStatusConexao.setBounds(177, 133, 203, 49);
		 lblStatusConexao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 painelPrincipal.add(lblStatusConexao);
		 
		 lblDebug = new JLabel("Debug:");
		 lblDebug.setBounds(18, 608, 101, 32);
		 lblDebug.setFont(new Font("Arial", Font.BOLD, 16));
		 painelPrincipal.add(lblDebug);
		 
		 entDebug = new JTextField();
		 entDebug.setBounds(93, 608, 266, 34);
		 painelPrincipal.add(entDebug);
		 entDebug.setColumns(10);
		 
		 btnEnviar = new JButton("Enviar");
		 btnEnviar.setBounds(369, 607, 91, 36);
		 btnEnviar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(conexao_global) {
		 			String texto = entDebug.getText();
		 			enviar(texto);

		 		}else {
		 			lblRetorno.setText("Nenhuma conexão encontrada!");
		 		}
		 	}
		 });
		 painelPrincipal.add(btnEnviar);
		 
		 lblRetorno = new JLabel("Retorno");
		 lblRetorno.setBorder(new LineBorder(new Color(0, 0, 0)));
		 lblRetorno.setBounds(18, 652, 442, 31);
		 lblRetorno.setForeground(new Color(153, 0, 0));
		 lblRetorno.setFont(new Font("Arial", Font.BOLD, 14));
		 painelPrincipal.add(lblRetorno);
		 
		  painelPlotter = new JPanel();
		 
		  scrollGrafico = new JScrollPane(painelPlotter);
		 painelPlotter.setLayout(new BorderLayout(0, 0));
		 scrollGrafico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 scrollGrafico.setBounds(10, 237, 1334, 359);
		 painelPrincipal.add(scrollGrafico);
		 
		 painelStatusFixo = new JPanel();
		 painelStatusFixo.setLayout(null);
		 painelStatusFixo.setBackground(Color.WHITE);
		 painelStatusFixo.setBounds(407, 16, 279, 140);
		 painelPrincipal.add(painelStatusFixo);
		 
		 lblStatus_1 = new JLabel("Status");
		 lblStatus_1.setFont(new Font("Arial", Font.BOLD, 16));
		 lblStatus_1.setBounds(44, 5, 71, 19);
		 painelStatusFixo.add(lblStatus_1);
		 
		 lblMedidaPadro = new JLabel("Medida Padr\u00E3o:");
		 lblMedidaPadro.setFont(new Font("Arial", Font.BOLD, 16));
		 lblMedidaPadro.setBounds(17, 36, 120, 19);
		 painelStatusFixo.add(lblMedidaPadro);
		 
		 lblMedidaPadrao = new JLabel("0.0");
		 lblMedidaPadrao.setFont(new Font("Arial", Font.BOLD, 16));
		 lblMedidaPadrao.setBounds(143, 36, 105, 19);
		 painelStatusFixo.add(lblMedidaPadrao);
		 
		 lasdasd = new JLabel("Toler\u00E2ncia:");
		 lasdasd.setFont(new Font("Arial", Font.BOLD, 16));
		 lasdasd.setBounds(51, 67, 86, 19);
		 painelStatusFixo.add(lasdasd);
		 
		 lblTolerancia = new JLabel("0.0");
		 lblTolerancia.setFont(new Font("Arial", Font.BOLD, 16));
		 lblTolerancia.setBounds(143, 67, 105, 19);
		 painelStatusFixo.add(lblTolerancia);
		 
		 JButton btnLimpar = new JButton("Limpar");
		 btnLimpar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		lista_medidas_global.putAll(lista_medidas);
		 		lista_medidas.clear();
		 		dataset.clear();
		 		 chartPanel = linha.getGraficoLinha(painelPlotter.getWidth()-100, painelPlotter.getHeight()-100, "Últimas 100 medidas");
		 		painelPlotter.remove(chartPanel);
		 		painelPlotter.repaint();
		 		painelPlotter.updateUI();
				 painelPlotter.add(chartPanel);
		 	}
		 });
		 btnLimpar.setBounds(1254, 611, 90, 28);
		 painelPrincipal.add(btnLimpar);
		 
		 btnMostrarGraficoFinal = new JButton("MostrarGraficoFinal");
		 btnMostrarGraficoFinal.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		lista_medidas = lista_medidas_global;
		 		plotar();
		 	}
		 });
		 btnMostrarGraficoFinal.setBounds(1209, 653, 135, 28);
		 painelPrincipal.add(btnMostrarGraficoFinal);
		 
		 JPanel painelStatusFixo_1 = new JPanel();
		 painelStatusFixo_1.setLayout(null);
		 painelStatusFixo_1.setBackground(Color.WHITE);
		 painelStatusFixo_1.setBounds(485, 608, 646, 75);
		 painelPrincipal.add(painelStatusFixo_1);
		 
		 JLabel xcvxc = new JLabel("Velocidade Maxima:");
		 xcvxc.setFont(new Font("Arial", Font.BOLD, 16));
		 xcvxc.setBounds(6, 6, 153, 19);
		 painelStatusFixo_1.add(xcvxc);
		 
		  lblVelocidadeMaxima = new JLabel("0.0");
		 lblVelocidadeMaxima.setFont(new Font("Arial", Font.BOLD, 16));
		 lblVelocidadeMaxima.setBounds(170, 6, 105, 19);
		 painelStatusFixo_1.add(lblVelocidadeMaxima);
		 
		 JLabel asdasdffg1123 = new JLabel("Velocidade Minima:");
		 asdasdffg1123.setFont(new Font("Arial", Font.BOLD, 16));
		 asdasdffg1123.setBounds(6, 37, 149, 19);
		 painelStatusFixo_1.add(asdasdffg1123);
		 
		  lblVelocidadeMinima = new JLabel("0.0");
		 lblVelocidadeMinima.setFont(new Font("Arial", Font.BOLD, 16));
		 lblVelocidadeMinima.setBounds(170, 37, 105, 19);
		 painelStatusFixo_1.add(lblVelocidadeMinima);
		 
		 vaa = new JLabel("Contra Medida:");
		 vaa.setFont(new Font("Arial", Font.BOLD, 16));
		 vaa.setBounds(303, 6, 117, 19);
		 painelStatusFixo_1.add(vaa);
		 
		 lblContraMedida = new JLabel("0.0");
		 lblContraMedida.setFont(new Font("Arial", Font.BOLD, 16));
		 lblContraMedida.setBounds(459, 6, 105, 19);
		 painelStatusFixo_1.add(lblContraMedida);
	
		
		 plotar();
	
		
		
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.setLocationRelativeTo(janela_pai);

		
		
		
	}
	
	
	public void atualizarRecebido(String texto) {
		
		
		java.awt.EventQueue.invokeLater(new Runnable() { 
		    public void run() { 
		    	textAreaMsg.setText("");
		    	
		    
		    	textAreaMsg.setText(texto);
		    	
		    	TratarDados tratamento = new TratarDados(texto);
		    	
		    	String status_filamento =  tratamento.tratar("Filamento: ", ";");
		    	String modo_operacao =  tratamento.tratar("Modo de Operação: ", ";");
		    	String diametro_padrao =  tratamento.tratar("Diametro Padrao: ", ";");
		    	String tolerancia =  tratamento.tratar("Tolerancia: ", ";");
		    	String velocidade_atual =  tratamento.tratar("Velocidade Atual: ", ";");
		    	String delay_medicao =  tratamento.tratar("Delay Medicao: ", ";");
		    	String delay_correcao =  tratamento.tratar("Delay Correcao: ", ";");
		    	String fator_ajuste =  tratamento.tratar("Remap Sensor: ", ";");
		    	String diametro = tratamento.tratar("Diametro Atual: ", ";");
		    	String velocidame_maxima = tratamento.tratar("Velocidade Maxima: ", ";");
		    	String velocidame_minima = tratamento.tratar("Velocidade Minima: ", ";");
		    	String contra_medida = tratamento.tratar("Contra Medida: ", ";");

		    	
		    	lblDiametroFilamento.setText(diametro);
		    	lblDiametroFilamento.repaint();
		    	
		    	
		    	lblModoOperacao.setText(modo_operacao);
		    	lblModoOperacao.repaint();

		    	
		    	
		    	lblStatusDiametro.setText(status_filamento.toUpperCase());
		    	lblStatusDiametro.repaint();

		    	
		    	
		    	lblMedidaPadrao.setText(diametro_padrao);
		    	lblMedidaPadrao.repaint();


		    	lblTolerancia.setText(tolerancia);
		    	lblTolerancia.repaint();


		    	lblVelocidadeAtual.setText(velocidade_atual);
		    	lblVelocidadeAtual.repaint();


		    	lblDelayMedicao.setText(delay_medicao);
		    	lblDelayMedicao.repaint();


		    	lblDelayCorrecao.setText(delay_correcao);
		    	lblDelayCorrecao.repaint();


		    	lblFatorAJuste.setText(fator_ajuste);
		    	lblFatorAJuste.repaint();
		    	
		    	lblVelocidadeMaxima.setText(velocidame_maxima);
		    	lblVelocidadeMaxima.repaint();

		    	lblVelocidadeMinima.setText(velocidame_minima);
		    	lblVelocidadeMinima.repaint();
		    	
		    	lblContraMedida.setText(contra_medida + "%");
		    	lblContraMedida.repaint();

		    	new Thread() {
		    		public void run() {
		    			lista_medidas.put(contador_medidas,Double.parseDouble(diametro.replace("[^0-9.]", "")));
				    	contador_medidas++;
				    	plotar();
				    	
				    	

		    		}
		    	}.start();
			
		    } 
		}); 
	}
	
	public void abrirConexao() {
		  int velocidade = Integer.parseInt(entVelocidade.getText().replace("[^0-9]", ""));
		  String porta = entPorta.getText().toUpperCase();
		  arduino = new ConexaoArduino(porta,velocidade);
		  arduino.setTelaPai(isto);
		  boolean conectado = arduino.iniciar();

		  if(conectado) {
			  lblStatusConexao.setText("Conectado!");
			  btnDesconectar.setEnabled(true);
			  btnConectar.setEnabled(false);
			  conexao_global = true;
			  
			 
		  }else
			  lblStatusConexao.setText("Erro ao Conectar!");

		  
	}
	
	public void plotar() {
			
			
			 for (Map.Entry<Integer,Double> pair : lista_medidas.entrySet()) {
				   
				    System.out.println(pair.getKey());
				    System.out.println(pair.getValue());
				    
					dataset.addValue(pair.getValue(), "",pair.getKey());
					
				}
			 
				
			linha.setDataset(dataset);
			
			if(chartPanel == null) {
			 chartPanel = linha.getGraficoLinha(painelPlotter.getWidth()-100, painelPlotter.getHeight()-100, "Últimas 100 medidas");
			 
			 painelPlotter.add(chartPanel);
			 
			}else
			 linha.atualizar();
			
			
			
	}
	
	public void enviar(String texto) {
		boolean enviado = arduino.enviaDados(texto);
		if(enviado) {
			lblRetorno.setText("Debug Enviado");
		}else {
			lblRetorno.setText("Erro ao enviar depuração");
		}
	}
}
