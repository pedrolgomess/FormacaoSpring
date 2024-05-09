package med.voll.api.domain.endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.numero = dadosEndereco.numero();
        this.complemento = dadosEndereco.complemento();
        this.bairro = dadosEndereco.bairro();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
        this.cep = dadosEndereco.cep();
    }

    public void atualizaInfo(DadosEndereco dadosEndereco) {
        if (dadosEndereco.logradouro() != null){
            this.logradouro = dadosEndereco.logradouro();
        }
        if (dadosEndereco.numero() != null){
            this.numero = dadosEndereco.numero();
        }
        if (dadosEndereco.complemento() != null){
            this.complemento = dadosEndereco.complemento();
        }
        if (dadosEndereco.bairro() != null){
            this.bairro = dadosEndereco.bairro();
        }
        if (dadosEndereco.cidade() != null){
            this.cidade = dadosEndereco.cidade();
        }
        if (dadosEndereco.uf() != null){
            this.uf = dadosEndereco.uf();
        }
        if (dadosEndereco.cep() != null){
            this.cep = dadosEndereco.cep();
        }
    }
}
