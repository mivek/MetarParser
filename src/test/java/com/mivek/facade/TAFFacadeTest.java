package com.mivek.facade;

import com.mivek.model.TAF;

public class TAFFacadeTest extends AbstractWeatherCodeFacadeTest<TAF> {

    private TAFFacade sut = TAFFacade.getInstance();

    @Override
    protected AbstractWeatherCodeFacade<TAF> getSut() {
        return sut;
    }
}
