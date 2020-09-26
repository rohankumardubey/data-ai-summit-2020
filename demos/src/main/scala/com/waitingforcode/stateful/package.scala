package com.waitingforcode

import org.apache.spark.sql.streaming.StreamingQuery

package object stateful {

  def explainQueryPlan(streamingQuery: StreamingQuery): Unit = {
    new Thread(
      new Runnable() {
        override def run(): Unit = {
          while (streamingQuery.status == null || streamingQuery.lastProgress == null) {
            Thread.sleep(1000L)
          }
          println(s"Got last progress ${streamingQuery.lastProgress}")
          streamingQuery.explain(true)
        }
      }
    ).start()
  }

}
