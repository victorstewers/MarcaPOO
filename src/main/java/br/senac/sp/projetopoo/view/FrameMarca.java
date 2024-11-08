package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senac.sp.projetopoo.dao.ConnectionFactory;
import br.senac.sp.projetopoo.dao.EMFactory;
import br.senac.sp.projetopoo.dao.InterfaceDao;
import br.senac.sp.projetopoo.dao.MarcaDAO;
import br.senac.sp.projetopoo.dao.MarcaDaoHib;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.tablemodel.MarcaTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private Marca marca;
	private InterfaceDao<Marca> dao; //trocamos MarcaDAO pra isso
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private File selecionado;
	private JTable tbMarca;
	private List<Marca> marcas;
	private MarcaTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMarca frame = new FrameMarca();
					frame.setVisible(true);
					// nome da classe e nome do método pois é estatico(nao precisa ser instanciado)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMarca() {
		
		dao = new MarcaDaoHib(EMFactory.getEntityManager());
		
		try {
			marcas = dao.listar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(FrameMarca.this,"Erro: " + e.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		tableModel = new MarcaTableModel(marcas);

		chooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

		setTitle("Cadastro de Marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 24, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 60, 46, 14);
		contentPane.add(lblNome);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(58, 23, 46, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(58, 59, 214, 20);
		contentPane.add(tfNome);

		JLabel lbLogo = new JLabel("");
		lbLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					//boolean teste = chooser.showOpenDialog(FrameMarca.this) == JFileChooser.CANCEL_OPTION;
					if(chooser.showOpenDialog(FrameMarca.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						try {
							BufferedImage bufImg = ImageIO.read(selecionado);
							Image imagem = bufImg.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_SMOOTH);
							ImageIcon imgLabel =  new ImageIcon(imagem);
							lbLogo.setIcon(imgLabel);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}
					//System.out.println(teste);
				}

			}
		});
		lbLogo.setBackground(Color.LIGHT_GRAY);
		lbLogo.setBounds(419, 26, 75, 69);
		lbLogo.setOpaque(true);
		contentPane.add(lbLogo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameMarca.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					tfNome.requestFocus();
				} else {
					if(marca == null) {
						marca = new Marca();
					}
					
					marca.setNome(tfNome.getText().trim());
					
					try {
						if(selecionado != null) {
							//byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							marca.setLogo(Files.readAllBytes(selecionado.toPath()));
						}
						if(marca.getId()==0){
							dao.inserir(marca);
						}else {
							dao.alterar(marca);
						}
						marcas = dao.listar();
						tableModel.setLista(marcas);
						tableModel.fireTableDataChanged();
						limpar();
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(FrameMarca.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				}
			}
		});
		btnSalvar.setMnemonic('s');
		btnSalvar.setBounds(15, 177, 89, 36);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(marcas != null) {
					if(JOptionPane.showConfirmDialog(FrameMarca.this, "Deseja excluir a marca"+marca.getNome()) == JOptionPane.YES_OPTION) {
						try {
							dao.excluir(marca.getId());
							marcas = dao.listar();
							tableModel.setLista(marcas);
							tableModel.fireTableDataChanged();
							limpar();
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
					}
					
					
				}else {
					JOptionPane.showMessageDialog(FrameMarca.this, "Selecione uma marca para excluir");
				}
			}
		});
		btnExcluir.setMnemonic('e');
		btnExcluir.setBounds(120, 177, 89, 36);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(219, 177, 89, 36);
		contentPane.add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 251, 352, 376);
		contentPane.add(scrollPane);
		
		tbMarca = new JTable(tableModel);
		tbMarca.setToolTipText("Selecione um item para alterar ou excluir");
		tbMarca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbMarca.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linha = tbMarca.getSelectedRow();
				if(linha>=0) {
					marca = marcas.get(linha);
					tfId.setText(""+marca.getId());
					tfNome.setText(marca.getNome());
				}
				
			}
		});
		
		scrollPane.setViewportView(tbMarca);
	}

	private void limpar() {
		tfId.setText("");
		tfNome.setText("");
		marca = null;
		tfNome.requestFocus();
	}
}
