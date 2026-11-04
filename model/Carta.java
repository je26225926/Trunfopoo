package model;

public class Carta {
    private String id;
    private String nome;
    private int forca, velocidade, habilidade, equipamento, inteligencia;

    public Carta(String id, String nome, int forca, int velocidade, int habilidade, int equipamento, int inteligencia) {
        this.id = id;
        this.nome = nome;
        this.forca = forca;
        this.velocidade = velocidade;
        this.habilidade = habilidade;
        this.equipamento = equipamento;
        this.inteligencia = inteligencia;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getForca() { return forca; }
    public int getVelocidade() { return velocidade; }
    public int getHabilidade() { return habilidade; }
    public int getEquipamento() { return equipamento; }
    public int getInteligencia() { return inteligencia; }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}