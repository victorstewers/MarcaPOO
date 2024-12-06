package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senac.sp.projetopoo.dao.CelularDaoHib;
import br.senac.sp.projetopoo.dao.ConnectionFactory;
import br.senac.sp.projetopoo.dao.EMFactory;
import br.senac.sp.projetopoo.dao.InterfaceDao;
import br.senac.sp.projetopoo.dao.MarcaDAO;
import br.senac.sp.projetopoo.modelo.Celular;
import br.senac.sp.projetopoo.modelo.Marca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class FrameCelular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfModelo;
	private Celular celular;
	private InterfaceDao<Celular> dao;
	private JTable table;
	private JTextField txtBuscar;
	private MarcaDAO marcaDao;
	private ConnectionFactory conn;

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
	 * @throws SQLException 
	 */
	public FrameCelular() throws SQLException {
		
		dao = new CelularDaoHib(EMFactory.getEntityManager());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 587);
		setLocationRelativeTo(null);
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
		tfModelo.setBounds(88, 62, 328, 20);
		contentPane.add(tfModelo);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(25, 98, 53, 14);
		contentPane.add(lblMarca);
		
		JComboBox cbMarca = new JComboBox();
		marcaDao = new MarcaDAO(conn.getConexao());
		Marca vetor[] = marcaDao.vetorMarcas();
		String vetorString[] = new String[]{"oi","victor"};
		cbMarca.setModel(new DefaultComboBoxModel(vetor));
		cbMarca.setBounds(88, 96, 328, 22);
		contentPane.add(cbMarca);
		
		JLabel lblSistemaOperacional = new JLabel("Sistema Operacional:");
		lblSistemaOperacional.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSistemaOperacional.setBounds(25, 135, 133, 14);
		contentPane.add(lblSistemaOperacional);
		
		JComboBox cbSistemaOperacional = new JComboBox();
		cbSistemaOperacional.setModel(new DefaultComboBoxModel(new String[] {"iOS", "Android"}));
		cbSistemaOperacional.setBounds(168, 129, 186, 22);
		contentPane.add(cbSistemaOperacional);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfModelo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameCelular.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					tfModelo.requestFocus();
				}else {
					if(celular == null) {
						celular = new Celular();
					}
					celular.setModelo(tfModelo.getText().trim());
					celular.setMarca((Marca)cbMarca.getSelectedItem());
				}
				
			}
		});
		btnSalvar.setBounds(39, 304, 119, 36);
		contentPane.add(btnSalvar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setBounds(168, 304, 119, 36);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(297, 304, 119, 36);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInicial frame = new FrameInicial();
				frame.setVisible(true);
				FrameCelular.this.setVisible(false);
				dispose();
			}
		});
		btnVoltar.setBounds(426, 304, 119, 36);
		contentPane.add(btnVoltar);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setOpaque(true);
		lbLogo.setBackground(new Color(192, 192, 192));
		lbLogo.setBounds(464, 28, 133, 105);
		contentPane.add(lbLogo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 351, 542, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(25, 172, 133, 14);
		contentPane.add(lblBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(88, 171, 186, 20);
		contentPane.add(txtBuscar);
		
		JComboBox cbFiltro = new JComboBox();
		cbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (cbFiltro.getSelectedIndex() == 0) {
		                JOptionPane.showMessageDialog(FrameCelular.this, "Por favor, escolha uma opção válida.");
		                cbFiltro.setSelectedIndex(-1); // Restaura sem seleção
		            }
			}
		});
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] {"Filtro", "Celular", "Marca"}));
		cbFiltro.setToolTipText("");
		cbFiltro.setBounds(297, 170, 107, 22);
		contentPane.add(cbFiltro);
	}
}
