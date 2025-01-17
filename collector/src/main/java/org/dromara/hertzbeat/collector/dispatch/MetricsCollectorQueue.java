/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.hertzbeat.collector.dispatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * queue of jobs to run
 * 待运行的job队列
 */
@Component
@Slf4j
public class MetricsCollectorQueue {

    private final PriorityBlockingQueue<MetricsCollect> jobQueue;

    public MetricsCollectorQueue() {
        jobQueue = new PriorityBlockingQueue<>(2000);
    }

    public void addJob(MetricsCollect job) {
        jobQueue.offer(job);
    }

    public MetricsCollect getJob() throws InterruptedException {
        return jobQueue.poll(2, TimeUnit.SECONDS);
    }

}
