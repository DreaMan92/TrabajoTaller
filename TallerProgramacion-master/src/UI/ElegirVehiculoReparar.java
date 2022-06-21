package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.GestorOperaciones;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import Modelos.Factura;
import Modelos.Vehiculo;

public class ElegirVehiculoReparar extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JList listaVehiculos;
	private JList listaVehiculosEnRep;
	private GestorOperaciones gestor;
	/**
	 * Create the frame.
	 */
	public ElegirVehiculoReparar(GestorOperaciones gestor) {
		this.gestor=gestor;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/imagenes/llave-inglesa.png")));	
		setTitle("     Hermanos Gutierrez S.A.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAtras = new JButton("Atras");
		panel.add(btnAtras);
		btnAtras.setActionCommand("Atras");
		btnAtras.addActionListener(this);
		
		JButton btnReparar = new JButton("Reparar");
		panel.add(btnReparar);
		btnReparar.setActionCommand("Reparar");
		btnReparar.addActionListener(this);
		listaVehiculos = new JList(gestor.getCoches().toArray());
		listaVehiculos.setSelectedIndex(0);
		listaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaVehiculos.setToolTipText("Veh\u00edculos registrados.\nElija uno y pulse en el boton Reparar para proceder.");
		JScrollPane panelVehiculos = new JScrollPane(listaVehiculos);
		panelVehiculos.setViewportBorder(new TitledBorder(null, "Vehiculos Registrados", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelVehiculos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(panelVehiculos, BorderLayout.CENTER);
		
		listaVehiculosEnRep = new JList(gestor.vehiculosEnReparacion().toArray());
		listaVehiculosEnRep.setEnabled(false);
		listaVehiculosEnRep.setToolTipText("Veh\u00edculos que se han empezado a reparar, pero no est\u00e1n terminados.");
		
		JScrollPane panelVehiculosEnRep = new JScrollPane(listaVehiculosEnRep);
		panelVehiculosEnRep.setViewportBorder(new TitledBorder(null, "Vehiculos en Reparacion", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(panelVehiculosEnRep, BorderLayout.EAST);
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent eleccion) {
		switch (eleccion.getActionCommand()) {
		case "Atras":
			Inicio inicio = new Inicio(gestor);
			inicio.setVisible(true);
			dispose();
			break;
			
		case "Reparar":
			Factura fac = null;
			try {
				fac = gestor.buscarFacturaActualCoche((Vehiculo) listaVehiculos.getSelectedValue());
				VentanaReparacion ventana =new VentanaReparacion(gestor,fac);
				ventana.setVisible(true);
				dispose();
			} catch (SQLException e) {
				
			}
			
			break;
		}
	}
}
