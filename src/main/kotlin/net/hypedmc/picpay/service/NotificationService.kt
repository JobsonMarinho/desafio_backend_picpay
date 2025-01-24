package net.hypedmc.picpay.service

import net.hypedmc.picpay.client.NotificationClient
import net.hypedmc.picpay.entity.Transfer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationClient: NotificationClient
) {
    private val logger = LoggerFactory.getLogger(NotificationService::class.java)

    fun sendNotification(transfer: Transfer) {
       try {
           logger.info("Sending notification for transfer: ${transfer.id}")

           val response = notificationClient.notify(transfer)

           if (response.statusCode.isError) {
               logger.error("An error occurred while sending notification")
           }

           logger.info("Notification sent successfully")
       } catch (e: Exception) {
           throw RuntimeException("An error occurred while sending notification", e)
       }
    }

}