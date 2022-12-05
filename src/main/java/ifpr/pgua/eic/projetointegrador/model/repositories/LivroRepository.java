package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.time.LocalDate;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.LivroDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class LivroRepository {
    
    private LivroDAO dao;

    public LivroRepository(LivroDAO dao) {
        this.dao = dao;
    }

    public Result cadastrar(String titulo, String editora, Integer paginas, Integer anoPublicacao){
        int year = LocalDate.now().getYear();
        if(paginas <= 0 || anoPublicacao > year){
            return Result.fail("Número de páginas menor ou igual a 0 ou ano de publicação menor que o atual");
        }
        
        Livro livro = new Livro(titulo, editora, paginas, anoPublicacao);
        return dao.inserir(livro);
    }

    public List<Livro> listar(){
        return dao.buscarTodos();
    }

}