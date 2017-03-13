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
    private static int id = 1;
    private int identificador;
    private boolean temPontoMapa;
    private double latitude , longitude;



    public Nota() { //construtor
        this.realizado = false;
        identificador = id;
        id++;
        temPontoMapa = false;
    }

    public boolean getTemPontoMapa() {
        return temPontoMapa;
    }

    public void setTemPontoMapa(boolean temPontoMapa) {
        this.temPontoMapa = temPontoMapa;
    }

    public static int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static void setId(int qtd){
       id = qtd;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
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

    public void setCor(String cor) {
        this.cor = cor;
    }

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

        if (data!=null){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataSelecionada = sdf.format((data));
        return nome + "\n" + dataSelecionada + " as " +horario + "                                                     ";}
        else {

            return  nome + "                                                                                            ";
        }

    }
}
