# What does it do?

This is a Hubitat driver that will deliver notifications via an [IFTTT Webhook](https://ifttt.com/maker_webhooks). It's a slightly modified version of mlritchie's [Twilio Notifications Driver](https://community.hubitat.com/t/twilio-notifications-driver/1676).

# How to use

* Create an IFTTT applet that looks like [this](https://raw.githubusercontent.com/janwerner/hubitat/master/Drivers/IFTTT%20Notifications/ifttt_applet.PNG)
* Install the Hubitat driver and enter your credentials (get your IFTTT Webhooks API key [here](https://ifttt.com/services/maker_webhooks/settings)) - it's the string behind "/use".
* Install the IFTTT [Android](https://play.google.com/store/apps/details?id=com.ifttt.ifttt&hl=de) or [iOS](https://itunes.apple.com/de/app/ifttt/id660944635?mt=8) app
* On Android, you might need to [exclude](https://gizmodo.com/how-to-exclude-certain-apps-from-androids-battery-savin-1742064352) the IFTTT app from Doze to receive notifications more or less instantaneously
* IFTTT-based notifications are pretty basic and might not be as reliable as the commercial alternatives, but hey - it's free :)
