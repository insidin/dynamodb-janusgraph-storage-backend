/*
 * Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amazon.janusgraph.testutils.CiHeartbeat;

/**
 *
 * @author Johan Jacobs
 *
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCiHeartbeat {

    @Mock
    private Appender mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @BeforeEach
    public void setup() {
        final Logger root = Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.WARN);
    }

    @Test
    public void testHeartbeatConsoleOutput(TestInfo testInfo) throws InterruptedException {

        final CiHeartbeat ciHeartbeat = new CiHeartbeat(500, 3);

        ciHeartbeat.startHeartbeat(testInfo.getTestMethod().get().getName());

        Thread.sleep(2000);

        ciHeartbeat.stopHeartbeat();

        verify(mockAppender, times(6)).doAppend(captorLoggingEvent.capture());

        final LoggingEvent unitTestStartEvent = captorLoggingEvent.getAllValues().get(0);
        assertEquals("Heartbeat - [started] - testHeartbeatConsoleOutput - 0ms", unitTestStartEvent.getMessage());

        final LoggingEvent heartbeatOneEvent = captorLoggingEvent.getAllValues().get(1);
        assertTrue(heartbeatOneEvent.getMessage().toString().contains("Heartbeat - [1] - testHeartbeatConsoleOutput - "));

        final LoggingEvent heartbeatTwoEvent = captorLoggingEvent.getAllValues().get(2);
        assertTrue(heartbeatTwoEvent.getMessage().toString().contains("Heartbeat - [2] - testHeartbeatConsoleOutput - "));

        final LoggingEvent heartbeatThreeEvent = captorLoggingEvent.getAllValues().get(3);
        assertTrue(heartbeatThreeEvent.getMessage().toString().contains("Heartbeat - [3] - testHeartbeatConsoleOutput - "));

        final LoggingEvent heartbeatfourEvent = captorLoggingEvent.getAllValues().get(4);
        assertTrue(heartbeatfourEvent.getMessage().toString().contains("Heartbeat - [4] - testHeartbeatConsoleOutput - "));

        final LoggingEvent unitTestEndEvent = captorLoggingEvent.getAllValues().get(5);
        assertTrue(unitTestEndEvent.getMessage().toString().contains("Heartbeat - [finished] - testHeartbeatConsoleOutput - "));
    }
}
