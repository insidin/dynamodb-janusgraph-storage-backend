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

import com.amazon.janusgraph.testcategory.IsolateMultiConcurrentGetSlice;
import com.amazon.janusgraph.testcategory.IsolateMultiConcurrentGetSliceAndMutate;
import com.amazon.janusgraph.testcategory.MultiDynamoDBStoreTestCategory;
import com.amazon.janusgraph.testcategory.MultipleItemTestCategory;
import org.janusgraph.testutil.FeatureFlag;
import org.janusgraph.testutil.JanusGraphFeature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * @author Alexander Patrikalakis
 */
public class MultiDynamoDBStoreTest extends AbstractDynamoDbStoreTest {

    public MultiDynamoDBStoreTest() {
        super(BackendDataModel.MULTI);
    }

    // $ cat TEST-com.amazon.titan.diskstorage.dynamodb.MultiDynamoDBStoreTest.xml | grep testcase | sed -e \
    // 's/.*\ name=\"\([^\"]*\)\".*/\ \ \ \ @Override\ @Tag("\ MultiDynamoDBStoreTestCategory.class\ ")\ public\ void\ \1()\ {\ super\.\1();\ }/'

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void testStoreTTL() throws Exception {
        super.testStoreTTL();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void storeAndRetrieveWithClosing() throws BackendException {
        super.storeAndRetrieveWithClosing();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void containsKeyColumnReturnsTrueOnExtantInput() throws Exception {
        super.containsKeyColumnReturnsTrueOnExtantInput();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void containsKeyColumnReturnsFalseOnNonexistentInput() throws Exception {
        super.containsKeyColumnReturnsFalseOnNonexistentInput();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void createDatabase() {
        super.createDatabase();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void intervalTest1() throws BackendException {
        super.intervalTest1();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class ")
    public void intervalTest2() throws BackendException {
        super.intervalTest2();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void scanTestWithSimpleJob() throws Exception {
        super.scanTestWithSimpleJob();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void testGetKeysColumnSlicesOnLowerTriangular() throws BackendException, IOException {
        super.testGetKeysColumnSlicesOnLowerTriangular();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void testGetKeysColumnSlicesSimple() throws BackendException {
        super.testGetKeysColumnSlicesSimple();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void getSliceRespectsAllBoundsInclusionArguments() throws Exception {
        super.getSliceRespectsAllBoundsInclusionArguments();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.Scan)
    public void scanTest() throws BackendException {
        super.scanTest();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void deleteColumnsTest1() throws BackendException {
        super.deleteColumnsTest1();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void deleteColumnsTest2() throws BackendException {
        super.deleteColumnsTest2();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void getNonExistentKeyReturnsNull() throws Exception {
        super.getNonExistentKeyReturnsNull();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void storeAndRetrievePerformance() throws BackendException {
        super.storeAndRetrievePerformance();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void storeAndRetrieve() throws BackendException {
        super.storeAndRetrieve();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void containsKeyReturnsTrueOnExtantKey() throws Exception {
        super.containsKeyReturnsTrueOnExtantKey();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void getSliceRespectsColumnLimit() throws Exception {
        super.getSliceRespectsColumnLimit();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void containsKeyReturnsFalseOnNonexistentKey() throws Exception {
        super.containsKeyReturnsFalseOnNonexistentKey();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.UnorderedScan)
    public void testGetKeysWithSliceQuery(TestInfo testInfo) throws Exception {
        super.testGetKeysWithSliceQuery(testInfo);
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void insertingGettingAndDeletingSimpleDataWorks() throws Exception {
        super.insertingGettingAndDeletingSimpleDataWorks();
    }

    @Test
    @Override
    @Tag("IsolateMultiConcurrentGetSliceAndMutate")
    @Tag("MultipleItemTestCategory.class")
    public void testConcurrentGetSliceAndMutate() throws ExecutionException, InterruptedException, BackendException {
        super.testConcurrentGetSliceAndMutate();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void testGetSlices() throws Exception {
        super.testGetSlices();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    public void deleteKeys() throws BackendException {
        super.deleteKeys();
    }

    @Test
    @Override
    @Tag("IsolateMultiConcurrentGetSlice")
    @Tag("MultipleItemTestCategory.class")
    public void testConcurrentGetSlice() throws ExecutionException, InterruptedException, BackendException {
        super.testConcurrentGetSlice();
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.OrderedScan)
    public void testOrderedGetKeysRespectsKeyLimit(TestInfo testInfo) throws BackendException {
        super.testOrderedGetKeysRespectsKeyLimit(testInfo);
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.OrderedScan)
    public void testGetKeysWithKeyRange(TestInfo testInfo) throws Exception {
        super.testGetKeysWithKeyRange(testInfo);
    }

    @Test
    @Override
    @Tag("MultiDynamoDBStoreTestCategory")
    @Tag("MultipleItemTestCategory.class")
    @FeatureFlag(feature = JanusGraphFeature.CellTtl)
    public void testTtl() throws Exception {
        super.testTtl();
    }
}
