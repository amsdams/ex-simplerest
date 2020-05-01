package com.amsdams.ex.simplerest.service;

import org.mapstruct.Named;

public interface FileLoader {

    // @Named or some custom annotation annotated with @Qualifier can be
    // used in order to make sure that this will only be applied to the specified mapping
    @Named("loadFile")
    byte[] loadFile(String data);
    
    @Named("createFile")
    String createFile(byte[] data);
}