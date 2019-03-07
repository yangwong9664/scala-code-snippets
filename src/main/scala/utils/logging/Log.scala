package utils.logging

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object Log extends DefaultJsonProtocol {

  implicit val logFormat: RootJsonFormat[Log] = jsonFormat8(Log)

  // Application Startup
  def logApplicationRunning(details: String): String = Log(
     action = "Application Running", details = Some(details)
  ).toJson.toString

  //Routes logging
  def logRequestReceived(requestType: String): String = Log(
     action = "Request Received", requestType = Some(requestType)
  ).toJson.toString

  def logResponseReturned(requestType: String, responseCode: Int, errorDetails: Option[String] = None): String = Log(
     action = "Response Returned", requestType = Some(requestType), errorDetails = errorDetails, responseCode = Some(responseCode)
  ).toJson.toString

  // Mongo Operations logging
  def logMongoInsertError(database: String, errorDetails: String): String = Log(
     action = "Mongo Insert Error", database = Some(database), errorDetails = Some(errorDetails)
  ).toJson.toString

  def logMongoReadError(database: String, details: String): String = Log(
     action = "Mongo Read Error", database = Some(database), details = Some(details)
  ).toJson.toString

  def logMongoUpdateError(database: String, errorDetails: String): String = Log(
     action = "Mongo Update Error", database = Some(database), errorDetails = Some(errorDetails)
  ).toJson.toString()

  def logMongoUpdateFailure(database: String): String = Log(
     action = "Mongo Update Failed", database = Some(database)
  ).toJson.toString

  // Http Request Logs
  def logHttpGetResponse(url: String, responseCode: Int, statusText: Option[String] = None,
                         errorDetails: Option[String] = None): String = Log(
     action = "Http GET Response", url = Some(url), errorDetails = errorDetails,
    statusText = statusText, responseCode = Some(responseCode)
  ).toJson.toString

  def logHttpDeleteResponse(url: String, responseCode: Int, statusText: Option[String] = None,
                         errorDetails: Option[String] = None): String = Log(
     action = "Http DELETE Response", url = Some(url), errorDetails = errorDetails,
    statusText = statusText, responseCode = Some(responseCode)
  ).toJson.toString

  def logHttpPostResponse(url: String, responseCode: Int, statusText: Option[String] = None, details: Option[String] = None,
                          errorDetails: Option[String] = None): String = Log(
     action = "Http POST Response", url = Some(url), statusText = statusText, details = details,
      errorDetails = errorDetails, responseCode = Some(responseCode)
  ).toJson.toString

  def logHttpGetError(url: String, errorDetails: String): String = Log(
     action = "Http GET Request Failure", url = Some(url), errorDetails = Some(errorDetails)
  ).toJson.toString

  def logHttpPostError(url: String, errorDetails: String, details: Option[String] = None): String = Log(
     action = "Http POST Request Failure", url = Some(url), details = details, errorDetails = Some(errorDetails)
  ).toJson.toString

  // SecureEncryption/Decryption
  def logSecureEncryptionFailure(details: String, errorDetails: String): String = Log(
     action = "Secure Encryption Failure", details = Some(details), errorDetails = Some(errorDetails)
  ).toJson.toString()

  def logSecureDecryptionFailure(details: String, errorDetails: String): String = Log(
     action = "Secure Decryption Failure", details = Some(details), errorDetails = Some(errorDetails)
  ).toJson.toString()

  def logMessage(action: String, details: String): String =
    Log( action = action, details = Some(details)).toJson.toString

  sealed case class Log(
                         action: String,
                        requestType: Option[String] = None,
                        database: Option[String] = None,
                        url: Option[String] = None,
                        details: Option[String] = None,
                        errorDetails: Option[String] = None,
                        statusText: Option[String] = None,
                        responseCode: Option[Int] = None)
}

