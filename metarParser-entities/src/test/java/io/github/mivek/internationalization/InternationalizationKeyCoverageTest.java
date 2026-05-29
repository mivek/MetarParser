package io.github.mivek.internationalization;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Flag;
import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.enums.TimeIndicator;
import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.enums.WeatherChangeType;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InternationalizationKeyCoverageTest {

    @Test
    void testCloudQuantityKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (CloudQuantity q : CloudQuantity.values()) {
            assertNotNull(bundle.getString("CloudQuantity." + q.name()),
                "Missing key: CloudQuantity." + q.name());
        }
    }

    @Test
    void testCloudTypeKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (CloudType t : CloudType.values()) {
            assertNotNull(bundle.getString("CloudType." + t.name()),
                "Missing key: CloudType." + t.name());
        }
    }

    @Test
    void testPhenomenonKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (Phenomenon p : Phenomenon.values()) {
            assertNotNull(bundle.getString("Phenomenon." + p.getShortcut()),
                "Missing key: Phenomenon." + p.getShortcut());
        }
    }

    @Test
    void testDescriptiveKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (Descriptive d : Descriptive.values()) {
            assertNotNull(bundle.getString("Descriptive." + d.getShortcut()),
                "Missing key: Descriptive." + d.getShortcut());
        }
    }

    @Test
    void testIntensityKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (Intensity i : Intensity.values()) {
            assertNotNull(bundle.getString("Intensity." + i.getShortcut()),
                "Missing key: Intensity." + i.getShortcut());
        }
    }

    @Test
    void testFlagKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (Flag f : Flag.values()) {
            assertNotNull(bundle.getString("Flag." + f.name()),
                "Missing key: Flag." + f.name());
        }
    }

    @Test
    void testWeatherChangeTypeKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (WeatherChangeType w : WeatherChangeType.values()) {
            assertNotNull(bundle.getString("WeatherChangeType." + w.name()),
                "Missing key: WeatherChangeType." + w.name());
        }
    }

    @Test
    void testTimeIndicatorKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (TimeIndicator t : TimeIndicator.values()) {
            assertNotNull(bundle.getString("TimeIndicator." + t.getShortcut()),
                "Missing key: TimeIndicator." + t.getShortcut());
        }
    }

    @Test
    void testIcingIntensityKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (IcingIntensity i : IcingIntensity.values()) {
            assertNotNull(bundle.getString("IcingIntensity." + i.getShortcut()),
                "Missing key: IcingIntensity." + i.getShortcut());
        }
    }

    @Test
    void testTurbulenceIntensityKeysExist() {
        ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages", Locale.ENGLISH);
        for (TurbulenceIntensity t : TurbulenceIntensity.values()) {
            assertNotNull(bundle.getString("TurbulenceIntensity." + t.getShortcut()),
                "Missing key: TurbulenceIntensity." + t.getShortcut());
        }
    }
}
