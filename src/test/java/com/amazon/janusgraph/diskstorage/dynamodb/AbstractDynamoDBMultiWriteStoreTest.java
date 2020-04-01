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

import java.util.ArrayList;
import java.util.List;

import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.MultiWriteKeyColumnValueStoreTest;
import org.janusgraph.diskstorage.configuration.BasicConfiguration;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.junit.jupiter.api.*;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.testutils.CiHeartbeat;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
public abstract class AbstractDynamoDBMultiWriteStoreTest extends MultiWriteKeyColumnValueStoreTest {

    private final CiHeartbeat ciHeartbeat;
    protected final BackendDataModel model;
    protected TestInfo testInfo;

    protected AbstractDynamoDBMultiWriteStoreTest(final BackendDataModel model) {
        this.model = model;
        this.ciHeartbeat = new CiHeartbeat();
    }

    public KeyColumnValueStoreManager openStorageManager() throws BackendException {
        final List<String> storeNames = new ArrayList<>(2);
        storeNames.add(storeName1);
        storeNames.add(storeName2);
        final WriteConfiguration wc = TestGraphUtil.instance.getStoreConfig(model, storeNames);
        final BasicConfiguration config = new BasicConfiguration(GraphDatabaseConfiguration.ROOT_NS, wc,
            BasicConfiguration.Restriction.NONE);

        return new DynamoDBStoreManager(config);
    }

    @AfterAll
    public static void cleanUpTables() throws Exception {
        TestGraphUtil.instance.cleanUpTables();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception {
        this.testInfo = testInfo;
        this.ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());
        super.setUp();
    }

    @Override
    public void setUp() {
        // deliberately override with noop to avoid execution of @BeforeEach login of parent class
    }

    @Override
    @AfterEach
    public void tearDown() throws Exception {
        this.ciHeartbeat.stopHeartbeat();
        super.tearDown();
    }

    @Override
    @Test
    public void mutateManyStressTest() throws BackendException {
        this.mutateManyStressTestWithVariableRounds();
    }
}
