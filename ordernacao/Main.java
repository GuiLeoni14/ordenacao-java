package ordernacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getNota() {
        return nota;
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

class InsertionSort<T extends Comparable<T>> {
    private long contaComparacoes;

    private void insert(T[] v, int i) {
        T eleito = v[i];
        int indice_comp = i - 1;
        while (indice_comp >= 0 && eleito.compareTo(v[indice_comp]) < 0) {
            this.contaComparacoes++;
            v[indice_comp + 1] = v[indice_comp];
            indice_comp--;
        }
        this.contaComparacoes++;
        v[indice_comp + 1] = eleito;
    }

    public void sort(T[] v) {
        this.contaComparacoes = 0;
        for (int i = 1; i < v.length; i++) {
            insert(v, i);
        }
    }

    public long getContaComparacoes() {
        return contaComparacoes;
    }
}

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "C:\\Users\\guile\\Documents\\Java\\Projetos\\ordernacao\\src\\ordernacao\\dadosConcurso.csv";
        List<Candidato> candidatosList = new ArrayList<>();
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
                candidatosList.add(candidato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Candidato[] candidatosArray = candidatosList.toArray(new Candidato[0]);

        InsertionSort<Candidato> insertionSort = new InsertionSort<>();
        insertionSort.sort(candidatosArray);

        for (Candidato candidato : candidatosArray) {
            System.out.println(candidato);
        }

        System.out.println("Número de comparações: " + insertionSort.getContaComparacoes());
    }
}
