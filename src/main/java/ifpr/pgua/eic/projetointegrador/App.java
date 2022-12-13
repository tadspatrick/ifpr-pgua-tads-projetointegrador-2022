package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.controllers.TelaProdutos;
import ifpr.pgua.eic.projetointegrador.controllers.TelaPrincipal;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaProdutosViewModel;
import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCProdutoDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.ProdutoDAO;
import ifpr.pgua.eic.projetointegrador.model.repositories.ProdutoRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.ScreenRegistryFXML;



/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private ProdutoDAO produtoDao;
    private ProdutoRepository produtoRepository;


    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();
        produtoDao = new JDBCProdutoDAO(FabricaConexoes.getInstance());
        produtoRepository = new ProdutoRepository(produtoDao);
        
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
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o)->new TelaLogin()));
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o)->new TelaPrincipal()));
        registraTela("LIVROS", new ScreenRegistryFXML(getClass(), "fxml/livros.fxml", (o)->new TelaProdutos(new TelaProdutosViewModel(produtoRepository))));  
    
    }


}