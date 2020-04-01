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

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.janusgraph.diskstorage.BackendException;

import com.amazon.janusgraph.testcategory.IsolateSingleConcurrentGetSlice;
import com.amazon.janusgraph.testcategory.IsolateSingleConcurrentGetSliceAndMutate;
import com.amazon.janusgraph.testcategory.SingleDynamoDBStoreTestCategory;
import com.amazon.janusgraph.testcategory.SingleItemTestCategory;
import org.janusgraph.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;
import org.janusgraph.testutil.FeatureFlag;
import org.janusgraph.testutil.JanusGraphFeature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 *
 * @author Alexander Patrikalakis
 *
 */
public class SingleDynamoDBStoreTest extends AbstractDynamoDbStoreTest {

    public SingleDynamoDBStoreTest()
    {
        super(BackendDataModel.SINGLE);
    }

    @Override
    protected KeyColumnValueStoreManager openStorageManagerForClearStorageTest() throws Exception {
        KeyColumnValueStoreManager keyColumnValueStoreManager = super.openStorageManager();
        keyColumnValueStoreManager.openDatabase(super.storeName);
        return keyColumnValueStoreManager;
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void testStoreTTL() throws Exception {
        super.testStoreTTL();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void storeAndRetrieveWithClosing() throws BackendException {
        super.storeAndRetrieveWithClosing();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void containsKeyColumnReturnsTrueOnExtantInput() throws Exception {
        super.containsKeyColumnReturnsTrueOnExtantInput();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void containsKeyColumnReturnsFalseOnNonexistentInput() throws Exception {
        super.containsKeyColumnReturnsFalseOnNonexistentInput();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void createDatabase() {
        super.createDatabase();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void intervalTest1() throws BackendException {
        super.intervalTest1();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void intervalTest2() throws BackendException {
        super.intervalTest2();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void scanTestWithSimpleJob() throws Exception {
        super.scanTestWithSimpleJob();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void testGetKeysColumnSlicesOnLowerTriangular() throws BackendException, IOException {
        super.testGetKeysColumnSlicesOnLowerTriangular();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void testGetKeysColumnSlicesSimple() throws BackendException {
        super.testGetKeysColumnSlicesSimple();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void getSliceRespectsAllBoundsInclusionArguments() throws Exception {
        super.getSliceRespectsAllBoundsInclusionArguments();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void scanTest() throws BackendException {
        super.scanTest();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void deleteColumnsTest1() throws BackendException {
        super.deleteColumnsTest1();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void deleteColumnsTest2() throws BackendException {
        super.deleteColumnsTest2();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void getNonExistentKeyReturnsNull() throws Exception {
        super.getNonExistentKeyReturnsNull();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void storeAndRetrievePerformance() throws BackendException {
        super.storeAndRetrievePerformance();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void storeAndRetrieve() throws BackendException {
        super.storeAndRetrieve();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void containsKeyReturnsTrueOnExtantKey() throws Exception {
        super.containsKeyReturnsTrueOnExtantKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void getSliceRespectsColumnLimit() throws Exception {
        super.getSliceRespectsColumnLimit();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void containsKeyReturnsFalseOnNonexistentKey() throws Exception {
        super.containsKeyReturnsFalseOnNonexistentKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.UnorderedScan)
    public void testGetKeysWithSliceQuery(TestInfo testInfo) throws Exception {
        super.testGetKeysWithSliceQuery(testInfo);
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void insertingGettingAndDeletingSimpleDataWorks() throws Exception {
        super.insertingGettingAndDeletingSimpleDataWorks();
    }

    @Test
    @Override
    @Tag("IsolateSingleConcurrentGetSliceAndMutate")
    @Tag("SingleItemTestCategory")
    public void testConcurrentGetSliceAndMutate() throws ExecutionException, InterruptedException, BackendException {
        super.testConcurrentGetSliceAndMutate();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void testGetSlices() throws Exception {
        super.testGetSlices();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    public void deleteKeys() throws BackendException {
        super.deleteKeys();
    }

    @Test
    @Override
    @Tag("IsolateSingleConcurrentGetSlice")
    @Tag("SingleItemTestCategory")
    public void testConcurrentGetSlice() throws ExecutionException, InterruptedException, BackendException {
        super.testConcurrentGetSlice();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.OrderedScan)
    public void testOrderedGetKeysRespectsKeyLimit(TestInfo testInfo) throws BackendException {
        super.testOrderedGetKeysRespectsKeyLimit(testInfo);
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.OrderedScan)
    public void testGetKeysWithKeyRange(TestInfo testInfo) throws Exception {
        super.testGetKeysWithKeyRange(testInfo);
    }

    @Test
    @Override
    @Tag("SingleDynamoDBStoreTestCategory")
    @Tag("SingleItemTestCategory")
    @FeatureFlag(feature = JanusGraphFeature.CellTtl)
    public void testTtl() throws Exception {
        super.testTtl();
    }
}
