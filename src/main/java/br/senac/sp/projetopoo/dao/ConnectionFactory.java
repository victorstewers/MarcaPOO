package br.senac.sp.projetopoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public  class ConnectionFactory {
   private static Connection conexao;
   
   public static Connection getConexao() {
	   if(conexao == null) {
		  try {
			  //jdbc:mysql:// é um protocolo, tipo http:
			  //Caso fosse um endereço remoto,externo seria um ip ao inves de localhost
			  //3306 é a porta de entrada
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetopoo","root","");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	   }
	   return conexao;
   }
}
