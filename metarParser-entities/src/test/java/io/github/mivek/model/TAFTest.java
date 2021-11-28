package io.github.mivek.model;

import io.github.mivek.model.trend.INTERTafTrend;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;

/**
 * @author Jean-Kevin KPADEY
 */
class TAFTest {

    @Test
    void testAddInter() {
        TAF taf = new TAF();
        INTERTafTrend trend = new INTERTafTrend();
        taf.addInter(trend);

        MatcherAssert.assertThat(taf.getInters(), hasSize(1));
    }
}
