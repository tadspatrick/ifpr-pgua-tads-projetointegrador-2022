package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.controllers.TelaLivros;
import ifpr.pgua.eic.projetointegrador.controllers.TelaPrincipal;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaLivrosViewModel;
import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCLivroDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.LivroDAO;
import ifpr.pgua.eic.projetointegrador.model.repositories.LivroRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.ScreenRegistryFXML;



/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private LivroDAO livroDao;
    private LivroRepository livroRepository;


    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();
        livroDao = new JDBCLivroDAO(FabricaConexoes.getInstance());
        livroRepository = new LivroRepository(livroDao);

        //vendaRepository = new VendaRepository(vendaDao,clienteDao,produtoDao);
        //vendaRepository.listar();
        
    }

    @Override
    public void stop() throws Exception {
        super.stop();

    }



    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Livros";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o)->new TelaPrincipal()));
        registraTela("LIVROS", new ScreenRegistryFXML(getClass(), "fxml/livros.fxml", (o)->new TelaLivros(new TelaLivrosViewModel(livroRepository))));  
    
    }


}