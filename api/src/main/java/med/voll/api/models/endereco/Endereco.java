package med.voll.api.models.endereco;
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
}
