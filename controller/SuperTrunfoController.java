package controller;

import model.Carta;
import model.Partida;
import dao.CartaDAO;
import java.util.List;
import java.util.Random;

public class SuperTrunfoController {
    private Partida partida;
    private List<Carta> todasCartas;
    private Random random = new Random();

    public SuperTrunfoController() throws Exception {
        CartaDAO dao = new CartaDAO();
        todasCartas = dao.listarTodas();
        if (todasCartas.size() != 30) {
            throw new IllegalStateException("Banco tem " + todasCartas.size() + " cartas, preciso de 30.");
        }
        partida = new Partida(todasCartas);
    }

    public List<Carta> getCartasDisponiveis() {
        return partida.getDisponiveis(); // já embaralhadas
    }

    public ResultadoRodada jogarRodada(Carta cartaJogador, String atributo) {
        List<Carta> disponiveis = partida.getDisponiveis();
        Carta cartaCPU = disponiveis.stream()
                .filter(c -> !c.getId().equals(cartaJogador.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Sem carta para CPU"));
        int vencedor = partida.combater(cartaJogador, cartaCPU, atributo);
        partida.usarCarta(cartaJogador.getId());
        partida.usarCarta(cartaCPU.getId());
        return new ResultadoRodada(cartaJogador, cartaCPU, atributo, vencedor, partida.acabou());
    }

    public static class ResultadoRodada {
        public final Carta cartaJogador;
        public final Carta cartaCPU;
        public final String atributo;
        public final int vencedor; // 1=jogador, 2=cpu, 0=empate
        public final boolean fimJogo;

        public ResultadoRodada(Carta cartaJogador, Carta cartaCPU, String atributo, int vencedor, boolean fimJogo) {
            this.cartaJogador = cartaJogador;
            this.cartaCPU = cartaCPU;
            this.atributo = atributo;
            this.vencedor = vencedor;
            this.fimJogo = fimJogo;
        }
    }
}