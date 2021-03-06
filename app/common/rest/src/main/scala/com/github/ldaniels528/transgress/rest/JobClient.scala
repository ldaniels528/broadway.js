package com.github.ldaniels528.transgress.rest

import com.github.ldaniels528.transgress.models.JobStates.JobState
import com.github.ldaniels528.transgress.models.{JobLike, JobStates, StatisticsLike, StatusMessage}
import io.scalajs.npm.request.RequestOptions

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Job REST Client
  * @author lawrence.daniels@gmail.com
  */
class JobClient(endpoint: String) extends AbstractRestClient(endpoint) {

  def changeState(jobId: String, state: JobState, message: String = null)(implicit ec: ExecutionContext): Future[Job] = {
    patch[Job](new RequestOptions(uri = getUrl(s"job/$jobId/state/$state"), json = new StatusMessage(message)))
  }

  def createJob(job: Job)(implicit ec: ExecutionContext): Future[Option[Job]] = {
    post[js.Array[Job]](new RequestOptions(uri = getUrl("jobs"), json = job)).map(_.headOption)
  }

  def getJobByID(id: String)(implicit ec: ExecutionContext): Future[Option[Job]] = {
    get[js.Array[Job]](s"job/$id").map(_.headOption)
  }

  def getJobs(implicit ec: ExecutionContext): Future[js.Array[Job]] = {
    get[js.Array[Job]]("jobs")
  }

  def getNextJob(slaveID: String)(implicit ec: ExecutionContext): Future[Option[Job]] = {
    patch[js.Array[Job]](s"jobs/checkout/$slaveID") map (_.headOption)
  }

  def updateJob(job: Job)(implicit ec: ExecutionContext): Future[Job] = {
    post[Job](new RequestOptions(uri = getUrl(s"job/${job._id.orNull}"), json = job))
  }

  def updateStatistics(jobId: String, statistics: StatisticsLike)(implicit ec: ExecutionContext): Future[Job] = {
    patch[Job](new RequestOptions(uri = getUrl(s"job/$jobId/statistics"), json = statistics))
  }

}

/**
  * Represents a job model
  * @author lawrence.daniels@gmail.com
  */
@ScalaJSDefined
class Job(val _id: js.UndefOr[String] = js.undefined,
          val name: js.UndefOr[String],
          var input: js.UndefOr[String],
          var inputSize: js.UndefOr[Double],
          var state: js.UndefOr[String] = JobStates.NEW,
          var workflowName: js.UndefOr[String],
          var processingHost: js.UndefOr[String] = js.undefined,
          var slaveID: js.UndefOr[String] = js.undefined,
          var lastUpdated: js.UndefOr[Double] = js.Date.now(),
          var message: js.UndefOr[String] = js.undefined,
          var statistics: js.UndefOr[StatisticsLike] = js.undefined) extends JobLike