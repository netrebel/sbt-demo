package com.poc.demo

/**
  * @author miguel.reyes on 7/15/16.
  */

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import play.api.libs.json.{JsString, JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class Health extends Controller {
  val isoDateTimeWrites = new Writes[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString(ISODateTimeFormat.dateTime()))
  }

  def check = Action { request =>
    val json = Json.obj(
      "version" -> BuildInfo.version,
      "timestamp" -> Json.toJson(DateTime.now())(isoDateTimeWrites),
      "reverse" -> routes.Health.check().absoluteURL(secure = true)(request)
    )
    Ok(json)
  }
}
