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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.example.GraphOfTheGodsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.amazon.janusgraph.graphdb.dynamodb.TestCombination;
import com.amazon.janusgraph.testcategory.IsolateRemainingTestsCategory;

/**
 *
 * @author Matthew Sowders
 * @author Alexander Patrikalakis
 *
 */
@Tag("IsolateRemainingTestsCategory.class")
public abstract class GraphOfTheGodsTest {
    private static final long EDGES = 17;
    private static final long VERTICES = 12;

    private JanusGraph graph;

    public static Collection<Object[]> data() {
        return TestCombination.NATIVE_LOCKING_CROSS_MODELS;
    }

    public GraphOfTheGodsTest(final TestCombination combination) {
        graph = TestGraphUtil.instance.openGraph(combination.getDataModel());
        GraphOfTheGodsFactory.loadWithoutMixedIndex(graph, true);
    }

    @AfterEach
    public void tearDownGraph() throws BackendException {
        TestGraphUtil.instance.tearDownGraph(graph);
    }

    @ParameterizedTest
    @MethodSource({"data"})
    public void testQueryByName(TestCombination combination) throws Exception {
        init(combination);
        final Iterator<Vertex> results = graph.traversal().V().has("name", "jupiter");
        assertTrue(results.hasNext(), "Query should return a result");
        final Vertex jupiter = results.next();
        assertNotNull(jupiter, "Query result should be non null");
    }

    @ParameterizedTest
    @MethodSource({"data"})
    public void testQueryAllVertices(TestCombination combination) throws Exception {
        init(combination);
        assertEquals(VERTICES, graph.traversal().V().count().tryNext().get().longValue(), "Expected the correct number of VERTICES");
    }

    @ParameterizedTest
    @MethodSource({"data"})
    public void testQueryAllEdges(TestCombination combination) throws Exception {
        init(combination);
        assertEquals(EDGES, graph.traversal().E().count().tryNext().get().longValue(), "Expected the correct number of EDGES");
    }

    private void init(TestCombination combination) {
        graph = TestGraphUtil.instance.openGraph(combination.getDataModel());
        GraphOfTheGodsFactory.loadWithoutMixedIndex(graph, true);
    }

/*
    public static class SingleGraphOfTheGodsTest extends GraphOfTheGodsTest {

        public SingleGraphOfTheGodsTest() {
            super(SINGLE_ITEM_DYNAMODB_LOCKING);
        }
    }

    public static class MultiGraphOfTheGodsTest extends GraphOfTheGodsTest {

        public MultiGraphOfTheGodsTest() {
            super(MULTIPLE_ITEM_DYNAMODB_LOCKING);
        }
    }
*/
}
