package ro.pub.cs.systems.eim.colocviu1_1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.util.Log
import java.util.Date
import kotlin.collections.get
import kotlin.math.sqrt
import kotlin.random.Random

class Colocviu1_1Service : Service() {

    private var processingThread: Thread? = null
    @Volatile private var isRunning = false
    @Volatile private var currentText: String? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        currentText = intent?.getStringExtra(Constants.TEXT_COORD)

        if (!isRunning) {
            isRunning = true
            processingThread = Thread {
                Log.d("[Thread]", "Thread started! PID: ${Process.myPid()} TID: ${Process.myTid()}")
                val random = Random(System.currentTimeMillis())

                while (isRunning) {
                    val intentBroadcast = Intent().apply {
                        action = Constants.actionTypes[random.nextInt(Constants.actionTypes.size)]
                    }
                    Log.d("SERVICE", "${Date(System.currentTimeMillis())} -> Text: $currentText")
                    sendBroadcast(intentBroadcast)

                    try {
                        Thread.sleep(Constants.SLEEP_TIME * 2)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                Log.d("[Thread]", "Thread stopped!")
            }
            processingThread?.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d("[Service]", "Service destroyed")
        isRunning = false
        processingThread?.interrupt()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
