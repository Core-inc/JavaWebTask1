package com.teamcore.site.utils;

import com.teamcore.site.security.ByteDecoder;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class FileConfigReader implements ConfigReader {

    private ByteArrayInputStream configInputStream;
    private Properties properties;

    public FileConfigReader(String fileName, ByteDecoder decoder) throws IOException {
        try(BufferedInputStream bin = new BufferedInputStream(
                        new ClassPathResource(fileName).getInputStream())) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            byte[] data = new byte[256];
            for(int nRead = 0; (nRead = bin.read(data, 0, data.length)) != -1;) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();

            //create decoded bytes stream
            configInputStream = new ByteArrayInputStream(
                            decoder.decode(buffer.toByteArray()));
            //parse properties
            properties = new Properties();
            properties.load(configInputStream);
        }
    }

    @Override
    public Set<String> getOptions() {
       return properties.stringPropertyNames();
    }

    @Override
    public String getOption(String option) throws ConfigException {
       String property = properties.getProperty(option);
       if (property == null) {
           throw new ConfigException(option + " doesn't exists!");
       }
       return property;
    }
}
