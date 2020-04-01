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
package com.amazon.janusgraph.graphdb.dynamodb;

import java.util.Collections;
import java.util.List;

import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.graphdb.JanusGraphTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.diskstorage.dynamodb.BackendDataModel;
import com.amazon.janusgraph.testutils.CiHeartbeat;
import org.junit.jupiter.api.TestInfo;

/**
 *
 * FunctionalTitanGraphTest contains the specializations of the Titan functional tests required for
 * the DynamoDB storage backend.
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
public abstract class AbstractDynamoDBGraphTest extends JanusGraphTest {

    private final CiHeartbeat ciHeartbeat;
    protected final BackendDataModel model;

    protected AbstractDynamoDBGraphTest(final BackendDataModel model) {
        this.model = model;
        this.ciHeartbeat = new CiHeartbeat();
    }

    @Override
    public WriteConfiguration getConfiguration() {
        final String methodName = super.testInfo.getTestMethod().get().getName();
        final List<String> extraStoreNames = methodName.contains("simpleLogTest") ? Collections.singletonList("ulog_test") : Collections.emptyList();
        return TestGraphUtil.instance.graphConfigWithClusterPartitionsAndExtraStores(model, extraStoreNames, 1 /*janusGraphClusterPartitions*/);
    }

    @AfterAll
    public static void deleteTables() throws BackendException {
        TestGraphUtil.instance.cleanUpTables();
    }

    @Override
    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception {
        super.setUp(testInfo);
        this.ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());
    }

    @AfterEach
    public void tearDownTest() throws Exception {
        this.ciHeartbeat.stopHeartbeat();
        super.tearDown();
    }
}
