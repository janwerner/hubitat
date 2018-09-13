/**
*   
*	File: IFTTT_Notifications.groovy
*	Platform: Hubitat
*   Modification History:
*       Date       Who              What
*		2018-09-13 Jan Werner       Modified for IFTTT Webhooks
*		2018-05-03 Michael Ritchie  Initial Release (Twilio Notifications Driver)
*
*  Copyright 2018 Michael Ritchie, Jan Werner
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions an limitations under the License.
*
*
*/
def version() {"v1.0.20180913"}

metadata {
  	definition (name: "IFTTT Notifications", namespace: "janwerner", author: "Jan Werner") {
    	capability "Notification"
  	}
	
	attribute "lastMessage", "string"
}

preferences {
    input("iftttEventName", "text", title: "IFTTT Maker Event Name:", description: "The name of the event, like \"hubitat_message\"", required: true)
  	input("iftttWebhookKey", "text", title: "IFTTT Webhooks Key:", description: "See <a href=\"https://ifttt.com/services/maker_webhooks/settings\">IFTTT Webhooks settings (string after /use/)</a>", required: true)
    input("iftttMessageUri", "text", title: "Link for push notification:", description: "E.g., your Hubitat Dashboard Cloud Link", required: true)
	input("isDebugEnabled", "bool", title: "Enable debug logging?", defaultValue: false, required: false)
}

def installed() {
    initialize()
}

def updated() {
 	initialize()
}

def initialize() {
    state.version = version()
}

def getValidated(type) {
	def validated = true
}

def deviceNotification(message) {
  	def postBody = [
   		value1: "${message}",
        value2: "${iftttMessageUri}"
  	]

  	def params = [
		uri: "https://maker.ifttt.com/trigger/" + iftttEventName + "/with/key/" + iftttWebhookKey,
    	body: postBody
  	]

    	httpPost(params){response ->
      		if (response.status != 200) {
        		log.error "Received HTTP error ${response.status}. Check your Credentials!"
      		} else {
                sendEvent(name: "lastMessage", value: "${message}", displayed: false)
				logDebug "Message Received by IFTTT: ${message}"
      		}
    	}
}

private logDebug(msg) {
	if (isDebugEnabled) {
		log.debug "$msg"
	}
}
