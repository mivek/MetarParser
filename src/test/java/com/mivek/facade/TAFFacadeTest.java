package com.mivek.facade;

import org.junit.Ignore;

import com.mivek.model.TAF;

@Ignore
public class TAFFacadeTest extends AbstractWeatherCodeFacadeTest<TAF> {

    private TAFFacade sut = TAFFacade.getInstance();

    @Override
    protected AbstractWeatherCodeFacade<TAF> getSut() {
        return sut;
    }
}
