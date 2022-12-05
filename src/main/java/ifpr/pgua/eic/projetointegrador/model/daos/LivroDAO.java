package ifpr.pgua.eic.projetointegrador.model.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface LivroDAO {
    Result inserir(Livro livro);
    List<Livro> buscarTodos();
}