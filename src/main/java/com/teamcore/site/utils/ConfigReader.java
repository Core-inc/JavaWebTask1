package com.teamcore.site.utils;

import java.util.Set;

public interface ConfigReader {

    Set<String> getOptions();
    String getOption(String option) throws ConfigException;
}
