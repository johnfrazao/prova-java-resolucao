import org.junit.jupiter.api.Test;
import ufpb.dcx.john.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaFuncionariosBomPratoTest {

    @Test
    public void testaCadastroEPesquisa() {
            SistemaFuncionariosBomPrato sistema = new SistemaFuncionariosBomPrato();

            try {
                sistema.cadastrarFuncionario(new Funcionario(
                        "333.333.333-33", "Ayla Rebouças", TipoFuncionario.GERENTE, 3000));

                assertTrue(sistema.funcionarioJaExiste("333.333.333-33"));

                Funcionario f1 = sistema.pesquisarFuncionario("333.333.333-33");

                sistema.cadastrarFuncionario(new Funcionario(
                        "222.222.222-22","João Paulo Silva", TipoFuncionario.COZINHEIRO, 5000));
                List<Funcionario> lista = sistema.pesquisarFuncionariosComSalarioMaiorQue(2000);
                assertEquals(2, lista.size());
                int quant = sistema.contarFuncionariosDoTipo(TipoFuncionario.COZINHEIRO);
                assertEquals(1, quant);


            } catch (FuncionarioJaExisteException | FuncionarioInexistenteException e) {
                fail("Não deveria lançar exceção");
            }
    }

    @Test
    public void testaPesquisaPorTipo() throws Exception {
        SistemaFuncionariosBomPrato sistema = new SistemaFuncionariosBomPrato();

        sistema.cadastrarFuncionario(
                "111",
                "João",
                TipoFuncionario.GERENTE,
                5000
        );

        sistema.cadastrarFuncionario(
                "222",
                "Pablo",
                TipoFuncionario.FAXINEIRO,
                2500
        );

        sistema.cadastrarFuncionario(
                "333",
                "Pedro",
                TipoFuncionario.GERENTE,
                6000
        );

        List<Funcionario> gerentes =
                sistema.pesquisarFuncionariosPorTipo(TipoFuncionario.GERENTE);

        assertEquals(2, gerentes.size());
    }


}
