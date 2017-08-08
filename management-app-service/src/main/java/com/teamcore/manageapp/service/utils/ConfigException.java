package com.teamcore.manageapp.service.utils;

/**
 * Describes application configuration
 * exceptions
 */
public class ConfigException extends Exception {
   public ConfigException() {}
   public ConfigException(String message) {
     super(message);
   }

   public ConfigException(Throwable cause) {
     super(cause);
   }

    public ConfigException(String message, Throwable cause) {
     super(message, cause);
   }
}
