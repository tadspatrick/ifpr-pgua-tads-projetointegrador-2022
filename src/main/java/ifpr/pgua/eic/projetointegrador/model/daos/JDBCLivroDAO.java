package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCLivroDAO implements LivroDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCLivroDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result inserir(Livro livro) {
        
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO livros(titulo,editora,paginas,anoPublicacao) VALUES (?,?,?,?)");

            pstm.setString(1, livro.getTitulo());
            pstm.setString(2, livro.getEditora());
            pstm.setInt(3, livro.getPaginas());
            pstm.setInt(4, livro.getAnoPublicacao());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Livro cadastrado!");
            

        }catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public List<Livro> buscarTodos() {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM livros");

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Livro> livros = new ArrayList<>();

            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String editora = resultSet.getString("editora");
                Integer paginas = resultSet.getInt("paginas");
                Integer anoPublicacao = resultSet.getInt("anoPublicacao");

                Livro livro = new Livro(id, titulo, editora, paginas, anoPublicacao);

                livros.add(livro);
            }
            
            resultSet.close();
            pstm.close();
            con.close();
            return livros;

        }catch(SQLException e ){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    
}