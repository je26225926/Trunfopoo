import model.*;
import dao.CartaDAO;
import java.util.*;

void main() {
    IO.println("⚡ SUPER TRUNFO - MARVEL ⚡\n");

    try {
        CartaDAO dao = new CartaDAO();
        List<Carta> todas = dao.listarTodas();

        if (todas.size() != 30) {
            IO.println("ERRO: o banco tem " + todas.size() + " cartas, mas deveriam ser 30.");
            return;
        }

        Partida partida = new Partida(todas);
        Random rand = new Random();
        int vitoriasJogador = 0, vitoriasCPU = 0;

        while (!partida.acabou()) {
            IO.println("\n--- RODADA " + (vitoriasJogador + vitoriasCPU + 1) + " ---");

            // As cartas disponíveis já vêm embaralhadas pelo getDisponiveis()
            List<Carta> disponiveis = partida.getDisponiveis();
            Carta cartaJogador = disponiveis.get(0);
            Carta cartaCPU = disponiveis.get(1);

            IO.println("Você jogou: " + cartaJogador);
            IO.println("CPU jogou : " + cartaCPU);

            String[] atributos = {"forca", "velocidade", "habilidade", "equipamento", "inteligencia"};
            String atributo = atributos[rand.nextInt(atributos.length)];
            IO.println("🎲 Atributo sorteado: " + atributo.toUpperCase());

            int resultado = partida.combater(cartaJogador, cartaCPU, atributo);
            if (resultado == 1) {
                IO.println("✨ VOCÊ VENCEU a rodada!");
                vitoriasJogador++;
            } else if (resultado == 2) {
                IO.println("💀 CPU VENCEU a rodada!");
                vitoriasCPU++;
            } else {
                IO.println("🤝 EMPATE!");
            }

            partida.usarCarta(cartaJogador.getId());
            partida.usarCarta(cartaCPU.getId());

            IO.println("📦 Cartas restantes no baralho: " + partida.getDisponiveis().size());
        }

        IO.println("\n🏆 FIM DE JOGO 🏆");
        IO.println("Placar final: Você " + vitoriasJogador + " x " + vitoriasCPU + " CPU");
        if (vitoriasJogador > vitoriasCPU)
            IO.println("PARABÉNS! Você é o SuperTrunfo!");
        else if (vitoriasCPU > vitoriasJogador)
            IO.println("Que pena... A CPU venceu. Tente novamente!");
        else
            IO.println("Empate geral!");

    } catch (Exception e) {
        IO.println("Erro: " + e.getMessage());
        e.printStackTrace();
    }
}