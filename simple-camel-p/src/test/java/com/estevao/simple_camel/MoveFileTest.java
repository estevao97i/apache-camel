package com.estevao.simple_camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@CamelSpringBootTest
@SpringBootTest
public class MoveFileTest {

    private static final Path INPUT_FILE = Paths.get("src/files/input/inputFile.txt");
    private static final Path OUTPUT_FILE = Paths.get("src/files/output/outputFile.txt");

    @EndpointInject("file:src/files/input")
    private ProducerTemplate producerTemplate;

    @BeforeEach
    void setup() throws Exception {
        // Cria os diretórios e o arquivo de entrada para o teste
        Files.createDirectories(INPUT_FILE.getParent());
        Files.createDirectories(OUTPUT_FILE.getParent());
        Files.write(INPUT_FILE, "Conteúdo de teste".getBytes());
    }

    @AfterEach
    void cleanup() throws Exception {
        // Remove os arquivos criados durante o teste
        Files.deleteIfExists(INPUT_FILE);
        Files.deleteIfExists(OUTPUT_FILE);
    }

    @Test
    public void testMoveFile() throws Exception {
        // Aguarda um tempo para o Camel processar o arquivo
        Thread.sleep(2000);

        // Verifica se o arquivo foi movido
        assertTrue(Files.exists(OUTPUT_FILE), "O arquivo não foi movido para o diretório de saída");
        assertTrue(Files.readString(OUTPUT_FILE).contains("Conteúdo de teste"), "O conteúdo do arquivo está incorreto");
    }
}
