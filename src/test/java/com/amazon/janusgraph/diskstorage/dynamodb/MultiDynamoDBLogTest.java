/*
 * Copyright 2014-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazon.janusgraph.diskstorage.dynamodb;

import com.amazon.janusgraph.testcategory.MultiIdAuthorityLogStoreCategory;
import com.amazon.janusgraph.testcategory.MultipleItemTestCategory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
*/
@Tag("MultiIdAuthorityLogStoreCategory.class")
@Tag("MultipleItemTestCategory.class")
public class MultiDynamoDBLogTest extends AbstractDynamoDBLogTest {
    public MultiDynamoDBLogTest() throws Exception {
        super(BackendDataModel.MULTI);
    }

    static private final long LONGER_TIMEOUT_MS = 120000;

    @Override
    @Test
    @Tag("requiresOrderPreserving")
    public void mediumSendReceiveSerial() throws Exception {
        sendReceive(1, 2000, 50, true, LONGER_TIMEOUT_MS);
    }
    @Override
    @Test
    public void testMultipleReadersOnSingleLog() throws Exception {
        sendReceive(4, 2000, 50, false, LONGER_TIMEOUT_MS);
    }
    @Override
    @Test
    @Tag("requiresOrderPreserving")
    public void testMultipleReadersOnSingleLogSerial() throws Exception {
        sendReceive(4, 2000, 50, true, LONGER_TIMEOUT_MS);
    }
}
