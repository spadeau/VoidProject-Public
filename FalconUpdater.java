package fr.voidnetwork.falcon.centralisation.updater;

import fr.voidnetwork.falcon.centralisation.updater.security.KeyManager;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FalconUpdater {
    private final String webSite = "";
    private final String pluginName = "Falcon";

    public FalconUpdater () {}

    public void update(){
        try {
            new KeyManager().onDownload();
            download(this.webSite, "plugins/" + this.pluginName + ".jar");
            System.out.println("[Updater] Update downloaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to download update: " + e.getMessage());
        }
    }

    private void download(String url, String output) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(output)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            new KeyManager().onFinishedDownload();
        }
    }
}
