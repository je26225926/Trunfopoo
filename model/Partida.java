package model;

import java.util.*;

public class Partida {
    private List<Carta> baralho;
    private Set<String> usadas;

    public Partida(List<Carta> todasCartas) {
        if (todasCartas.size() != 30)
            throw new IllegalArgumentException("Precisamos de exatamente 30 cartas.");
        this.baralho = new ArrayList<>(todasCartas);
        this.usadas = new HashSet<>();
    }

    public boolean isUsada(String id) { return usadas.contains(id); }
    public void usarCarta(String id) { usadas.add(id); }
    public boolean acabou() { return usadas.size() == baralho.size(); }

    public List<Carta> getDisponiveis() {
        List<Carta> disp = new ArrayList<>();
        for (Carta c : baralho)
            if (!usadas.contains(c.getId())) disp.add(c);
        Collections.shuffle(disp);   // embaralha a cada chamada (rodada)
        return disp;
    }

    public int combater(Carta c1, Carta c2, String atributo) {
        if ("A1".equals(c1.getId())) return 1;
        if ("A1".equals(c2.getId())) return 2;

        int v1 = valorAtributo(c1, atributo);
        int v2 = valorAtributo(c2, atributo);
        if (v1 > v2) return 1;
        if (v2 > v1) return 2;
        return 0; // empate
    }

    private int valorAtributo(Carta c, String attr) {
        return switch (attr.toLowerCase()) {
            case "forca" -> c.getForca();
            case "velocidade" -> c.getVelocidade();
            case "habilidade" -> c.getHabilidade();
            case "equipamento" -> c.getEquipamento();
            case "inteligencia" -> c.getInteligencia();
            default -> throw new IllegalArgumentException("Atributo inválido");
        };
    }
}