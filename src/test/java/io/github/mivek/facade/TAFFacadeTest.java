package io.github.mivek.facade;

import io.github.mivek.model.TAF;

public class TAFFacadeTest extends AbstractWeatherCodeFacadeTest<TAF> {

    private TAFFacade sut = TAFFacade.getInstance();

    @Override
    protected AbstractWeatherCodeFacade<TAF> getSut() {
        return sut;
    }
}
