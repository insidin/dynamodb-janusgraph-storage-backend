/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
package com.amazon.janusgraph;

import org.janusgraph.diskstorage.BaseTransactionConfig;
import org.janusgraph.diskstorage.util.StandardBaseTransactionConfig;
import org.janusgraph.diskstorage.util.StandardBaseTransactionConfig.Builder;
import org.janusgraph.diskstorage.util.time.TimestampProviders;

import com.amazon.janusgraph.diskstorage.dynamodb.DynamoDbStoreTransaction;
import com.amazon.janusgraph.testcategory.IsolateRemainingTestsCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Matthew Sowders
 * @author Alexander Patrikalakis
 */
@Tag("IsolateRemainingTestsCategory.class")
public class DynamoDbStoreTransactionTest {
    private DynamoDbStoreTransaction instance;
    private BaseTransactionConfig config;

    @BeforeEach
    public void setup() {
        final Builder txBuilder = new StandardBaseTransactionConfig.Builder().timestampProvider(TimestampProviders.NANO);
        config = txBuilder.build();
        instance = new DynamoDbStoreTransaction(config);
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertTrue(instance.equals(instance));
    }

}
