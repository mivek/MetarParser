package io.github.mivek.command.common;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.model.Cloud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mivek
 */
public class CloudCommandTest {

    private CloudCommand sut;

    @BeforeEach
    public void setUp() {
        sut = new CloudCommand();
    }

    @Test
    void testParseCloudNullCloudQuantity() {
        String cloud = "AZE015";

        Cloud res = sut.parseCloud(cloud);

        assertNull(res);
    }

    @Test
    void testParseCloudSkyClear() {
        String cloud = "SKC";

        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SKC, res.getQuantity());
        assertNull(res.getHeight());
        assertNull(res.getType());
    }

    @Test
    void testParseCloudWithAltitude() {
        String cloud = "SCT016";
        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(1600, res.getHeight());
        assertNull(res.getType());
    }

    @Test
    void testParseCloudWithType() {
        String cloud = "SCT026CB";

        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(2600, res.getHeight());
        assertNotNull(res.getType());
        assertEquals(CloudType.CB, res.getType());
    }

    @Test
    void testParseCloudWithNSC() {
        String cloud = "NSC";
        Cloud res = sut.parseCloud(cloud);
        assertNotNull(res);
        assertEquals(CloudQuantity.NSC, res.getQuantity());
    }

}
