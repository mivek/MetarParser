package io.github.mivek.command.common;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.model.Cloud;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mivek
 */
public class CloudCommandTest {

    private CloudCommand sut;

    @Before public void setUp() {
        sut = new CloudCommand();
    }

    @Test public void testParseCloudNullCloudQuantity() {
        String cloud = "AZE015";

        Cloud res = sut.parseCloud(cloud);

        assertNull(res);
    }

    @Test public void testParseCloudSkyClear() {
        String cloud = "SKC";

        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SKC, res.getQuantity());
        assertEquals(0, res.getHeight());
        assertNull(res.getType());
    }

    @Test public void testParseCloudWithAltitude() {
        String cloud = "SCT016";
        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(1600, res.getHeight());
        assertNull(res.getType());
    }

    @Test public void testParseCloudWithType() {
        String cloud = "SCT026CB";

        Cloud res = sut.parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(2600, res.getHeight());
        assertNotNull(res.getType());
        assertEquals(CloudType.CB, res.getType());
    }

    @Test public void testParseCloudWithNSC() {
        String cloud = "NSC";
        Cloud res = sut.parseCloud(cloud);
        assertNotNull(res);
        assertEquals(CloudQuantity.NSC, res.getQuantity());
    }

}
