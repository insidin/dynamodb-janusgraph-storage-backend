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

import org.janusgraph.diskstorage.BackendException;

import com.amazon.janusgraph.diskstorage.dynamodb.BackendDataModel;
import com.amazon.janusgraph.testcategory.SingleItemTestCategory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;

/**
*
* @author Alexander Patrikalakis
*
*/
@Tag("SingleItemTestCategory.class")
@Disabled
public class SingleDynamoDBGraphSpeedTest extends AbstractDynamoDBGraphSpeedTest {

    public SingleDynamoDBGraphSpeedTest() throws BackendException {
        super(BackendDataModel.SINGLE);
    }

}
