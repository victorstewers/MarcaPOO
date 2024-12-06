package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrameInicial extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInicial frame = new FrameInicial();
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
	public FrameInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
        setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFrameMarca = new JButton("Cadastra Marca");
		btnFrameMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMarca frame = new FrameMarca();
				frame.setVisible(true);
				FrameInicial.this.setVisible(false);
				dispose();
			}
		});
		btnFrameMarca.setBounds(58, 99, 135, 59);
		contentPane.add(btnFrameMarca);
		
		JButton btnFrameCelular = new JButton("Cadastrar Celular");
		btnFrameCelular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameCelular frame;
				try {
					frame = new FrameCelular();
					frame.setVisible(true);
					FrameInicial.this.setVisible(false);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnFrameCelular.setBounds(230, 99, 135, 59);
		contentPane.add(btnFrameCelular);
	}
}
