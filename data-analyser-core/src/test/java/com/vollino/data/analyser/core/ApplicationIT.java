package com.vollino.data.analyser.core;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class ApplicationIT {

    @Autowired
    private DataConfigurationProperties configuration;

    @Autowired
    private CamelContext camelContext;

    private NotifyBuilder notifyBuilder;

    @Before
    public void setUp() throws IOException {
        deleteRecursively(configuration.inDirectory());
        deleteRecursively(configuration.outDirectory());
        Files.createDirectories(configuration.inDirectory());
        Files.createDirectories(configuration.outDirectory());
        notifyBuilder = new NotifyBuilder(camelContext);
    }

    @Test
    public void shouldBootApplication() {
        assertNotNull(configuration);
    }

    @Test
    public void shouldProcessInputFilesAndCreateSummaryFile() throws IOException {
        //given
        Path expectedSummaryFile = configuration.outFile();
        List<String> expectedOutputFileLines = Lists.newArrayList("3ç3ç10çRenato");

        //when
        feedWithInputFile("sample-input-1.dat");
        feedWithInputFile("sample-input-2.dat");
        notifyBuilder.wereSentTo("direct:analyse").whenDone(1).create()
                .matches(5, TimeUnit.SECONDS);

        //then
        assertTrue(Files.exists(expectedSummaryFile));
        assertThat(Files.readAllLines(expectedSummaryFile), is(expectedOutputFileLines));
    }

    @Test
    public void shouldProcessInputFilesIncrementally() throws IOException {
        //given
        Path expectedSummaryFile = configuration.outFile();
        List<String> expectedOutputFileLines = Lists.newArrayList("3ç3ç10çRenato");

        //when
        feedWithInputFile("sample-input-1.dat");
        notifyBuilder.wereSentTo("direct:analyse").whenDone(1).create()
                .matches(5, TimeUnit.SECONDS);
        feedWithInputFile("sample-input-2.dat");
        notifyBuilder.wereSentTo("direct:analyse").whenDone(1).create()
                .matches(5, TimeUnit.SECONDS);

        //then
        assertTrue(Files.exists(expectedSummaryFile));
        assertThat(Files.readAllLines(expectedSummaryFile), is(expectedOutputFileLines));
    }

    private void feedWithInputFile(String filename) {
        try {
            Path inputFilePath = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            Files.copy(inputFilePath, configuration.inDirectory().resolve(filename));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteRecursively(Path path) {
        try {
            Files.walk(path).map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            //do nothing
        }
    }
}