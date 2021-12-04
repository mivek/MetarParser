package io.github.mivek.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.TAF;
import org.junit.jupiter.api.Test;

public class TAFServiceTest extends AbstractWeatherCodeServiceTest<TAF> {

  private final TAFService sut = TAFService.getInstance();

  @Override
  protected AbstractWeatherCodeService<TAF> getSut() {
    return sut;
  }

  @Test
  public void testFormatWithAMD() throws ParseException {
    // Given a taf message with AMD on second line.
    String message =
        "TAF \n"
            + "AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035 \n"
            + "TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30 \n"
            + "TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB \n"
            + "BECMG 1021/1024 27010KT PROB30 \n"
            + "TEMPO 1105/1107 BKN014 \n"
            + "BECMG 1109/1111 34010KT";

    String formatedString =
        "TAF AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035 \n"
            + "TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30 \n"
            + "TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB \n"
            + "BECMG 1021/1024 27010KT PROB30 \n"
            + "TEMPO 1105/1107 BKN014 \n"
            + "BECMG 1109/1111 34010KT\n";

    // When formating the message
    String result = sut.format(message);
    // Then the 2 first lines are merged.
    assertNotNull(result);
    assertEquals(formatedString, result);
  }

  @Test
  public void testFormatWithoutReformat() throws ParseException {
    // GIVEN a taf with a full first line ie the first line is not only work "TAF"
    String message =
        "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z \n"
            + "TEMPO 1303/1308 4000 BR";
    // WHEN formating the message
    String result = sut.format(message);
    // THEN the message is not edited.
    assertEquals(message, result);
  }

  @Test
  public void testFormat() throws ParseException {
    String tafMessage =
        "TAF \n"
            + "AMD TAF \n"
            + "AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z \n"
            + "BECMG 2413/2415 BKN040 \n"
            + "BECMG 2415/2417 CAVOK \n"
            + "BECMG 2509/2511 BKN030 \n"
            + "TEMPO 2514/2516 36015G25KT \n"
            + "BECMG 2516/2518 CAVOK";

    String formatted =
        "TAF AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z \n"
            + "BECMG 2413/2415 BKN040 \n"
            + "BECMG 2415/2417 CAVOK \n"
            + "BECMG 2509/2511 BKN030 \n"
            + "TEMPO 2514/2516 36015G25KT \n"
            + "BECMG 2516/2518 CAVOK\n";
    // When formating the message
    String result = sut.format(tafMessage);
    // Then the 2 first lines are merged.
    assertNotNull(result);
    assertEquals(formatted, result);
  }
}
