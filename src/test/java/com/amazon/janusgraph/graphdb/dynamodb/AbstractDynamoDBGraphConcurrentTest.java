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

import java.util.concurrent.ExecutionException;

import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.graphdb.JanusGraphConcurrentTest;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.diskstorage.dynamodb.BackendDataModel;
import com.amazon.janusgraph.testutils.CiHeartbeat;
import org.junit.jupiter.api.*;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
public abstract class AbstractDynamoDBGraphConcurrentTest extends JanusGraphConcurrentTest
{

    private final CiHeartbeat ciHeartbeat;
    protected final BackendDataModel model;

    protected AbstractDynamoDBGraphConcurrentTest(final BackendDataModel model) {
        this.model = model;
        this.ciHeartbeat = new CiHeartbeat();
    }

    @Override
    public WriteConfiguration getConfiguration()
    {
        return TestGraphUtil.instance.graphConfig(model);
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

    @Override
    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        this.ciHeartbeat.stopHeartbeat();
    }

    //begin janusgraph-test code - modified because test takes too long, kept number of runnables
    //the same
    //https://github.com/thinkaurelius/titan/blob/0.9.0-M2/titan-test/src/main/java/com/thinkaurelius/titan/graphdb/TitanGraphConcurrentTest.java#L291
    @Test
    @Override
    public void testStandardIndexVertexPropertyReads() throws InterruptedException, ExecutionException {
        testStandardIndexVertexPropertyReadsLogic();
    }
}
