package ordernacao;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Candidato implements Comparable<Candidato> {
    private int identificador;
    private String nome;
    private LocalDate dataNascimento;
    private int nota;

    public Candidato(int identificador, String nome, LocalDate dataNascimento, int nota) {
        this.identificador = identificador;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nota = nota;
    }

    @Override
    public int compareTo(Candidato outro) {
        if (this.nota != outro.nota) {
            return Integer.compare(outro.nota, this.nota);
        }
        return this.dataNascimento.compareTo(outro.dataNascimento);
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador +
                ", Nome: " + nome +
                ", Data de Nascimento: " + dataNascimento +
                ", Nota: " + nota;
    }
}

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "C:\\Users\\guile\\Documents\\Java\\Projetos\\ordernacao\\src\\ordernacao\\dadosConcurso.csv";
        List<Candidato> candidatos = new ArrayList<>();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
            	String[] dados = linha.split(",");

                int identificador = Integer.parseInt(dados[0]);
                String nome = dados[1];
                LocalDate dataNascimento = LocalDate.parse(dados[2], formatoData);
                int nota = Integer.parseInt(dados[3]);

                Candidato candidato = new Candidato(identificador, nome, dataNascimento, nota);
                candidatos.add(candidato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(candidatos);

        for (Candidato candidato : candidatos) {
            System.out.println(candidato);
        }
    }
}
