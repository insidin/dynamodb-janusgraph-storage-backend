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

import java.util.List;

import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.LockKeyColumnValueStoreTest;
import org.janusgraph.diskstorage.configuration.BasicConfiguration;
import org.janusgraph.diskstorage.configuration.Configuration;
import org.janusgraph.diskstorage.configuration.MergedConfiguration;
import org.janusgraph.diskstorage.configuration.ModifiableConfiguration;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.junit.jupiter.api.*;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.graphdb.dynamodb.TestCombination;
import com.amazon.janusgraph.testcategory.IsolateRemainingTestsCategory;
import com.amazon.janusgraph.testutils.CiHeartbeat;
import com.google.common.collect.ImmutableList;

import static com.amazon.janusgraph.graphdb.dynamodb.TestCombination.*;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
@Tag("IsolateRemainingTestsCategory.class")
public abstract class DynamoDBLockStoreTest extends LockKeyColumnValueStoreTest {

    private final CiHeartbeat ciHeartbeat;
    private final TestCombination combination;
    private TestInfo testInfo;

    public DynamoDBLockStoreTest(TestCombination combination) {
        this.ciHeartbeat = new CiHeartbeat();
        this.combination = SINGLE_ITEM_DYNAMODB_LOCKING;
    }

    @Override
    public DynamoDBStoreManager openStorageManager(final int id, Configuration configuration) throws BackendException {

        final List<String> storeNames = ImmutableList.of(DB_NAME,
            combination.getDataModel().name() + "_" + DB_NAME + "_lock_",
            DB_NAME + "_lock_",
            "multi_store_lock_0",
            "multi_store_lock_1",
            "multi_store_lock_2",
            "multi_store_lock_3",
            "multi_store_lock_4",
            "multi_store_lock_5");
        final ModifiableConfiguration dynamodbOverrides = new ModifiableConfiguration(GraphDatabaseConfiguration.ROOT_NS,
            TestGraphUtil.instance.getStoreConfig(combination.getDataModel(), storeNames),
            BasicConfiguration.Restriction.NONE);
        dynamodbOverrides.set(Constants.DYNAMODB_USE_NATIVE_LOCKING, combination.getUseNativeLocking());
        dynamodbOverrides.set(GraphDatabaseConfiguration.LOCK_LOCAL_MEDIATOR_GROUP, combination.toString() + id);
        return new DynamoDBStoreManager(new MergedConfiguration(dynamodbOverrides, configuration));
    }

    @BeforeEach
    public void setUpTest(TestInfo testInfo) throws Exception {
        this.testInfo = testInfo;
        this.ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());
        // https://github.com/awslabs/dynamodb-titan-storage-backend/issues/160
        // super.open() is called here, and super.open() calls super.openStoreManager(int)
        super.setUp();
    }

    @Override
    public void setUp() {
        // deliberately override with noop to avoid execution of @BeforeEach login of parent class
    }


    @AfterEach
    public void tearDownTest() throws Exception {
        this.ciHeartbeat.stopHeartbeat();
        super.tearDown();
    }

    @AfterAll
    public static void deleteTables() throws BackendException {
        TestGraphUtil.instance.cleanUpTables();
    }


    @Override
    @Test
    public void testRemoteLockContention() throws InterruptedException, BackendException {
        //The DynamoDB Storage Backend for Titan does not support remote lock expiry currently.
        //Re-read the KeyColumns (edges, vertices, index entries) and retry.
        //If you enable a DynamoDB Stream on the store (table) and have a Lambda function
        //write the stream to a Kinesis stream, and then use KCL inside the AbstractDynamoDbStore,
        //it may be possible to support remote lock expiry in the future. To not confuse transactions
        //in process on the same host, each item would need to write the machine id to a metadata
        //attribute to each column, so the KCL application on the Titan nodes can selectively
        //ignore key-column writes originating from the same node. DynamoDB Streams fan-out is
        //only twice the number of writes, so to support remote lock expiry on more than two writers
        //that are saturating the write capacity of the store, you need to stream the records through
        //Kinesis and configure the fan-out you need for all your Titan writers. Adding Streams +
        //Lambda + Kinesis + KCL to the loop may add around 1sec to remote lock expiry latency.
        //This is partially quantifiable with the approximate time attribute added to Kinesis
        //record format recently.
        if (combination.getUseNativeLocking()) { //
            return;

        }
        super.testRemoteLockContention();
    }

    public static class SingleDynamodbDynamoDBLockStoreTest extends DynamoDBLockStoreTest {

        public SingleDynamodbDynamoDBLockStoreTest() {
            super(SINGLE_ITEM_DYNAMODB_LOCKING);
        }
    }

    public static class SingleJanusgraphDynamoDBLockStoreTest extends DynamoDBLockStoreTest {

        public SingleJanusgraphDynamoDBLockStoreTest() {
            super(SINGLE_ITEM_JANUSGRAPH_LOCKING);
        }
    }

    public static class MultiDynamodbDynamoDBLockStoreTest extends DynamoDBLockStoreTest {

        public MultiDynamodbDynamoDBLockStoreTest() {
            super(MULTIPLE_ITEM_DYNAMODB_LOCKING);
        }
    }

    public static class MultiJanusgraphDynamoDBLockStoreTest extends DynamoDBLockStoreTest {

        public MultiJanusgraphDynamoDBLockStoreTest() {
            super(MULTIPLE_ITEM_JANUSGRAPH_LOCKING);
        }
    }

}
