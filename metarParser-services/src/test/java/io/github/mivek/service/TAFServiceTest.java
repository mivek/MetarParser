package io.github.mivek.service;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.TAF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TAFServiceTest extends AbstractWeatherCodeServiceTest<TAF> {

    private final TAFService sut = TAFService.getInstance();

    @Override
    protected AbstractWeatherCodeService<TAF> getSut() {
        return sut;
    }

    @Test
    void testFormatWithAMD() throws ParseException {
        // Given a taf message with AMD on second line.
        String message =
                """
                        TAF\s
                        AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035\s
                        TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30\s
                        TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB\s
                        BECMG 1021/1024 27010KT PROB30\s
                        TEMPO 1105/1107 BKN014\s
                        BECMG 1109/1111 34010KT""";

        String formatedString =
                """
                        TAF AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035\s
                        TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30\s
                        TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB\s
                        BECMG 1021/1024 27010KT PROB30\s
                        TEMPO 1105/1107 BKN014\s
                        BECMG 1109/1111 34010KT
                        """;

        // When formating the message
        String result = sut.format(message);
        // Then the 2 first lines are merged.
        assertNotNull(result);
        assertEquals(formatedString, result);
    }

    @Test
    void testFormatWithoutReformat() throws ParseException {
        // GIVEN a taf with a full first line ie the first line is not only work "TAF"
        String message = "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z \n" + "TEMPO 1303/1308 4000 BR";
        // WHEN formating the message
        String result = sut.format(message);
        // THEN the message is not edited.
        assertEquals(message, result);
    }

    @Test
    void testFormat() throws ParseException {
        String tafMessage = """
                TAF\s
                AMD TAF\s
                AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z\s
                BECMG 2413/2415 BKN040\s
                BECMG 2415/2417 CAVOK\s
                BECMG 2509/2511 BKN030\s
                TEMPO 2514/2516 36015G25KT\s
                BECMG 2516/2518 CAVOK""";

        String formatted = """
                TAF AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z\s
                BECMG 2413/2415 BKN040\s
                BECMG 2415/2417 CAVOK\s
                BECMG 2509/2511 BKN030\s
                TEMPO 2514/2516 36015G25KT\s
                BECMG 2516/2518 CAVOK
                """;
        // When formating the message
        String result = sut.format(tafMessage);
        // Then the 2 first lines are merged.
        assertNotNull(result);
        assertEquals(formatted, result);
    }
}
