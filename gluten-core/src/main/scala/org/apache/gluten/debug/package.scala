/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.gluten

import org.apache.gluten.execution.WholeStageTransformer

import org.apache.spark.sql.execution.QueryExecution

package object debug {
  implicit class queryExecutionImplicit(qe: QueryExecution) {
    def debugWholeStageTransform(): Unit = {
      val executedPlan = qe.executedPlan
      executedPlan.foreach {
        case w: WholeStageTransformer => w.doWholeStageTransform(true)
        case _ =>
      }

      // scalastyle:off println
      println(executedPlan.treeString)
      // scalastyle:on println
    }
  }
}