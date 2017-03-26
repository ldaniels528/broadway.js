package com.github.ldaniels528.bourne.client
package controllers

import io.scalajs.dom.html.browser._
import io.scalajs.npm.angularjs.toaster.Toaster
import io.scalajs.npm.angularjs.{Controller, Interval, Scope}

import scala.scalajs.js

/**
  * Dashboard Controller
  * @author lawrence.daniels@gmail.com
  */
class DashboardController($scope: DashboardScope, $interval: Interval, toaster: Toaster)
  extends Controller {

  /////////////////////////////////////////////////////////
  //    Public Methods
  /////////////////////////////////////////////////////////

  /**
    * Initializes the controller
    */
  $scope.init = () => {
    console.info(s"Initializing ${getClass.getSimpleName}...")
  }

}

/**
  * Dashboard Scope
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait DashboardScope extends Scope with JobHandlingScope {

  // functions
  var init: js.Function0[Unit] = js.native

}
