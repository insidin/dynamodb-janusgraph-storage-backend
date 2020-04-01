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

import com.amazon.janusgraph.diskstorage.dynamodb.BackendDataModel;
import com.amazon.janusgraph.testcategory.GraphSimpleLogTestCategory;
import com.amazon.janusgraph.testcategory.SingleDynamoDBGraphTestCategory;
import com.amazon.janusgraph.testcategory.SingleItemTestCategory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 *
 * @author Alexander Patrikalakis
 *
 */
public class SingleDynamoDBGraphTest extends AbstractDynamoDBGraphTest {
    public SingleDynamoDBGraphTest()
    {
        super(BackendDataModel.SINGLE);
    }

    @Override
    public WriteConfiguration getConfiguration() {
        final WriteConfiguration wc = super.getConfiguration();
        final String methodName = testInfo.getTestMethod().get().getName();
        if(methodName.contains("testEdgesExceedCacheSize")) {
            //default: 20000, testEdgesExceedCacheSize fails at 16459, passes at 16400
            //this is the maximum number of edges supported for a vertex with no vertex partitioning.
            wc.set("cache.tx-cache-size", 16400);
        }
        return wc;
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testVertexCentricQuery() {
        super.testVertexCentricQuery(1450);
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testOpenClose() {
        super.testOpenClose();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testLargeJointIndexRetrieval() {
        super.testLargeJointIndexRetrieval();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testMediumCreateRetrieve() {
        super.testMediumCreateRetrieve();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testSettingTTLOnUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> super.testSettingTTLOnUnsupportedType());
    }

    /*
    @Test
    @Override
    @Category({SingleDynamoDBGraphTestCategory.class, SingleItemTestCategory.class })
    public void testSchemaNameChange() {
        super.testSchemaNameChange();
    }
    */

    @Test
    @Override
    @Tag("GraphSimpleLogTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void simpleLogTest() throws InterruptedException {
        super.simpleLogTest();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testSchemaTypes() {
        super.testSchemaTypes();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testTinkerPopOptimizationStrategies() {
        super.testTinkerPopOptimizationStrategies();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGetTTLFromUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> super.testGetTTLFromUnsupportedType());
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testLocalGraphConfiguration() {
        super.testLocalGraphConfiguration();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testConcurrentConsistencyEnforcement() throws Exception {
        super.testConcurrentConsistencyEnforcement();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testTransactionalScopeOfSchemaTypes() {
        super.testTransactionalScopeOfSchemaTypes();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testNestedTransactions() {
        super.testNestedTransactions();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testBasic() throws BackendException {
        super.testBasic();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testUnsettingTTL() throws InterruptedException {
        super.testUnsettingTTL();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGlobalOfflineGraphConfig() {
        super.testGlobalOfflineGraphConfig();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testLimitWithMixedIndexCoverage() {
        super.testLimitWithMixedIndexCoverage();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testMultivaluedVertexProperty() {
        super.testMultivaluedVertexProperty();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGlobalGraphConfig() {
        super.testGlobalGraphConfig();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testManagedOptionMasking() throws BackendException {
        super.testManagedOptionMasking();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGlobalGraphIndexingAndQueriesForInternalIndexes() {
        super.testGlobalGraphIndexingAndQueriesForInternalIndexes();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testWithoutIndex() {
        super.testWithoutIndex();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testIndexUpdatesWithReindexAndRemove() throws InterruptedException, ExecutionException {
        super.testIndexUpdatesWithReindexAndRemove();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgeTTLTiming() throws Exception {
        super.testEdgeTTLTiming();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testStaleVertex() {
        super.testStaleVertex();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGettingUndefinedVertexLabelTTL() {
        super.testGettingUndefinedVertexLabelTTL();
    }

    @Test
    @Override
    @Tag("GraphSimpleLogTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void simpleLogTestWithFailure() throws InterruptedException {
        super.simpleLogTestWithFailure();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testVertexCentricIndexWithNull() {
        super.testVertexCentricIndexWithNull();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testVertexTTLImplicitKey() throws Exception {
        super.testVertexTTLImplicitKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testImplicitKey() {
        super.testImplicitKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testMaskableGraphConfig() {
        super.testMaskableGraphConfig();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testDataTypes() {
        super.testDataTypes();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgeTTLImplicitKey() throws Exception {
        super.testEdgeTTLImplicitKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testTinkerPopCardinality() {
        super.testTinkerPopCardinality();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testPropertyCardinality() {
        super.testPropertyCardinality();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testArrayEqualityUsingImplicitKey() {
        super.testArrayEqualityUsingImplicitKey();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testFixedGraphConfig() {
        super.testFixedGraphConfig();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testAutomaticTypeCreation() {
        super.testAutomaticTypeCreation();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGettingUndefinedEdgeLabelTTL() {
        super.testGettingUndefinedEdgeLabelTTL();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testSimpleTinkerPopTraversal() {
        super.testSimpleTinkerPopTraversal();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGlobalIteration() {
        super.testGlobalIteration();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testVertexRemoval() {
        super.testVertexRemoval();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testForceIndexUsage() {
        super.testForceIndexUsage();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testSettingTTLOnNonStaticVertexLabel() {
        assertThrows(IllegalArgumentException.class, () -> super.testSettingTTLOnNonStaticVertexLabel());
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testTransactionConfiguration() {
        super.testTransactionConfiguration();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testConsistencyEnforcement() {
        super.testConsistencyEnforcement();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testHasNot() {
        super.testHasNot();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testVertexTTLWithCompositeIndex() throws Exception {
        super.testVertexTTLWithCompositeIndex();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testRelationTypeIndexes() {
        super.testRelationTypeIndexes();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testGotGIndexRemoval() throws InterruptedException, ExecutionException {
        super.testGotGIndexRemoval();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testTransactionIsolation() {
        super.testTransactionIsolation();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testSelfLoop() {
        super.testSelfLoop();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testIndexUniqueness() {
        super.testIndexUniqueness();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgeTTLWithTransactions() throws Exception {
        super.testEdgeTTLWithTransactions();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testIndexQueryWithLabelsAndContainsIN() {
        super.testIndexQueryWithLabelsAndContainsIN();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgesExceedCacheSize() {
        super.testEdgesExceedCacheSize();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testThreadBoundTx() {
        super.testThreadBoundTx();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testCreateDelete() {
        super.testCreateDelete();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgeTTLLimitedByVertexTTL() throws Exception {
        super.testEdgeTTLLimitedByVertexTTL();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testEdgeTTLWithIndex() throws Exception {
        super.testEdgeTTLWithIndex();
    }

    @Test
    @Override
    @Tag("SingleDynamoDBGraphTestCategory.class")
    @Tag("SingleItemTestCategory.class")
    public void testIndexUpdateSyncWithMultipleInstances() throws InterruptedException {
        super.testIndexUpdateSyncWithMultipleInstances();
    }

}
