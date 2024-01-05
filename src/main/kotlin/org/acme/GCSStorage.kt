package org.acme

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Blob
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Scanner
import java.util.zip.GZIPInputStream
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.QueryParam

@Path("/gcsstorage")
class GCSStorage {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun gcsStorage(@QueryParam("filePath") filePath: String?): String {
        if (filePath == null) {
            return "filePath is null"
        }
        println("File path: $filePath")
        val projectId = System.getenv("PROJECT_ID")


        println("Project ID: $projectId")

        try {
            val credentials = GoogleCredentials.getApplicationDefault()
            // println("Credentials: $credentials")

            val storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId(projectId)
                .build()
                .service

            // var bucketName = ""
            val envBucketName = System.getenv("BUCKET_NAME")
            println("Bucket Name: $envBucketName")

            val blob = storage.get(envBucketName, filePath)
            val blobContent = blob.getContent()

            val gzipInputStream = GZIPInputStream(blobContent.inputStream())
            val scanner = Scanner(gzipInputStream).useDelimiter("\\A")
            val fileContent = if (scanner.hasNext()) scanner.next() else ""

            println(fileContent)
            return fileContent
        } catch (e: Exception) {
            // Log the exception message and stack trace
            e.printStackTrace()
            return "An error occurred: ${e.message}"
        }
    }
}
