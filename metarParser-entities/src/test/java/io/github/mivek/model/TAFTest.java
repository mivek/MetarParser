package io.github.mivek.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.TafProbTrend;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.BeginningValidity;
import io.github.mivek.model.trend.validity.Validity;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TAFTest {

  @Test
  void testGetTEMPO() {
    TafTrend becmg1 = new TafTrend(WeatherChangeType.BECMG);
    Validity validity1 = new Validity();
    validity1.setStartDay(15);
    validity1.setStartHour(21);
    validity1.setEndDay(15);
    validity1.setEndHour(24);
    Visibility visibility1 = new Visibility();
    visibility1.setMainVisibility("3000m");
    becmg1.setValidity(validity1);
    becmg1.setVisibility(visibility1);

    WeatherCondition wc1 = new WeatherCondition();
    wc1.addPhenomenon(Phenomenon.MIST);
    becmg1.addWeatherCondition(wc1);

    Validity validity2 = new Validity();
    validity2.setStartDay(15);
    validity2.setStartHour(21);
    validity2.setEndDay(15);
    validity2.setEndHour(24);

    Visibility visibility2 = new Visibility();
    visibility2.setMainVisibility("800m");
    WeatherCondition wc2 = new WeatherCondition();
    wc2.setDescriptive(Descriptive.PATCHES);
    wc2.addPhenomenon(Phenomenon.FOG);

    TafProbTrend tempo1 = new TafProbTrend(WeatherChangeType.TEMPO);
    tempo1.setProbability(40);
    tempo1.setValidity(validity2);
    tempo1.setVisibility(visibility2);
    tempo1.addWeatherCondition(wc2);

    TafProbTrend tempo2 = new TafProbTrend(WeatherChangeType.TEMPO);
    Validity validity3 = new Validity();
    validity3.setStartDay(16);
    validity3.setStartHour(2);
    validity3.setEndHour(16);
    validity3.setEndHour(8);
    Cloud c1 = new Cloud();
    c1.setQuantity(CloudQuantity.BKN);
    c1.setHeight(130);
    Cloud c2 = new Cloud();
    c2.setQuantity(CloudQuantity.BKN);
    c2.setHeight(230);
    tempo2.setValidity(validity3);
    tempo2.addCloud(c1);
    tempo2.addCloud(c2);

    TafProbTrend tempo3 = new TafProbTrend(WeatherChangeType.TEMPO);
    Validity validity4 = new Validity();
    validity4.setStartDay(16);
    validity4.setStartHour(3);
    validity4.setEndHour(16);
    validity4.setEndHour(7);
    Cloud c3 = new Cloud();
    c3.setQuantity(CloudQuantity.BKN);
    c3.setHeight(90);
    Cloud c4 = new Cloud();
    c4.setQuantity(CloudQuantity.BKN);
    c4.setHeight(230);
    tempo3.setValidity(validity4);
    tempo3.addCloud(c3);
    tempo3.addCloud(c4);

    TAF taf = new TAF();
    taf.addTrend(becmg1);
    taf.addTrend(tempo1);
    taf.addTrend(tempo2);
    taf.addTrend(tempo3);

    List<TafProbTrend> tempos = taf.getTempos();

    assertThat(tempos, hasSize(3));
  }

  @Test
  void testGetBECMG() {
    TafTrend becmg1 = new TafTrend(WeatherChangeType.BECMG);
    Validity validity1 = new Validity();
    validity1.setStartDay(15);
    validity1.setStartHour(21);
    validity1.setEndDay(15);
    validity1.setEndHour(24);
    Visibility visibility1 = new Visibility();
    visibility1.setMainVisibility("3000m");
    becmg1.setValidity(validity1);
    becmg1.setVisibility(visibility1);

    WeatherCondition wc1 = new WeatherCondition();
    wc1.addPhenomenon(Phenomenon.MIST);
    becmg1.addWeatherCondition(wc1);

    Validity validity2 = new Validity();
    validity2.setStartDay(15);
    validity2.setStartHour(21);
    validity2.setEndDay(15);
    validity2.setEndHour(24);

    Visibility visibility2 = new Visibility();
    visibility2.setMainVisibility("800m");
    WeatherCondition wc2 = new WeatherCondition();
    wc2.setDescriptive(Descriptive.PATCHES);
    wc2.addPhenomenon(Phenomenon.FOG);

    TafProbTrend tempo1 = new TafProbTrend(WeatherChangeType.TEMPO);
    tempo1.setProbability(40);
    tempo1.setValidity(validity2);
    tempo1.setVisibility(visibility2);
    tempo1.addWeatherCondition(wc2);

    TafProbTrend tempo2 = new TafProbTrend(WeatherChangeType.TEMPO);
    Validity validity3 = new Validity();
    validity3.setStartDay(16);
    validity3.setStartHour(2);
    validity3.setEndHour(16);
    validity3.setEndHour(8);
    Cloud c1 = new Cloud();
    c1.setQuantity(CloudQuantity.BKN);
    c1.setHeight(130);
    Cloud c2 = new Cloud();
    c2.setQuantity(CloudQuantity.BKN);
    c2.setHeight(230);
    tempo2.setValidity(validity3);
    tempo2.addCloud(c1);
    tempo2.addCloud(c2);

    TafProbTrend tempo3 = new TafProbTrend(WeatherChangeType.TEMPO);
    Validity validity4 = new Validity();
    validity4.setStartDay(16);
    validity4.setStartHour(3);
    validity4.setEndHour(16);
    validity4.setEndHour(7);
    Cloud c3 = new Cloud();
    c3.setQuantity(CloudQuantity.BKN);
    c3.setHeight(90);
    Cloud c4 = new Cloud();
    c4.setQuantity(CloudQuantity.BKN);
    c4.setHeight(230);
    tempo3.setValidity(validity4);
    tempo3.addCloud(c3);
    tempo3.addCloud(c4);

    TAF taf = new TAF();
    taf.addTrend(becmg1);
    taf.addTrend(tempo1);
    taf.addTrend(tempo2);
    taf.addTrend(tempo3);

    List<TafTrend> becmGs = taf.getBECMGs();

    assertThat(becmGs, hasSize(1));
  }

  @Test
  void testGetPROB() {
    TafTrend becmg1 = new TafTrend(WeatherChangeType.BECMG);
    Validity validity1 = new Validity();
    validity1.setStartDay(15);
    validity1.setStartHour(21);
    validity1.setEndDay(15);
    validity1.setEndHour(24);
    Visibility visibility1 = new Visibility();
    visibility1.setMainVisibility("3000m");
    becmg1.setValidity(validity1);
    becmg1.setVisibility(visibility1);

    WeatherCondition wc1 = new WeatherCondition();
    wc1.addPhenomenon(Phenomenon.MIST);
    becmg1.addWeatherCondition(wc1);

    Validity validity2 = new Validity();
    validity2.setStartDay(15);
    validity2.setStartHour(21);
    validity2.setEndDay(15);
    validity2.setEndHour(24);

    Visibility visibility2 = new Visibility();
    visibility2.setMainVisibility("800m");
    WeatherCondition wc2 = new WeatherCondition();
    wc2.setDescriptive(Descriptive.PATCHES);
    wc2.addPhenomenon(Phenomenon.FOG);

    TafProbTrend prob1 = new TafProbTrend(WeatherChangeType.PROB);
    prob1.setProbability(40);
    prob1.setValidity(validity2);
    prob1.setVisibility(visibility2);
    prob1.addWeatherCondition(wc2);

    TafProbTrend prob2 = new TafProbTrend(WeatherChangeType.PROB);
    Validity validity3 = new Validity();
    validity3.setStartDay(16);
    validity3.setStartHour(2);
    validity3.setEndHour(16);
    validity3.setEndHour(8);
    Cloud c1 = new Cloud();
    c1.setQuantity(CloudQuantity.BKN);
    c1.setHeight(130);
    Cloud c2 = new Cloud();
    c2.setQuantity(CloudQuantity.BKN);
    c2.setHeight(230);
    prob2.setValidity(validity3);
    prob2.addCloud(c1);
    prob2.addCloud(c2);
    prob2.setProbability(50);

    TafProbTrend prob3 = new TafProbTrend(WeatherChangeType.PROB);
    Validity validity4 = new Validity();
    validity4.setStartDay(16);
    validity4.setStartHour(3);
    validity4.setEndHour(16);
    validity4.setEndHour(7);
    Cloud c3 = new Cloud();
    c3.setQuantity(CloudQuantity.BKN);
    c3.setHeight(90);
    Cloud c4 = new Cloud();
    c4.setQuantity(CloudQuantity.BKN);
    c4.setHeight(230);
    prob3.setValidity(validity4);
    prob3.addCloud(c3);
    prob3.addCloud(c4);

    TAF taf = new TAF();
    taf.addTrend(becmg1);
    taf.addTrend(prob1);
    taf.addTrend(prob2);
    taf.addTrend(prob3);

    List<TafProbTrend> probs = taf.getProbs();

    assertThat(probs, hasSize(3));
  }


  @Test
  void testGetINTER() {
    TafTrend becmg1 = new TafTrend(WeatherChangeType.BECMG);
    Validity validity1 = new Validity();
    validity1.setStartDay(15);
    validity1.setStartHour(21);
    validity1.setEndDay(15);
    validity1.setEndHour(24);
    Visibility visibility1 = new Visibility();
    visibility1.setMainVisibility("3000m");
    becmg1.setValidity(validity1);
    becmg1.setVisibility(visibility1);

    WeatherCondition wc1 = new WeatherCondition();
    wc1.addPhenomenon(Phenomenon.MIST);
    becmg1.addWeatherCondition(wc1);

    Validity validity2 = new Validity();
    validity2.setStartDay(15);
    validity2.setStartHour(21);
    validity2.setEndDay(15);
    validity2.setEndHour(24);

    Visibility visibility2 = new Visibility();
    visibility2.setMainVisibility("800m");
    WeatherCondition wc2 = new WeatherCondition();
    wc2.setDescriptive(Descriptive.PATCHES);
    wc2.addPhenomenon(Phenomenon.FOG);

    TafTrend tempo1 = new TafProbTrend(WeatherChangeType.INTER);
    tempo1.setValidity(validity2);
    tempo1.setVisibility(visibility2);
    tempo1.addWeatherCondition(wc2);

    TafTrend inter2 = new TafProbTrend(WeatherChangeType.INTER);
    Validity validity3 = new Validity();
    validity3.setStartDay(16);
    validity3.setStartHour(2);
    validity3.setEndHour(16);
    validity3.setEndHour(8);
    Cloud c1 = new Cloud();
    c1.setQuantity(CloudQuantity.BKN);
    c1.setHeight(130);
    Cloud c2 = new Cloud();
    c2.setQuantity(CloudQuantity.BKN);
    c2.setHeight(230);
    inter2.setValidity(validity3);
    inter2.addCloud(c1);
    inter2.addCloud(c2);

    TafTrend inter3 = new TafProbTrend(WeatherChangeType.INTER);
    Validity validity4 = new Validity();
    validity4.setStartDay(16);
    validity4.setStartHour(3);
    validity4.setEndHour(16);
    validity4.setEndHour(7);
    Cloud c3 = new Cloud();
    c3.setQuantity(CloudQuantity.BKN);
    c3.setHeight(90);
    Cloud c4 = new Cloud();
    c4.setQuantity(CloudQuantity.BKN);
    c4.setHeight(230);
    inter3.setValidity(validity4);
    inter3.addCloud(c3);
    inter3.addCloud(c4);

    TAF taf = new TAF();
    taf.addTrend(becmg1);
    taf.addTrend(tempo1);
    taf.addTrend(inter2);
    taf.addTrend(inter3);

    List<TafTrend> inters = taf.getInters();

    assertThat(inters, hasSize(3));
  }

  @Test
  void testGetFMs() {
    TafTrend becmg1 = new TafTrend(WeatherChangeType.BECMG);
    Validity validity1 = new Validity();
    validity1.setStartDay(15);
    validity1.setStartHour(21);
    validity1.setEndDay(15);
    validity1.setEndHour(24);
    Visibility visibility1 = new Visibility();
    visibility1.setMainVisibility("3000m");
    becmg1.setValidity(validity1);
    becmg1.setVisibility(visibility1);

    WeatherCondition wc1 = new WeatherCondition();
    wc1.addPhenomenon(Phenomenon.MIST);
    becmg1.addWeatherCondition(wc1);

    BeginningValidity validity2 = new BeginningValidity();
    validity2.setStartDay(15);
    validity2.setStartHour(21);
    validity2.setStartMinutes(35);

    Visibility visibility2 = new Visibility();
    visibility2.setMainVisibility("800m");
    WeatherCondition wc2 = new WeatherCondition();
    wc2.setDescriptive(Descriptive.PATCHES);
    wc2.addPhenomenon(Phenomenon.FOG);

    FMTafTrend fm1 = new FMTafTrend();
    fm1.setValidity(validity2);
    fm1.setVisibility(visibility2);
    fm1.addWeatherCondition(wc2);

    FMTafTrend fm2 = new FMTafTrend();
    BeginningValidity validity3 = new BeginningValidity();
    validity3.setStartDay(16);
    validity3.setStartHour(2);
    validity3.setStartMinutes(25);
    Cloud c1 = new Cloud();
    c1.setQuantity(CloudQuantity.BKN);
    c1.setHeight(130);
    Cloud c2 = new Cloud();
    c2.setQuantity(CloudQuantity.BKN);
    c2.setHeight(230);
    fm2.setValidity(validity3);
    fm2.addCloud(c1);
    fm2.addCloud(c2);

    FMTafTrend fm3 = new FMTafTrend();
    BeginningValidity validity4 = new BeginningValidity();
    validity4.setStartDay(16);
    validity4.setStartHour(3);
    validity4.setStartMinutes(40);
    Cloud c3 = new Cloud();
    c3.setQuantity(CloudQuantity.BKN);
    c3.setHeight(90);
    Cloud c4 = new Cloud();
    c4.setQuantity(CloudQuantity.BKN);
    c4.setHeight(230);
    fm3.setValidity(validity4);
    fm3.addCloud(c3);
    fm3.addCloud(c4);

    TAF taf = new TAF();
    taf.addTrend(becmg1);
    taf.addTrend(fm1);
    taf.addTrend(fm2);
    taf.addTrend(fm3);

    List<FMTafTrend> fm = taf.getFMs();

    assertThat(fm, hasSize(3));
  }

}
