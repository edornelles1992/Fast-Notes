package eduardodornelles.fastnotes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eduardo on 06/03/2017.
 */

public class Nota implements Serializable {
    private String nome, observacao;
    private Date data;
    private String horario;
    private boolean realizado;
    private String nivelRelevancia;
    private String cor;

    public Nota() { //construtor
        this.realizado = false;
    }

    public String getNivelRelevancia() {
        return nivelRelevancia;
    }

    public void setNivelRelevancia(String nivelRelevancia) {
        this.nivelRelevancia = nivelRelevancia;
        setCor();
    }

    private void setCor() {
        if (nivelRelevancia.equals("Normal")){ //verde
            cor = "#66BB6A";
        } else if (nivelRelevancia.equals("Importante")){ //amarelo
            cor = "#D4E157";
        } else { //muito importante - vermelho
            cor = "#EF5350";
        }
    }

    public String getCor(){return cor;}


    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }


    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataSelecionada = sdf.format((data));
        return nome + "\n" + dataSelecionada + " as " +horario;
    }
}
