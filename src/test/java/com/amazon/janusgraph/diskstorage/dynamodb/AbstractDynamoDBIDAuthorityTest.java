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
package com.amazon.janusgraph.diskstorage.dynamodb;

import java.util.Collections;

import org.apache.commons.configuration.Configuration;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.IDAuthorityTest;
import org.janusgraph.diskstorage.configuration.BasicConfiguration;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.testutils.CiHeartbeat;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
public abstract class AbstractDynamoDBIDAuthorityTest extends IDAuthorityTest {

    private final CiHeartbeat ciHeartbeat;
    protected TestInfo testInfo;
    protected final BackendDataModel model;

    protected AbstractDynamoDBIDAuthorityTest(/*final WriteConfiguration baseConfig,*/ final BackendDataModel model) {
        super();//TestGraphUtil.instance.appendStoreConfig(model, baseConfig.copy(), Collections.singletonList("ids")));
        this.model = model;
        this.ciHeartbeat = new CiHeartbeat();
    }
    
    @Override
    public KeyColumnValueStoreManager openStorageManager() throws BackendException {
        WriteConfiguration ids = TestGraphUtil.instance.getStoreConfig(model, Collections.singletonList("ids"));
        final BasicConfiguration config = new BasicConfiguration(GraphDatabaseConfiguration.ROOT_NS, ids, BasicConfiguration.Restriction.NONE);
        return new DynamoDBStoreManager(config);
    }

    @BeforeEach
    public void setUpTest(TestInfo testInfo) throws Exception {
        this.testInfo = testInfo;
        this.ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());
    }

    @Override
    @AfterEach
    public void tearDown() throws Exception {
        this.ciHeartbeat.stopHeartbeat();
        super.tearDown();
    }

    @AfterAll
    public static void deleteTables() throws BackendException {
        TestGraphUtil.instance.cleanUpTables();
    }

}
