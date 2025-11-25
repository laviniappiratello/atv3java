package com.autobots.automanager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.automanager.entidades.CredencialUsuarioSenha;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Email;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.enumeracoes.StatusVenda;
import com.autobots.automanager.enumeracoes.TipoDocumento;
import com.autobots.automanager.enumeracoes.TipoEndereco;
import com.autobots.automanager.enumeracoes.TipoTelefone;
import com.autobots.automanager.enumeracoes.TipoVeiculo;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@SpringBootApplication
public class AutomanagerApplication implements CommandLineRunner {

	@Autowired
	private RepositorioEmpresa repositorioEmpresa;

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Verificar se já existem dados no banco
		if (repositorioEmpresa.count() > 0) {
			System.out.println("Dados já existem no banco. Pulando inserção de dados de exemplo.");
			return;
		}

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Oficina SuperCar LTDA 2025");
		empresa.setNomeFantasia("SuperCar Serviços Automotivos");
		empresa.setCadastro(new Date());

		Endereco enderecoEmpresa = new Endereco();
		enderecoEmpresa.setTipo(TipoEndereco.COMERCIAL);
		enderecoEmpresa.setEstado("Minas Gerais");
		enderecoEmpresa.setCidade("Belo Horizonte");
		enderecoEmpresa.setBairro("Savassi");
		enderecoEmpresa.setRua("Rua da Bahia");
		enderecoEmpresa.setNumero("123");
		enderecoEmpresa.setCodigoPostal("30160-011");
		empresa.setEndereco(enderecoEmpresa);

		Telefone telefoneEmpresa = new Telefone();
		telefoneEmpresa.setDdd("031");
		telefoneEmpresa.setNumero("33334444");
		telefoneEmpresa.setTipo(TipoTelefone.COMERCIAL);
		empresa.getTelefones().add(telefoneEmpresa);

		// FUNCIONÁRIO
		Usuario funcionario = new Usuario();
		funcionario.setNome("João Pereira");
		funcionario.setNomeSocial("João");
		funcionario.getPerfis().add(PerfilUsuario.FUNCIONARIO);

		Email emailFuncionario = new Email();
		emailFuncionario.setEndereco("joao.pereira@supercar.com");
		funcionario.getEmails().add(emailFuncionario);

		Endereco enderecoFuncionario = new Endereco();
		enderecoFuncionario.setEstado("Minas Gerais");
		enderecoFuncionario.setCidade("Belo Horizonte");
		enderecoFuncionario.setBairro("Funcionários");
		enderecoFuncionario.setRua("Rua Gonçalves Dias");
		enderecoFuncionario.setNumero("456");
		enderecoFuncionario.setCodigoPostal("30140-091");
		enderecoFuncionario.setTipo(TipoEndereco.RESIDENCIAL);
		funcionario.setEndereco(enderecoFuncionario);

		Telefone telefoneFuncionario = new Telefone();
		telefoneFuncionario.setDdd("031");
		telefoneFuncionario.setNumero("988887777");
		telefoneFuncionario.setTipo(TipoTelefone.CELULAR);
		funcionario.getTelefones().add(telefoneFuncionario);

		Documento cpf = new Documento();
		cpf.setDataEmissao(new Date());
		cpf.setNumero("11122233344");
		cpf.setTipo(TipoDocumento.CPF);
		funcionario.getDocumentos().add(cpf);

		CredencialUsuarioSenha credencialFuncionario = new CredencialUsuarioSenha();
		credencialFuncionario.setInativo(false);
		credencialFuncionario.setNomeUsuario("joaopereira_func");
		credencialFuncionario.setSenha("abc123");
		credencialFuncionario.setCriacao(new Date());
		credencialFuncionario.setUltimoAcesso(new Date());
		funcionario.getCredenciais().add(credencialFuncionario);

		empresa.getUsuarios().add(funcionario);

		// FORNECEDOR
		Usuario fornecedor = new Usuario();
		fornecedor.setNome("AutoParts Nacional SA");
		fornecedor.setNomeSocial("AutoParts");
		fornecedor.getPerfis().add(PerfilUsuario.FORNECEDOR);

		Email emailFornecedor = new Email();
		emailFornecedor.setEndereco("contato@autoparts.com");
		fornecedor.getEmails().add(emailFornecedor);

		CredencialUsuarioSenha credencialFornecedor = new CredencialUsuarioSenha();
		credencialFornecedor.setInativo(false);
		credencialFornecedor.setNomeUsuario("autoparts2025");
		credencialFornecedor.setSenha("forn123");
		credencialFornecedor.setCriacao(new Date());
		credencialFornecedor.setUltimoAcesso(new Date());
		fornecedor.getCredenciais().add(credencialFornecedor);

		Documento cnpj = new Documento();
		cnpj.setDataEmissao(new Date());
		cnpj.setNumero("12345067000199");
		cnpj.setTipo(TipoDocumento.CNPJ);
		fornecedor.getDocumentos().add(cnpj);

		Endereco enderecoFornecedor = new Endereco();
		enderecoFornecedor.setEstado("Rio Grande do Sul");
		enderecoFornecedor.setCidade("Porto Alegre");
		enderecoFornecedor.setBairro("Centro Histórico");
		enderecoFornecedor.setRua("Av. Borges de Medeiros");
		enderecoFornecedor.setNumero("789");
		enderecoFornecedor.setCodigoPostal("90020-021");
		enderecoFornecedor.setTipo(TipoEndereco.COMERCIAL);
		fornecedor.setEndereco(enderecoFornecedor);

		empresa.getUsuarios().add(fornecedor);

		// MERCADORIA
		Mercadoria bateria = new Mercadoria();
		bateria.setCadastro(new Date());
		bateria.setFabricao(new Date());
		bateria.setNome("Bateria Moura 60Ah");
		bateria.setValidade(new Date());
		bateria.setQuantidade(20);
		bateria.setValor(550.0);
		bateria.setDescricao("Bateria automotiva de alta performance para veículos populares e sedans.");
		empresa.getMercadorias().add(bateria);
		fornecedor.getMercadorias().add(bateria);

		// CLIENTE
		Usuario cliente = new Usuario();
		cliente.setNome("Ana Oliveira");
		cliente.setNomeSocial("Ana");
		cliente.getPerfis().add(PerfilUsuario.CLIENTE);

		Email emailCliente = new Email();
		emailCliente.setEndereco("ana.oliveira@email.com");
		cliente.getEmails().add(emailCliente);

		Documento cpfCliente = new Documento();
		cpfCliente.setDataEmissao(new Date());
		cpfCliente.setNumero("99988877766");
		cpfCliente.setTipo(TipoDocumento.CPF);
		cliente.getDocumentos().add(cpfCliente);

		CredencialUsuarioSenha credencialCliente = new CredencialUsuarioSenha();
		credencialCliente.setInativo(false);
		credencialCliente.setNomeUsuario("anaoliveira_cli");
		credencialCliente.setSenha("cli123");
		credencialCliente.setCriacao(new Date());
		credencialCliente.setUltimoAcesso(new Date());
		cliente.getCredenciais().add(credencialCliente);

		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setEstado("Minas Gerais");
		enderecoCliente.setCidade("Contagem");
		enderecoCliente.setBairro("Eldorado");
		enderecoCliente.setRua("Rua Rio Comprido");
		enderecoCliente.setNumero("321");
		enderecoCliente.setCodigoPostal("32340-000");
		enderecoCliente.setTipo(TipoEndereco.RESIDENCIAL);
		cliente.setEndereco(enderecoCliente);

		// VEÍCULO
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("ABC-9876");
		veiculo.setModelo("Onix Plus");
		veiculo.setTipo(TipoVeiculo.SUV);
		veiculo.setProprietario(cliente);
		cliente.getVeiculos().add(veiculo);
		empresa.getUsuarios().add(cliente);

		// SERVIÇOS
		Servico trocaBateria = new Servico();
		trocaBateria.setDescricao("Troca de bateria descarregada por nova");
		trocaBateria.setNome("Troca de bateria");
		trocaBateria.setValor(80);

		Servico revisaoBasica = new Servico();
		revisaoBasica.setDescricao("Revisão de itens básicos do veículo");
		revisaoBasica.setNome("Revisão básica");
		revisaoBasica.setValor(200);

		empresa.getServicos().add(trocaBateria);
		empresa.getServicos().add(revisaoBasica);

		// VENDA 1
		Venda venda = new Venda();
		venda.setCadastro(new Date());
		venda.setCliente(cliente);
		venda.getMercadorias().add(bateria);
		venda.setIdentificacao("VENDA" + System.currentTimeMillis());
		venda.setFuncionario(funcionario);
		venda.getServicos().add(trocaBateria);
		venda.getServicos().add(revisaoBasica);
		venda.setVeiculo(veiculo);
		venda.setStatus(StatusVenda.CONFIRMADA);
		veiculo.getVendas().add(venda);
		empresa.getVendas().add(venda);

		repositorioEmpresa.save(empresa);
	}

}
