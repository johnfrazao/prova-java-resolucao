package ufpb.dcx.john;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaFuncionariosBomPrato implements SistemaFuncionarios {
    private Map<String, Funcionario> funcionarios;


    public SistemaFuncionariosBomPrato() {
        this.funcionarios = new HashMap<String, Funcionario>();
    }


    @Override
    public void cadastrarFuncionario(Funcionario funcionario)
            throws FuncionarioJaExisteException {

        cadastrarFuncionario(
                funcionario.getCpf(),
                funcionario.getNome(),
                funcionario.getTipo(),
                funcionario.getSalario()
        );
    }

    @Override
    public void cadastrarFuncionario(String cpf, String nome, TipoFuncionario
            tipo, double salario) throws FuncionarioJaExisteException {
        if (this.funcionarios.containsKey(cpf)) {
            throw new FuncionarioJaExisteException(
                    "Já existe funcionário com o cpf " + cpf);
        } else {
            this.funcionarios.put(cpf, new Funcionario(cpf, nome, tipo, salario));
        }
    }

    @Override
    public void alterarSalarioDeFuncionario(String cpfFuncionario, double novoSalario) throws FuncionarioInexistenteException {
        if (funcionarios.containsKey(cpfFuncionario)) {
            funcionarios.get(cpfFuncionario).setSalario(novoSalario);
        } else {
            throw new FuncionarioInexistenteException("Usuário não encontrado.");
        }
    }

    @Override
    public int contarFuncionariosDoTipo(TipoFuncionario tipo) {
        int quant = 0;
        for (Funcionario f : funcionarios.values()) {
            if (f.getTipo().equals(tipo)) {
                quant += 1;
            }
        }
        return quant;
    }

    @Override
    public boolean funcionarioJaExiste(String cpfFuncionario) {
        return funcionarios.containsKey(cpfFuncionario);
    }

    @Override
    public List<Funcionario> pesquisarFuncionariosPorTipo(TipoFuncionario tipo) {
        List<Funcionario> funcionariosDoTipo = new ArrayList<>();
        for(Funcionario f : funcionarios.values()){
            if (f.getTipo().equals(tipo)){
                funcionariosDoTipo.add(f);
            }
        }
        return funcionariosDoTipo;
    }

    @Override
    public Funcionario pesquisarFuncionario(String cpfFuncionario) throws FuncionarioInexistenteException {
        if (funcionarios.containsKey(cpfFuncionario)){
            return funcionarios.get(cpfFuncionario);
        }
        throw  new FuncionarioInexistenteException("Funcionario inexistente.");
    }

    @Override
    public List<Funcionario> pesquisarFuncionariosComSalarioMaiorQue(double valor) {
        List<Funcionario> funcionarioComSalarioMaiorQue = new ArrayList<>();
        for (Funcionario f : funcionarios.values()) {
            if (f.getSalario() > valor) {
                funcionarioComSalarioMaiorQue.add(f);
            }
        }
        return funcionarioComSalarioMaiorQue;
    }
}

