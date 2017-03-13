package eduardodornelles.fastnotes;

/**
 * Created by Eduardo on 10/03/2017.
 */

public class ItemListView {

    private String texto;

    private int identificador;
    private String cor;
    private int iconeRid;


    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public ItemListView(String texto, String cor_imp) {
        this.texto = texto;
        cor = cor_imp;

    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }



    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
