package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Parâmetros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	// CRUD
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,telefone,email) values(?,?,?)";

		try {
			// abrir a conexão
			Connection con = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// classe método
			// substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());

			// Executar a query - insere os dados no banco
			 

			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para enxergar as variáveis JavaBeans
		ArrayList<JavaBeans> contatosLista = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// execua o comando select ...
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto tiver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				// recebe o primeiro campo e assim por diante
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatosLista.add(new JavaBeans(idcon, nome, telefone, email));

			}
			con.close();
			return contatosLista;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select*from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variáveis JB
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?, telefone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e+"fff");
		}
	}

	// teste
	/*
	 * public void testeConexão() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e); } }
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
