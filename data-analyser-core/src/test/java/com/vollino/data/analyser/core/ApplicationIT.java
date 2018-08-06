package com.vollino.data.analyser.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApplicationIT {

    @Autowired
    private DataConfigurationProperties configuration;

    @Before
    public void setUp() throws IOException {
        Files.walk(configuration.inDirectory()).map(Path::toFile).forEach(File::delete);
        Files.walk(configuration.outDirectory()).map(Path::toFile).forEach(File::delete);
        Files.createDirectories(configuration.inDirectory());
        Files.createDirectories(configuration.outDirectory());
    }

    @Test
    public void shouldBootApplication() {
        assertNotNull(configuration);
    }

    @Test
    public void shouldCreateFileDoneTokens() throws InterruptedException {
        //given
        Path expectedFileDone1 = configuration.outDirectory().resolve("sample-input-1.done.dat");
        Path expectedFileDone2 = configuration.outDirectory().resolve("sample-input-2.done.dat");

        //when
        feedWithInputFile("sample-input-1.dat");
        feedWithInputFile("sample-input-2.dat");
        Thread.sleep(2000);

        //then
        assertTrue(Files.exists(expectedFileDone1));
        assertTrue(Files.exists(expectedFileDone2));
    }

    private void feedWithInputFile(String filename) {
        try {
            Path inputFilePath = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            Files.copy(inputFilePath, configuration.inDirectory().resolve(filename));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}