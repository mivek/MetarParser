package io.github.mivek.facade;

import org.junit.Ignore;

import io.github.mivek.facade.AbstractWeatherCodeFacade;
import io.github.mivek.facade.TAFFacade;
import io.github.mivek.model.TAF;

@Ignore
public class TAFFacadeTest extends AbstractWeatherCodeFacadeTest<TAF> {

    private TAFFacade sut = TAFFacade.getInstance();

    @Override
    protected AbstractWeatherCodeFacade<TAF> getSut() {
        return sut;
    }
}
