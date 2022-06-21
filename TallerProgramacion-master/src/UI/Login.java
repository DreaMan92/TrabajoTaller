package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.ConexionDB;
import System.GestorOperaciones;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.GridLayout;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		setTitle("     Acceso Taller Hermanos Gutierrez S.A.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/imagenes/llave-inglesa.png")));	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 308);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		
	
		contentPane.setLayout(new GridLayout(3, 3, 0, 10));
		
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setToolTipText("Usuarios con acceso: Mecanicos y Jefe.");
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
	
		JLabel lblContra = new JLabel("Contrase\u00F1a");
		lblContra.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblContra);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setToolTipText("Contrase\u00f1a para su usuario.");
		contentPane.add(passwordField);
		
		JButton botonLogin = new JButton("Acceder");
		botonLogin.addActionListener(this);
		contentPane.add(botonLogin);
	}
	
	@Override
	public void actionPerformed(ActionEvent click) {
		String usuario = txtUsuario.getText();
		String passwd = passwordField.getText();
		if(new ConexionDB().conectarOracle(usuario,passwd)!=null) {
			try {
				GestorOperaciones gestor = new GestorOperaciones(usuario,passwd);
				Inicio programa = new Inicio(gestor);
				programa.setVisible(true);
				this.dispose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Contrase\u00F1a incorrecta", "Liadon", JOptionPane.ERROR_MESSAGE);
		} 
	}

}
