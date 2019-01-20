package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Messages class for internationalization.
 * @author mivek
 */
public final class Messages {
    /** The singleton instance. */
    private static final Messages INSTANCE = new Messages();
    /** Name of the bundle. */
    private static final String BUNDLE_NAME = "internationalization.messages"; //$NON-NLS-1$
    /** Bundle variable. */
    private ResourceBundle fResourceBundle;
    /** Cloud quantity broken. */
    private String fCloudQuantityBKN;
    /** Cloud quantity few. */
    private String fCloudQuantityFEW;
    /** Cloud quantity overcast. */
    private String fCloudQuantityOVC;
    /** Cloud quantity scattered. */
    private String fCloudQuantitySCT;
    /** Cloud quantity sky clear. */
    private String fCloudQuantitySKC;
    /** Cloud quantity no significant cloud. */
    private String fCloudQuantityNSC;
    /** Cloud type cumulonimbus. */
    private String fCloudTypeCB;
    /** Cloud type towering cumulus. */
    private String fCloudTypeTCU;
    /** CloudType altocumulus. */
    private String fCloudTypeAC;
    /** Cloud type cirrus. */
    private String fCloudTypeCI;
    /** Cloud type corrocumulus. */
    private String fCloudTypeCC;
    /** Cloud type cirrostratus. */
    private String fCloudTypeCS;
    /** Cloud type stratus. */
    private String fCloudTypeST;
    /** Cloud type Cumulus. */
    private String fCloudTypeCU;
    /** Cloud type Astrostratus. */
    private String fCloudTypeAS;
    /** Cloud type Nimbostratus. */
    private String fCloudTypeNS;
    /** Cloud type stratocumulus. */
    private String fCloudTypeSC;
    /** Descriptive patches. */
    private String fDescriptiveBC;
    /** Descriptive blowing. */
    private String fDescriptiveBL;
    /** Descriptive drifting. */
    private String fDescriptiveDR;
    /** Descriptive freezing. */
    private String fDescriptiveFZ;
    /** Descriptive shallow. */
    private String fDescriptiveMI;
    /** Descriptive partial. */
    private String fDescriptivePR;
    /** Descriptive shower. */
    private String fDescriptiveSH;
    /** Descriptive thunderstorm. */
    private String fDescriptiveTS;
    /** Intensity light. */
    private String fIntensityLight;
    /** Intensity heavy. */
    private String fIntensityHeavy;
    /** Intensity in vincinity. */
    private String fIntensityVC;
    /** Phenomenon mist. */
    private String fPhenomenonBR;
    /** Phenomenon duststorm. */
    private String fPhenomenonDS;
    /** Phenomenon widespread dust. */
    private String fPhenomenonDU;
    /** Phenomenon drizzle. */
    private String fPhenomenonDZ;
    /** Phenomenon funnel cloud. */
    private String fPhenomenonFC;
    /** Phenomenon fog. */
    private String fPhenomenonFG;
    /** Phenomenon smoke. */
    private String fPhenomenonFU;
    /** Phenomenon hail. */
    private String fPhenomenonGR;
    /** Phenomenon small hail. */
    private String fPhenomenonGS;
    /** Phenomenon haze. */
    private String fPhenomenonHZ;
    /** Phenomenon ice crystals. */
    private String fPhenomenonIC;
    /** Phenomenon ice pellets. */
    private String fPhenomenonPL;
    /** Phenomenon dust. */
    private String fPhenpmenonPO;
    /** Phenomenon spray. */
    private String fPhenomenonPY;
    /** Phenomenon rain. */
    private String fPhenomenonRA;
    /** Phenomenon sand. */
    private String fPhenomenonSA;
    /** Phenomenon snow grains. */
    private String fPhenomenonSG;
    /** Phenomenon snow. */
    private String fPhenomenonSN;
    /** Phenomenon squall. */
    private String fPhenomenonSQ;
    /** Phenomenon sandstorm. */
    private String fPhenomenonSS;
    /** Phenomenon unknown precipitation. */
    private String fPhenomenonUP;
    /** Phenomenon volcanic ashes. */
    private String fPhenomenonVA;
    /** Invalid icao message. */
    private String fInvalidIcao;
    /** Decreasing. */
    private String fConverterD;
    /** East. */
    private String fConverterE;
    /** North. */
    private String fConverterN;
    /** North east. */
    private String fConverterNE;
    /** No significant change. */
    private String fConverterNSC;
    /** North West. */
    private String fConverterNW;
    /** South. */
    private String fConverterS;
    /** South East. */
    private String fConverterSE;
    /** South West. */
    private String fConverterSW;
    /** Uprising. */
    private String fConverterU;
    /** Variable. */
    private String fConverterVRB;
    /** West. */
    private String fConverterW;
    /** TEMPO. */
    private String fTempo;
    /** FROM. */
    private String fFrom;
    /** BECMG. */
    private String fBecmg;
    /** PROB. */
    private String fProb;
    /** AT. */
    private String fAt;
    /** TL. */
    private String fTl;
    /** Error code: The entered code is invalid. */
    private String fInvalidMessage;
    /** Error code: No airport was found for this message. */
    private String fAirportNotFound;
    /** Error prefix: An error occured. N° */
    private String fErrorPrefix;

    /**
     * Private constructor.
     */
    private Messages() {
        fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        init();
    }

    /**
     * @return the Messages instance.
     */
    public static Messages getInstance() {
        return INSTANCE;
    }
    /**
     * Sets the locale of the bundle.
     * @param pLocale the locale to set.
     */
    public void setLocale(final Locale pLocale) {
        Locale.setDefault(pLocale);
        ResourceBundle.clearCache();
        fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, pLocale);
        init();
    }

    /**
     * Initialize the values of strings.
     */
    public void init() {
        fCloudQuantityBKN = fResourceBundle.getString("CloudQuantity.BKN");
        fCloudQuantityFEW = fResourceBundle.getString("CloudQuantity.FEW");
        fCloudQuantityOVC = fResourceBundle.getString("CloudQuantity.OVC");
        fCloudQuantitySCT = fResourceBundle.getString("CloudQuantity.SCT");
        fCloudQuantitySKC = fResourceBundle.getString("CloudQuantity.SKC");
        fCloudQuantityNSC = fResourceBundle.getString("CloudQuantity.NSC");
        fCloudTypeCB = fResourceBundle.getString("CloudType.CB");
        fCloudTypeTCU = fResourceBundle.getString("CloudType.TCU");
        fCloudTypeAC = fResourceBundle.getString("CloudType.AC");
        fCloudTypeCI = fResourceBundle.getString("CloudType.CI");
        fCloudTypeCC = fResourceBundle.getString("CloudType.CC");
        fCloudTypeCS = fResourceBundle.getString("CloudType.CS");
        fCloudTypeST = fResourceBundle.getString("CloudType.ST");
        fCloudTypeCU = fResourceBundle.getString("CloudType.CU");
        fCloudTypeAS = fResourceBundle.getString("CloudType.AS");
        fCloudTypeNS = fResourceBundle.getString("CloudType.NS");
        fCloudTypeSC = fResourceBundle.getString("CloudType.SC");
        fDescriptiveBC = fResourceBundle.getString("Descriptive.BC");
        fDescriptiveBL = fResourceBundle.getString("Descriptive.BL");
        fDescriptiveDR = fResourceBundle.getString("Descriptive.DR");
        fDescriptiveFZ = fResourceBundle.getString("Descriptive.FZ");
        fDescriptiveMI = fResourceBundle.getString("Descriptive.MI");
        fDescriptivePR = fResourceBundle.getString("Descriptive.PR");
        fDescriptiveSH = fResourceBundle.getString("Descriptive.SH");
        fDescriptiveTS = fResourceBundle.getString("Descriptive.TS");
        fIntensityLight = fResourceBundle.getString("Intensity.-");
        fIntensityHeavy = fResourceBundle.getString("Intensity.+");
        fIntensityVC = fResourceBundle.getString("Intensity.VC");
        fPhenomenonBR = fResourceBundle.getString("Phenomenon.BR");
        fPhenomenonDS = fResourceBundle.getString("Phenomenon.DS");
        fPhenomenonDU = fResourceBundle.getString("Phenomenon.DU");
        fPhenomenonDZ = fResourceBundle.getString("Phenomenon.DZ");
        fPhenomenonFC = fResourceBundle.getString("Phenomenon.FC");
        fPhenomenonFG = fResourceBundle.getString("Phenomenon.FG");
        fPhenomenonFU = fResourceBundle.getString("Phenomenon.FU");
        fPhenomenonGR = fResourceBundle.getString("Phenomenon.GR");
        fPhenomenonGS = fResourceBundle.getString("Phenomenon.GS");
        fPhenomenonHZ = fResourceBundle.getString("Phenomenon.HZ");
        fPhenomenonIC = fResourceBundle.getString("Phenomenon.IC");
        fPhenomenonPL = fResourceBundle.getString("Phenomenon.PL");
        fPhenpmenonPO = fResourceBundle.getString("Phenomenon.PO");
        fPhenomenonPY = fResourceBundle.getString("Phenomenon.PY");
        fPhenomenonRA = fResourceBundle.getString("Phenomenon.RA");
        fPhenomenonSA = fResourceBundle.getString("Phenomenon.SA");
        fPhenomenonSG = fResourceBundle.getString("Phenomenon.SG");
        fPhenomenonSN = fResourceBundle.getString("Phenomenon.SN");
        fPhenomenonSQ = fResourceBundle.getString("Phenomenon.SQ");
        fPhenomenonSS = fResourceBundle.getString("Phenomenon.SS");
        fPhenomenonUP = fResourceBundle.getString("Phenomenon.UP");
        fPhenomenonVA = fResourceBundle.getString("Phenomenon.VA");
        fInvalidIcao = fResourceBundle.getString("MetarFacade.InvalidIcao");
        fConverterD = fResourceBundle.getString("Converter.D");
        fConverterE = fResourceBundle.getString("Converter.E");
        fConverterN = fResourceBundle.getString("Converter.N");
        fConverterNE = fResourceBundle.getString("Converter.NE");
        fConverterNSC = fResourceBundle.getString("Converter.NSC");
        fConverterNW = fResourceBundle.getString("Converter.NW");
        fConverterS = fResourceBundle.getString("Converter.S");
        fConverterSE = fResourceBundle.getString("Converter.SE");
        fConverterSW = fResourceBundle.getString("Converter.SW");
        fConverterU = fResourceBundle.getString("Converter.U");
        fConverterVRB = fResourceBundle.getString("Converter.VRB");
        fConverterW = fResourceBundle.getString("Converter.W");
        fTempo = fResourceBundle.getString("WeatherChangeType.TEMPO");
        fFrom = fResourceBundle.getString("WeatherChangeType.FM");
        fBecmg = fResourceBundle.getString("WeatherChangeType.BECMG");
        fProb = fResourceBundle.getString("WeatherChangeType.PROB");
        fAt = fResourceBundle.getString("TimeIndicator.AT");
        fTl = fResourceBundle.getString("TimeIndicator.TL");
        fInvalidMessage = fResourceBundle.getString("ErrorCode.InvalidMessage");
        fAirportNotFound = fResourceBundle.getString("ErrorCode.AirportNotFound");
        fErrorPrefix = fResourceBundle.getString("Error.prefix");
    }

    /**
     * @return the cloudQuantityBKN
     */
    public String getCloudQuantityBKN() {
        return fCloudQuantityBKN;
    }

    /**
     * @return the cloudQuantityFEW
     */
    public String getCloudQuantityFEW() {
        return fCloudQuantityFEW;
    }

    /**
     * @return the cloudQuantityOVC
     */
    public String getCloudQuantityOVC() {
        return fCloudQuantityOVC;
    }

    /**
     * @return the cloudQuantitySCT
     */
    public String getCloudQuantitySCT() {
        return fCloudQuantitySCT;
    }

    /**
     * @return the cloudQuantitySKC
     */
    public String getCloudQuantitySKC() {
        return fCloudQuantitySKC;
    }

    /**
     * @return the cloudQuantityNSC
     */
    public String getCloudQuantityNSC() {
        return fCloudQuantityNSC;
    }

    /**
     * @return the cloudTypeCB
     */
    public String getCloudTypeCB() {
        return fCloudTypeCB;
    }

    /**
     * @return the cloudTypeTCU
     */
    public String getCloudTypeTCU() {
        return fCloudTypeTCU;
    }

    /**
     * @return the cloudTypeAC
     */
    public String getCloudTypeAC() {
        return fCloudTypeAC;
    }

    /**
     * @return the cloudTypeCI
     */
    public String getCloudTypeCI() {
        return fCloudTypeCI;
    }

    /**
     * @return the cloudTypeCC
     */
    public String getCloudTypeCC() {
        return fCloudTypeCC;
    }

    /**
     * @return the cloudTypeCS
     */
    public String getCloudTypeCS() {
        return fCloudTypeCS;
    }

    /**
     * @return the cloudTypeST
     */
    public String getCloudTypeST() {
        return fCloudTypeST;
    }

    /**
     * @return the cloudTypeCU
     */
    public String getCloudTypeCU() {
        return fCloudTypeCU;
    }

    /**
     * @return the cloudTypeAS
     */
    public String getCloudTypeAS() {
        return fCloudTypeAS;
    }

    /**
     * @return the cloudTypeNS
     */
    public String getCloudTypeNS() {
        return fCloudTypeNS;
    }

    /**
     * @return the cloudTypeSC
     */
    public String getCloudTypeSC() {
        return fCloudTypeSC;
    }

    /**
     * @return the descriptiveBC
     */
    public String getDescriptiveBC() {
        return fDescriptiveBC;
    }

    /**
     * @return the descriptiveBL
     */
    public String getDescriptiveBL() {
        return fDescriptiveBL;
    }

    /**
     * @return the descriptiveDR
     */
    public String getDescriptiveDR() {
        return fDescriptiveDR;
    }

    /**
     * @return the descriptiveFZ
     */
    public String getDescriptiveFZ() {
        return fDescriptiveFZ;
    }

    /**
     * @return the descriptiveMI
     */
    public String getDescriptiveMI() {
        return fDescriptiveMI;
    }

    /**
     * @return the descriptivePR
     */
    public String getDescriptivePR() {
        return fDescriptivePR;
    }

    /**
     * @return the descriptiveSH
     */
    public String getDescriptiveSH() {
        return fDescriptiveSH;
    }

    /**
     * @return the descriptiveTS
     */
    public String getDescriptiveTS() {
        return fDescriptiveTS;
    }

    /**
     * @return the intensityLight
     */
    public String getIntensityLight() {
        return fIntensityLight;
    }

    /**
     * @return the intensityHeavy
     */
    public String getIntensityHeavy() {
        return fIntensityHeavy;
    }

    /**
     * @return the intensityVC
     */
    public String getIntensityVC() {
        return fIntensityVC;
    }

    /**
     * @return the phenomenonBR
     */
    public String getPhenomenonBR() {
        return fPhenomenonBR;
    }

    /**
     * @return the phenomenonDS
     */
    public String getPhenomenonDS() {
        return fPhenomenonDS;
    }

    /**
     * @return the phenomenonDU
     */
    public String getPhenomenonDU() {
        return fPhenomenonDU;
    }

    /**
     * @return the phenomenonDZ
     */
    public String getPhenomenonDZ() {
        return fPhenomenonDZ;
    }

    /**
     * @return the phenomenonFC
     */
    public String getPhenomenonFC() {
        return fPhenomenonFC;
    }

    /**
     * @return the phenomenonFG
     */
    public String getPhenomenonFG() {
        return fPhenomenonFG;
    }

    /**
     * @return the phenomenonFU
     */
    public String getPhenomenonFU() {
        return fPhenomenonFU;
    }

    /**
     * @return the phenomenonGR
     */
    public String getPhenomenonGR() {
        return fPhenomenonGR;
    }

    /**
     * @return the phenomenonGS
     */
    public String getPhenomenonGS() {
        return fPhenomenonGS;
    }

    /**
     * @return the phenomenonHZ
     */
    public String getPhenomenonHZ() {
        return fPhenomenonHZ;
    }

    /**
     * @return the phenomenonIC
     */
    public String getPhenomenonIC() {
        return fPhenomenonIC;
    }

    /**
     * @return the phenomenonPL
     */
    public String getPhenomenonPL() {
        return fPhenomenonPL;
    }

    /**
     * @return the phenpmenonPO
     */
    public String getPhenpmenonPO() {
        return fPhenpmenonPO;
    }

    /**
     * @return the phenomenonPY
     */
    public String getPhenomenonPY() {
        return fPhenomenonPY;
    }

    /**
     * @return the phenomenonRA
     */
    public String getPhenomenonRA() {
        return fPhenomenonRA;
    }

    /**
     * @return the phenomenonSA
     */
    public String getPhenomenonSA() {
        return fPhenomenonSA;
    }

    /**
     * @return the phenomenonSG
     */
    public String getPhenomenonSG() {
        return fPhenomenonSG;
    }

    /**
     * @return the phenomenonSN
     */
    public String getPhenomenonSN() {
        return fPhenomenonSN;
    }

    /**
     * @return the phenomenonSQ
     */
    public String getPhenomenonSQ() {
        return fPhenomenonSQ;
    }

    /**
     * @return the phenomenonSS
     */
    public String getPhenomenonSS() {
        return fPhenomenonSS;
    }

    /**
     * @return the phenomenonUP
     */
    public String getPhenomenonUP() {
        return fPhenomenonUP;
    }

    /**
     * @return the phenomenonVA
     */
    public String getPhenomenonVA() {
        return fPhenomenonVA;
    }

    /**
     * @return the invalidIcao
     */
    public String getInvalidIcao() {
        return fInvalidIcao;
    }

    /**
     * @return the converterD
     */
    public String getConverterD() {
        return fConverterD;
    }

    /**
     * @return the converterE
     */
    public String getConverterE() {
        return fConverterE;
    }

    /**
     * @return the converterN
     */
    public String getConverterN() {
        return fConverterN;
    }

    /**
     * @return the converterNE
     */
    public String getConverterNE() {
        return fConverterNE;
    }

    /**
     * @return the converterNSC
     */
    public String getConverterNSC() {
        return fConverterNSC;
    }

    /**
     * @return the converterNW
     */
    public String getConverterNW() {
        return fConverterNW;
    }

    /**
     * @return the converterS
     */
    public String getConverterS() {
        return fConverterS;
    }

    /**
     * @return the converterSE
     */
    public String getConverterSE() {
        return fConverterSE;
    }

    /**
     * @return the converterSW
     */
    public String getConverterSW() {
        return fConverterSW;
    }

    /**
     * @return the converterU
     */
    public String getConverterU() {
        return fConverterU;
    }

    /**
     * @return the converterVRB
     */
    public String getConverterVRB() {
        return fConverterVRB;
    }

    /**
     * @return the converterW
     */
    public String getConverterW() {
        return fConverterW;
    }

    /**
     * @return the tempo
     */
    public String getTempo() {
        return fTempo;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return fFrom;
    }

    /**
     * @return the becmg
     */
    public String getBecmg() {
        return fBecmg;
    }

    /**
     * @return the prob
     */
    public String getProb() {
        return fProb;
    }

    /**
     * @return the tl
     */
    public String getTl() {
        return fTl;
    }

    /**
     * @return the invalidMessage
     */
    public String getInvalidMessage() {
        return fInvalidMessage;
    }

    /**
     * @return the at
     */
    public String getAt() {
        return fAt;
    }

    /**
     * @return the airportNotFound
     */
    public String getAirportNotFound() {
        return fAirportNotFound;
    }

    /**
     * @return the errorPrefix
     */
    public String getErrorPrefix() {
        return fErrorPrefix;
    }
}
