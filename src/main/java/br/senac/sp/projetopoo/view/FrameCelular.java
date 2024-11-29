package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FrameCelular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfModelo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCelular frame = new FrameCelular();
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
	public FrameCelular() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(25, 28, 53, 14);
		contentPane.add(lblNewLabel);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBackground(Color.LIGHT_GRAY);
		tfId.setBounds(55, 25, 107, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModelo.setBounds(25, 63, 53, 14);
		contentPane.add(lblModelo);
		
		tfModelo = new JTextField();
		tfModelo.setColumns(10);
		tfModelo.setBounds(88, 62, 186, 20);
		contentPane.add(tfModelo);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(25, 98, 53, 14);
		contentPane.add(lblMarca);
		
		JComboBox cbMarca = new JComboBox();
		cbMarca.setBounds(88, 96, 186, 22);
		contentPane.add(cbMarca);
		
		JLabel lblSistemaOperacional = new JLabel("Sistema Operacional:");
		lblSistemaOperacional.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSistemaOperacional.setBounds(25, 135, 133, 14);
		contentPane.add(lblSistemaOperacional);
		
		JComboBox cbSistemaOperacional = new JComboBox();
		cbSistemaOperacional.setBounds(168, 129, 186, 22);
		contentPane.add(cbSistemaOperacional);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalvar.setBounds(71, 192, 119, 36);
		contentPane.add(btnSalvar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setBounds(201, 192, 119, 36);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(330, 192, 119, 36);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoltar.setBounds(459, 192, 119, 36);
		contentPane.add(btnVoltar);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setOpaque(true);
		lbLogo.setBackground(new Color(192, 192, 192));
		lbLogo.setBounds(464, 28, 133, 105);
		contentPane.add(lbLogo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 239, 542, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
