package io.github.mivek.facade;

import org.junit.Ignore;

import com.mivek.model.TAF;

import io.github.mivek.facade.AbstractWeatherCodeFacade;
import io.github.mivek.facade.TAFFacade;

@Ignore
public class TAFFacadeTest extends AbstractWeatherCodeFacadeTest<TAF> {

    private TAFFacade sut = TAFFacade.getInstance();

    @Override
    protected AbstractWeatherCodeFacade<TAF> getSut() {
        return sut;
    }
}
