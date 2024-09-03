package br.ifrn.queiroga.model;

public class Pessoa {
  
    private Integer id;
    private String nome;
    private String endereco;
    private String cpf;
    private String email;
    private String apelido;
    private String nasc;
    private String telefone;
    private int categoria;

    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCategoria() {
        return categoria;
    }

    /**
     * @return the categoria
     */
    public void setCategoria(int categoria) {   
        this.categoria = categoria;
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @return the nasc
     */
    public String getNasc() {
        return nasc;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     * @param nasc the nasc to set
     */
    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
	
	
}
