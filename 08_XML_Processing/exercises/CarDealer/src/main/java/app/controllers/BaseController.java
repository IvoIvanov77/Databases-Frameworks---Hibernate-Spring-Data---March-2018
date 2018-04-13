package app.controllers;

import app.io.contracts.Parser;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected   Parser xmlParser;



    public abstract String importFromXmlString(String jsonString);
}
