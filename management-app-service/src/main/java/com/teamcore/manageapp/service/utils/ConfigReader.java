package com.teamcore.manageapp.service.utils;

import java.util.Set;

public interface ConfigReader {

    Set<String> getOptions();
    String getOption(String option) throws ConfigException;
}
