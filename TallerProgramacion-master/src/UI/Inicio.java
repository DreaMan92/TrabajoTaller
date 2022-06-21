package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import System.GestorOperaciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Inicio extends JFrame implements ActionListener{
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btnSalir ;
	private GestorOperaciones gestor;
	
	private JLabel etiqueta;

	public Inicio(GestorOperaciones gestor) {
		this.gestor=gestor;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/imagenes/llave-inglesa.png")));	
		setTitle("     Hermanos Gutierrez S.A.");
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		iniciarComponenetes();		
		
	}
	private void iniciarComponenetes() {
		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));
		JPanel panel_opciones = new JPanel();
		panel_opciones.setMaximumSize(new Dimension(300,300));
		panel_opciones.setLayout(new GridLayout(0,1,10,10));
		panel_opciones.setBorder(new EmptyBorder(50,50,50,50));
		panel.add(panel_opciones,BorderLayout.CENTER);
		
		JPanel panel_norte = new JPanel(new FlowLayout());
		panel_norte.setBorder(new LineBorder(Color.BLACK,2));
		panel.add(panel_norte,BorderLayout.NORTH);

		btn1 = new JButton();;
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.setBackground(Color.WHITE);
		btn1.setText("Reparar un veh\u00edculo");
		btn1.setToolTipText("Reparaci\u00f3n de un vehiculo ya registrado.\nPara reparar un veh\u00edculo nuevo registrelo anteriormente.");
		btn1.addActionListener(this);
		
		panel_opciones.add(btn1);
		
		btn2 = new JButton();
		btn2.setText("Registrar veh\u00edculo");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.setBackground(Color.WHITE);
		btn2.addActionListener(this);
		panel_opciones.add(btn2);
		
		btn3 = new JButton();
		btn3.setText("Ver Facturas");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn3.setBackground(Color.WHITE);
		btn3.addActionListener(this);
		panel_opciones.add(btn3);
		
		btn4 = new JButton();
		btn4.setText("Pedir Piezas");
		btn4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btn4.setBackground(Color.WHITE);
		btn4.addActionListener(this);
		panel_opciones.add(btn4);
		
		btn5 = new JButton();
		btn5.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn5.setText("Registrar Cliente");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn5.setBackground(Color.WHITE);
		btn5.addActionListener(this);
		panel_opciones.add(btn5);
		
		
		btnSalir = new JButton();
		btnSalir.setText("Cerrar Sesi\u00f3n");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(this);
		panel_norte.add(btnSalir);
		
		/*-----------*/
		etiqueta= new JLabel("");
		panel.add(new JPanel(new FlowLayout()).add(etiqueta),BorderLayout.SOUTH);
		etiqueta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Reparar un veh\u00edculo" :
			ElegirVehiculoReparar nuevo = new ElegirVehiculoReparar(gestor);
			nuevo.setVisible(true);
			this.dispose();
			break;
			
		case "Registrar veh\u00edculo" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Ver Facturas" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Pedir Piezas" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Registrar Cliente" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Cerrar Sesi\u00f3n" :
			Login login = new Login();
			login.setVisible(true);
			dispose();
			
			break;
		
		
		}
	}
}