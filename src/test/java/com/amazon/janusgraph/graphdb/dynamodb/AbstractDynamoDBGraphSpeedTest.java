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

import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.configuration.BasicConfiguration;
import org.janusgraph.diskstorage.configuration.ModifiableConfiguration;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.graphdb.JanusGraphSpeedTest;
import org.janusgraph.graphdb.SpeedTestSchema;
import org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration;
import org.janusgraph.graphdb.configuration.builder.GraphDatabaseConfigurationBuilder;
import org.janusgraph.graphdb.database.StandardJanusGraph;

import com.amazon.janusgraph.TestGraphUtil;
import com.amazon.janusgraph.diskstorage.dynamodb.BackendDataModel;
import com.amazon.janusgraph.testutils.CiHeartbeat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
//import org.junit.rules.TestName;

/**
 *
 * @author Alexander Patrikalakis
 * @author Johan Jacobs
 *
 */
public abstract class AbstractDynamoDBGraphSpeedTest extends JanusGraphSpeedTest {

    private final CiHeartbeat ciHeartbeat;
    private static StandardJanusGraph graph;
    private static SpeedTestSchema schema;
    protected final BackendDataModel model;

    protected AbstractDynamoDBGraphSpeedTest(final BackendDataModel model) throws BackendException {
        super(TestGraphUtil.instance.graphConfig(model));
        this.model = model;
        this.ciHeartbeat = new CiHeartbeat();
    }

    @AfterAll
    public static void deleteTables() throws BackendException {
        TestGraphUtil.instance.cleanUpTables();
    }

    @Override
    protected StandardJanusGraph getGraph() throws BackendException {
        if (null == graph) {
            //ModifiableConfiguration graphconfig = new ModifiableConfiguration(GraphDatabaseConfiguration.ROOT_NS, super.conf.copy(), BasicConfiguration.Restriction.NONE);
            //final GraphDatabaseConfiguration graphconfig = new GraphDatabaseConfiguration(super.conf);
            //graphconfig.getBackend().clearStorage();
            //final Backend backend = new Backend(graphconfig);
            //graph = (StandardJanusGraph) JanusGraphFactory.open(graphconfig);
            graph = (StandardJanusGraph)TestGraphUtil.instance.openGraph(model);
            initializeGraph(graph);
        }
        return graph;
    }

    @Override
    protected SpeedTestSchema getSchema() {
        if (null == schema) {
            schema = SpeedTestSchema.get();
        }
        return schema;
    }

    @BeforeEach
    public void setUpTest(TestInfo testInfo) throws Exception {
        this.ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());
    }

    @AfterEach
    public void tearDownTest() throws Exception {
        this.ciHeartbeat.stopHeartbeat();
    }
}
