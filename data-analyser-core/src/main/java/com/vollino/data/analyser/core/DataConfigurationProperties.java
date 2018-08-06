package com.vollino.data.analyser.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Bruno Vollino
 */
@Configuration
public class DataConfigurationProperties {

    @Value("${data.working-dir-property}")
    private String workingDir;

    @Value("${data.in.directory}")
    private String inDirectory;

    @Value("${data.out.directory}")
    private String outDirectory;

    @Value("${data.out.file}")
    private String outFile;

    public Path workingDirectory() {
        return Paths.get(System.getProperty(workingDir));
    }

    public Path inDirectory() {
        return workingDirectory().resolve(inDirectory);
    }

    public Path outDirectory() {
        return workingDirectory().resolve(outDirectory);
    }

    public Path outFile() {
        return outDirectory().resolve(outFile);
    }
}
